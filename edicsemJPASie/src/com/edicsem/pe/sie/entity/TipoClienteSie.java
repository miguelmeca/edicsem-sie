package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_cliente database table.
 * 
 */
@Entity
@Table(name="tb_tipo_cliente", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoClienteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_CLIENTE_IDTIPOCLIENTE_GENERATOR", sequenceName="SIE.TB_TIPO_CLIENTE_IDTIPOCLIENTE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_CLIENTE_IDTIPOCLIENTE_GENERATOR")
	private Integer idtipocliente;

	private String descripcion;
	
	private Integer diasretrazoini;
	
	private Integer diasretrazofin;
	
	private Integer cantcuotaretrazo;
	
	private Integer criterio;
	
	//bi-directional many-to-one association to ConfigCobranzaOperaSie
	@OneToMany(mappedBy="tbTipoCliente")
	private Set<ConfigCobranzaOperaSie> tbConfigCobranza;
	
	//bi-directional many-to-one association to ClienteSie
	@OneToMany(mappedBy="tbTipoCliente")
	private Set<ClienteSie> tbCliente;
	
	//bi-directional many-to-one association to TipoRefinanciaSie
	@OneToMany(mappedBy="tbTipoCliente")
	private Set<TipoRefinanciaSie> tbTipoRefinancia;
	
	//bi-directional many-to-one association to ConfigNotificacionSie
	@OneToMany(mappedBy="tbTipoCliente")
	private Set<ConfigNotificacionSie> tbConfigNotifica;
	
    public TipoClienteSie() {
    }

	public Integer getIdtipocliente() {
		return this.idtipocliente;
	}

	public void setIdtipocliente(Integer idtipocliente) {
		this.idtipocliente = idtipocliente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDiasretrazoini() {
		return diasretrazoini;
	}

	public void setDiasretrazoini(Integer diasretrazoini) {
		this.diasretrazoini = diasretrazoini;
	}

	public Integer getDiasretrazofin() {
		return diasretrazofin;
	}

	public void setDiasretrazofin(Integer diasretrazofin) {
		this.diasretrazofin = diasretrazofin;
	}

	public Integer getCantcuotaretrazo() {
		return cantcuotaretrazo;
	}

	public void setCantcuotaretrazo(Integer cantcuotaretrazo) {
		this.cantcuotaretrazo = cantcuotaretrazo;
	}

	public Integer getCriterio() {
		return criterio;
	}

	public void setCriterio(Integer criterio) {
		this.criterio = criterio;
	}

	public Set<ConfigCobranzaOperaSie> getTbConfigCobranza() {
		return tbConfigCobranza;
	}

	public void setTbConfigCobranza(Set<ConfigCobranzaOperaSie> tbConfigCobranza) {
		this.tbConfigCobranza = tbConfigCobranza;
	}

	public Set<ClienteSie> getTbCliente() {
		return tbCliente;
	}

	public void setTbCliente(Set<ClienteSie> tbCliente) {
		this.tbCliente = tbCliente;
	}

	public Set<ConfigNotificacionSie> getTbConfigNotifica() {
		return tbConfigNotifica;
	}

	public void setTbConfigNotifica(Set<ConfigNotificacionSie> tbConfigNotifica) {
		this.tbConfigNotifica = tbConfigNotifica;
	}

	public Set<TipoRefinanciaSie> getTbTipoRefinancia() {
		return tbTipoRefinancia;
	}

	public void setTbTipoRefinancia(Set<TipoRefinanciaSie> tbTipoRefinancia) {
		this.tbTipoRefinancia = tbTipoRefinancia;
	}

}