package com.caps.jsp;

public class PasswordNotMatch extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "New Passwords not matching!!!";
	}

}
