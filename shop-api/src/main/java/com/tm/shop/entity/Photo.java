package com.tm.shop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tm.shop.repository.PhotoRepository;

/**
 * photo entity
 * 
 * @author Park SeongMin
 * @see Shop
 * @see PhotoRepository
 */
@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "shop_fk")
	private Shop shop;

	/* url */
	@NotNull(message="url is required.")
	@Size(max=100, message="url is invalid size.")
	private String url;

	/* 출처 */
	@NotNull(message="sourceType is required.")
	private SourceType sourceType;
	
	public Photo() {
	}
	
	public Photo(String url, SourceType soureType) {
		setUrl(url);
		setSourceType(soureType);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}


	@Override
	public String toString() {
		return "Photo [id=" + id + ", url=" + url + ", sourceType=" + sourceType + "]";
	}


	public static enum SourceType {
		itSelf, providedShop
	}
	
}
