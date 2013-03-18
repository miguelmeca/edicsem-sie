package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_grupo_venta database table.
 * 
 */
@Entity
@Table(name="tb_grupo_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class GrupoVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_GRUPO_VENTA_IDGRUPO_GENERATOR", sequenceName="SIE.TB_GRUPO_VENTA_IDGRUPO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_GRUPO_VENTA_IDGRUPO_GENERATOR")
	private Integer idgrupo;

	private String descripcion;
	
	private String detalle;
	
	//bi-directional many-to-one association to TipoEventoVentaSie
    @ManyToOne
	@JoinColumn(name="idtipoevento")
	private TipoEventoVentaSie tbTipoEventoVenta;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to DetGrupoEmpleadoSie
	@OneToMany(mappedBy="tbGrupoVenta")
	private Set<DetGrupoEmpleadoSie> tbDetGrupoEmpleados;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public GrupoVentaSie() {
    }

	public Integer getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
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

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
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

	public Set<DetGrupoEmpleadoSie> getTbDetGrupoEmpleados() {
		return this.tbDetGrupoEmpleados;
	}

	public void setTbDetGrupoEmpleados(Set<DetGrupoEmpleadoSie> tbDetGrupoEmpleados) {
		this.tbDetGrupoEmpleados = tbDetGrupoEmpleados;
	}
	
	public TipoEventoVentaSie getTbTipoEventoVenta() {
		return tbTipoEventoVenta;
	}
	
	public void setTbTipoEventoVenta(TipoEventoVentaSie tbTipoEventoVenta) {
		this.tbTipoEventoVenta = tbTipoEventoVenta;
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

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
}