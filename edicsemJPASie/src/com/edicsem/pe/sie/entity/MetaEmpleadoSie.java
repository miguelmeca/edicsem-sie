package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_meta_empresa database table.
 * 
 */
@Entity
@Table(name="tb_meta_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class MetaEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_META_EMPLEADO_IDMETAEMPLEADO_GENERATOR", sequenceName="SIE.TB_META_EMPLEADO_IDMETAEMPLEADO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_META_EMPLEADO_IDMETAEMPLEADO_GENERATOR")
	private Integer idmetaempleado;

	private String descripcion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	private Integer valormeta;
    
    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;
    
    //bi-directional many-to-one association to MetaMesSie
  	@OneToMany(mappedBy="tbMetaEmpleado")
  	private Set<MetaMesSie> tbMetameses;

    public MetaEmpleadoSie() {
    }
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getUsuariocreacion() {
		return this.usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return this.usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public Integer getValormeta() {
		return this.valormeta;
	}

	public void setValormeta(Integer valormeta) {
		this.valormeta = valormeta;
	}

	public Integer getIdmetaempleado() {
		return idmetaempleado;
	}


	public void setIdmetaempleado(Integer idmetaempleado) {
		this.idmetaempleado = idmetaempleado;
	}

	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public Set<MetaMesSie> getTbMetameses() {
		return tbMetameses;
	}

	public void setTbMetameses(Set<MetaMesSie> tbMetameses) {
		this.tbMetameses = tbMetameses;
	}
	
}