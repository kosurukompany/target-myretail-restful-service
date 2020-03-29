package com.target.casestudy.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.common.Constants;
import com.target.casestudy.model.Products;
import com.target.casestudy.service.ProductsService;
import com.target.casestudy.test.common.CommonMethods;

/**
 * @author Satya Kosuru
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsControllerTest {

	@LocalServerPort
	int randomPort;

	@Autowired
	private ProductsService productsService;

	@Autowired
	private CommonMethods commonMethods;

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_GET_ALL_PRODUCTS + Constants.SPACE + Constants.REST_API)
	@Order(1)
	@Test
	public void getAllProductsTest() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();
		final String url = commonMethods.getURL(randomPort);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

		final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		assertEquals(Constants.RESPONSE_CODE_200, response.getStatusCodeValue());
		assertEquals(productsService.findAll().size(), new JSONArray(response.getBody()).length());
	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_SAVE_PRODUCT + Constants.SPACE + Constants.REST_API)
	@Order(2)
	@Test
	public void saveProductTest() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();
		final String url = commonMethods.getURL(randomPort) + Constants.ADD_URL;

		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		final String[][] data = Constants.UNIT_TEST_DATA_SET;

		for (int i = 0; i < data.length; i++) {

			final long id = Long.parseLong(data[i][0]);

			final Products product = commonMethods.getProduct(id, data[i][1], data[i][2], data[i][3]);

			final ResponseEntity<Products> response = restTemplate.postForEntity(url, product, Products.class);

			assertEquals(Constants.RESPONSE_CODE_200, response.getStatusCodeValue());
			assertNotNull(productsService.findById(id));
			assertNotNull(response.getBody());
		}
	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_GET_PRODUCT_BY_ID + Constants.SPACE + Constants.REST_API)
	@Order(3)
	@Test
	public void getProductByIdTest() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final String[][] data = Constants.UNIT_TEST_DATA_SET;

		for (int i = 0; i < data.length; i++) {

			final long id = Long.parseLong(data[i][0]);

			final String url = commonMethods.getURL(randomPort) + Constants.SLASH + id;

			final ResponseEntity<Products> response = restTemplate.getForEntity(url, Products.class);

			assertEquals(Constants.RESPONSE_CODE_200, response.getStatusCodeValue());
			assertNotNull(response);
			assertEquals(response.getBody().getName(), data[i][1]);
			assertEquals(response.getBody().getCurrent_price().getValue(), data[i][2]);
			assertEquals(response.getBody().getCurrent_price().getCurrency_code(), data[i][3]);
		}
	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_GET_EXTERNAL_PRODUCT_BY_ID + Constants.SPACE + Constants.REST_API)
	@Order(4)
	@Test
	public void getExternalProductByIdTest() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		// final String[][] data = Constants.UNIT_TEST_DATA_SET;

//		for (int i = 0; i < data.length; i++) {

		final long id = 13860428; // Long.parseLong(data[0][0]);

		final String url = commonMethods.getURL(randomPort) + Constants.EXTERNAL_GET_URL_FOR_TEST + id;

		final ResponseEntity<Products> response = restTemplate.getForEntity(url, Products.class);

		assertEquals(Constants.RESPONSE_CODE_200, response.getStatusCodeValue());
		assertNotNull(response);
		assertNotEquals(productsService.findById(id).getName(), response.getBody().getName());

		final ResponseEntity<String> responseForReturnName = restTemplate.getForEntity(url + Constants.QUESTION_MARK
				+ Constants.EXTERNAL_API_CONDITION + Constants.EQUAL + Constants.EXTERNAL_API_CONDITION_OPTION,
				String.class);

		assertEquals(Constants.RESPONSE_CODE_200, response.getStatusCodeValue());
		assertNotNull(responseForReturnName);
		assertEquals(response.getBody().getName(),
				new JSONObject(responseForReturnName.getBody()).get(Constants.EXTERNAL_TITLE.toUpperCase()));
//		}
	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_UPDATE_PRODUCT + Constants.SPACE + Constants.REST_API)
	@Order(5)
	@Test
	public void updateProductTest() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final String[][] data = Constants.UNIT_TEST_DATA_SET_2;

		for (int i = 0; i < data.length; i++) {

			final long id = Long.parseLong(data[i][0]);

			final String url = commonMethods.getURL(randomPort) + Constants.SLASH + id;

			final Products product = commonMethods.getProduct(id, data[i][1], data[i][2], data[i][3]);

			final HttpEntity<Products> httpEntity = new HttpEntity<>(product, headers);

			final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity,
					String.class);

			assertEquals(Constants.RESPONSE_CODE_200, response.getStatusCodeValue());
			assertNotNull(response);
			assertNotEquals(productsService.findById(id).getName(), data[i][1]);
			assertEquals(productsService.findById(id).getCurrent_price().getValue(), data[i][2]);
			assertEquals(productsService.findById(id).getCurrent_price().getCurrency_code(), data[i][3]);
		}
	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_DELETE_PRODUCT + Constants.SPACE + Constants.REST_API)
	@Test
	@Order(6)
	public void deleteProductTest() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final String[][] data = Constants.UNIT_TEST_DATA_SET;

		for (int i = 0; i < data.length; i++) {

			final long id = Long.parseLong(data[i][0]);

			final String url = commonMethods.getURL(randomPort) + Constants.SLASH + id;

			restTemplate.delete(url);

			final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

			assertEquals(Constants.RESPONSE_CODE_404, new JSONObject(response.getBody()).get(Constants.CODE));

		}
	}

}
