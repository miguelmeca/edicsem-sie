package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.util.Set;


/**
 * The persistent class for the tb_tipo_importe database table.
 * 
 */
@Entity
@Table(name="tb_tipo_importe", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoImporteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_IMPORTE_IDTIPOIMPORTE_GENERATOR", sequenceName="SIE.TB_TIPO_IMPORTE_IDTIPOIMPORTE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_IMPORTE_IDTIPOIMPORTE_GENERATOR")
	private Integer idtipoimporte;

	private String descripcion;

	//bi-directional many-to-one association to DetpagoSie
	@OneToMany(mappedBy="tbTipoImporte")
	private Set<DetpagoSie> tbDetpagos;

	//bi-directional many-to-one association to ImporteSie
	@OneToMany(mappedBy="tbTipoImporte")
	private Set<ImporteSie> tbImportes;

    public TipoImporteSie() {
    }

	public Integer getIdtipoimporte() {
		return this.idtipoimporte;
	}

	public void setIdtipoimporte(Integer idtipoimporte) {
		this.idtipoimporte = idtipoimporte;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<DetpagoSie> getTbDetpagos() {
		return this.tbDetpagos;
	}

	public void setTbDetpagos(Set<DetpagoSie> tbDetpagos) {
		this.tbDetpagos = tbDetpagos;
	}
	
	public Set<ImporteSie> getTbImportes() {
		return this.tbImportes;
	}

	public void setTbImportes(Set<ImporteSie> tbImportes) {
		this.tbImportes = tbImportes;
	}
	
}