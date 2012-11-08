package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.util.Set;


/**
 * The persistent class for the tb_tipo_filtro database table.
 * 
 */
@Entity
@Table(name="tb_tipo_filtro", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoFiltroSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_FILTRO_IDTIPOFILTRO_GENERATOR", sequenceName="SIE.TB_TIPO_FILTRO_IDTIPOFILTRO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_FILTRO_IDTIPOFILTRO_GENERATOR")
	private Integer idtipofiltro;

	private String descripcion;

	//bi-directional many-to-one association to FiltroHorarioVentaSie
	@OneToMany(mappedBy="tbTipoFiltro")
	private Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas;

    public TipoFiltroSie() {
    }

	public Integer getIdtipofiltro() {
		return this.idtipofiltro;
	}

	public void setIdtipofiltro(Integer idtipofiltro) {
		this.idtipofiltro = idtipofiltro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<FiltroHorarioVentaSie> getTbFiltroHorarioVentas() {
		return this.tbFiltroHorarioVentas;
	}

	public void setTbFiltroHorarioVentas(Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas) {
		this.tbFiltroHorarioVentas = tbFiltroHorarioVentas;
	}
	
}