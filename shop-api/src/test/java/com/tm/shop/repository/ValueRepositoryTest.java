package com.tm.shop.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.tm.shop.TestFramework;
import com.tm.shop.entity.Value;
import com.tm.shop.entity.ValueDetail;

public class ValueRepositoryTest extends TestFramework {

	@Test
	public void testFindAll() {
		List<Value> valueList = (List<Value>) valueRepository.findAll();
		assertThat(valueList.size(), is(2));
		
	}
	
	@Test
	public void testFindOne() {

		List<Value> valueList = (List<Value>) valueRepository.findAll();
		
		long id = valueList.get(0).getId();
		Value value = valueRepository.findOne(id);
		assertEquals(value.getDescription(), "customer");

		List<ValueDetail> valueDetailList = value.getValueDetailList();
		assertThat(valueDetailList.size(), is(3));
		
	}
	
}
