package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_verifica_telefono database table.
 * 
 */
@Entity
@Table(name="tb_verifica_telefono", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class VerificaTelefonoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_VERIFICA_TELEFONO_IDVERIFICATELEFONO_GENERATOR", sequenceName="SIE.TB_VERIFICA_TELEFONO_IDVERIFICATELEFONO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_VERIFICA_TELEFONO_IDVERIFICATELEFONO_GENERATOR")
	private Integer idverificatelefono;

	private String desctelefono;

	private String operadortelefonico;

	private String telefono;

	private String tipotelefono;

	//bi-directional many-to-one association to VerificaClienteSie
    @ManyToOne
	@JoinColumn(name="idverificacliente")
	private VerificaClienteSie tbVerificaCliente;

    public VerificaTelefonoSie() {
    }

	public Integer getIdverificatelefono() {
		return this.idverificatelefono;
	}

	public void setIdverificatelefono(Integer idverificatelefono) {
		this.idverificatelefono = idverificatelefono;
	}

	public String getDesctelefono() {
		return this.desctelefono;
	}

	public void setDesctelefono(String desctelefono) {
		this.desctelefono = desctelefono;
	}

	public String getOperadortelefonico() {
		return this.operadortelefonico;
	}

	public void setOperadortelefonico(String operadortelefonico) {
		this.operadortelefonico = operadortelefonico;
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

	public VerificaClienteSie getTbVerificaCliente() {
		return this.tbVerificaCliente;
	}

	public void setTbVerificaCliente(VerificaClienteSie tbVerificaCliente) {
		this.tbVerificaCliente = tbVerificaCliente;
	}
	
}