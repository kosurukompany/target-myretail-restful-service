package com.target.casestudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.target.casestudy.common.Constants;
import com.target.casestudy.model.Products;

/**
 * @author Satya Kosuru
 *
 */

@RestController
public class ProductsController {

	@RequestMapping(value = Constants.PRODUCTS_URL, method = RequestMethod.GET, produces = Constants.APPLICATION_JSON)
	public @ResponseBody Products getProduct() {

		return null;
	}
}
