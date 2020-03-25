package com.target.casestudy.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	private static Log _log = LogFactory.getLog(ProductsController.class.getName());

	@Autowired
	ProductsService productsService;

	@PostMapping(value = Constants.POST_URL, produces = Constants.APPLICATION_JSON, consumes = Constants.APPLICATION_JSON)
	public String postProduct(@RequestBody @Valid Products product) {

		if (productsService.findById(product.getId()) == null) {

			final Products newProduct = productsService.saveProduct(product);

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_CREATED + newProduct.getId());
			return httpResponse(Constants.RESPONSE_CODE_201, HttpStatus.CREATED,
					Constants.PRODUCT_MESSAGE_CREATED + newProduct.getId());

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_EXISTS + product.getId());
			return httpResponse(Constants.RESPONSE_CODE_403, HttpStatus.FORBIDDEN,
					Constants.PRODUCT_MESSAGE_EXISTS + product.getId());
		}
	}

	@GetMapping(value = Constants.PATH_VARIABLE_ID, produces = Constants.APPLICATION_JSON)
	public Object getProduct(@PathVariable(required = true) long id) {

		final Products existingProduct = productsService.findById(id);

		if (existingProduct != null) {

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_FOUND + id);
			return existingProduct;

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

			return httpResponse(Constants.RESPONSE_CODE_404, HttpStatus.NOT_FOUND,
					Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

		}

	}

	@GetMapping(value = Constants.EXTERNAL_GET_URL, produces = Constants.APPLICATION_JSON)
	public String getProductFromExtrenalAPI(@PathVariable(required = true) long id) throws URISyntaxException {

		String productName = null;

		final URI uri = new URI(Constants.EXTERNAL_GET_URL_PART_1 + id + Constants.EXTERNAL_GET_URL_PART_2);
		final RestTemplate rest = new RestTemplate();

		try {

			final ResponseEntity<String> externalResponse = rest.getForEntity(uri, String.class);
			final JSONObject json = new JSONObject(externalResponse.getBody());
			productName = json.getJSONObject(Constants.EXTERNAL_PRODUCT).getJSONObject(Constants.EXTERNAL_ITEM)
					.getJSONObject(Constants.EXTERNAL_PRODUCT_DESCRIPTION).getString(Constants.EXTERNAL_TITLE);

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_FOUND + id);

			return productName;

		} catch (final Exception e) {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);
			return httpResponse(Constants.RESPONSE_CODE_404, HttpStatus.NOT_FOUND,
					Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

		}

	}

	@PutMapping(value = Constants.PATH_VARIABLE_ID, produces = Constants.APPLICATION_JSON, consumes = Constants.APPLICATION_JSON)
	public @ResponseBody String updateProduct(@RequestBody @Valid Products product,
			@PathVariable(required = true) long id) {

		final Products existingProduct = productsService.findById(id);

		if (existingProduct == null) {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);
			return httpResponse(Constants.RESPONSE_CODE_404, HttpStatus.NOT_FOUND,
					Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

		} else if (product.getId() != id) {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_1 + id
					+ Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_2 + product.getId());
			return httpResponse(Constants.RESPONSE_CODE_400, HttpStatus.BAD_REQUEST,
					Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_1 + id + Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_2
							+ product.getId());
		}

		final Products updatedProduct = productsService.updateProduct(existingProduct, product);

		_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_UPDATED + updatedProduct.getId());
		return httpResponse(Constants.RESPONSE_CODE_200, HttpStatus.OK,
				Constants.PRODUCT_MESSAGE_UPDATED + updatedProduct.getId());

	}

	@DeleteMapping(value = Constants.PATH_VARIABLE_ID, produces = Constants.APPLICATION_JSON)
	public Object deleteProduct(@PathVariable(required = true) long id) {

		final Products existingProduct = productsService.findById(id);

		if (existingProduct != null) {

			productsService.delete(existingProduct);

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_DELETED + id);
			return httpResponse(Constants.RESPONSE_CODE_200, HttpStatus.OK, Constants.PRODUCT_MESSAGE_DELETED + id);

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

			return httpResponse(Constants.RESPONSE_CODE_404, HttpStatus.NOT_FOUND,
					Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

		}
	}

	private String httpResponse(String code, HttpStatus status, String message) {

		final JSONObject httpResponse = new JSONObject();

		httpResponse.put(Constants.CODE, code);
		httpResponse.put(Constants.STATUS, status);
		httpResponse.put(Constants.MESSAGE, message);

		return httpResponse.toString();
	}
}
