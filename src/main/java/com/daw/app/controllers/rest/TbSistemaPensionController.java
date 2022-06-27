/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbSistemaPension;
import com.daw.app.services.TbSistemaPensionService;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 5 jun. 2022
 */
@RestController
@RequestMapping("/api/tbsistemapensiones")
public class TbSistemaPensionController extends GenericController<TbSistemaPension, Integer> {

	@Autowired
	TbSistemaPensionService sistemaPensionService;
	
	@Override
	public GenericCrud<TbSistemaPension, Integer> getCrud() {
		return sistemaPensionService;
	}

	@Override
	protected Integer getPK(TbSistemaPension e) {
		return e.getIdSistemaPension();
	}

}
