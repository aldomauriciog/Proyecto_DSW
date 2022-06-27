/**
 * 
 */
package com.daw.app.services;

import org.springframework.web.multipart.MultipartFile;

import com.daw.app.entity.TbUsuario;
import com.daw.app.utils.GenericCrud;

/**
 * @author Orlando Pasaca
 *
 * @since 29 may. 2022
 */
public interface TbUsuarioService extends GenericCrud<TbUsuario, Integer>{
	TbUsuario findByUsername(String username);
	TbUsuario findByUsernameAndIdUsuarioNot(String username, Integer idUsuario);
	void insertCustom(MultipartFile file, TbUsuario usuario);
	void updateCustom(MultipartFile file, TbUsuario usuario);
}
