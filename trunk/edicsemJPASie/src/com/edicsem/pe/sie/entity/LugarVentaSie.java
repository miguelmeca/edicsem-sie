package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

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
 * The persistent class for the tb_lugar_venta database table.
 * 
 */
@Entity
@Table(name="tb_lugar_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class LugarVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_LUGAR_VENTA_IDLUGAR_GENERATOR", sequenceName="SIE.TB_LUGAR_VENTA_IDLUGAR_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_LUGAR_VENTA_IDLUGAR_GENERATOR")
	private Integer idlugar;

	private String descripcion;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to ContratoSie
  	@OneToMany(mappedBy="tbLugarVenta")
  	private Set<ContratoSie> tbContratos;

    public LugarVentaSie() {
    }

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public Integer getIdlugar() {
		return idlugar;
	}
	
	public void setIdlugar(Integer idlugar) {
		this.idlugar = idlugar;
	}

	public Set<ContratoSie> getTbContratos() {
		return tbContratos;
	}

	public void setTbContratos(Set<ContratoSie> tbContratos) {
		this.tbContratos = tbContratos;
	}
	
}