package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_punto_venta database table.
 * 
 */
@Entity
@Table(name="tb_punto_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class PuntoVentaSie extends BaseMantenimientoForm  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PUNTO_VENTA_IDPUNTOVENTA_GENERATOR", sequenceName="SIE.TB_PUNTO_VENTA_IDPUNTOVENTA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PUNTO_VENTA_IDPUNTOVENTA_GENERATOR")
	private Integer idpuntoventa;

	private String almacen;

	private String descripcion;

	private String direccion;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to HorariosPvSie
	@OneToMany(mappedBy="tbPuntoVenta")
	private Set<HorariosPvSie> tbHorariosPvs;

	//bi-directional many-to-one association to KardexSie
	@OneToMany(mappedBy="tbPuntoVenta")
	private Set<KardexSie> tbKardexs;

	//bi-directional many-to-one association to UbigeoSie
    @ManyToOne
	@JoinColumn(name="idubigeo")
	private UbigeoSie tbUbigeo;

    public PuntoVentaSie() {
    }

	public Integer getIdpuntoventa() {
		return this.idpuntoventa;
	}

	public void setIdpuntoventa(Integer idpuntoventa) {
		this.idpuntoventa = idpuntoventa;
	}

	public String getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public Set<HorariosPvSie> getTbHorariosPvs() {
		return this.tbHorariosPvs;
	}

	public void setTbHorariosPvs(Set<HorariosPvSie> tbHorariosPvs) {
		this.tbHorariosPvs = tbHorariosPvs;
	}
	
	public Set<KardexSie> getTbKardexs() {
		return this.tbKardexs;
	}

	public void setTbKardexs(Set<KardexSie> tbKardexs) {
		this.tbKardexs = tbKardexs;
	}
	
	public UbigeoSie getTbUbigeo() {
		return this.tbUbigeo;
	}

	public void setTbUbigeo(UbigeoSie tbUbigeo) {
		this.tbUbigeo = tbUbigeo;
	}
	
}