package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
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
 * The persistent class for the tb_tipo_producto database table.
 * 
 */
@Entity
@Table(name="tb_tipo_producto", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoProductoSie implements  Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_PRODUCTO_IDTIPOPRODUCTO_GENERATOR", sequenceName="SIE.TB_TIPO_PRODUCTO_IDTIPOPRODUCTO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="TB_TIPO_PRODUCTO_IDTIPOPRODUCTO_GENERATOR" )
	private Integer idtipoproducto;

	private String codtipoproducto;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String nombretipoproducto;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to ProductoSie
	@OneToMany(mappedBy="tbTipoProducto")
	private Set<ProductoSie> tbProductos;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public TipoProductoSie() {
    }

	public Integer getIdtipoproducto() {
		return this.idtipoproducto;
	}

	public void setIdtipoproducto(Integer idtipoproducto) {
		this.idtipoproducto = idtipoproducto;
	}

	public String getCodtipoproducto() {
		return this.codtipoproducto;
	}

	public void setCodtipoproducto(String codtipoproducto) {
		this.codtipoproducto = codtipoproducto;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getNombretipoproducto() {
		return this.nombretipoproducto;
	}

	public void setNombretipoproducto(String nombretipoproducto) {
		this.nombretipoproducto = nombretipoproducto;
	}

	public String getUsuariocreacion() {
		return this.usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return this.usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public Set<ProductoSie> getTbProductos() {
		return this.tbProductos;
	}

	public void setTbProductos(Set<ProductoSie> tbProductos) {
		this.tbProductos = tbProductos;
	}

	/**
	 * @return the tbEstadoGeneral
	 */
	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	/**
	 * @param tbEstadoGeneral the tbEstadoGeneral to set
	 */
	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}