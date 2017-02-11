package com.tm.shop;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tm.shop.config.RepositoryInitializer;
import com.tm.shop.config.RepositoryInitializerAccessor;
import com.tm.shop.repository.ShopRepository;
import com.tm.shop.repository.ValueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFramework {

	@Autowired
	RepositoryInitializer initializer;
	
	@Autowired
	protected ShopRepository shopRepository;
	
	@Autowired
	protected ValueRepository valueRepository;
	
	@Before
	public void setUp() throws Exception {
		initializer.initialize();
	}
	
	@After
	public void tearDown() throws Exception {
		RepositoryInitializerAccessor.clear(initializer);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// nothing
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// nothing
	}
	
}
