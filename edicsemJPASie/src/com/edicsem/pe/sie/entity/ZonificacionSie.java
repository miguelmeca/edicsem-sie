package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_zonificacion database table.
 * 
 */
@Entity
@Table(name="tb_zonificacion", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ZonificacionSie  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ZONIFICACION_IDZONIFICA_GENERATOR", sequenceName="SIE.TB_ZONIFICACION_IDZONIFICA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ZONIFICACION_IDZONIFICA_GENERATOR")
	private Integer idzonifica;
	
	private String codplano;
	
	private String codsector;
	
	private String codletra;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to UbigeoSie
    @ManyToOne
	@JoinColumn(name="idubigeo")
	private UbigeoSie tbUbigeo;
    
    public ZonificacionSie() {
    }

	public Integer getIdzonifica() {
		return idzonifica;
	}

	public void setIdzonifica(Integer idzonifica) {
		this.idzonifica = idzonifica;
	}

	public String getCodplano() {
		return codplano;
	}

	public void setCodplano(String codplano) {
		this.codplano = codplano;
	}

	public String getCodsector() {
		return codsector;
	}

	public void setCodsector(String codsector) {
		this.codsector = codsector;
	}

	public String getCodletra() {
		return codletra;
	}

	public void setCodletra(String codletra) {
		this.codletra = codletra;
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

	public UbigeoSie getTbUbigeo() {
		return tbUbigeo;
	}

	public void setTbUbigeo(UbigeoSie tbUbigeo) {
		this.tbUbigeo = tbUbigeo;
	}
    
}