package com.tm.shop.repository.event;

import java.util.Date;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.tm.shop.entity.Photo;

@RepositoryEventHandler(Photo.class)
public class PhotoEventHandler {
	
	@HandleBeforeSave
	public void handlePhotoSave(Photo photo) {
		System.err.println("ddd");
		photo.getShop().setLastModified(new Date());
	}

	@HandleBeforeCreate
	public void handleShopCreate(Photo photo) {
		System.err.println("aaa");
		photo.getShop().setLastModified(new Date());
	}

}
