package com.target.casestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.casestudy.model.Products;
import com.target.casestudy.repository.ProductsRepository;

/**
 * @author Satya Kosuru
 *
 */

@Service("productsService")
public class ProductsService {

	private final ProductsRepository productsRepository;

	@Autowired
	public ProductsService(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	public Products findById(String id) {
		return productsRepository.findById(id);
	}

}
