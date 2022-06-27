/**
 * 
 */
package com.daw.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.app.entity.TbEmpleado;
import com.daw.app.entity.TbMarcacion;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
public interface TbMarcacionDao extends JpaRepository<TbMarcacion, Integer>{
	List<TbMarcacion> findByTbEmpleado(TbEmpleado empleado);
	TbMarcacion findByFechaAndTbEmpleado(LocalDate fecha, TbEmpleado empleado);
}
