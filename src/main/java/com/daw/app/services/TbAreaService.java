/**
 * 
 */
package com.daw.app.services;

import com.daw.app.entity.TbArea;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
public interface TbAreaService extends GenericCrud<TbArea, Integer> {
	TbArea findByNombre(String nombre);
	TbArea findByNombreAndIdAreaNot(String nombre, Integer idArea);
}
