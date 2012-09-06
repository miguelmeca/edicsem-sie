package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_kardex database table.
 * 
 */
@Entity
@Table(name="tb_kardex")
public class KardexSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_KARDEX_IDKARDEX_GENERATOR", sequenceName="TB_KARDEX_IDKARDEX_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_KARDEX_IDKARDEX_GENERATOR")
	private Integer idkardex;

	private Integer cantentrada;

	private Integer cantexistencia;

	private Integer cantsalida;

	private String detallekardex;

    @Temporal( TemporalType.DATE)
	private Date fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechamodifica;

	private String tipokardex;

	private String usuariocreacion;

	private String usuariomodifica;

	private String valortotal;

	private String valorunitarioentrada;

	private String valorunitarioexistencia;

	private String valorunitariosalida;

	//bi-directional many-to-one association to DetalleComprobanteSie
	@OneToMany(mappedBy="tbKardex")
	private Set<DetalleComprobanteSie> tbDetalleComprobantes;

	//bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idproducto")
	private ProductoSie tbProducto;

	//bi-directional many-to-one association to PuntoVentaSie
    @ManyToOne
	@JoinColumn(name="idalmacenpuntosventa")
	private PuntoVentaSie tbPuntoVenta;

	//bi-directional many-to-one association to TipoKardexProductoSie
    @ManyToOne
	@JoinColumn(name="idtipokardexproducto")
	private TipoKardexProductoSie tbTipoKardexProducto;

    public KardexSie() {
    }

	public Integer getIdkardex() {
		return this.idkardex;
	}

	public void setIdkardex(Integer idkardex) {
		this.idkardex = idkardex;
	}

	public Integer getCantentrada() {
		return this.cantentrada;
	}

	public void setCantentrada(Integer cantentrada) {
		this.cantentrada = cantentrada;
	}

	public Integer getCantexistencia() {
		return this.cantexistencia;
	}

	public void setCantexistencia(Integer cantexistencia) {
		this.cantexistencia = cantexistencia;
	}

	public Integer getCantsalida() {
		return this.cantsalida;
	}

	public void setCantsalida(Integer cantsalida) {
		this.cantsalida = cantsalida;
	}

	public String getDetallekardex() {
		return this.detallekardex;
	}

	public void setDetallekardex(String detallekardex) {
		this.detallekardex = detallekardex;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Date fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getTipokardex() {
		return this.tipokardex;
	}

	public void setTipokardex(String tipokardex) {
		this.tipokardex = tipokardex;
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

	public String getValortotal() {
		return this.valortotal;
	}

	public void setValortotal(String valortotal) {
		this.valortotal = valortotal;
	}

	public String getValorunitarioentrada() {
		return this.valorunitarioentrada;
	}

	public void setValorunitarioentrada(String valorunitarioentrada) {
		this.valorunitarioentrada = valorunitarioentrada;
	}

	public String getValorunitarioexistencia() {
		return this.valorunitarioexistencia;
	}

	public void setValorunitarioexistencia(String valorunitarioexistencia) {
		this.valorunitarioexistencia = valorunitarioexistencia;
	}

	public String getValorunitariosalida() {
		return this.valorunitariosalida;
	}

	public void setValorunitariosalida(String valorunitariosalida) {
		this.valorunitariosalida = valorunitariosalida;
	}

	public Set<DetalleComprobanteSie> getTbDetalleComprobantes() {
		return this.tbDetalleComprobantes;
	}

	public void setTbDetalleComprobantes(Set<DetalleComprobanteSie> tbDetalleComprobantes) {
		this.tbDetalleComprobantes = tbDetalleComprobantes;
	}
	
	public ProductoSie getTbProducto() {
		return this.tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
	}
	
	public PuntoVentaSie getTbPuntoVenta() {
		return this.tbPuntoVenta;
	}

	public void setTbPuntoVenta(PuntoVentaSie tbPuntoVenta) {
		this.tbPuntoVenta = tbPuntoVenta;
	}
	
	public TipoKardexProductoSie getTbTipoKardexProducto() {
		return this.tbTipoKardexProducto;
	}

	public void setTbTipoKardexProducto(TipoKardexProductoSie tbTipoKardexProducto) {
		this.tbTipoKardexProducto = tbTipoKardexProducto;
	}
	
}