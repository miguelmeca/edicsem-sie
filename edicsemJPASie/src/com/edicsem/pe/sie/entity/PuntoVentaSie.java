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
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_punto_venta database table.
 * 
 */
@Entity
@Table(name="tb_punto_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class PuntoVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PUNTO_VENTA_IDPUNTOVENTA_GENERATOR", sequenceName="SIE.TB_PUNTO_VENTA_IDPUNTOVENTA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PUNTO_VENTA_IDPUNTOVENTA_GENERATOR")
	private Integer idpuntoventa;

	private String almacen;

	private String descripcion;

	private String direccion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to KardexSie
	@OneToMany(mappedBy="tbPuntoVenta")
	private Set<KardexSie> tbKardexs;

	//bi-directional many-to-one association to UbigeoSie
    @ManyToOne
	@JoinColumn(name="idubigeo")
	private UbigeoSie tbUbigeo;
    
    //bi-directional many-to-one association to FiltroHorarioVentaSie
  	@OneToMany(mappedBy="tbPuntoventa")
  	private Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas;

	//bi-directional many-to-one association to HorarioPuntoVentaSie
	@OneToMany(mappedBy="tbPuntoVenta")
	private Set<HorarioPuntoVentaSie> tbHorarioPuntoVentas;
  	
    @Transient
	private String descTipoAlmacen;

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

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public String getDescTipoAlmacen() {
		if(almacen.equals("A"))
			descTipoAlmacen="Almacén";
		else
			descTipoAlmacen="Punto de Venta";
		return descTipoAlmacen;
	}

	public void setDescTipoAlmacen(String descTipoAlmacen) {
		this.descTipoAlmacen = descTipoAlmacen;
	}

	public Set<FiltroHorarioVentaSie> getTbFiltroHorarioVentas() {
		return tbFiltroHorarioVentas;
	}

	public void setTbFiltroHorarioVentas(
			Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas) {
		this.tbFiltroHorarioVentas = tbFiltroHorarioVentas;
	}

	public Set<HorarioPuntoVentaSie> getTbHorarioPuntoVentas() {
		return tbHorarioPuntoVentas;
	}

	public void setTbHorarioPuntoVentas(
			Set<HorarioPuntoVentaSie> tbHorarioPuntoVentas) {
		this.tbHorarioPuntoVentas = tbHorarioPuntoVentas;
	}
	
}