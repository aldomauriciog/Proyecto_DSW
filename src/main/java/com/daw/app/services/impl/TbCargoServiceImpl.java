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

import com.daw.app.dao.TbCargoDao;
import com.daw.app.entity.TbArea;
import com.daw.app.entity.TbCargo;
import com.daw.app.exception.CustomException;
import com.daw.app.services.TbCargoService;

/**
 * @author Orlando Pasaca
 *
 * @since 4 jun. 2022
 */
@Service
@Transactional
public class TbCargoServiceImpl implements TbCargoService {

	@Autowired
	TbCargoDao cargoDao;

	@Override
	public TbCargo save(TbCargo e) {
		this.validateCargo(e);
		if(findByNombreAndTbArea(e.getNombre(), e.getTbArea()) != null)
			throw new CustomException("El nombre de cargo ya existe en el area seleccionada");
		return cargoDao.save(e);
	}

	@Override
	public TbCargo update(TbCargo e) {
		this.validateCargo(e);
		if(e.getIdCargo() == null)
			throw new CustomException("El id cargo es requerido");
		if(findByNombreAndTbAreaAndIdCargoNot(e.getNombre(), e.getTbArea(), e.getIdCargo()) != null)
			throw new CustomException("El nombre de cargo ya existe en el area seleccionada");
		return cargoDao.save(e);
	}

	private void validateCargo(TbCargo e) {
		if (e.getNombre() == null || StringUtils.isBlank(e.getNombre()))
			throw new CustomException("El nombre de cargo es requerido");
		if (e.getSueldo() == null)
			throw new CustomException("El sueldo es requerido");
		if (e.getTbArea() == null || e.getTbArea().getIdArea() == null)
			throw new CustomException("El area es requerida");
	}

	@Override
	public void delete(TbCargo e) {
		cargoDao.delete(e);
	}

	@Override
	public TbCargo findById(Integer id) {
		Optional<TbCargo> cargo = cargoDao.findById(id);
		return cargo.isPresent() ? cargo.get() : null;
	}

	@Override
	public List<TbCargo> findAll() {
		return cargoDao.findAll();
	}

	@Override
	public TbCargo findByNombreAndTbArea(String nombre, TbArea area) {
		return cargoDao.findByNombreAndTbArea(nombre, area);
	}

	@Override
	public TbCargo findByNombreAndTbAreaAndIdCargoNot(String nombre, TbArea area, Integer idCargo) {
		return cargoDao.findByNombreAndTbAreaAndIdCargoNot(nombre, area, idCargo);
	}

	@Override
	public List<TbCargo> findByTbAreaIdArea(Integer idArea) {
		return cargoDao.findByTbAreaIdArea(idArea);
	}
}
