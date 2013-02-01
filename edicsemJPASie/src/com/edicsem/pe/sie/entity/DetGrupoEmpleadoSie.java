package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_det_grupo_empleado database table.
 * 
 */
@Entity
@Table(name="tb_det_grupo_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetGrupoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_GRUPO_EMPLEADO_IDDETGRUPO_GENERATOR", sequenceName="SIE.TB_DET_GRUPO_EMPLEADO_IDDETGRUPO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_GRUPO_EMPLEADO_IDDETGRUPO_GENERATOR")
	private Integer iddetgrupo;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie idempleado;

	//bi-directional many-to-one association to GrupoVentaSie
    @ManyToOne
	@JoinColumn(name="idgrupo")
	private GrupoVentaSie tbGrupoVenta;

    public DetGrupoEmpleadoSie() {
    }

	public Integer getIddetgrupo() {
		return this.iddetgrupo;
	}

	public void setIddetgrupo(Integer iddetgrupo) {
		this.iddetgrupo = iddetgrupo;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public GrupoVentaSie getTbGrupoVenta() {
		return this.tbGrupoVenta;
	}

	public void setTbGrupoVenta(GrupoVentaSie tbGrupoVenta) {
		this.tbGrupoVenta = tbGrupoVenta;
	}

	public EmpleadoSie getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(EmpleadoSie idempleado) {
		this.idempleado = idempleado;
	}
	
}