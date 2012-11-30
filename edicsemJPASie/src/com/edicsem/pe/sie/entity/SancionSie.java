package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_sancion database table.
 * 
 */
@Entity
@Table(name="tb_sancion", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class SancionSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_SANCION_IDSANCION_GENERATOR", sequenceName="SIE.TB_SANCION_IDSANCION_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SANCION_IDSANCION_GENERATOR")
	private Integer idsancion;

	private String descripcion;

	private String detalle;
	
	@Temporal( TemporalType.DATE)
	private Date diafin;

    @Temporal( TemporalType.DATE)
	private Date diainicio;

    @Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to FactorSancionSie
    @ManyToOne
	@JoinColumn(name="idfactor")
	private FactorSancionSie tbFactorSancion;
    
    //bi-directional many-to-one association to DetSancionCargoSie
  	@OneToMany(mappedBy="tbSancion")
  	private Set<DetSancionCargoSie> tbDetSancionCargo;

    public SancionSie() {
    }

	public Integer getIdsancion() {
		return this.idsancion;
	}

	public void setIdsancion(Integer idsancion) {
		this.idsancion = idsancion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getDiafin() {
		return this.diafin;
	}

	public void setDiafin(Date diafin) {
		this.diafin = diafin;
	}

	public Date getDiainicio() {
		return this.diainicio;
	}

	public void setDiainicio(Date diainicio) {
		this.diainicio = diainicio;
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

	public FactorSancionSie getTbFactorSancion() {
		return this.tbFactorSancion;
	}

	public void setTbFactorSancion(FactorSancionSie tbFactorSancion) {
		this.tbFactorSancion = tbFactorSancion;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Set<DetSancionCargoSie> getTbDetSancionCargo() {
		return tbDetSancionCargo;
	}

	public void setTbDetSancionCargo(Set<DetSancionCargoSie> tbDetSancionCargo) {
		this.tbDetSancionCargo = tbDetSancionCargo;
	}
	
}