package com.daw.app.entity;

import java.io.Serializable;
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
@Table(name = "tb_sistema_pensiones")
public class TbSistemaPension implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sistema_pension")
	private Integer idSistemaPension;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "porcentaje_descuento")
	private Double porcentajeDescuento;

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "tbSistemaPension")
	private List<TbEmpleado> tbEmpleado = new ArrayList<>();
}
