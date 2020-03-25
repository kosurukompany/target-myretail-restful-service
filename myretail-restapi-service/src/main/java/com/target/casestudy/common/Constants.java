package com.target.casestudy.common;

/**
 * @author Satya Kosuru
 *
 */
public class Constants {

	public static final String APP_NAME = "My Retail Restfull Services";

	public static final String ERROR = "[API REQUEST ERROR]:";
	public static final String SUCCESS = "[API REQUEST SUCCESS]:";

	public static final String API_POST_REQUEST = "Rest API Post Request";
	public static final String API_PUT_REQUEST = "Rest API Put Request";

	public static final String ID = "id";
	public static final String PRODUCTS_URL = "/v1/myretail/products";
	public static final String PATH_VARIABLE_ID = "/{id}";
	public static final String EXTERNAL_GET_URL = "/external/{id}";
	public static final String POST_URL = "/add";
	public static final String APPLICATION_JSON = "application/json";

	public static final String EXTERNAL_GET_URL_PART_1 = "https://redsky.target.com/v2/pdp/tcin/";
	public static final String EXTERNAL_GET_URL_PART_2 = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

	public static final String SWAGGER_TITLE = "Target My Retail Rest API Case Study Documentation";
	public static final String SWAGGER_DESCRIPTION = "Here you will find all the detailed methods and details about all rest API calls for target myretail restapi service case study";
	public static final String SWAGGER_API_VERSION = "1.0";

	public static final String EXTERNAL_PRODUCT = "product";
	public static final String EXTERNAL_ITEM = "item";
	public static final String EXTERNAL_PRODUCT_DESCRIPTION = "product_description";
	public static final String EXTERNAL_TITLE = "title";

	public static final String SLASH = "/";

	public static final String RESOURCES = "resources";

	public static final String ASTERISK = "*";

	public static final String CODE = "Code";
	public static final String STATUS = "Status";
	public static final String MESSAGE = "Message";

	public static final String RESPONSE_CODE_200 = "200";
	public static final String RESPONSE_CODE_201 = "201";
	public static final String RESPONSE_CODE_400 = "400";
	public static final String RESPONSE_CODE_403 = "403";
	public static final String RESPONSE_CODE_404 = "404";

	public static final String PRODUCT_MESSAGE_EXISTS = "Product already exist with Id: ";
	public static final String PRODUCT_MESSAGE_CREATED = "Product Successfully created with Id: ";
	public static final String PRODUCT_MESSAGE_UPDATED = "Product Successfully updated with Id: ";
	public static final String PRODUCT_MESSAGE_DELETED = "Product Successfully deleted with Id: ";
	public static final String PRODUCT_MESSAGE_FOUND = "Requested Product found with Id: ";
	public static final String PRODUCT_MESSAGE_NOT_FOUND = "Product is not found with the requested Id: ";
	public static final String PRODUCT_MESSAGE_ID_MISMATCH_PART_1 = "Requested Product Id = ";
	public static final String PRODUCT_MESSAGE_ID_MISMATCH_PART_2 = ", is not match with Id in body data = ";

}