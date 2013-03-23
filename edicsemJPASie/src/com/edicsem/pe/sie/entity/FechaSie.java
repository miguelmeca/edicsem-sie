package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.util.Set;


/**
 * The persistent class for the tb_fecha database table.
 * 
 */
@Entity
@Table(name="tb_fecha", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class FechaSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_FECHA_ID_FECHA_GENERATOR", sequenceName="SIE.TB_FECHA_ID_FECHA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FECHA_ID_FECHA_GENERATOR")
	@Column(name="id_fecha")
	private Integer idFecha;

	private String dia;

	//bi-directional many-to-one association to HorarioPersonalSie
	@OneToMany(mappedBy="tbFecha")
	private Set<HorarioPersonalSie> tbHorarioPersonals;
	
	//bi-directional many-to-one association to FiltroHorarioVentaSie
	@OneToMany(mappedBy="tbFecha")
	private Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas;
	
	//bi-directional many-to-one association to HorarioPuntoVentaSie
	@OneToMany(mappedBy="tbFecha")
	private Set<HorarioPuntoVentaSie> tbHorarioPuntoVentas;
	
	//bi-directional many-to-one association to TurnoSie
	@OneToMany(mappedBy="tbFecha")
	private Set<TurnoSie> tbTurnos;

    public FechaSie() {
    }

	public Integer getIdFecha() {
		return this.idFecha;
	}

	public void setIdFecha(Integer idFecha) {
		this.idFecha = idFecha;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Set<HorarioPersonalSie> getTbHorarioPersonals() {
		return tbHorarioPersonals;
	}

	public void setTbHorarioPersonals(Set<HorarioPersonalSie> tbHorarioPersonals) {
		this.tbHorarioPersonals = tbHorarioPersonals;
	}

	public Set<FiltroHorarioVentaSie> getTbFiltroHorarioVentas() {
		return tbFiltroHorarioVentas;
	}

	public void setTbFiltroHorarioVentas(
			Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas) {
		this.tbFiltroHorarioVentas = tbFiltroHorarioVentas;
	}

	/**
	 * @return the tbHorarioPuntoVentas
	 */
	public Set<HorarioPuntoVentaSie> getTbHorarioPuntoVentas() {
		return tbHorarioPuntoVentas;
	}

	/**
	 * @param tbHorarioPuntoVentas the tbHorarioPuntoVentas to set
	 */
	public void setTbHorarioPuntoVentas(
			Set<HorarioPuntoVentaSie> tbHorarioPuntoVentas) {
		this.tbHorarioPuntoVentas = tbHorarioPuntoVentas;
	}

	public Set<TurnoSie> getTbTurnos() {
		return tbTurnos;
	}

	public void setTbTurnos(Set<TurnoSie> tbTurnos) {
		this.tbTurnos = tbTurnos;
	}
}