/**
 * 
 */
package com.daw.app.services;

import java.util.List;

import com.daw.app.entity.TbArea;
import com.daw.app.entity.TbCargo;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 4 jun. 2022
 */
public interface TbCargoService extends GenericCrud<TbCargo, Integer> {
	TbCargo findByNombreAndTbArea(String nombre, TbArea area);
	TbCargo findByNombreAndTbAreaAndIdCargoNot(String nombre, TbArea area, Integer idCargo);
	List<TbCargo> findByTbAreaIdArea(Integer idArea);
}
