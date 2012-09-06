package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_det_paquete database table.
 * 
 */
@Entity
@Table(name="tb_det_paquete", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetPaqueteSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_PAQUETE_IDDETPAQUETE_GENERATOR", sequenceName="SIE.TB_DET_PAQUETE_IDDETPAQUETE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_PAQUETE_IDDETPAQUETE_GENERATOR")
	@Column(name="\"idDetPaquete\"")
	private Integer idDetPaquete;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private Integer unidproducto;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to PaqueteSie
    @ManyToOne
	@JoinColumn(name="idpaquete")
	private PaqueteSie tbPaquete;

	//bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idproducto")
	private ProductoSie tbProducto;

    public DetPaqueteSie() {
    }

	public Integer getIdDetPaquete() {
		return this.idDetPaquete;
	}

	public void setIdDetPaquete(Integer idDetPaquete) {
		this.idDetPaquete = idDetPaquete;
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

	public Integer getUnidproducto() {
		return this.unidproducto;
	}

	public void setUnidproducto(Integer unidproducto) {
		this.unidproducto = unidproducto;
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

	public PaqueteSie getTbPaquete() {
		return this.tbPaquete;
	}

	public void setTbPaquete(PaqueteSie tbPaquete) {
		this.tbPaquete = tbPaquete;
	}
	
	public ProductoSie getTbProducto() {
		return this.tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
	}
	
}