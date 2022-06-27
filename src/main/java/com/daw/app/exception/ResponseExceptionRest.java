/**
 * 
 */
package com.daw.app.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.daw.app.utils.ApiResponse;

/**
 * @author Orlando Pasaca
 *
 * @since 3 jun. 2022
 */
@ControllerAdvice(annotations = RestController.class)
@RestController
public class ResponseExceptionRest extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex, WebRequest request) {
		if (ex instanceof CustomException) {
			CustomException ce = (CustomException) ex;
			return new ResponseEntity<>(ApiResponse.load(true, ce.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else if(ex instanceof DataIntegrityViolationException){
	        DataIntegrityViolationException di = (DataIntegrityViolationException) ex;
			return new ResponseEntity<>(ApiResponse.load(true, di.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	    } else {
			ex.printStackTrace();
			return new ResponseEntity<>(ApiResponse.load(true, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ApiResponse.load(true, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}
