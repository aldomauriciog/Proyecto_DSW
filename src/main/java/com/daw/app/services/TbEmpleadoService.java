/**
 * 
 */
package com.daw.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.app.entity.TbEmpleado;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@Service
public interface TbEmpleadoService extends GenericCrud<TbEmpleado, Integer> {
	List<TbEmpleado> findByTbUsuarioIsNull();
	TbEmpleado findByNroDocumentoAndIdEmpleadoNot(String nroDocumento, Integer idEmpleado);
}
