/**
 * 
 */
package com.daw.app.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
public abstract class GenericController<E, K> {

	public abstract GenericCrud<E, K> getCrud();
	
	@GetMapping("/findAll")
	public ResponseEntity<ApiResponse> findAll() {
		return ResponseEntity.ok(ApiResponse.ok(getCrud().findAll()));
	}

	@PostMapping(consumes ="application/json",produces = "application/json")
	public ResponseEntity<ApiResponse> insert(@RequestBody E e) {
		e = getCrud().save(e);
		return ResponseEntity.ok(ApiResponse.ok(e));
	}

	@PutMapping
	public ResponseEntity<ApiResponse> update(@RequestBody E e) {
		e = getCrud().update(e);
		return ResponseEntity.ok(ApiResponse.ok(e));
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse> delete(@RequestBody E e) {
		getCrud().delete(e);
		return ResponseEntity.ok(ApiResponse.ok(""));
	}

	protected abstract K getPK(E e);

}
