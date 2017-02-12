package com.tm.shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.tm.shop.repository.ShopRepository;

/**
 * shop entity
 * 
 * @author Park SeongMin
 * @see ShopRepository
 */
@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/* 명칭 */
	private long id; // 명칭

	/* 이름 (최대 100자) */
	@NotNull(message="name is required.")
	@Size(min=1, max=100, message="name is invalid size.")
	private String name;

	/* 설명 (null 허용) (최대허용없음, 큰타입으로 설정하기) */
	private String description;

	/* 연락처 (유효한 국내전화) */
	@NotNull(message="tellNumber is required")
	@Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$", message="verify your tell number")
	private String telNumber;

	/* 조회수 (0 이상) - 최대 12자리로 정함 */
	@Digits(fraction = 0, integer = 12, message="viewCnt only digits.")
	private long viewCnt;
	
	/* 좋아요수 (0 이상) 최대 12자리로 정함 */
	@Digits(fraction = 0, integer = 12, message="likeCnt only digits.")
	private long likeCnt;
	
	/* 평점 (0~10) */
	@DecimalMin(value="0") @DecimalMax(value="10")
	private double rating;

	/* 사진 (null 허용) */
	@OneToMany(mappedBy="shop", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Photo> photoList;

	/* 상점타입 */
	@NotNull(message="shopType is required")
	private ShopType shopType;

	/* 할인 여부 */
	private boolean isDiscounted;

	/* 가치 점수 */
	@DecimalMin(value="0")
	private long valueScore;
	
	/* 최종 수정 */
	@NotNull
	private Date lastModified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public long getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(long viewCnt) {
		this.viewCnt = viewCnt;
	}

	public long getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(long likeCnt) {
		this.likeCnt = likeCnt;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		for(Photo photo: photoList) {
			photo.setShop(this);
		}
		this.photoList = photoList;
	}
	
	public void addPhoto(Photo photo) {
		if(this.photoList == null) {
			this.photoList = new ArrayList<Photo>();
		}
		photo.setShop(this);
		this.photoList.add(photo);
	}

	public ShopType getShopType() {
		return shopType;
	}

	public void setShopType(ShopType shopType) {
		this.shopType = shopType;
	}

	public boolean isDiscounted() {
		return isDiscounted;
	}

	public void setDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}

	public long getValueScore() {
		return valueScore;
	}

	public void setValueScore(long valueScore) {
		this.valueScore = valueScore;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	// for value expression
	public long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", description=" + description + ", telNumber=" + telNumber
				+ ", viewCnt=" + viewCnt + ", likeCnt=" + likeCnt + ", rating=" + rating + ", shopType=" + shopType
				+ ", isDiscounted=" + isDiscounted + ", valueScore=" + valueScore + ", lastModified=" + lastModified
				+ "]";
	}

	public static enum ShopType {
		small, medium, large
	}


}
