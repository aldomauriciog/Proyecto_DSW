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

import com.daw.app.dao.TbEmpleadoDao;
import com.daw.app.entity.TbEmpleado;
import com.daw.app.entity.TbTipoDocumento;
import com.daw.app.exception.CustomException;
import com.daw.app.services.TbEmpleadoService;
import com.daw.app.services.TbTipoDocumentoService;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Service
@Transactional
public class TbEmpleadoServiceImpl implements TbEmpleadoService {
	
	@Autowired
	TbEmpleadoDao empleadoDao;
	
	@Autowired
	TbTipoDocumentoService tipoDocumentoService;
	
	@Override
	public TbEmpleado save(TbEmpleado e) {
		validateEmpleado(e);
		return empleadoDao.save(e);
	}

	@Override
	public TbEmpleado update(TbEmpleado e) {
		validateEmpleado(e);
		if(findByNroDocumentoAndIdEmpleadoNot(e.getNroDocumento(), e.getIdEmpleado()) != null) {
			throw new CustomException("Ya existe un empleado con el numero de documento");
		}
		TbEmpleado em = findById(e.getIdEmpleado());
		e.setTbUsuario(em.getTbUsuario());
		return empleadoDao.save(e);
	}

	private void validateEmpleado(TbEmpleado e) {
		if(e.getNombres() == null || StringUtils.isBlank(e.getNombres()))
			throw new CustomException("El nombre es requerido");
		if(e.getApePaterno() == null || StringUtils.isBlank(e.getApePaterno()))
			throw new CustomException("El apellido paterno es requerido");
		if(e.getApeMaterno() == null || StringUtils.isBlank(e.getApeMaterno()))
			throw new CustomException("El apellido paterno es requerido");
		if(e.getSexo() == null)
			throw new CustomException("El sexo es requerido");
		if(e.getFechaNacimiento() == null)
			throw new CustomException("La fecha de nacimiento es requerida");
		if(e.getTbTipoDocumento() == null || e.getTbTipoDocumento().getIdTipoDocumento() == null)
			throw new CustomException("El tipo de documento es requerido");
		TbTipoDocumento tipoDocumento = tipoDocumentoService.findById(e.getTbTipoDocumento().getIdTipoDocumento());
		if(tipoDocumento == null)
			throw new CustomException("El tipo de documento no existe");
		if(e.getNroDocumento() == null)
			throw new CustomException("El numero de documento es requerido");
		if(e.getNroDocumento().length() != tipoDocumento.getLength())
			throw new CustomException("El numero de documento debe tener ".concat(tipoDocumento.getLength().toString()).concat(" digitos"));
		if(e.getEstadoCivil() == null)
			throw new CustomException("El estado civil es requerido");
		if(e.getEmail() == null || StringUtils.isBlank(e.getEmail()))
			throw new CustomException("El email es requerido");
		if(e.getCelular() == null || StringUtils.isBlank(e.getCelular()))
			throw new CustomException("El celular es requerido");
		if(e.getDireccion() == null || StringUtils.isBlank(e.getDireccion()))
			throw new CustomException("La direccion es requerido");
		if(e.getTbCargo() == null || e.getTbCargo().getIdCargo() == null)
			throw new CustomException("El cargo es requerido");
		if(e.getTbTipoHorario() == null || e.getTbTipoHorario().getIdTipoHorario() == null)
			throw new CustomException("El tipo de horario es requerido");
		if(e.getFechaIngreso() == null)
			throw new CustomException("La fecha de ingreso es requerido");
	}
	
	@Override
	public void delete(TbEmpleado e) {
		empleadoDao.delete(e);
	}

	@Override
	public TbEmpleado findById(Integer id) {
		Optional<TbEmpleado> empleado = empleadoDao.findById(id);
		return empleado.isPresent() ? empleado.get() : null;
	}

	@Override
	public List<TbEmpleado> findAll() {
		return empleadoDao.findAll();
	}

	@Override
	public List<TbEmpleado> findByTbUsuarioIsNull() {
		return empleadoDao.findByTbUsuarioIsNull();
	}

	@Override
	public TbEmpleado findByNroDocumentoAndIdEmpleadoNot(String nroDocumento, Integer idEmpleado) {
		return empleadoDao.findByNroDocumentoAndIdEmpleadoNot(nroDocumento, idEmpleado);
	}

}
