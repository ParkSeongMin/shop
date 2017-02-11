package com.tm.shop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.tm.shop.repository.ValueDetailRepository;

/**
 * value detail entity
 * 
 * @author Park SeongMin
 * @see Value
 * @see ValueDetailRepository
 */
@Entity
public class ValueDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; // 식별자
	
	/* 상위 분류 */
	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="value_fk")
	private Value value;
	
	/* 조건 (shop의 특정 필드를 대상으로 한 조건을 삽입한다.) */
	@NotNull(message="condition is required.")
	private String expression;
	
	/* 적용 비율 (0~1) - 모든 value 항목의 값은 1을 넘어설수 없다. */
	@DecimalMin(value="0") @DecimalMax(value="1")
	private float applyRate;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public float getApplyRate() {
		return applyRate;
	}

	public void setApplyRate(float applyRate) {
		this.applyRate = applyRate;
	}


	@Override
	public String toString() {
		return "ValueDetail [id=" + id + ", expression=" + expression + ", applyRate=" + applyRate + "]";
	}

}
