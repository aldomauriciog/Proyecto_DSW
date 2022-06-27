/**
 * 
 */
package com.daw.app.controllers.rest;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.app.entity.TbEmpleado;
import com.daw.app.services.TbEmpleadoService;
import com.daw.app.utils.ApiResponse;
import com.daw.app.utils.GenericController;
import com.daw.app.utils.GenericCrud;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author Orlando Pasaca
 *
 * @since 30 may. 2022
 */
@CrossOrigin
@RestController
@RequestMapping("/api/tbempleados")
public class TbEmpleadoController extends GenericController<TbEmpleado, Integer> {

	@Autowired
	TbEmpleadoService empleadoService;

	@Autowired
	private DataSource dataSource;

	@Override
	public GenericCrud<TbEmpleado, Integer> getCrud() {
		return empleadoService;
	}

	@Override
	protected Integer getPK(TbEmpleado e) {
		return e.getIdEmpleado();
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/findByTbUsuarioIsNull")
	public ResponseEntity<ApiResponse> findByTbUsuarioIsNull() {
		return ResponseEntity.ok(ApiResponse.ok(empleadoService.findByTbUsuarioIsNull()));
	}

	@GetMapping("/boleta/{idEmpleado}")
	public void demoReport1(@PathVariable("idEmpleado") final Integer reportName,
			@RequestParam(required = false) Map<String, Object> parameters, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		
		parameters = parameters == null ? new HashMap<>() : parameters;
		parameters.put("nrodoc", reportName);
		ClassPathResource resource = new ClassPathResource("reportes" + File.separator + "boleta" + ".jasper");
		InputStream jasperStream = resource.getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline;");
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
