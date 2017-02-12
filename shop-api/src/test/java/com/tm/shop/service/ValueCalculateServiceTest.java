package com.tm.shop.service;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tm.shop.TestFramework;
import com.tm.shop.entity.Shop;

public class ValueCalculateServiceTest extends TestFramework {

	@Autowired
	private ValueCalculateService service;
	
	@Test
	public void testCalculate() {
		
		service.calculate();
		
		Iterator<Shop> iterator = shopRepository.findAll().iterator();
		while(iterator.hasNext()) {
			Shop calculatedShop = iterator.next();
			assertThat(calculatedShop.getValueScore(), greaterThan(0l));
		}
	}
	
}
