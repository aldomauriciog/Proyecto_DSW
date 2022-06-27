/**
 * 
 */
package com.daw.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.app.entity.TbEmpleado;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
public interface TbEmpleadoDao extends JpaRepository<TbEmpleado, Integer> {
	List<TbEmpleado> findByTbUsuarioIsNull();
	TbEmpleado findByNroDocumentoAndIdEmpleadoNot(String nroDocumento, Integer idEmpleado);
}
