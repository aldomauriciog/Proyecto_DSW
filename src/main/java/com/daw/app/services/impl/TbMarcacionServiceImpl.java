/**
 * 
 */
package com.daw.app.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daw.app.dao.TbMarcacionDao;
import com.daw.app.entity.TbEmpleado;
import com.daw.app.entity.TbMarcacion;
import com.daw.app.entity.TbUsuario;
import com.daw.app.exception.CustomException;
import com.daw.app.services.TbMarcacionService;
import com.daw.app.services.TbUsuarioService;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Transactional
@Service
public class TbMarcacionServiceImpl implements TbMarcacionService{

	@Autowired
	TbMarcacionDao marcacionDao;
	
	@Autowired
	TbUsuarioService usuarioService;
	
	@Override
	public TbMarcacion save(TbMarcacion e) {
		return marcacionDao.save(e);
	}

	@Override
	public TbMarcacion update(TbMarcacion e) {
		return marcacionDao.save(e);
	}

	@Override
	public void delete(TbMarcacion e) {
		marcacionDao.delete(e);
	}

	@Override
	public TbMarcacion findById(Integer id) {
		Optional<TbMarcacion> marcacion = marcacionDao.findById(id);
		return marcacion.isPresent() ? marcacion.get() : null;
	}

	@Override
	public List<TbMarcacion> findAll() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TbUsuario usuario = usuarioService.findByUsername(user.getUsername());
		return marcacionDao.findByTbEmpleado(usuario.getTbEmpleado());
	}

	@Override
	public void registrar(TbMarcacion m) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TbUsuario usuario = usuarioService.findByUsername(user.getUsername());
		TbMarcacion marcacion = findByFechaAndTbEmpleado(LocalDate.now(), usuario.getTbEmpleado());
		if(marcacion == null) {
			marcacion = TbMarcacion.builder()
					.fecha(LocalDate.now())
					.horaIngreso(LocalTime.now())
					.tbEmpleado(usuario.getTbEmpleado())
					.build();
			if(usuario.getTbEmpleado().getTbTipoHorario().getHoraIngreso().isBefore(LocalTime.now())) {
				if(m.getObservacion() == null || m.getObservacion().isBlank())
					throw new CustomException("Ingrese la observacion de su tardanza");
				else {
					marcacion.setObservacion(m.getObservacion());
					marcacion.setCtrlTardanza("*");
				}
			}
		} else if(marcacion.getHoraIngreso() != null && marcacion.getHoraSalida() == null) {
			if(LocalTime.now().isBefore(usuario.getTbEmpleado().getTbTipoHorario().getHoraSalida())) {
				throw new CustomException("No puede marcar su salida antes de ".concat(usuario.getTbEmpleado().getTbTipoHorario().getHoraSalida().toString()));
			}
			marcacion.setHoraSalida(LocalTime.now());
		} else if(marcacion.getHoraIngreso() != null && marcacion.getHoraSalida() != null) {
			throw new CustomException("Ya marco su salida");
		}
		save(marcacion);
	}

	@Override
	public TbMarcacion findByFechaAndTbEmpleado(LocalDate fecha, TbEmpleado empleado) {
		return marcacionDao.findByFechaAndTbEmpleado(fecha, empleado);
	}

}
