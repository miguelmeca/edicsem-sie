package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_kardex database table.
 * 
 */
@Entity
@Table(name="tb_kardex", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class KardexSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_KARDEX_IDKARDEX_GENERATOR", sequenceName="SIE.TB_KARDEX_IDKARDEX_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_KARDEX_IDKARDEX_GENERATOR")
	private Integer idkardex;

	private Integer cantentrada;

	private Integer cantexistencia;

	private Integer cantsalida;

	private String detallekardex;
 
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechamodifica;

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

	//bi-directional many-to-one association to EmpresaSie
    @ManyToOne
	@JoinColumn(name="idempresa")
	private EmpresaSie tbEmpresa;
    
    @Transient
    private String fecha;
    
	@Transient
	private String fechaCreacionString;
    
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

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Date fechamodifica) {
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

	public EmpresaSie getTbEmpresa() {
		return tbEmpresa;
	}

	public void setTbEmpresa(EmpresaSie tbEmpresa) {
		this.tbEmpresa = tbEmpresa;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		fecha = DateUtil.convertDateToString(fechacreacion)+" "+DateUtil.getTimeNow(fechacreacion);
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
	
		this.fecha = fecha;
	}

	public String getFechaCreacionString() {
		fechaCreacionString = DateUtil.formatoString(getFechacreacion(), "dd/MM/yyyy HH:mm");
		return fechaCreacionString;
	}

	public void setFechaCreacionString(String fechaCreacionString) {
		this.fechaCreacionString = fechaCreacionString;
	}
	
}