package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the tb_producto database table.
 * 
 */
@Entity
@Table(name="tb_producto", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ProductoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PRODUCTO_IDPRODUCTO_GENERATOR", sequenceName="SIE.TB_PRODUCTO_IDPRODUCTO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PRODUCTO_IDPRODUCTO_GENERATOR")
	private Integer idproducto;

	private String codproducto;

	private String descripcionproducto;

	private String detalleproducto;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String rutaimagenproducto;

	private Integer stkmaximo;

	private Integer stkminimoproducto;

	private Integer unidproducto;

	private String usuariocreacion;

	private String usuariomodifica;

	private BigDecimal valorporcentaje;

	//bi-directional many-to-one association to DetPaqueteSie
	@OneToMany(mappedBy="tbProducto")
	private Set<DetPaqueteSie> tbDetPaquetes;

	//bi-directional many-to-one association to KardexSie
	@OneToMany(mappedBy="tbProducto")
	private Set<KardexSie> tbKardexs;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to TipoProductoSie
    @ManyToOne
	@JoinColumn(name="idtipoproducto")
	private TipoProductoSie tbTipoProducto;
    
    //bi-directional many-to-one association to DetProductoContratoSie
  	@OneToMany(mappedBy="tbProducto")
  	private Set<DetProductoContratoSie> tbDetProductoContrato;
  	
  	//bi-directional many-to-one association to ControlKardexSie
  	@OneToMany(mappedBy="tbProducto")
  	private Set<ControlKardexSie> tbControlKardex;
  	
	//bi-directional many-to-one association to VerificaProductoSie
  	@OneToMany(mappedBy="tbProducto")
  	private Set<VerificaProductoSie> tbVerificaProducto;
  	
  	@Transient 
  	private String observacionContrato;
  	
  	@Transient 
  	private int cantidadContrato;

    public ProductoSie() {
    }

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getCodproducto() {
		return this.codproducto;
	}

	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}

	public String getDescripcionproducto() {
		return this.descripcionproducto;
	}

	public void setDescripcionproducto(String descripcionproducto) {
		this.descripcionproducto = descripcionproducto;
	}

	public String getDetalleproducto() {
		return this.detalleproducto;
	}

	public void setDetalleproducto(String detalleproducto) {
		this.detalleproducto = detalleproducto;
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

	public String getRutaimagenproducto() {
		return this.rutaimagenproducto;
	}

	public void setRutaimagenproducto(String rutaimagenproducto) {
		this.rutaimagenproducto = rutaimagenproducto;
	}

	public Integer getStkmaximo() {
		return this.stkmaximo;
	}

	public void setStkmaximo(Integer stkmaximo) {
		this.stkmaximo = stkmaximo;
	}

	public Integer getStkminimoproducto() {
		return this.stkminimoproducto;
	}

	public void setStkminimoproducto(Integer stkminimoproducto) {
		this.stkminimoproducto = stkminimoproducto;
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

	public BigDecimal getValorporcentaje() {
		return this.valorporcentaje;
	}

	public void setValorporcentaje(BigDecimal valorporcentaje) {
		this.valorporcentaje = valorporcentaje;
	}
	
	public Set<DetPaqueteSie> getTbDetPaquetes() {
		return this.tbDetPaquetes;
	}

	public void setTbDetPaquetes(Set<DetPaqueteSie> tbDetPaquetes) {
		this.tbDetPaquetes = tbDetPaquetes;
	}
	
	public Set<KardexSie> getTbKardexs() {
		return this.tbKardexs;
	}

	public void setTbKardexs(Set<KardexSie> tbKardexs) {
		this.tbKardexs = tbKardexs;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public TipoProductoSie getTbTipoProducto() {
		return this.tbTipoProducto;
	}

	public void setTbTipoProducto(TipoProductoSie tbTipoProducto) {
		this.tbTipoProducto = tbTipoProducto;
	}

	public Set<DetProductoContratoSie> getTbDetProductoContrato() {
		return tbDetProductoContrato;
	}

	public void setTbDetProductoContrato(
			Set<DetProductoContratoSie> tbDetProductoContrato) {
		this.tbDetProductoContrato = tbDetProductoContrato;
	}

	/**
	 * @return the observacionContrato
	 */
	public String getObservacionContrato() {
		return observacionContrato;
	}

	/**
	 * @param observacionContrato the observacionContrato to set
	 */
	public void setObservacionContrato(String observacionContrato) {
		this.observacionContrato = observacionContrato;
	}

	/**
	 * @return the cantidadContrato
	 */
	public int getCantidadContrato() {
		return cantidadContrato;
	}

	/**
	 * @param cantidadContrato the cantidadContrato to set
	 */
	public void setCantidadContrato(int cantidadContrato) {
		this.cantidadContrato = cantidadContrato;
	}

	public Set<ControlKardexSie> getTbControlKardex() {
		return tbControlKardex;
	}

	public void setTbControlKardex(Set<ControlKardexSie> tbControlKardex) {
		this.tbControlKardex = tbControlKardex;
	}

	public Set<VerificaProductoSie> getTbVerificaProducto() {
		return tbVerificaProducto;
	}

	public void setTbVerificaProducto(Set<VerificaProductoSie> tbVerificaProducto) {
		this.tbVerificaProducto = tbVerificaProducto;
	}
	
}