/**
 * 
 */
package com.daw.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daw.app.dao.TbTipoHorarioDao;
import com.daw.app.entity.TbTipoHorario;
import com.daw.app.services.TbTipoHorarioService;

/**
 * @author Orlando Pasaca
 *
 * @since 5 jun. 2022
 */
@Service
@Transactional
public class TbTipoHorarioServiceImpl implements TbTipoHorarioService {

	@Autowired
	TbTipoHorarioDao tipoHorarioDao;
	
	@Override
	public TbTipoHorario save(TbTipoHorario e) {
		return tipoHorarioDao.save(e);
	}

	@Override
	public TbTipoHorario update(TbTipoHorario e) {
		return tipoHorarioDao.save(e);
	}

	@Override
	public void delete(TbTipoHorario e) {
		tipoHorarioDao.delete(e);
	}

	@Override
	public TbTipoHorario findById(Integer id) {
		Optional<TbTipoHorario> tipoHorario = tipoHorarioDao.findById(id);
		return tipoHorario.isPresent() ? tipoHorario.get() : null;
	}

	@Override
	public List<TbTipoHorario> findAll() {
		return tipoHorarioDao.findAll();
	}

}
