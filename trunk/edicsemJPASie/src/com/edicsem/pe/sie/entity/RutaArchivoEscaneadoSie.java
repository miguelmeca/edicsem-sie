package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_ruta_archivo_escaneado database table.
 * 
 */
@Entity
@Table(name="tb_ruta_archivo_escaneado")
public class RutaArchivoEscaneadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_RUTA_ARCHIVO_ESCANEADO_IDRUTAARCHIVOESCANEADO_GENERATOR", sequenceName="TB_RUTA_ARCHIVO_ESCANEADO_IDRUTAARCHIVOESCANEADO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_RUTA_ARCHIVO_ESCANEADO_IDRUTAARCHIVOESCANEADO_GENERATOR")
	private Integer idrutaarchivoescaneado;

	private Timestamp fechacreacion;

	private String ruta;

	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato")
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public RutaArchivoEscaneadoSie() {
    }

	public Integer getIdrutaarchivoescaneado() {
		return this.idrutaarchivoescaneado;
	}

	public void setIdrutaarchivoescaneado(Integer idrutaarchivoescaneado) {
		this.idrutaarchivoescaneado = idrutaarchivoescaneado;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ContratoSie getTbContrato() {
		return this.tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}