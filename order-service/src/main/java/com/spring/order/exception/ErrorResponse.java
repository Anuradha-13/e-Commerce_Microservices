package com.spring.order.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private HttpStatus errorcode;

	private String errormessage;

	public ErrorResponse() {
		super();

	}

	public ErrorResponse(HttpStatus errorcode, String errormessage) {
		super();
		this.errorcode = errorcode;
		this.errormessage = errormessage;
	}

	public HttpStatus getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(HttpStatus errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorcode == null) ? 0 : errorcode.hashCode());
		result = prime * result + ((errormessage == null) ? 0 : errormessage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		if (errorcode != other.errorcode)
			return false;
		if (errormessage == null) {
			if (other.errormessage != null)
				return false;
		} else if (!errormessage.equals(other.errormessage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorResponse [errorcode=" + errorcode + ", errormessage=" + errormessage + "]";
	}

}
