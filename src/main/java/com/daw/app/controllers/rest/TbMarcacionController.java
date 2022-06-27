/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbMarcacion;
import com.daw.app.services.TbMarcacionService;
import com.daw.app.utils.ApiResponse;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@CrossOrigin
@RestController
@RequestMapping("/api/tbmarcaciones")
public class TbMarcacionController extends GenericController<TbMarcacion, Integer> {

	@Autowired
	TbMarcacionService marcacionService;
	
	@Override
	public GenericCrud<TbMarcacion, Integer> getCrud() {
		return marcacionService;
	}

	@Override
	protected Integer getPK(TbMarcacion e) {
		return e.getIdMarcacion();
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<ApiResponse> registrar(@RequestBody TbMarcacion marcacion) {
		marcacionService.registrar(marcacion);
		return ResponseEntity.ok(ApiResponse.ok("Registro satisfactorio"));
	}

}
