/**
 * 
 */
package com.daw.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daw.app.dao.TbTipoDocumentoDao;
import com.daw.app.entity.TbTipoDocumento;
import com.daw.app.services.TbTipoDocumentoService;

/**
 * @author Orlando Pasaca
 *
 * @since 5 jun. 2022
 */
@Transactional
@Service
public class TbTipoDocumentoServiceImpl implements TbTipoDocumentoService {

	@Autowired
	TbTipoDocumentoDao tipoDocumentoDao;
	
	@Override
	public TbTipoDocumento save(TbTipoDocumento e) {
		return tipoDocumentoDao.save(e);
	}

	@Override
	public TbTipoDocumento update(TbTipoDocumento e) {
		return tipoDocumentoDao.save(e);
	}

	@Override
	public void delete(TbTipoDocumento e) {
		tipoDocumentoDao.delete(e);
	}

	@Override
	public TbTipoDocumento findById(Integer id) {
		Optional<TbTipoDocumento> tipoDocumento = tipoDocumentoDao.findById(id);
		return tipoDocumento.isPresent() ? tipoDocumento.get() : null;
	}

	@Override
	public List<TbTipoDocumento> findAll() {
		return tipoDocumentoDao.findAll();
	}
	
	
}
