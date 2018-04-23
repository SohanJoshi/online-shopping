package com.shj.onlineshopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable {

	/**
	 * UID for Serialization
	 */
	private static final long serialVersionUID = 606262453956663106L;

	public ProductNotFoundException() {
		this("Product is not available");
	}
	
	public ProductNotFoundException(String message) {
		super(System.currentTimeMillis() + ": " + message);
	}
	
}
