package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_concepto database table.
 * 
 */
@Entity
@Table(name="tb_concepto")
public class ConceptoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CONCEPTO_IDCONCEPTO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONCEPTO_IDCONCEPTO_GENERATOR")
	@Column(name="id_concepto")
	private Integer idConcepto;

	private String estado;

	//bi-directional many-to-one association to BeneficiosDescuentoSie
	@OneToMany(mappedBy="tbConcepto")
	private Set<BeneficiosDescuentoSie> tbBeneficiosDescuentos;

    public ConceptoSie() {
    }

	public Integer getIdConcepto() {
		return this.idConcepto;
	}

	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Set<BeneficiosDescuentoSie> getTbBeneficiosDescuentos() {
		return this.tbBeneficiosDescuentos;
	}

	public void setTbBeneficiosDescuentos(Set<BeneficiosDescuentoSie> tbBeneficiosDescuentos) {
		this.tbBeneficiosDescuentos = tbBeneficiosDescuentos;
	}
	
}