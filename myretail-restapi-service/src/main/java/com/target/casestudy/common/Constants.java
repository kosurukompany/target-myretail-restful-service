package com.target.casestudy.common;

/**
 * @author Satya Kosuru
 *
 */
public class Constants {

	public static final String APP_NAME = "My Retail Restfull Services";

	public static final String AUTHOR_NAME = "Satya Kumar Kosuru";
	public static final String AUTHOR_EMAIL = "kosurusatya@gmail.com";
	public static final String AUTHOR_WEBSITE = "";
	public static final String MIT = "MIT";
	public static final String LICENSE_URL = "https://github.com/kosurukompany/target-myretail-restful-service/blob/master/LICENSE";

	public static final String LOCALHOST = "http://localhost:";

	public static final String ERROR = "[API REQUEST ERROR]:";
	public static final String SUCCESS = "[API REQUEST SUCCESS]:";

	public static final String REST_API = "Rest Api";
	public static final String API_POST_REQUEST = "Rest API Post Request";
	public static final String API_PUT_REQUEST = "Rest API Put Request";

	public static final String ID = "id";
	public static final String PRODUCTS_URL = "/v1/myretail/products";
	public static final String PATH_VARIABLE_ID = "/{id}";
	public static final String EXTERNAL_GET_URL = "/external/{id}";
	public static final String EXTERNAL_GET_URL_FOR_TEST = "/external/";
	public static final String ADD_URL = "/add";
	public static final String APPLICATION_JSON = "application/json";

	public static final String EXTERNAL_GET_URL_PART_1 = "https://redsky.target.com/v2/pdp/tcin/";
	public static final String EXTERNAL_GET_URL_PART_2 = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

	public static final String SWAGGER_TITLE = "Target My Retail Rest API Case Study Documentation";
	public static final String SWAGGER_DESCRIPTION = "Here you will find all the detailed methods and details about all rest API calls for target myretail restapi service case study";
	public static final String SWAGGER_API_VERSION = "1.0";
	public static final String SWAGGER_PARAM_DES_CONDITION = "If condition = nameonly then the response will contain product name only. If left balnk or given anything else it will return the combiled product from extrenal api data(id and name) and data from database(current_price)";
	public static final String SWAGGER_PARAM_DES_PRODUCT = "Valid Product data in JSON format is required";
	public static final String SWAGGER_PARAM_DES_ID = "Valid Product Id is required. Example product IDs: 13860428, 15117729, 16483589, 16696652, 16752456, 15643793 ";

	public static final String EXTERNAL_PRODUCT = "product";
	public static final String EXTERNAL_TCIN = "tcin";
	public static final String EXTERNAL_ITEM = "item";
	public static final String EXTERNAL_PRODUCT_DESCRIPTION = "product_description";
	public static final String EXTERNAL_TITLE = "title";
	public static final String EXTERNAL_API_CONDITION = "condition";
	public static final String EXTERNAL_API_CONDITION_OPTION = "nameonly";

	public static final String SLASH = "/";
	public static final String QUESTION_MARK = "?";
	public static final String EQUAL = "=";
	public static final String SPACE = " ";

	public static final String RESOURCES = "resources";

	public static final String ASTERISK = "*";

	public static final String TIMESTAMP = "timestamp";
	public static final String CODE = "Code";
	public static final String STATUS = "Status";
	public static final String MESSAGE = "Message";

	public static final int RESPONSE_CODE_200 = 200;
	public static final int RESPONSE_CODE_201 = 201;
	public static final int RESPONSE_CODE_204 = 204;
	public static final int RESPONSE_CODE_400 = 400;
	public static final int RESPONSE_CODE_401 = 401;
	public static final int RESPONSE_CODE_403 = 403;
	public static final int RESPONSE_CODE_404 = 404;

	public static final String RESPONSE_CODE_200_DES = "Request Is Successfull";
	public static final String RESPONSE_CODE_201_DES = "Product Craeted Successfully";
	public static final String RESPONSE_CODE_204_DES = "No Content Available";
	public static final String RESPONSE_CODE_400_DES = "Product Id's Mis-Match. Bad Request";
	public static final String RESPONSE_CODE_401_DES = "User Not Authorized To Make The Request";
	public static final String RESPONSE_CODE_403_DES = "Product Is Already Exists. Buplicates Forbidden";
	public static final String RESPONSE_CODE_404_DES = "Requested Product Not Found";

	public static final String PRODUCT_MESSAGE_SIZE = "Products list returned with size: ";
	public static final String PRODUCT_MESSAGE_EXISTS = "Product already exist and duplicates are not allowed";
	public static final String PRODUCT_MESSAGE_CREATED = "Product is Successfully Created";
	public static final String PRODUCT_MESSAGE_UPDATED = "Product Successfully updated with Id: ";
	public static final String PRODUCT_MESSAGE_DELETED = "Product Successfully deleted with Id: ";
	public static final String PRODUCT_MESSAGE_FOUND = "Requested Product found with Id: ";
	public static final String PRODUCT_MESSAGE_NOT_FOUND = "Product is not found with the requested Id: ";
	public static final String PRODUCT_MESSAGE_NO_PRODUCTS = "There are no products in the system";
	public static final String PRODUCT_MESSAGE_ID_MISMATCH_PART_1 = "Requested Product Id = ";
	public static final String PRODUCT_MESSAGE_ID_MISMATCH_PART_2 = ", is not match with Id in body data = ";

	public static final String[][] UNIT_TEST_DATA_SET = {
			{ "16483600", "The Big Lebowski (Blu-ray) (Widescreen)", "13.49", "USD" },
			{ "15117729", "LG", "30000", "INR" }, { "16483589", "Samsung", "50.99", "USD" } };

	public static final String[][] UNIT_TEST_DATA_SET_2 = { { "16483600", "Sony 123", "130.49", "USD" },
			{ "15117729", "LG 123", "300000", "INR" }, { "16483589", "Samsung 1230", "50.99", "USD" } };

	public static final String UNIT_TEST_CASE_NAME_CONTEXT_LOAD = "Test for Context Load";
	public static final String UNIT_TEST_CASE_NAME_SAVE_PRODUCT = "Test for Save Product";
	public static final String UNIT_TEST_CASE_NAME_UPDATE_PRODUCT = "Test for Update Product";
	public static final String UNIT_TEST_CASE_NAME_DELETE_PRODUCT = "Test for Delete Product";
	public static final String UNIT_TEST_CASE_NAME_GET_ALL_PRODUCTS = "Test for Get All Products";
	public static final String UNIT_TEST_CASE_NAME_GET_PRODUCT_BY_ID = "Test for Get Product by ID";
	public static final String UNIT_TEST_CASE_NAME_GET_EXTERNAL_PRODUCT_BY_ID = "Test for Get Extrenal Product By ID";

}