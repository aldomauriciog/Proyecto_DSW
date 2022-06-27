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
@Table(name = "tb_tipo_documentos")
public class TbTipoDocumento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_documento")
	private Integer idTipoDocumento;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "length")
	private Integer length;
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "tbTipoDocumento")
	private List<TbEmpleado> tbEmpleadoForIdTipoDocumentoEmpleado = new ArrayList<>();
}
