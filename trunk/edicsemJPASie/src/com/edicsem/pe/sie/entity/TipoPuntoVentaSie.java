package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_punto_venta database table.
 * 
 */
@Entity
@Table(name="tb_tipo_punto_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoPuntoVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_PUNTO_VENTA_IDTIPOPUNTOVENTA_GENERATOR", sequenceName="SIE.TB_TIPO_PUNTO_VENTA_IDTIPOPUNTOVENTA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_PUNTO_VENTA_IDTIPOPUNTOVENTA_GENERATOR")
	private Integer idtipopuntoventa;

	private String descripcion;
	
	//bi-directional many-to-one association to PuntoVentaSie
	@OneToMany(mappedBy="tbTipoPuntoVenta")
	private Set<PuntoVentaSie> tbPuntoVentas;

    public TipoPuntoVentaSie() {
    }

	public Integer getIdtipopuntoventa() {
		return this.idtipopuntoventa;
	}

	public void setIdtipopuntoventa(Integer idtipopuntoventa) {
		this.idtipopuntoventa = idtipopuntoventa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<PuntoVentaSie> getTbPuntoVentas() {
		return tbPuntoVentas;
	}

	public void setTbPuntoVentas(Set<PuntoVentaSie> tbPuntoVentas) {
		this.tbPuntoVentas = tbPuntoVentas;
	}

}