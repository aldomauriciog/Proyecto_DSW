/**
 * 
 */
package com.daw.app.exception;

/**
 * @author Orlando Pasaca
 *
 * @since 3 jun. 2022
 */
public class CustomException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomException() {
		super();
	}
	public CustomException(String message) {
		super(message);
	}
}
