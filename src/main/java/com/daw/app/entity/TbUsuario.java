package com.daw.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios")
public class TbUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "tbEmpleado"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id_usuario", unique = true, nullable = false)
	private Integer idUsuario;
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "id_usuario")
	private TbEmpleado tbEmpleado;
	@Column(name = "username")
	private String username;
	@Column(name = "clave")
	private String clave;
	@Column(name = "foto")
	private String foto;
	public void setTbEmpleado(TbEmpleado e) {
		this.tbEmpleado = e;
		this.idUsuario = e.getIdEmpleado();
	}
}
