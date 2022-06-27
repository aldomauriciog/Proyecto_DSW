/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbTipoDocumento;
import com.daw.app.services.TbTipoDocumentoService;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 5 jun. 2022
 */
@RestController
@RequestMapping("/api/tbtipodocumentos")
public class TbTipoDocumentoController extends GenericController<TbTipoDocumento, Integer> {

	@Autowired
	TbTipoDocumentoService tipoDocumentoService;
	
	@Override
	public GenericCrud<TbTipoDocumento, Integer> getCrud() {
		return tipoDocumentoService;
	}

	@Override
	protected Integer getPK(TbTipoDocumento e) {
		return e.getIdTipoDocumento();
	}

}
