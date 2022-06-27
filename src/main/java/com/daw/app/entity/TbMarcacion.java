package com.daw.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_marcacion")
public class TbMarcacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_marcacion")
	private Integer idMarcacion;
	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private TbEmpleado tbEmpleado;
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "hora_ingreso")
	private LocalTime horaIngreso;
	@Column(name = "hora_salida")
	private LocalTime horaSalida;
	@Column(name = "ctrl_tardanza", length = 1)
	private String ctrlTardanza;
	@Column(name = "observacion")
	private String observacion;
}
