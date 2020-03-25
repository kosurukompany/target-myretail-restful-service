package com.target.casestudy.service;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.casestudy.common.Constants;
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

	public Products findById(long id) {
		return productsRepository.findById(id);
	}

	public Products saveProduct(@Valid Products product) {

		product.setCreatedBy(Constants.API_POST_REQUEST);
		product.setCreatedOn(LocalDateTime.now());

		return productsRepository.save(product);
	}

	public Products updateProduct(@Valid Products existingProduct, @Valid Products product) {

		existingProduct.setCurrent_price(product.getCurrent_price());
		existingProduct.setUpdatedBy(Constants.API_PUT_REQUEST);
		existingProduct.setUpdatedOn(LocalDateTime.now());

		return productsRepository.save(existingProduct);
	}

	public void delete(@Valid Products product) {
		productsRepository.delete(product);
	}
}
