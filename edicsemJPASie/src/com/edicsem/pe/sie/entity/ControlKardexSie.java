package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_Control_Kardex database table.
 * 
 */
@Entity
@Table(name="tb_Control_Kardex", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ControlKardexSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CONTROL_KARDEX_IDCONTROLKARDEX_GENERATOR", sequenceName="SIE.TB_CONTROL_KARDEX_IDCONTROLKARDEX_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONTROL_KARDEX_IDCONTROLKARDEX_GENERATOR")
	private Integer idControlKardex;
	
	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="encargado")
	private EmpleadoSie tbEmpleado;
    
    private Integer cantidad;
    
    private Integer cantidaddeberia;
    
    //bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idProducto")
	private ProductoSie tbProducto;
    
    //bi-directional many-to-one association to PuntoVentaSie
    @ManyToOne
	@JoinColumn(name="idalmacenpuntosventa")
	private PuntoVentaSie tbPuntoVenta;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	private Timestamp fechamodifica;
	
	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
	
    @Transient
    private int item;
    
    public ControlKardexSie() {
    }

	public Integer getIdControlKardex() {
		return idControlKardex;
	}

	public void setIdControlKardex(Integer idControlKardex) {
		this.idControlKardex = idControlKardex;
	}

	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoSie getTbProducto() {
		return tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
	}

	public PuntoVentaSie getTbPuntoVenta() {
		return tbPuntoVenta;
	}

	public void setTbPuntoVenta(PuntoVentaSie tbPuntoVenta) {
		this.tbPuntoVenta = tbPuntoVenta;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	/**
	 * @return the cantidaddeberia
	 */
	public Integer getCantidaddeberia() {
		return cantidaddeberia;
	}

	/**
	 * @param cantidaddeberia the cantidaddeberia to set
	 */
	public void setCantidaddeberia(Integer cantidaddeberia) {
		this.cantidaddeberia = cantidaddeberia;
	}

	/**
	 * @return the item
	 */
	public int getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(int item) {
		this.item = item;
	}
    
}