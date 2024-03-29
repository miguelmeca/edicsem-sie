package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_comprobante database table.
 * 
 */
@Entity
@Table(name="tb_comprobante", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ComprobanteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_COMPROBANTE_IDCOMPROBANTE_GENERATOR", sequenceName="SIE.TB_COMPROBANTE_IDCOMPROBANTE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_COMPROBANTE_IDCOMPROBANTE_GENERATOR")
	private Integer idcomprobante;

	private String codcomprobante;

    @Temporal( TemporalType.DATE)
	private Date fechacancelacion;
    
    @Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String tipocomprobante;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to ProveedorSie
    @ManyToOne
	@JoinColumn(name="idproveedor")
	private ProveedorSie tbProveedor;

	//bi-directional many-to-one association to DetalleComprobanteSie
	@OneToMany(mappedBy="tbComprobante")
	private Set<DetalleComprobanteSie> tbDetalleComprobantes;
	
	@Transient
	private String fechaCreacionString;

    public ComprobanteSie() {
    }

	public Integer getIdcomprobante() {
		return this.idcomprobante;
	}

	public void setIdcomprobante(Integer idcomprobante) {
		this.idcomprobante = idcomprobante;
	}

	public String getCodcomprobante() {
		return this.codcomprobante;
	}

	public void setCodcomprobante(String codcomprobante) {
		this.codcomprobante = codcomprobante;
	}

	public Date getFechacancelacion() {
		return this.fechacancelacion;
	}

	public void setFechacancelacion(Date fechacancelacion) {
		this.fechacancelacion = fechacancelacion;
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

	public String getTipocomprobante() {
		return this.tipocomprobante;
	}

	public void setTipocomprobante(String tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
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

	public ProveedorSie getTbProveedor() {
		return this.tbProveedor;
	}

	public void setTbProveedor(ProveedorSie tbProveedor) {
		this.tbProveedor = tbProveedor;
	}
	
	public Set<DetalleComprobanteSie> getTbDetalleComprobantes() {
		return this.tbDetalleComprobantes;
	}

	public void setTbDetalleComprobantes(Set<DetalleComprobanteSie> tbDetalleComprobantes) {
		this.tbDetalleComprobantes = tbDetalleComprobantes;
	}

	public String getFechaCreacionString() {
		fechaCreacionString = DateUtil.formatoString(getFechacreacion(), "dd/MM/yyyy");
		return fechaCreacionString;
	}

	public void setFechaCreacionString(String fechaCreacionString) {
		this.fechaCreacionString = fechaCreacionString;
	}
	
}