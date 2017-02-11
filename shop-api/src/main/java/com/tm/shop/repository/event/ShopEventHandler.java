package com.tm.shop.repository.event;

import java.util.Date;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.tm.shop.entity.Shop;

/**
 * shop event handler <br/>
 * set lastmodified
 * 
 * @author Park SeongMin
 *
 */
//@Component
@RepositoryEventHandler(Shop.class)
public class ShopEventHandler {

	@HandleBeforeSave
	public void handleShopSave(Shop shop) {
		System.err.println("ddd");
		shop.setLastModified(new Date());
	}

	@HandleBeforeCreate
	public void handleShopCreate(Shop shop) {
		System.err.println("aaa");
		shop.setLastModified(new Date());
	}

}
