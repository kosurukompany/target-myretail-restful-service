package com.target.casestudy.common;

/**
 * @author Satya Kosuru
 *
 */
public class Constants {

	public static final String APP_NAME = "My Retail Restfull Services";

	public static final String ID = "id";
	public static final String PRODUCTS_URL = "/v1/myretail/products";
	public static final String GET_URL = "/{id}";
	public static final String EXTERNAL_GET_URL = "/external/{id}";
	public static final String POST_URL = "/add";
	public static final String APPLICATION_JSON = "application/json";

	public static final String EXTERNAL_GET_URL_PART_1 = "https://redsky.target.com/v2/pdp/tcin/";
	public static final String EXTERNAL_GET_URL_PART_2 = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

}