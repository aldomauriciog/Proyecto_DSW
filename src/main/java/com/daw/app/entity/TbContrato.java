package com.daw.app.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_contrato")
public class TbContrato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contrato")
	private Integer idContrato;
	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private TbEmpleado tbEmpleado;
	@ManyToOne
	@JoinColumn(name = "id_tipo_contrato")
	private TbTipoContrato tbTipoContrato;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private TbEmpresa tbEmpresa;
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;	
}
