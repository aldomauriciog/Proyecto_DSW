/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daw.app.entity.TbUsuario;
import com.daw.app.services.TbUsuarioService;
import com.daw.app.utils.ApiResponse;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 29 may. 2022
 */
@CrossOrigin
@RestController
@RequestMapping("/api/tbusuarios")
public class TbUsuarioController extends GenericController<TbUsuario, Integer>{

	@Autowired
	TbUsuarioService usuarioService;
	
	@Override
	public GenericCrud<TbUsuario, Integer> getCrud() {
		return usuarioService;
	}

	@Override
	protected Integer getPK(TbUsuario e) {
		return e.getIdUsuario();
	}
	@PostMapping(path = "/insertCustom",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> insertCustom(@RequestPart(name = "file",required = false) MultipartFile file, @RequestPart TbUsuario usuario) {
		usuarioService.insertCustom(file, usuario);
		return ResponseEntity.ok(ApiResponse.ok("Registro insertado correctamente"));
	}
	@PutMapping(path = "/updateCustom",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> updateCustom(@RequestPart(name = "file",required = false) MultipartFile file, @RequestPart TbUsuario usuario) {
		usuarioService.updateCustom(file, usuario);
		return ResponseEntity.ok(ApiResponse.ok("Registro actualizado correctamente"));
	}
}
