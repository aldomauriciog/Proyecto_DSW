/**
 * 
 */
package com.daw.app.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.daw.app.dao.TbUsuarioDao;
import com.daw.app.entity.TbEmpleado;
import com.daw.app.entity.TbUsuario;
import com.daw.app.exception.CustomException;
import com.daw.app.services.TbEmpleadoService;
import com.daw.app.services.TbUsuarioService;

/**
 * @author Orlando Pasaca
 *
 * @since 29 may. 2022
 */
@Transactional
@Service
public class TbUsuarioServiceImpl implements TbUsuarioService {

	@Autowired
	private TbUsuarioDao usuarioDao;
	@Autowired
	private TbEmpleadoService empleadoService;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	Cloudinary cloudinary;
	
	@Override
	public TbUsuario save(TbUsuario usuario) {
		if(findByUsername(usuario.getUsername())!=null)
			throw new CustomException("El usuario ya existe");
		usuario.setClave(bcryptEncoder.encode(usuario.getClave()));
		TbEmpleado e = empleadoService.findById(usuario.getIdUsuario());
		usuario.setIdUsuario(e.getIdEmpleado());
		usuario.setTbEmpleado(e);
		return usuarioDao.save(usuario);
	}

	@Override
	public TbUsuario update(TbUsuario usuario) {
		TbUsuario u = findById(usuario.getIdUsuario());
		u.setClave(bcryptEncoder.encode(usuario.getClave()));
		return usuarioDao.save(u);
	}

	@Override
	public void delete(TbUsuario usuario) {
		try {
			usuarioDao.delete(usuario);
			cloudinary.uploader().destroy(usuario.getIdUsuario().toString(), ObjectUtils.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public TbUsuario findById(Integer id) {
		Optional<TbUsuario> usuario = usuarioDao.findById(id);
		return usuario.isPresent() ? usuario.get() : null;
	}

	@Override
	public List<TbUsuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public TbUsuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public TbUsuario findByUsernameAndIdUsuarioNot(String username, Integer idUsuario) {
		return usuarioDao.findByUsernameAndIdUsuarioNot(username, idUsuario);
	}

	@Override
	public void insertCustom(MultipartFile file, TbUsuario usuario) {
		try {
			TbUsuario usuarioInsert = save(usuario);
			if(file != null) {
				File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+usuarioInsert.getIdUsuario());
			    file.transferTo(convFile);
			    Map<String, String> response =  cloudinary.uploader().upload(convFile, ObjectUtils.asMap("public_id",usuarioInsert.getIdUsuario().toString()));
			    usuarioInsert.setFoto(response.get("secure_url"));
			    usuarioDao.save(usuarioInsert);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public void updateCustom(MultipartFile file, TbUsuario usuario) {
		try {
			TbUsuario usuarioInsert = update(usuario);
			if(file != null) {
				File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+usuarioInsert.getIdUsuario());
			    file.transferTo(convFile);
			    Map<String, String> response =  cloudinary.uploader().upload(convFile, ObjectUtils.asMap("public_id",usuarioInsert.getIdUsuario().toString()));
			    usuarioInsert.setFoto(response.get("secure_url"));
			    update(usuarioInsert);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
	}

}
