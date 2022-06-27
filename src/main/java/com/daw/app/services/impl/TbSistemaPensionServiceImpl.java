/**
 * 
 */
package com.daw.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daw.app.dao.TbSistemaPensionDao;
import com.daw.app.entity.TbSistemaPension;
import com.daw.app.services.TbSistemaPensionService;

/**
 * @author Orlando Pasaca
 *
 * @since 5 jun. 2022
 */
@Service
@Transactional
public class TbSistemaPensionServiceImpl implements TbSistemaPensionService {
	
	@Autowired
	TbSistemaPensionDao sistemaPensionDao;

	@Override
	public TbSistemaPension save(TbSistemaPension e) {
		return sistemaPensionDao.save(e);
	}

	@Override
	public TbSistemaPension update(TbSistemaPension e) {
		return sistemaPensionDao.save(e);
	}

	@Override
	public void delete(TbSistemaPension e) {
		sistemaPensionDao.delete(e);
	}

	@Override
	public TbSistemaPension findById(Integer id) {
		Optional<TbSistemaPension> sistemaPension = sistemaPensionDao.findById(id);
		return sistemaPension.isPresent() ? sistemaPension.get() : null;
	}

	@Override
	public List<TbSistemaPension> findAll() {
		return sistemaPensionDao.findAll();
	}

}
