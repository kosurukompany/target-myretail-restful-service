package com.target.casestudy.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.common.Constants;
import com.target.casestudy.model.Products;
import com.target.casestudy.service.ProductsService;

/**
 * @author Satya Kosuru
 *
 */

@RestController
@RequestMapping(value = Constants.PRODUCTS_URL)
public class ProductsController {

	@Autowired
	ProductsService productsService;

	@PostMapping(value = Constants.POST_URL, produces = Constants.APPLICATION_JSON, consumes = Constants.APPLICATION_JSON)
	public String postProduct(@RequestBody @Valid List<Products> product) {

		for (final Products p : product)
			productsService.saveProduct(p);

		return "Success";

	}

	@GetMapping(value = Constants.GET_URL, produces = Constants.APPLICATION_JSON)
	public @ResponseBody Products getProduct(@PathVariable("id") long id) {

		return productsService.findById(id);
	}

	@GetMapping(value = Constants.EXTERNAL_GET_URL, produces = "application/json")
	public String getProductFromExtrenal(@PathVariable("id") long id) throws URISyntaxException {

		final URI uri = new URI(Constants.EXTERNAL_GET_URL_PART_1 + id + Constants.EXTERNAL_GET_URL_PART_2);
		final RestTemplate rest = new RestTemplate();

		final ResponseEntity<String> response = rest.getForEntity(uri, String.class);

		final JSONObject json = new JSONObject(response.getBody());
		final String name = json.getJSONObject("product").getJSONObject("item").getJSONObject("product_description")
				.getString("title");
		return name;
	}

	@PutMapping(value = Constants.GET_URL, produces = Constants.APPLICATION_JSON, consumes = Constants.APPLICATION_JSON)
	public @ResponseBody String updateProduct(@RequestBody @Valid Products product, @PathVariable("id") long id) {

		if (productsService.findById(id) == null) {

			return "Product not exist";
		} else if (product.getId() != id) {

			return "Product mis match";
		}
		productsService.saveProduct(product);

		return "Success";

	}

}
