package com.ajaxstudy.contact.domain;

public class ErrorMessage {
	private String errormessage;

	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorMessage(String errormessage) {
		super();
		this.errormessage = errormessage;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	@Override
	public String toString() {
		return "ErrorMessage [errormessage=" + errormessage + "]";
	}
}
