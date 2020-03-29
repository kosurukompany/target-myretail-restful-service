package com.target.casestudy.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

/**
 * @author Satya Kosuru
 *
 */

@Document(collection = "products")
public class Products {

	@Id
	@NonNull
	private long id;

	private String name;

	private CurrentPrices current_price;

	@NonNull
	@CreatedBy
	private String created_by;

	@NonNull
	@LastModifiedBy
	private String updated_by;

	@NonNull
	@CreatedDate
	private LocalDateTime created_on;

	@NonNull
	@LastModifiedDate
	private LocalDateTime updated_on;

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

	public CurrentPrices getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPrices current_price) {
		this.current_price = current_price;
	}

	public String getCreatedBy() {
		return created_by;
	}

	public void setCreatedBy(String createdBy) {
		this.created_by = createdBy;
	}

	public String getUpdatedBy() {
		return updated_by;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updated_by = updatedBy;
	}

	public LocalDateTime getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.created_on = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updated_on = updatedOn;
	}

}
