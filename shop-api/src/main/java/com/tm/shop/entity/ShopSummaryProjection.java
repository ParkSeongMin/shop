package com.tm.shop.entity;

import org.springframework.data.rest.core.config.Projection;

import com.tm.shop.entity.Shop.ShopType;

@Projection(name = "summary", types = Shop.class)
public interface ShopSummaryProjection {

	String getName();

	String getDescription();

	double getRating();

	ShopType getShopType();

	boolean isDiscounted();

	long getValueScore();

}
