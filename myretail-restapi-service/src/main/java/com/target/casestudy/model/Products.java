package com.target.casestudy.model;

import org.springframework.data.annotation.Id;
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

}
