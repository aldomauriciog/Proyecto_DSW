/**
 * 
 */
package com.daw.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daw.app.dao.TbAreaDao;
import com.daw.app.entity.TbArea;
import com.daw.app.exception.CustomException;
import com.daw.app.services.TbAreaService;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Transactional
@Service
public class TbAreaServiceImpl implements TbAreaService{

	@Autowired
	TbAreaDao areaDao;
	
	@Override
	public TbArea save(TbArea e) {
		if(e.getNombre() == null || StringUtils.isBlank(e.getNombre()))
			throw new CustomException("El nombre es obligatorio");
		if(findByNombre(e.getNombre())!=null) 
			throw new CustomException("El area ya existe");
		return areaDao.save(e);
	}

	@Override
	public TbArea update(TbArea e) {
		if(e.getNombre() == null || StringUtils.isBlank(e.getNombre()))
			throw new CustomException("El nombre es obligatorio");
		if(findByNombreAndIdAreaNot(e.getNombre(), e.getIdArea())!=null)
			throw new CustomException("El area ya existe");
		return areaDao.save(e);
	}

	@Override
	public void delete(TbArea e) {
		areaDao.delete(e);
	}

	@Override
	public TbArea findById(Integer id) {
		Optional<TbArea> area = areaDao.findById(id);
		return area.isPresent() ? area.get() : null;
	}

	@Override
	public List<TbArea> findAll() {
		return areaDao.findAll();
	}

	@Override
	public TbArea findByNombre(String nombre) {
		return areaDao.findByNombre(nombre);
	}

	@Override
	public TbArea findByNombreAndIdAreaNot(String nombre, Integer idArea) {
		return areaDao.findByNombreAndIdAreaNot(nombre, idArea);
	}

}
