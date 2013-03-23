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

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_carro database table.
 * 
 */
@Entity
@Table(name="tb_carro", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CarroSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CARRO_IDCARRO_GENERATOR", sequenceName="SIE.TB_CARRO_IDCARRO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CARRO_IDCARRO_GENERATOR")
	private Integer idcarro;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

    @Temporal( TemporalType.DATE)
	private Date fecnacimiento;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to DetTurnoEmplSie
  	@OneToMany(mappedBy="tbCarro")
  	private Set<DetTurnoEmplSie> tbDetTurnoEmpl;
  	
    public CarroSie() {
    }

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Date fechamodifica) {
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

	public Date getFecnacimiento() {
		return fecnacimiento;
	}

	public void setFecnacimiento(Date fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Integer getIdcarro() {
		return idcarro;
	}

	public void setIdcarro(Integer idcarro) {
		this.idcarro = idcarro;
	}

	public Set<DetTurnoEmplSie> getTbDetTurnoEmpl() {
		return tbDetTurnoEmpl;
	}

	public void setTbDetTurnoEmpl(Set<DetTurnoEmplSie> tbDetTurnoEmpl) {
		this.tbDetTurnoEmpl = tbDetTurnoEmpl;
	}
}