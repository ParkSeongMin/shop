package com.tm.shop.entity;

import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;

import com.tm.shop.repository.ValueRepository;

/**
 * value entity
 * 
 * @author Park SeongMin
 * @see ValueRepository
 */
@Entity
public class Value {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/* 가치설명(고객관점 | 상점관점) */
	@NotNull(message="description is required.")
	private String description;

	/* 적용 비율 (0~1) - 모든 value 항목의 값은 1을 넘어설수 없다. */
	@DecimalMin(value="0") @DecimalMax(value="1")
	private float applyRate;
	
	@OneToMany(mappedBy="value", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ValueDetail> valueDetailList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getApplyRate() {
		return applyRate;
	}

	public void setApplyRate(float applyRate) {
		this.applyRate = applyRate;
	}

	public List<ValueDetail> getValueDetailList() {
		return valueDetailList;
	}

	public void setValueDetailList(List<ValueDetail> valueDetailList) {
		for(ValueDetail valueDetail: valueDetailList) {
			valueDetail.setValue(this);
		}
		this.valueDetailList = valueDetailList;
	}

	public void addValueDetail(ValueDetail valueDetail) {
		if(this.valueDetailList == null) {
			this.valueDetailList = new ArrayList<ValueDetail>();
		}
		valueDetail.setValue(this);
		this.valueDetailList.add(valueDetail);
	}

	@Override
	public String toString() {
		return "Value [id=" + id + ", description=" + description + ", applyRate=" + applyRate + "]";
	}

}
