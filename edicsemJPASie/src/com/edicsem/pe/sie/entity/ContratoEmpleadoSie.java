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
	
	private BigDecimal basico;
	
	private Integer  periodoPrueba;
	
    @Transient
    private int item;
    
    @Transient
    private String tipo;
	
    @Transient
    private int empresa;
 
    @Transient
    private String descEmpresa;
    
    @Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	@Temporal( TemporalType.DATE)
	private Date fechaInicioContrato;
	
	@Temporal( TemporalType.DATE)
	private Date fechainiciopatrocinio;
	
	@Temporal( TemporalType.DATE)
	private Date fechafinpatrocinio;
	
	//bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private CargoEmpleadoSie tbCargoempleado;
    
	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado" )
	private EmpleadoSie tbEmpleado1;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="patrocinador", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado2;
    
    //bi-directional many-to-one association to TipoPagoSie
    @ManyToOne
	@JoinColumn(name="idtipopago")
	private TipoPagoSie tbTipoPago;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
	//bi-directional many-to-one association to EmpresaSie
    @ManyToOne
	@JoinColumn(name="idempresa")
	private EmpresaSie tbEmpresa;
    
	@Transient
	private Integer cantContratoXPatrocinado;
	
    public ContratoEmpleadoSie() {
    }
    
	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
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

	public BigDecimal getBasico() {
		return basico;
	}

	public void setBasico(BigDecimal basico) {
		this.basico = basico;
	}

	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}

	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}

	public TipoPagoSie getTbTipoPago() {
		return tbTipoPago;
	}

	public void setTbTipoPago(TipoPagoSie tbTipoPago) {
		this.tbTipoPago = tbTipoPago;
	}

	public Integer getPeriodoPrueba() {
		return periodoPrueba;
	}

	public void setPeriodoPrueba(Integer periodoPrueba) {
		this.periodoPrueba = periodoPrueba;
	}

	public CargoEmpleadoSie getTbCargoempleado() {
		return tbCargoempleado;
	}

	public void setTbCargoempleado(CargoEmpleadoSie tbCargoempleado) {
		this.tbCargoempleado = tbCargoempleado;
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

	/**
	 * @return the empresa
	 */
	public int getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the descEmpresa
	 */
	public String getDescEmpresa() {
		return descEmpresa;
	}

	/**
	 * @param descEmpresa the descEmpresa to set
	 */
	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public EmpresaSie getTbEmpresa() {
		return tbEmpresa;
	}

	public void setTbEmpresa(EmpresaSie tbEmpresa) {
		this.tbEmpresa = tbEmpresa;
	}
	
}