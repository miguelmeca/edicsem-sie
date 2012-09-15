package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_empleado database table.
 * 
 */
@Entity
@Table(name="tb_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class EmpleadoSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_EMPLEADO_IDEMPLEADO_GENERATOR", sequenceName="SIE.TB_EMPLEADO_IDEMPLEADO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_EMPLEADO_IDEMPLEADO_GENERATOR")
	private Integer idempleado;

	private String apematemp;

	private String apepatemp;

	private String contrasena;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String fechanacimiento;

	private String nombreemp;

	private String numdocumento;

	private String usuario;

	private String usuariocreacion;

	private String usuariomodifica;
	
	@Transient
	private String nombresCompletos;
	
	//bi-directional many-to-one association to BeneficiosDescuentoSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<BeneficiosDescuentoSie> tbBeneficiosDescuentos;

	//bi-directional many-to-one association to CobranzaOperadoraSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<CobranzaOperadoraSie> tbCobranzaOperadoras;

	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbEmpleado1")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados1;

	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbEmpleado2")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados2;

	//bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private CargoEmpleadoSie tbCargoEmpleado;

	//bi-directional many-to-one association to DomicilioPersonaSie
    @ManyToOne
	@JoinColumn(name="iddomicilioempleado")
	private DomicilioPersonaSie tbDomicilioPersona;

	//bi-directional many-to-one association to TelefonoPersonaSie
    @ManyToOne
	@JoinColumn(name="idtelefonoempleado")
	private TelefonoPersonaSie tbTelefonoPersona;

	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
    @ManyToOne
	@JoinColumn(name="idtipodocumentoidentidad")
	private TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad;

	//bi-directional many-to-one association to HorarioAsistenciaSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<HorarioAsistenciaSie> tbHorarioAsistencias;

	//bi-directional many-to-one association to HorarioPersonalSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<HorarioPersonalSie> tbHorarioPersonals;

	//bi-directional many-to-one association to HorariosPvSie
	@OneToMany(mappedBy="tbEmpleado1")
	private Set<HorariosPvSie> tbHorariosPvs1;

	//bi-directional many-to-one association to HorariosPvSie
	@OneToMany(mappedBy="tbEmpleado2")
	private Set<HorariosPvSie> tbHorariosPvs2;

	//bi-directional many-to-one association to HorariosPvSie
	@OneToMany(mappedBy="tbEmpleado3")
	private Set<HorariosPvSie> tbHorariosPvs3;

    public EmpleadoSie() {
    }

	public Integer getIdempleado() {
		return this.idempleado;
	}

	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public String getApematemp() {
		return this.apematemp;
	}

	public void setApematemp(String apematemp) {
		this.apematemp = apematemp;
	}

	public String getApepatemp() {
		return this.apepatemp;
	}

	public void setApepatemp(String apepatemp) {
		this.apepatemp = apepatemp;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public String getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombreemp() {
		return this.nombreemp;
	}

	public void setNombreemp(String nombreemp) {
		this.nombreemp = nombreemp;
	}

	public String getNumdocumento() {
		return this.numdocumento;
	}

	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public Set<BeneficiosDescuentoSie> getTbBeneficiosDescuentos() {
		return this.tbBeneficiosDescuentos;
	}

	public void setTbBeneficiosDescuentos(Set<BeneficiosDescuentoSie> tbBeneficiosDescuentos) {
		this.tbBeneficiosDescuentos = tbBeneficiosDescuentos;
	}
	
	public Set<CobranzaOperadoraSie> getTbCobranzaOperadoras() {
		return this.tbCobranzaOperadoras;
	}

	public void setTbCobranzaOperadoras(Set<CobranzaOperadoraSie> tbCobranzaOperadoras) {
		this.tbCobranzaOperadoras = tbCobranzaOperadoras;
	}
	
	public Set<ContratoEmpleadoSie> getTbContratoEmpleados1() {
		return this.tbContratoEmpleados1;
	}

	public void setTbContratoEmpleados1(Set<ContratoEmpleadoSie> tbContratoEmpleados1) {
		this.tbContratoEmpleados1 = tbContratoEmpleados1;
	}
	
	public Set<ContratoEmpleadoSie> getTbContratoEmpleados2() {
		return this.tbContratoEmpleados2;
	}

	public void setTbContratoEmpleados2(Set<ContratoEmpleadoSie> tbContratoEmpleados2) {
		this.tbContratoEmpleados2 = tbContratoEmpleados2;
	}
	
	public CargoEmpleadoSie getTbCargoEmpleado() {
		return this.tbCargoEmpleado;
	}

	public void setTbCargoEmpleado(CargoEmpleadoSie tbCargoEmpleado) {
		this.tbCargoEmpleado = tbCargoEmpleado;
	}
	
	public DomicilioPersonaSie getTbDomicilioPersona() {
		return this.tbDomicilioPersona;
	}

	public void setTbDomicilioPersona(DomicilioPersonaSie tbDomicilioPersona) {
		this.tbDomicilioPersona = tbDomicilioPersona;
	}
	
	public TelefonoPersonaSie getTbTelefonoPersona() {
		return this.tbTelefonoPersona;
	}

	public void setTbTelefonoPersona(TelefonoPersonaSie tbTelefonoPersona) {
		this.tbTelefonoPersona = tbTelefonoPersona;
	}
	
	public TipoDocumentoIdentidadSie getTbTipoDocumentoIdentidad() {
		return this.tbTipoDocumentoIdentidad;
	}

	public void setTbTipoDocumentoIdentidad(TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad) {
		this.tbTipoDocumentoIdentidad = tbTipoDocumentoIdentidad;
	}
	
	public Set<HorarioAsistenciaSie> getTbHorarioAsistencias() {
		return this.tbHorarioAsistencias;
	}

	public void setTbHorarioAsistencias(Set<HorarioAsistenciaSie> tbHorarioAsistencias) {
		this.tbHorarioAsistencias = tbHorarioAsistencias;
	}
	
	public Set<HorarioPersonalSie> getTbHorarioPersonals() {
		return this.tbHorarioPersonals;
	}

	public void setTbHorarioPersonals(Set<HorarioPersonalSie> tbHorarioPersonals) {
		this.tbHorarioPersonals = tbHorarioPersonals;
	}
	
	public Set<HorariosPvSie> getTbHorariosPvs1() {
		return this.tbHorariosPvs1;
	}

	public void setTbHorariosPvs1(Set<HorariosPvSie> tbHorariosPvs1) {
		this.tbHorariosPvs1 = tbHorariosPvs1;
	}
	
	public Set<HorariosPvSie> getTbHorariosPvs2() {
		return this.tbHorariosPvs2;
	}

	public void setTbHorariosPvs2(Set<HorariosPvSie> tbHorariosPvs2) {
		this.tbHorariosPvs2 = tbHorariosPvs2;
	}
	
	public Set<HorariosPvSie> getTbHorariosPvs3() {
		return this.tbHorariosPvs3;
	}

	public void setTbHorariosPvs3(Set<HorariosPvSie> tbHorariosPvs3) {
		this.tbHorariosPvs3 = tbHorariosPvs3;
	}

	/**
	 * @return the nombresCompletos
	 */
	public String getNombresCompletos() {
		nombresCompletos =nombreemp + " " + apepatemp + " " +apematemp; 
		return nombresCompletos;
	}

	/**
	 * @param nombresCompletos the nombresCompletos to set
	 */
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}
	
}