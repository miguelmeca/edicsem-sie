package com.edicsem.pe.sie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tb_horario_asistencia database table.
 * 
 */
@Embeddable
public class HorarioAsistenciaSiePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_horario_asistencia")
	private Integer idHorarioAsistencia;

	private Integer idempleado;

    public HorarioAsistenciaSiePK() {
    }
	public Integer getIdHorarioAsistencia() {
		return this.idHorarioAsistencia;
	}
	public void setIdHorarioAsistencia(Integer idHorarioAsistencia) {
		this.idHorarioAsistencia = idHorarioAsistencia;
	}
	public Integer getIdempleado() {
		return this.idempleado;
	}
	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HorarioAsistenciaSiePK)) {
			return false;
		}
		HorarioAsistenciaSiePK castOther = (HorarioAsistenciaSiePK)other;
		return 
			this.idHorarioAsistencia.equals(castOther.idHorarioAsistencia)
			&& this.idempleado.equals(castOther.idempleado);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idHorarioAsistencia.hashCode();
		hash = hash * prime + this.idempleado.hashCode();
		
		return hash;
    }
}