package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_importe database table.
 * 
 */
@Entity
@Table(name="tb_importe", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ImporteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_IMPORTE_IDIMPORTE_GENERATOR", sequenceName="SIE.TB_IMPORTE_IDIMPORTE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_IMPORTE_IDIMPORTE_GENERATOR")
	private Integer idimporte;

	private String descripcion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private BigDecimal monto;

	private Integer porcentaje;

	private String tipoconfig;
	
	//bi-directional many-to-one association to TipoImporteSie
    @ManyToOne
	@JoinColumn(name="idtipoimporte")
	private TipoImporteSie tbTipoImporte;

    public ImporteSie() {
    }

	public Integer getIdimporte() {
		return this.idimporte;
	}

	public void setIdimporte(Integer idimporte) {
		this.idimporte = idimporte;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public TipoImporteSie getTbTipoImporte() {
		return this.tbTipoImporte;
	}

	public void setTbTipoImporte(TipoImporteSie tbTipoImporte) {
		this.tbTipoImporte = tbTipoImporte;
	}

	public String getTipoconfig() {
		return tipoconfig;
	}

	public void setTipoconfig(String tipoconfig) {
		this.tipoconfig = tipoconfig;
	}
	
}