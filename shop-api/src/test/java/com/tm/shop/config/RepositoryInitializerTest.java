package com.tm.shop.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tm.shop.entity.Shop;
import com.tm.shop.entity.Value;
import com.tm.shop.entity.ValueDetail;
import com.tm.shop.repository.ShopRepository;
import com.tm.shop.repository.ValueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryInitializerTest {

	@Autowired
	private RepositoryInitializer initializer;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ValueRepository valueRepository;
	
	@After
	public void tearDown() {
		reset();
	}
	
	@Test
	public void testInitializeRepository() {
		initialize();
	}
	
	@Test
	public void testMultipleInitializer() {
		initialize();
		initialize();
	}
	
	@Test
	public void testResetRepository() {
		initialize();
		reset();
	}
	
	private void initialize() {
		initializer.initialize();
		
		checkTheShopRepository();
		checkTheValueRepository();
	}
	
	private void reset() {
		RepositoryInitializerAccessor.clear(initializer);
		assertFalse(shopRepository.findAll().iterator().hasNext());
		assertFalse(valueRepository.findAll().iterator().hasNext());
	}
	
	private void checkTheShopRepository() {
		Iterator<Shop> iterator = shopRepository.findAll().iterator();
		assertTrue(iterator.hasNext());
	}
	
	private void checkTheValueRepository() {
		
		Iterator<Value> valueIterator = valueRepository.findAll().iterator();
		assertTrue(valueIterator.hasNext());
		
		// 가치 반영비율의 합이 1인지 확인한다.
		float valueApplyRate = 0;
		while(valueIterator.hasNext()) {
			Value value = valueIterator.next();
			valueApplyRate += value.getApplyRate();
			
			List<ValueDetail> valueDetailList = value.getValueDetailList();
			assertNotNull(valueDetailList);

			float valueDetailApplyRate = 0;
			for(ValueDetail valueDetail: valueDetailList) {
				valueDetailApplyRate += valueDetail.getApplyRate();
			}
			
			assertThat("sum of applyRate(ValueDetail) must be 1. check the ValueDetail applyRate", valueDetailApplyRate, is(1f));
		}
		
		assertThat("sum of applyRate(Value) must be 1. check the Value applyRate", valueApplyRate, is(1f));
	}
	
}
