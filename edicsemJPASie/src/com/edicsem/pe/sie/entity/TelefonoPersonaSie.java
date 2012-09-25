package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;


/**
 * The persistent class for the tb_telefono_persona database table.
 * 
 */
@Entity
@Table(name="tb_telefono_persona", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TelefonoPersonaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TELEFONO_PERSONA_IDTELEFONOPERSONA_GENERATOR", sequenceName="SIE.TB_TELEFONO_PERSONA_IDTELEFONOPERSONA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TELEFONO_PERSONA_IDTELEFONOPERSONA_GENERATOR")
	private Integer idtelefonopersona;

	private String telefono;

	private String tipotelefono;

	//bi-directional many-to-one association to ClienteSie
    @ManyToOne
	@JoinColumn(name="idcliente")
	private ClienteSie idcliente;
    
    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie idempleado;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public TelefonoPersonaSie() {
    }

	public Integer getIdtelefonopersona() {
		return this.idtelefonopersona;
	}

	public void setIdtelefonopersona(Integer idtelefonopersona) {
		this.idtelefonopersona = idtelefonopersona;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipotelefono() {
		return this.tipotelefono;
	}

	public void setTipotelefono(String tipotelefono) {
		this.tipotelefono = tipotelefono;
	}
	
	public EmpleadoSie getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(EmpleadoSie idempleado) {
		this.idempleado = idempleado;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public ClienteSie getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(ClienteSie idcliente) {
		this.idcliente = idcliente;
	}
	
}