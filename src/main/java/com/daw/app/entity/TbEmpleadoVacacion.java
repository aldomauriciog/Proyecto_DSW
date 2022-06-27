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
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_empleado_vaciones", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "id_vacacion", "id_empleado" }) })
public class TbEmpleadoVacacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vacacion")
	private Integer idVacaciones;
	@ManyToOne
	@JoinColumn(name = "id_empleado", nullable = false)
	private TbEmpleado tbEmpleado;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
}
