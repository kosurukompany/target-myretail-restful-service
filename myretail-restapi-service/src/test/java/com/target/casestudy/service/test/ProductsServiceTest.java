package com.target.casestudy.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.target.casestudy.model.CurrentPrices;
import com.target.casestudy.model.Products;
import com.target.casestudy.repository.ProductsRepository;
import com.target.casestudy.service.ProductsService;

/**
 * @author Satya Kosuru
 *
 */

@SpringBootTest
public class ProductsServiceTest {

	@Mock
	private ProductsRepository productsRepository;

	@InjectMocks
	private ProductsService productsService;

	@DisplayName("Test for save product method")
	@Test
	public void saveProductTest() {

		final String[][] data = getDataSet();

		for (int i = 0; i < data.length; i++) {

			final Products product = getProduct(Long.parseLong(data[i][0]), data[i][1], data[i][2], data[i][3]);

			productsService.saveProduct(product);

			verify(productsRepository, times(1)).save(product);
		}

	}

	@DisplayName("Test for delete product method")
	@Test
	public void deleteProductTest() {

		final String[][] data = getDataSet();

		for (int i = 0; i < data.length; i++) {

			final Products product = getProduct(Long.parseLong(data[i][0]), data[i][1], data[i][2], data[i][3]);

			productsService.delete(product);

			doNothing().when(productsRepository).delete(product);
			verify(productsRepository, times(1)).delete(product);

		}
	}

	@DisplayName("Test for get all products method")
	@Test
	public void findAllProductsTest() {

		final String[][] data = getDataSet();
		final List<Products> addProducts = new ArrayList<>();

		for (int i = 0; i < data.length; i++) {

			final Products product = getProduct(Long.parseLong(data[i][0]), data[i][1], data[i][2], data[i][3]);

			addProducts.add(product);
		}

		doReturn(addProducts).when(productsRepository).findAll();

		final List<Products> getAllProducts = productsService.findAll();

		assertEquals(data.length, getAllProducts.size());
		verify(productsRepository, times(1)).findAll();

	}

	@DisplayName("Test for get find product by ID method")
	@Test
	public void findProductByIdTest() {

		final String[][] data = getDataSet();

		for (int i = 0; i < data.length; i++) {

			final long id = Long.parseLong(data[i][0]);

			final Products product = getProduct(id, data[i][1], data[i][2], data[i][3]);

			when(productsRepository.findById(id)).thenReturn(product);

			final Products result = productsService.findById(Long.parseLong(data[i][0]));

			assertEquals(data[i][1], result.getName());
			assertEquals(data[i][2], result.getCurrent_price().getValue());
			assertEquals(data[i][3], result.getCurrent_price().getCurrency_code());

			verify(productsRepository, times(1)).findById(id);
		}

	}

	private String[][] getDataSet() {

		final String[][] data = { { "13860428", "Sony", "20", "USD" }, { "15117729", "LG", "30000", "INR" },
				{ "16483589", "Samsung", "50.99", "USD" } };

		return data;
	}

	private Products getProduct(long id, String name, String price, String priceCode) {

		final Products product = new Products();
		final CurrentPrices currentPrice = new CurrentPrices();

		product.setId(id);
		product.setName(name);
		currentPrice.setValue(price);
		currentPrice.setCurrency_code(priceCode);
		product.setCurrent_price(currentPrice);

		return product;

	}

}
