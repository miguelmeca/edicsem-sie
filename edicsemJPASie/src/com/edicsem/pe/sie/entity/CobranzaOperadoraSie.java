package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;


/**
 * The persistent class for the tb_cobranza_operadora database table.
 * 
 */
@Entity
@Table(name="tb_cobranza_operadora", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CobranzaOperadoraSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_COBRANZA_OPERADORA_IDCOBRANZAOPERADORA_GENERATOR" , sequenceName="SIE.TB_COBRANZA_OPERADORA_IDCOBRANZA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_COBRANZA_OPERADORA_IDCOBRANZAOPERADORA_GENERATOR")
	@Column(name="\"idCobranzaOperadora\"")
	private Integer idCobranzaOperadora;

	private String observaciones;

	//bi-directional many-to-one association to CobranzaSie
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="idcliente", referencedColumnName="idcontrato"),
		@JoinColumn(name="idcobranza", referencedColumnName="idcobranza"),
		@JoinColumn(name="idcontrato", referencedColumnName="idcliente")
		})
	private CobranzaSie tbCobranza;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

	//bi-directional many-to-one association to TipoLlamadaSie
    @ManyToOne
	@JoinColumn(name="idtipo_llamada")
	private TipoLlamadaSie tbTipoLlamada;

    public CobranzaOperadoraSie() {
    }

	public Integer getIdCobranzaOperadora() {
		return this.idCobranzaOperadora;
	}

	public void setIdCobranzaOperadora(Integer idCobranzaOperadora) {
		this.idCobranzaOperadora = idCobranzaOperadora;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public CobranzaSie getTbCobranza() {
		return this.tbCobranza;
	}

	public void setTbCobranza(CobranzaSie tbCobranza) {
		this.tbCobranza = tbCobranza;
	}
	
	public EmpleadoSie getTbEmpleado() {
		return this.tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}
	
	public TipoLlamadaSie getTbTipoLlamada() {
		return this.tbTipoLlamada;
	}

	public void setTbTipoLlamada(TipoLlamadaSie tbTipoLlamada) {
		this.tbTipoLlamada = tbTipoLlamada;
	}
	
}