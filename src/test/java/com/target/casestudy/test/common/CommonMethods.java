package com.target.casestudy.test.common;

import org.springframework.stereotype.Service;

import com.target.casestudy.common.Constants;
import com.target.casestudy.model.CurrentPrices;
import com.target.casestudy.model.Products;

/**
 * @author Satya
 *
 */
@Service
public class CommonMethods {

	public Products getProduct(long id, String name, String price, String priceCode) {

		final Products product = new Products();
		final CurrentPrices currentPrice = new CurrentPrices();

		product.setId(id);
		product.setName(name);
		currentPrice.setValue(price);
		currentPrice.setCurrency_code(priceCode);
		product.setCurrent_price(currentPrice);

		return product;

	}

	public String getURL(int randomPort) {

		return Constants.LOCALHOST + randomPort + Constants.PRODUCTS_URL;
	}

}
