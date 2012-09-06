package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.math.BigDecimal;


/**
 * The persistent class for the tb_detalle_comprobante database table.
 * 
 */
@Entity
@Table(name="tb_detalle_comprobante", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetalleComprobanteSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleComprobanteSiePK id;

	private Integer cantproducto;

	private String descripcion;

	private BigDecimal preciounitario;

	//bi-directional many-to-one association to ComprobanteSie
    @ManyToOne
	@JoinColumn(name="idcomprobante" , insertable = false, updatable = false)
	private ComprobanteSie tbComprobante;

	//bi-directional many-to-one association to KardexSie
    @ManyToOne
	@JoinColumn(name="idkardex" , insertable = false, updatable = false)
	private KardexSie tbKardex;

    public DetalleComprobanteSie() {
    }

	public DetalleComprobanteSiePK getId() {
		return this.id;
	}

	public void setId(DetalleComprobanteSiePK id) {
		this.id = id;
	}
	
	public Integer getCantproducto() {
		return this.cantproducto;
	}

	public void setCantproducto(Integer cantproducto) {
		this.cantproducto = cantproducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}

	public ComprobanteSie getTbComprobante() {
		return this.tbComprobante;
	}

	public void setTbComprobante(ComprobanteSie tbComprobante) {
		this.tbComprobante = tbComprobante;
	}
	
	public KardexSie getTbKardex() {
		return this.tbKardex;
	}

	public void setTbKardex(KardexSie tbKardex) {
		this.tbKardex = tbKardex;
	}
	
}