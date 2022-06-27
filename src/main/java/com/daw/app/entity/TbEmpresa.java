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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_empresas")
public class TbEmpresa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
	private Integer idEmpresa;
	@Column(name = "ruc")
	private String ruc;
	@Column(name = "razon_social")
	private String razonSocial;
	@Column(name = "direccion")
	private String direccion;
	
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "tbEmpresa")
	private List<TbContrato> tbContrato = new ArrayList<>();
}
