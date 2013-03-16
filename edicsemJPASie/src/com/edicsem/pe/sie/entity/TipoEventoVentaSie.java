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
 * The persistent class for the tb_auditoria_usuario database table.
 * 
 */
@Entity
@Table(name="tb_tipo_evento_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoEventoVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_EVENTO_VENTA_IDTIPOEVENTO_GENERATOR", sequenceName="SIE.TB_TIPO_EVENTO_VENTA_IDTIPOEVENTO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_EVENTO_VENTA_IDTIPOEVENTO_GENERATOR")
	private Integer idtipoevento;
	
	private String descripcion;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to ComisionVentaSie
  	@OneToMany(mappedBy="tbTipoEventoVenta")
  	private Set<ComisionVentaSie> tbComisionVenta;
  	
  	//bi-directional many-to-one association to ContratoSie
  	@OneToMany(mappedBy="tbTipoEvento")
  	private Set<ContratoSie> tbContratos;
  	
  	 //bi-directional many-to-one association to GrupoVentaSie
  	@OneToMany(mappedBy="tbTipoEventoVenta")
  	private Set<GrupoVentaSie> tbGrupoVenta;
    
    public TipoEventoVentaSie() {
    }

	public Integer getIdtipoevento() {
		return idtipoevento;
	}

	public void setIdtipoevento(Integer idtipoevento) {
		this.idtipoevento = idtipoevento;
	}

	public String getDescripcion() {
		return descripcion;
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

	/**
	 * @return the tbComisionVenta
	 */
	public Set<ComisionVentaSie> getTbComisionVenta() {
		return tbComisionVenta;
	}

	/**
	 * @param tbComisionVenta the tbComisionVenta to set
	 */
	public void setTbComisionVenta(Set<ComisionVentaSie> tbComisionVenta) {
		this.tbComisionVenta = tbComisionVenta;
	}

	/**
	 * @return the tbContratos
	 */
	public Set<ContratoSie> getTbContratos() {
		return tbContratos;
	}

	/**
	 * @param tbContratos the tbContratos to set
	 */
	public void setTbContratos(Set<ContratoSie> tbContratos) {
		this.tbContratos = tbContratos;
	}

	public Set<GrupoVentaSie> getTbGrupoVenta() {
		return tbGrupoVenta;
	}

	public void setTbGrupoVenta(Set<GrupoVentaSie> tbGrupoVenta) {
		this.tbGrupoVenta = tbGrupoVenta;
	}

}