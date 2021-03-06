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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.target.casestudy.common.Constants;
import com.target.casestudy.model.Products;
import com.target.casestudy.repository.ProductsRepository;
import com.target.casestudy.service.ProductsService;
import com.target.casestudy.test.common.CommonMethods;

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

	@Autowired
	private CommonMethods commonMethods;

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_SAVE_PRODUCT)
	@Test
	public void saveProductTest() throws Exception {

		final String[][] data = Constants.UNIT_TEST_DATA_SET;

		for (int i = 0; i < data.length; i++) {

			final Products product = commonMethods.getProduct(Long.parseLong(data[i][0]), data[i][1], data[i][2],
					data[i][3]);

			productsService.saveProduct(product);

			verify(productsRepository, times(1)).save(product);
		}

	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_DELETE_PRODUCT)
	@Test
	public void deleteProductTest() throws Exception {

		final String[][] data = Constants.UNIT_TEST_DATA_SET;

		for (int i = 0; i < data.length; i++) {

			final Products product = commonMethods.getProduct(Long.parseLong(data[i][0]), data[i][1], data[i][2],
					data[i][3]);

			productsService.delete(product);

			doNothing().when(productsRepository).delete(product);
			verify(productsRepository, times(1)).delete(product);

		}
	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_GET_ALL_PRODUCTS)
	@Test
	public void findAllProductsTest() throws Exception {

		final String[][] data = Constants.UNIT_TEST_DATA_SET;
		final List<Products> addProducts = new ArrayList<>();

		for (int i = 0; i < data.length; i++) {

			final Products product = commonMethods.getProduct(Long.parseLong(data[i][0]), data[i][1], data[i][2],
					data[i][3]);

			addProducts.add(product);
		}

		doReturn(addProducts).when(productsRepository).findAll();

		final List<Products> getAllProducts = productsService.findAll();

		assertEquals(data.length, getAllProducts.size());
		verify(productsRepository, times(1)).findAll();

	}

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_GET_PRODUCT_BY_ID)
	@Test
	public void findProductByIdTest() throws Exception {

		final String[][] data = Constants.UNIT_TEST_DATA_SET;

		for (int i = 0; i < data.length; i++) {

			final long id = Long.parseLong(data[i][0]);

			final Products product = commonMethods.getProduct(id, data[i][1], data[i][2], data[i][3]);

			when(productsRepository.findById(id)).thenReturn(product);

			final Products result = productsService.findById(Long.parseLong(data[i][0]));

			assertEquals(data[i][1], result.getName());
			assertEquals(data[i][2], result.getCurrent_price().getValue());
			assertEquals(data[i][3], result.getCurrent_price().getCurrency_code());

			verify(productsRepository, times(1)).findById(id);
		}

	}

}
