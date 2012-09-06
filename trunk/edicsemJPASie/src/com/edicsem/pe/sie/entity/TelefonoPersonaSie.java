package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.util.Set;


/**
 * The persistent class for the tb_telefono_persona database table.
 * 
 */
@Entity
@Table(name="tb_telefono_persona", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TelefonoPersonaSie extends BaseMantenimientoForm  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TELEFONO_PERSONA_IDTELEFONOPERSONA_GENERATOR", sequenceName="SIE.TB_TELEFONO_PERSONA_IDTELEFONOPERSONA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TELEFONO_PERSONA_IDTELEFONOPERSONA_GENERATOR")
	private Integer idtelefonopersona;

	private String telefono;

	private String tipotelefono;

	//bi-directional many-to-one association to ClienteSie
	@OneToMany(mappedBy="tbTelefonoPersona")
	private Set<ClienteSie> tbClientes;

	//bi-directional many-to-one association to EmpleadoSie
	@OneToMany(mappedBy="tbTelefonoPersona")
	private Set<EmpleadoSie> tbEmpleados;

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

	public Set<ClienteSie> getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(Set<ClienteSie> tbClientes) {
		this.tbClientes = tbClientes;
	}
	
	public Set<EmpleadoSie> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(Set<EmpleadoSie> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}