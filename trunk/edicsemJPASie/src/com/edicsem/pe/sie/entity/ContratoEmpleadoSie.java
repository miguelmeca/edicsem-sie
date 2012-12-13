package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_contrato_empleado database table.
 * 
 */
@Entity
@Table(name="tb_contrato_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ContratoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_CONTRATO_EMPLEADO_IDCONTRATOEMPL_GENERATOR", sequenceName="SIE.TB_CONTRATO_EMPLEADO_IDCONTRATOEMPL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONTRATO_EMPLEADO_IDCONTRATOEMPL_GENERATOR")
	private Integer idContratoEmpl;

	private Integer idempleado;

	private BigDecimal comision;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private String pago;
	
	@Transient
	private Integer cantContratoXPatrocinado;
	
	@Temporal( TemporalType.DATE)
	private Date fechainiciopatrocinio;
	
	@Temporal( TemporalType.DATE)
	private Date fechafinpatrocinio;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado1;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="patrocinador", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado2;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    public ContratoEmpleadoSie() {
    }
	
	public BigDecimal getComision() {
		return this.comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getPago() {
		return this.pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}
	
	public EmpleadoSie getTbEmpleado1() {
		return this.tbEmpleado1;
	}

	public void setTbEmpleado1(EmpleadoSie tbEmpleado1) {
		this.tbEmpleado1 = tbEmpleado1;
	}
	
	public EmpleadoSie getTbEmpleado2() {
		return this.tbEmpleado2;
	}

	public void setTbEmpleado2(EmpleadoSie tbEmpleado2) {
		this.tbEmpleado2 = tbEmpleado2;
	}
	
	public Date getFechainiciopatrocinio() {
		return fechainiciopatrocinio;
	}

	public void setFechainiciopatrocinio(Date fechainiciopatrocinio) {
		this.fechainiciopatrocinio = fechainiciopatrocinio;
	}
	
	public Date getFechafinpatrocinio() {
		return fechafinpatrocinio;
	}
	
	public void setFechafinpatrocinio(Date fechafinpatrocinio) {
		this.fechafinpatrocinio = fechafinpatrocinio;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	/**
	 * @return the idempleado
	 */
	public Integer getIdempleado() {
		return idempleado;
	}

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}
	
	/**
	 * @return the idContratoEmpl
	 */
	public Integer getIdContratoEmpl() {
		return idContratoEmpl;
	}

	/**
	 * @param idContratoEmpl the idContratoEmpl to set
	 */
	public void setIdContratoEmpl(Integer idContratoEmpl) {
		this.idContratoEmpl = idContratoEmpl;
	}

	/**
	 * @return the cantContratoXPatrocinado
	 */
	public Integer getCantContratoXPatrocinado() {
		return cantContratoXPatrocinado;
	}

	/**
	 * @param cantContratoXPatrocinado the cantContratoXPatrocinado to set
	 */
	public void setCantContratoXPatrocinado(Integer cantContratoXPatrocinado) {
		this.cantContratoXPatrocinado = cantContratoXPatrocinado;
	}
	
}