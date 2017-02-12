package com.tm.shop.entity;

import java.util.Date;
import java.util.List;

import com.tm.shop.entity.Shop.ShopType;

public class ShopBuilder {
	
	private Shop shop;
	
	public ShopBuilder() {
		this.shop = new Shop();
	}
	
	public ShopBuilder name(String name) {
		this.shop.setName(name);
		return this;
	}
	
	public ShopBuilder description(String description) {
		this.shop.setDescription(description);
		return this;
	}
	
	public ShopBuilder tellNumber(String tellNumber) {
		this.shop.setTelNumber(tellNumber);
		return this;
	}
	
	public ShopBuilder viewCnt(long viewCnt) {
		this.shop.setViewCnt(viewCnt);
		return this;
	}
	
	public ShopBuilder likeCnt(long likeCnt) {
		this.shop.setLikeCnt(likeCnt);
		return this;
	}
	
	public ShopBuilder rating(double rating) {
		this.shop.setRating(rating);
		return this;
	}
	
	public ShopBuilder photoList(List<Photo> photoList) {
		this.shop.setPhotoList(photoList);
		return this;
	}
	
	public ShopBuilder photo(Photo photo) {
		this.shop.addPhoto(photo);
		return this;
	}
	
	public ShopBuilder shopType(ShopType shopType) {
		this.shop.setShopType(shopType);
		return this;
	}
	
	public ShopBuilder discounted(boolean isDiscounted) {
		this.shop.setDiscounted(isDiscounted);
		return this;
	}
	
	public ShopBuilder valueScore(long valueScore) {
		this.shop.setValueScore(valueScore);
		return this;
	}
	
	public ShopBuilder lastModified(Date lastModified) {
		this.shop.setLastModified(lastModified);
		return this;
	}
	
	public Shop build() {
		return this.shop;
	}
	
}
