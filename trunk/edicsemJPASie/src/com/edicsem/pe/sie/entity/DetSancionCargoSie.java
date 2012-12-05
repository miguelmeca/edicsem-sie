package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_det_sancion_cargo database table.
 * 
 */
@Entity
@Table(name="tb_det_sancion_cargo", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetSancionCargoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_SANCION_CARGO_IDDETSANCIONCARGO_GENERATOR", sequenceName="SIE.TB_DET_SANCION_CARGO_IDDETSANCIONCARGO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_SANCION_CARGO_IDDETSANCIONCARGO_GENERATOR")
	private Integer iddetsancioncargo;
	
	private BigDecimal descuento;
	
	private Integer cantdiaSuspension;
	
	//bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private CargoEmpleadoSie tbCargoempleado;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to SancionSie
    @ManyToOne
	@JoinColumn(name="idsancion")
	private SancionSie tbSancion;
    
  //bi-directional many-to-one association to DetSancionEmpleadoSie
  	@OneToMany(mappedBy="tbEmpleado")
  	private Set<DetSancionEmpleadoSie> tbDetSancionEmpleado;
  	
  	@Transient
  	private String isnew;
  	
    public Set<DetSancionEmpleadoSie> getTbDetSancionEmpleado() {
		return tbDetSancionEmpleado;
	}

	public void setTbDetSancionEmpleado(
			Set<DetSancionEmpleadoSie> tbDetSancionEmpleado) {
		this.tbDetSancionEmpleado = tbDetSancionEmpleado;
	}

	public DetSancionCargoSie() {
    }

	public Integer getIddetsancioncargo() {
		return this.iddetsancioncargo;
	}

	public void setIddetsancioncargo(Integer iddetsancioncargo) {
		this.iddetsancioncargo = iddetsancioncargo;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public CargoEmpleadoSie getTbCargoempleado() {
		return tbCargoempleado;
	}

	public void setTbCargoempleado(CargoEmpleadoSie tbCargoempleado) {
		this.tbCargoempleado = tbCargoempleado;
	}

	public SancionSie getTbSancion() {
		return tbSancion;
	}

	public void setTbSancion(SancionSie tbSancion) {
		this.tbSancion = tbSancion;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public Integer getCantdiaSuspension() {
		return cantdiaSuspension;
	}

	public void setCantdiaSuspension(Integer cantdiaSuspension) {
		this.cantdiaSuspension = cantdiaSuspension;
	}

	/**
	 * @return the isnew
	 */
	public String getIsnew() {
		return isnew;
	}

	/**
	 * @param isnew the isnew to set
	 */
	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}

}