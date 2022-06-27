package com.daw.app.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.daw.app.enumerados.EnumEstadoCivil;
import com.daw.app.enumerados.EnumSexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_empleados")
@JsonInclude(value = Include.NON_NULL)
public class TbEmpleado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private Integer idEmpleado;
	@ManyToOne
	@JoinColumn(name = "id_tipo_documento", nullable = false)
	private TbTipoDocumento tbTipoDocumento;
	@Column(name = "nro_documento", unique = true, nullable = false)
	private String nroDocumento;
	@Column(name = "ape_paterno", nullable = false)
	private String apePaterno;
	@Column(name = "ape_materno", nullable = false)
	private String apeMaterno;
	@Column(name = "nombres", nullable = false)
	private String nombres;
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDate fechaIngreso;
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", nullable = false)
	private EnumSexo sexo;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_civil", nullable = false)
	private EnumEstadoCivil estadoCivil;
	@Column(name = "hijos")
	private Integer hijos;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "celular", nullable = false)
	private String celular;
	@Column(name= "direccion", nullable = false)
	private String direccion;
	@ManyToOne
	@JoinColumn(name = "id_cargo", nullable = false)
	private TbCargo tbCargo;
	@ManyToOne
	@JoinColumn(name = "id_tipo_horario", nullable = false)
	private TbTipoHorario tbTipoHorario;
	@ManyToOne
	@JoinColumn(name = "id_sistema_pension", nullable = false)
	private TbSistemaPension tbSistemaPension;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToOne( mappedBy = "tbEmpleado", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private TbUsuario tbUsuario;
	
	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "tbEmpleado")
	private List<TbEmpleadoVacacion> empleadoVacacion = new ArrayList<>();
	
	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "tbEmpleado")
	private List<TbContrato> tbContrato = new ArrayList<>();
	
	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "tbEmpleado")
	private List<TbMarcacion> tbMarcacion = new ArrayList<>();
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TbEmpleado other = (TbEmpleado) obj;
        if (idEmpleado == null) {
            if (other.idEmpleado != null) {
                return false;
            }
        } else if (!idEmpleado.equals(other.idEmpleado)) {
            return false;
        }
        return true;
    }
}