package com.daw.app.entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_tipo_contratos")
public class TbTipoContrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_contrato")
	private Integer idTipoContrato;
	@Column(name = "nombre")
	private String nombre;
	
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "tbTipoContrato")
	List<TbContrato> tbContrato = new ArrayList<>();
}