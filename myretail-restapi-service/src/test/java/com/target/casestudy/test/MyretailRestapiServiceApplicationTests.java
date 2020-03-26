package com.target.casestudy.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.target.casestudy.common.Constants;
import com.target.casestudy.controller.ProductsController;

/**
 * @author Satya Kosuru
 *
 */

@SpringBootTest
class MyretailRestapiServiceApplicationTests {

	@Autowired
	private ProductsController productsController;

	@DisplayName(Constants.UNIT_TEST_CASE_NAME_CONTROLLERS)
	@Test
	public void contextLoads() throws Exception {

		assertNotNull(productsController);
	}

}
