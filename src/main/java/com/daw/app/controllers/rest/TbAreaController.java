/**
 * 
 */
package com.daw.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbArea;
import com.daw.app.services.TbAreaService;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@RestController
@RequestMapping("/api/tbareas")
public class TbAreaController extends GenericController<TbArea, Integer>{

	@Autowired
	TbAreaService areaService;
	
	@Override
	public GenericCrud<TbArea, Integer> getCrud() {
		return areaService;
	}

	@Override
	protected Integer getPK(TbArea e) {
		// TODO Auto-generated method stub
		return e.getIdArea();
	}

	
}
