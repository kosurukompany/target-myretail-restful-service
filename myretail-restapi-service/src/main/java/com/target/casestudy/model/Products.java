package com.target.casestudy.model;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Satya Kosuru
 *
 */

@Document(collection = "products")
public class Products {

	@Id
	@NotEmpty
	private String id;

	private String name;

	private Set<CurrentPrices> current_price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CurrentPrices> getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(Set<CurrentPrices> current_price) {
		this.current_price = current_price;
	}

}
