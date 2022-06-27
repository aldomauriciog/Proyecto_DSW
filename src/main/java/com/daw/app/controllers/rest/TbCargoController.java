/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbCargo;
import com.daw.app.services.TbCargoService;
import com.daw.app.utils.ApiResponse;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 4 jun. 2022
 */
@RestController
@RequestMapping("/api/tbcargos")
public class TbCargoController extends GenericController<TbCargo, Integer>{
	
	@Autowired
	TbCargoService cargoService;
	
	@Override
	public GenericCrud<TbCargo, Integer> getCrud() {
		return cargoService;
	}

	@Override
	protected Integer getPK(TbCargo e) {
		return e.getIdCargo();
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/findByIdArea/{idArea}")
	public ResponseEntity<ApiResponse> findByIdArea(@PathVariable Integer idArea) {
		return ResponseEntity.ok(ApiResponse.ok(cargoService.findByTbAreaIdArea(idArea)));
	}
}
