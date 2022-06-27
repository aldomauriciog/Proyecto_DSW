package com.daw.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "tb_cargos", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"nombre", "id_area"})
})
public class TbCargo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cargo")
	private Integer idCargo;
	@ManyToOne
	@JoinColumn(name = "id_area", nullable = false)
	private TbArea tbArea;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "sueldo", nullable = false)
	private Double sueldo;
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "tbCargo")
	private List<TbEmpleado> tbEmpleado = new ArrayList<>();
}
