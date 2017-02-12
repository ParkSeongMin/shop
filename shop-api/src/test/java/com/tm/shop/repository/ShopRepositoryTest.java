package com.tm.shop.repository;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import com.tm.shop.TestFramework;
import com.tm.shop.config.RepositoryInitializer;
import com.tm.shop.entity.Photo;
import com.tm.shop.entity.Photo.SourceType;
import com.tm.shop.entity.Shop;
import com.tm.shop.entity.Shop.ShopType;
import com.tm.shop.entity.ShopBuilder;

public class ShopRepositoryTest extends TestFramework {

	@Test
	public void testFindAll() {
		List<Shop> shopList = (List<Shop>) shopRepository.findAll();
		assertThat(shopList.size(), is(RepositoryInitializer.DEFAULT_COUNT));
		
	}
	
	@Test
	public void testFindOne() {

		List<Shop> shopList = (List<Shop>) shopRepository.findAll();
		
		long id = shopList.get(0).getId();
		Shop shop = shopRepository.findOne(id);
		assertEquals(shop.getName(), RepositoryInitializer.DEFAULT_SHOP + "-" + 0l);
		
		List<Photo> photoList = shop.getPhotoList();
		assertThat(photoList.size(), is(2));
	}
	
	@Test
	public void testSave() {
	
		String shopName = "dakyonail";
		String description = "desc";
		Shop shop = new ShopBuilder().name(shopName)
				.description(description)
				.tellNumber("010-1111-1111")
				.viewCnt(10).likeCnt(10).rating(10)
				.shopType(ShopType.large)
				.discounted(true)
				.valueScore(0)
				.lastModified(new Date())
				.photo(new Photo("http://gelato.im/photo/" + shopName +"/1", SourceType.itSelf))
				.photo(new Photo("http://gelato.im/photo/" + shopName +"/2", SourceType.providedShop))
				.build();
		
		shopRepository.save(shop);
		
		List<Shop> findByName = shopRepository.findByName(shopName);
		assertThat(findByName.size(), is(1));
	}
	
	@Test
	public void testNullValue() {
		Shop build = new ShopBuilder().build();
		try {
			shopRepository.save(build);
			fail("must be error occured.");
		} catch(Throwable e) {
			assertThat(e, instanceOf(ConstraintViolationException.class));
		}
	}
	
}
