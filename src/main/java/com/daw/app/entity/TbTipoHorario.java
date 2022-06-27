package com.daw.app.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tipo_horarios")
public class TbTipoHorario implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_horario")
	private Integer idTipoHorario;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "hora_ingreso")
	private LocalTime horaIngreso;
	@Column(name = "hora_salida")
	private LocalTime horaSalida;
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "tbTipoHorario")
	private List<TbEmpleado> tbEmpleados = new ArrayList<>();
}
