package com.tm.shop;

import java.util.Date;

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
import com.tm.shop.entity.Photo;
import com.tm.shop.entity.Shop;
import com.tm.shop.entity.ShopBuilder;
import com.tm.shop.entity.Photo.SourceType;
import com.tm.shop.entity.Shop.ShopType;
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
	
	protected Shop createShop(long index, String name) {
		
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
										.build();
		
		return shop;
	}
	
}
