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
 * The persistent class for the tb_parametro_sistema database table.
 * 
 */
@Entity
@Table(name="tb_parametro_sistema", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ParametroSistemaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PARAMETRO_SISTEMA_IDPARAMETROSISTEMA_GENERATOR", sequenceName="SIE.TB_PARAMETRO_SISTEMA_IDPARAMETROSISTEMA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PARAMETRO_SISTEMA_IDPARAMETROSISTEMA_GENERATOR")
	private Integer idparametrosistema;

	private String areasistema;

	private String descripcion;

	private String valor;
	
	private String usuarioCreacion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	private String usuariomodifica;
	
	private Timestamp fechamodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to HistorialParametroSistemaSie
  	@OneToMany(mappedBy="tbparametroSistemas")
  	private Set<HistorialParametroSistemaSie> tbHistorialParametro;

    public ParametroSistemaSie() {
    }

	public Integer getIdparametrosistema() {
		return this.idparametrosistema;
	}

	public void setIdparametrosistema(Integer idparametrosistema) {
		this.idparametrosistema = idparametrosistema;
	}

	public String getAreasistema() {
		return this.areasistema;
	}

	public void setAreasistema(String areasistema) {
		this.areasistema = areasistema;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Timestamp getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getUsuariomodifica() {
		return usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}
	
	public Set<HistorialParametroSistemaSie> getTbHistorialParametro() {
		return tbHistorialParametro;
	}
	
	public void setTbHistorialParametro(Set<HistorialParametroSistemaSie> tbHistorialParametro) {
		this.tbHistorialParametro = tbHistorialParametro;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	
}