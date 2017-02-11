package com.tm.shop.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tm.shop.entity.Photo;
import com.tm.shop.entity.Photo.SourceType;
import com.tm.shop.entity.Shop;
import com.tm.shop.entity.Shop.ShopType;
import com.tm.shop.entity.ShopBuilder;
import com.tm.shop.entity.Value;
import com.tm.shop.entity.ValueDetail;
import com.tm.shop.repository.ShopRepository;
import com.tm.shop.repository.ValueRepository;
import com.tm.shop.service.ValueCalculateService;

/**
 * repository initializer
 * 
 * @author Park SeongMin
 *
 */
@Component
public class RepositoryInitializer {

	private static final Logger log = LoggerFactory.getLogger(RepositoryInitializer.class);
	
	public static final String DEFAULT_SHOP = "ilovenail";
	public static final int DEFAULT_COUNT = 10;
	
	private static boolean initialized = false;
	
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ValueRepository valueRepository;
	
	@Autowired
	private ValueCalculateService calcService;
	
	public void initialize() {
		
		if(initialized) {
			return;
		}
		
		initializerShop();
		initializeValue();
		applyValue();
		
		
		initialized = true;
	
	}
	
	void clear() {
		clearRepository();
	}
	
	private void clearRepository() {
		shopRepository.deleteAll();
		valueRepository.deleteAll();
		initialized = false;
	}
	
	private void initializerShop() {
		
		for(int i=0; i<DEFAULT_COUNT; i++) {
			shopRepository.save(createShop(i, DEFAULT_SHOP));
		}
		
		log.info("shop repository initialized.");
	}
	
	private Shop createShop(long index, String name) {
		
		Shop shop = new ShopBuilder().name(name + "-" + index)
										.description(name + "-desc-" + index )
										.tellNumber("010-1111-111" + index % 10)
										.viewCnt(index).likeCnt(index).rating(index % 10)
										.shopType(ShopType.large)
										.discounted(true)
										.valueScore(0)
										.lastModified(new Date())
										.photo(new Photo("http://gelato.im/photo/" + name +"/" + index, SourceType.itSelf))
										.photo(new Photo("http://gelato.im/photo/" + name +"/" + index+1, SourceType.providedShop))
										.bulid();
		
		return shop;
	}
	
	private void initializeValue() {
		
		// 고객 관점의 가치 설정
		Value customValue = new Value();
		customValue.setDescription("customer");
		customValue.setApplyRate(0.7f);
		
		ValueDetail viewValueDetail = new ValueDetail();
		viewValueDetail.setApplyRate(0.3f);
		// 조회수 값이 [1000 ~ :3 점] [200~999: 2점] [~ 199 : 1점]
		viewValueDetail.setExpression("viewCnt > 999 ? 3 : (viewCnt > 199 ? 2 : 1)");
		
		ValueDetail likeValueDetail = new ValueDetail();
		likeValueDetail.setApplyRate(0.4f);
		// 좋아요 수가 [400 ~ :3 점] [110~399: 2점] [~ 109 : 1점]
		likeValueDetail.setExpression("likeCnt > 399 ? 3 : (likeCnt > 109 ? 2 : 1)");
		
		ValueDetail ratingValueDetail = new ValueDetail();
		ratingValueDetail.setApplyRate(0.3f);
		// 사용자 평점이 [~ 10 :3 점] [5~9: 2점] [0~4: 1점]
		ratingValueDetail.setExpression("rating > 9 ? 3 : (rating > 4 ? 2 : 1)");
		
		customValue.addValueDetail(viewValueDetail);
		customValue.addValueDetail(likeValueDetail);
		customValue.addValueDetail(ratingValueDetail);
		
		valueRepository.save(customValue);
		
		
		// 상점 관점의 가치 설정
		Value shopValue = new Value();
		shopValue.setDescription("shop");
		shopValue.setApplyRate(0.3f);
		
		ValueDetail infoValueDetail = new ValueDetail();
		infoValueDetail.setApplyRate(0.55f);
		// 최종수정일과 현재 날짜와의 차이 [~1일: 3점] [2~7: 2점] [8일~: 1점]
		// 1일=24*60*60*1000=86400000
		infoValueDetail.setExpression("getCurrentTime() - lastModified.getTime() > 86400000 * 7 ? 1 :"
				+ "getCurrentTime() - lastModified.getTime() > 86400000 * 1 ? 2 : 1");
		
		
		
		ValueDetail discountValueDetail = new ValueDetail();
		discountValueDetail.setApplyRate(0.45f);
		// 할인 이벤트 중 : 3점, 이벤트 하지 않음 : 0점 
		// boolean type : isDiscounted -> discounted
		discountValueDetail.setExpression("discounted? 3: 0");
		
		shopValue.addValueDetail(infoValueDetail);
		shopValue.addValueDetail(discountValueDetail);
		
		valueRepository.save(shopValue);
		
		log.info("value repository initialized.");
	}
	
	private void applyValue() {

		calcService.calculate();
		
		log.info("shop value applied");
	}
	
}
