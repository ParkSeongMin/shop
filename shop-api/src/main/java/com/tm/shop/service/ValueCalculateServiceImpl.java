package com.tm.shop.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import com.tm.shop.entity.Shop;
import com.tm.shop.entity.Value;
import com.tm.shop.entity.ValueDetail;
import com.tm.shop.repository.ShopRepository;
import com.tm.shop.repository.ValueRepository;

@Service
public class ValueCalculateServiceImpl implements ValueCalculateService {

	private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ValueRepository valueRepository;
	
	@Override
	public void calculate() {

		Iterator<Shop> shopIterator = shopRepository.findAll().iterator();
		List<Value> valueList = (List<Value>) valueRepository.findAll();
		
		while(shopIterator.hasNext()) {
			Shop shop = shopIterator.next();
			StandardEvaluationContext context = new StandardEvaluationContext(shop);
			
			long score = 0;
			for(Value value: valueList) {
				
				long detailScore = 0;
				List<ValueDetail> valueDetailList = value.getValueDetailList();
				for(ValueDetail valueDetail: valueDetailList) {
					String expression = valueDetail.getExpression();
					
					ExpressionParser parser = new SpelExpressionParser();
					Expression parseExpression = parser.parseExpression(expression);
					Long valueDetailScore = parseExpression.getValue(context, Long.class);
					
					// 상세 가치 = 항목별가치  * 반영비율
					detailScore += ((valueDetail.getApplyRate() * 100) * valueDetailScore);
				}
				
				// 대분류 가치 = (상세가치 * 반영비율)
				score += ((value.getApplyRate() * 100) * detailScore);
			}
			
			shop.setValueScore(score);
			
			// update score
			shopRepository.save(shop);
		}
	
		log.info("shop value calculated.");
	}

}
