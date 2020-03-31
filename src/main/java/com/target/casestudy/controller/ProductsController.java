package com.target.casestudy.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.common.Constants;
import com.target.casestudy.model.Products;
import com.target.casestudy.service.ProductsService;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Satya Kosuru
 *
 */

@ApiResponses(value = { @ApiResponse(code = Constants.RESPONSE_CODE_200, message = Constants.RESPONSE_CODE_200_DES),
		@ApiResponse(code = Constants.RESPONSE_CODE_401, message = Constants.RESPONSE_CODE_401_DES) })
@RestController
@RequestMapping(value = Constants.PRODUCTS_URL, produces = Constants.APPLICATION_JSON)
public class ProductsController {

	private static Log _log = LogFactory.getLog(ProductsController.class.getName());

	@Autowired
	private ProductsService productsService;

	/**
	 * 
	 * Get all Products (Response time in Postman 30ms)
	 * 
	 * @return Product list (or) JSON Response
	 * @throws IOException
	 * 
	 */
	@ApiResponses(value = {
			@ApiResponse(code = Constants.RESPONSE_CODE_204, message = Constants.RESPONSE_CODE_204_DES) })

	@GetMapping()
	public Object getAllProducts(HttpServletResponse response) throws IOException {

		final List<Products> productsList = productsService.findAll();

		if (productsList.size() != 0) {

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_SIZE + productsList.size());
			return productsList;

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NO_PRODUCTS);

			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}
	}

	/**
	 * 
	 * Add a new product (Response time in Postman 34ms)
	 * 
	 * @param Valid product
	 * @return Success/Fail JSON Response
	 * 
	 */

	@ApiResponses(value = { @ApiResponse(code = Constants.RESPONSE_CODE_201, message = Constants.RESPONSE_CODE_201_DES),
			@ApiResponse(code = Constants.RESPONSE_CODE_403, message = Constants.RESPONSE_CODE_403_DES) })
	@PostMapping(consumes = Constants.APPLICATION_JSON)
	public Object addProduct(
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_PRODUCT, required = true) @RequestBody @Valid Products product) {

		if (productsService.findById(product.getId()) == null) {

			final Products newProduct = productsService.saveProduct(product);

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_CREATED + newProduct.getId());

			return new ResponseEntity<>(
					customResponse(HttpStatus.CREATED, Constants.PRODUCT_MESSAGE_CREATED + newProduct.getId()),
					HttpStatus.CREATED);

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_EXISTS + product.getId());

			return new ResponseEntity<>(
					customResponse(HttpStatus.FORBIDDEN, Constants.PRODUCT_MESSAGE_EXISTS + product.getId()),
					HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * 
	 * Get a product (Response time in Postman 9ms)
	 * 
	 * @param id
	 * @return On success return requested product (or) sends product not found
	 *         response
	 * 
	 * 
	 */

	@ApiResponses(value = {
			@ApiResponse(code = Constants.RESPONSE_CODE_404, message = Constants.RESPONSE_CODE_404_DES) })

	@GetMapping(value = Constants.PATH_VARIABLE_ID)
	public Object getProduct(
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_ID, required = true) @PathVariable() long id) {

		final Products existingProduct = productsService.findById(id);

		if (existingProduct != null) {

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_FOUND + id);
			return existingProduct;

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

			return new ResponseEntity<>(customResponse(HttpStatus.NOT_FOUND, Constants.PRODUCT_MESSAGE_NOT_FOUND + id),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * 
	 * Get a product from external API Given in the case
	 * study(https://redsky.target.com) (Response time in Postman 27ms,33ms)
	 * 
	 * @param id
	 * @throws URISyntaxException
	 * @return If condition=nameonly then returns the name of the product otherwise
	 *         its returns the combined id and name from external API and price data
	 *         from database (or) sends product not found response
	 * 
	 */

	@ApiResponses(value = {
			@ApiResponse(code = Constants.RESPONSE_CODE_404, message = Constants.RESPONSE_CODE_404_DES) })
	@GetMapping(value = Constants.EXTERNAL_GET_URL)
	public Object getExternalProduct(
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_ID, required = true) @PathVariable() long id,
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_CONDITION) @RequestParam(required = false) String condition,
			HttpServletRequest req) throws URISyntaxException {

		String productName = null;

		final URI uri = new URI(Constants.EXTERNAL_GET_URL_PART_1 + id + Constants.EXTERNAL_GET_URL_PART_2);
		final RestTemplate rest = new RestTemplate();

		try {

			final ResponseEntity<String> externalResponse = rest.getForEntity(uri, String.class);

			final JSONObject json = new JSONObject(externalResponse.getBody());

			productName = json.getJSONObject(Constants.EXTERNAL_PRODUCT).getJSONObject(Constants.EXTERNAL_ITEM)
					.getJSONObject(Constants.EXTERNAL_PRODUCT_DESCRIPTION).getString(Constants.EXTERNAL_TITLE);

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_FOUND + id);

			if (req.getParameterMap().containsKey(Constants.EXTERNAL_API_CONDITION.toLowerCase())
					&& req.getParameter(Constants.EXTERNAL_API_CONDITION.toLowerCase())
							.equals(Constants.EXTERNAL_API_CONDITION_OPTION.toLowerCase())) {

				final JSONObject productNameJson = new JSONObject();
				productNameJson.put(Constants.EXTERNAL_TITLE.toUpperCase(), productName);

				return productNameJson.toString();

			} else {

				final Products product = productsService.findById(id);
				product.setId(Long.parseLong(json.getJSONObject(Constants.EXTERNAL_PRODUCT)
						.getJSONObject(Constants.EXTERNAL_ITEM).getString(Constants.EXTERNAL_TCIN)));
				product.setName(productName);

				return product;
			}

		} catch (final Exception e) {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

			return new ResponseEntity<>(customResponse(HttpStatus.NOT_FOUND, Constants.PRODUCT_MESSAGE_NOT_FOUND + id),
					HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * 
	 * This PUT method only update the pricing of requested product. (Response time
	 * in Postman 12ms)
	 * 
	 * @param Valid product
	 * @return Success/Fail JSON Response
	 * 
	 */

	@ApiResponses(value = { @ApiResponse(code = Constants.RESPONSE_CODE_200, message = Constants.RESPONSE_CODE_200_DES),
			@ApiResponse(code = Constants.RESPONSE_CODE_400, message = Constants.RESPONSE_CODE_400_DES) })

	@PutMapping(value = Constants.PATH_VARIABLE_ID, consumes = Constants.APPLICATION_JSON)
	public @ResponseBody Object updateProductPriceData(
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_PRODUCT, required = true) @RequestBody @Valid Products product,
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_ID, required = true) @PathVariable() long id) {

		final Products existingProduct = productsService.findById(id);

		if (existingProduct == null) {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

			return new ResponseEntity<>(customResponse(HttpStatus.NOT_FOUND, Constants.PRODUCT_MESSAGE_NOT_FOUND + id),
					HttpStatus.NOT_FOUND);

		} else if (product.getId() != id) {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_1 + id
					+ Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_2 + product.getId());

			return new ResponseEntity<>(
					customResponse(HttpStatus.BAD_REQUEST,
							Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_1 + id
									+ Constants.PRODUCT_MESSAGE_ID_MISMATCH_PART_2 + product.getId()),
					HttpStatus.BAD_REQUEST);
		}

		final Products updatedProduct = productsService.updateProduct(existingProduct, product);

		_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_UPDATED + updatedProduct.getId());

		return new ResponseEntity<>(
				customResponse(HttpStatus.OK, Constants.PRODUCT_MESSAGE_UPDATED + updatedProduct.getId()),
				HttpStatus.OK);

	}

	/**
	 * 
	 * This DELETE method delete the requested product. (Response time in Postman
	 * 9ms)
	 * 
	 * @param id
	 * @return Success/Fail JSON Response
	 * 
	 */

	@ApiResponses(value = { @ApiResponse(code = Constants.RESPONSE_CODE_200, message = Constants.RESPONSE_CODE_200_DES),
			@ApiResponse(code = Constants.RESPONSE_CODE_404, message = Constants.RESPONSE_CODE_404_DES) })

	@DeleteMapping(value = Constants.PATH_VARIABLE_ID)
	public Object deleteProduct(
			@ApiParam(value = Constants.SWAGGER_PARAM_DES_ID, required = true) @PathVariable() long id) {

		final Products existingProduct = productsService.findById(id);

		if (existingProduct != null) {

			productsService.delete(existingProduct);

			_log.info(Constants.SUCCESS + Constants.PRODUCT_MESSAGE_DELETED + id);

			return new ResponseEntity<>(customResponse(HttpStatus.OK, Constants.PRODUCT_MESSAGE_DELETED + id),
					HttpStatus.OK);

		} else {

			_log.error(Constants.ERROR + Constants.PRODUCT_MESSAGE_NOT_FOUND + id);

			return new ResponseEntity<>(customResponse(HttpStatus.NOT_FOUND, Constants.PRODUCT_MESSAGE_NOT_FOUND + id),
					HttpStatus.NOT_FOUND);

		}
	}

	private String customResponse(HttpStatus status, String message) {

		final JSONObject response = new JSONObject();

		response.put(Constants.TIMESTAMP, LocalDateTime.now());
		response.put(Constants.CODE, status.value());
		response.put(Constants.STATUS, status);
		response.put(Constants.MESSAGE, message);

		return response.toString();
	}
}
