package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_kardex_producto database table.
 * 
 */
@Entity
@Table(name="tb_tipo_kardex_producto", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoKardexProductoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_KARDEX_PRODUCTO_IDTIPOKARDEXPRODUCTO_GENERATOR", sequenceName="SIE.TB_TIPO_KARDEX_PRODUCTO_IDTIPOKARDEXPRODUCTO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_KARDEX_PRODUCTO_IDTIPOKARDEXPRODUCTO_GENERATOR")
	private Integer idtipokardexproducto;

	private String descripcion;

	//bi-directional many-to-one association to KardexSie
	@OneToMany(mappedBy="tbTipoKardexProducto")
	private Set<KardexSie> tbKardexs;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public TipoKardexProductoSie() {
    }

	public Integer getIdtipokardexproducto() {
		return this.idtipokardexproducto;
	}

	public void setIdtipokardexproducto(Integer idtipokardexproducto) {
		this.idtipokardexproducto = idtipokardexproducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<KardexSie> getTbKardexs() {
		return this.tbKardexs;
	}

	public void setTbKardexs(Set<KardexSie> tbKardexs) {
		this.tbKardexs = tbKardexs;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}