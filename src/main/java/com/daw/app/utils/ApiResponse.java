/**
 * 
 */
package com.daw.app.utils;

import lombok.Data;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Data
public class ApiResponse<E> {
	private Boolean error;
	private E data;
	
	public static ApiResponse ok(Object data) {
		return load(null, data);
	}
	
	public static ApiResponse<Object> load(Boolean error, Object data) {
		ApiResponse<Object> apiResponse = new ApiResponse<Object>();
		apiResponse.setData(data);
		apiResponse.setError(error == null ? false : error);
		return apiResponse;
	}
}
