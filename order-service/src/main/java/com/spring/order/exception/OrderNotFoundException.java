package com.spring.order.exception;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(String message, Throwable ex) {
		super(message, ex);
	}
}
