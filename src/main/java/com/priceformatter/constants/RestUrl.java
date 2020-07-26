package com.priceformatter.constants;

public final class RestUrl {

	private RestUrl() {
	}

	public static final String REST_V1 = "/api/v1/";
	public static final String PRICE_REQUEST_URI = REST_V1+"price";
	public static final String PRICE_REQUEST_FORMAT_ZERO_URI = "/formatZero";
	public static final String PRICE_REQUEST_FORMAT_TRIM_ZERO_URI = "/formatTrimZero";

}
