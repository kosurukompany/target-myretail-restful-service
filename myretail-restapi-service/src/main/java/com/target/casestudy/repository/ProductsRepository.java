package com.target.casestudy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.target.casestudy.model.Products;

/**
 * @author Satya Kosuru
 *
 */

@Repository("productsRepository")
public interface ProductsRepository extends MongoRepository<Products, Long> {

	Products findById(String id);

}
