package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_turno database table.
 * 
 */
@Entity
@Table(name="tb_tipo_turno", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoTurnoSie implements  Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_TURNO_IDTIPOTUNRO_GENERATOR", sequenceName="SIE.TB_TIPO_TURNO_IDTIPOTUNRO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="TB_TIPO_TURNO_IDTIPOTUNRO_GENERATOR" )
	private Integer idtipoturno;
	
	private String descripcion;
	
	//bi-directional many-to-one association to TurnoSie
  	@OneToMany(mappedBy="tbTipoTurno")
  	private Set<TurnoSie> tbTurno;

    public TipoTurnoSie() {
    }

	public Integer getIdtipoturno() {
		return idtipoturno;
	}

	public void setIdtipoturno(Integer idtipoturno) {
		this.idtipoturno = idtipoturno;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<TurnoSie> getTbTurno() {
		return tbTurno;
	}

	public void setTbTurno(Set<TurnoSie> tbTurno) {
		this.tbTurno = tbTurno;
	}
	
}