package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_tipo_producto database table.
 * 
 */
@Entity
@Table(name="tb_tipo_producto", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoProductoSie  extends BaseMantenimientoForm implements  Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_PRODUCTO_IDTIPOPRODUCTO_GENERATOR", sequenceName="SIE.TB_TIPO_PRODUCTO_IDTIPOPRODUCTO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="TB_TIPO_PRODUCTO_IDTIPOPRODUCTO_GENERATOR" )
	private Integer idtipoproducto;

	private String codtipoproducto;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String nombretipoproducto;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to ProductoSie
	@OneToMany(mappedBy="tbTipoProducto")
	private Set<ProductoSie> tbProductos;

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
	
}