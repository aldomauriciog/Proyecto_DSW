/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbTipoHorario;
import com.daw.app.services.TbTipoHorarioService;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 5 jun. 2022
 */
@RestController
@RequestMapping("/api/tbtipohorarios")
public class TbTipoHorarioController extends GenericController<TbTipoHorario, Integer> {

	@Autowired
	TbTipoHorarioService tipoHorarioService;
	
	@Override
	public GenericCrud<TbTipoHorario, Integer> getCrud() {
		return tipoHorarioService;
	}

	@Override
	protected Integer getPK(TbTipoHorario e) {
		return e.getIdTipoHorario();
	}

}
