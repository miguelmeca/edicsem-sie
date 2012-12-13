package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_promocion database table.
 * 
 */
@Entity
@Table(name="tb_promocion")
public class PromocionSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PROMOCION_IDPROMOCION_GENERATOR", sequenceName="TB_PROMOCION_IDPROMOCION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PROMOCION_IDPROMOCION_GENERATOR")
	private Integer idpromocion;

	private String descripcion;

	private Integer idestadogeneral;

	private Integer porcentaje;

    public PromocionSie() {
    }

	public Integer getIdpromocion() {
		return this.idpromocion;
	}

	public void setIdpromocion(Integer idpromocion) {
		this.idpromocion = idpromocion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdestadogeneral() {
		return this.idestadogeneral;
	}

	public void setIdestadogeneral(Integer idestadogeneral) {
		this.idestadogeneral = idestadogeneral;
	}

	public Integer getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

}