package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TurnoSie;
import com.edicsem.pe.sie.service.facade.TurnoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="turno")
@SessionScoped
public class MantenimientoHorarioTurnoSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private List<TurnoSie> listaTurno;
	private TurnoSie objTurnoSie;
	private int idturno;
	private Date  horaIngreso,horaSalida;
	private String mensaje;
	private boolean newRecord =false;
	
	@EJB 
	private TurnoService objTurnoService;
	
	public static Log log = LogFactory.getLog(MantenimientoHorarioTurnoSearchAction.class);
	
	public MantenimientoHorarioTurnoSearchAction() {
		log.info("inicializando mi constructor 'MantenimientoHorarioTurnoSearchAction'");
		init();
	}
	
	public void init() {
		log.info("init()");
		listaTurno = new ArrayList<TurnoSie>();
		objTurnoSie = new TurnoSie();
		horaIngreso = new Date();
		horaSalida= new Date();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		listaTurno = new ArrayList<TurnoSie>();
		objTurnoSie = new TurnoSie();
		horaIngreso = new Date();
		horaSalida= new Date();
		newRecord=true;
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update() ");
		newRecord=false;
		setHoraIngreso(objTurnoSie.getHoraInicio());
		setHoraSalida(objTurnoSie.getHoraFin());
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("entrando a insertar()");
		mensaje=null;
		
		try {
			Time hora1 = new Time(getHoraIngreso().getTime());
			Time hora2= new Time(getHoraSalida().getTime());
			if(hora2.before(hora1)){
				mensaje="La hora inicial no puede ser mayor que la hora final";
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			}
			else if(isNewRecord()&& mensaje ==null){
				objTurnoSie.setHoraInicio(hora1);
				objTurnoSie.setHoraFin(hora2);
				objTurnoService.insertTurno(objTurnoSie);
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);				
			}else{
				objTurnoSie.setHoraInicio(hora1);
				objTurnoSie.setHoraFin(hora2);
				objTurnoService.updateTurno(objTurnoSie);
				mensaje =Constants.MESSAGE_ACTUALIZO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return listar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		listaTurno = objTurnoService.listarTurno();
		if (listaTurno == null) {
			listaTurno = new ArrayList<TurnoSie>();
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_HORARIO_TURNO;
	}

	/**
	 * @return the listaTurno
	 */
	public List<TurnoSie> getListaTurno() {
		return listaTurno;
	}

	/**
	 * @param listaTurno the listaTurno to set
	 */
	public void setListaTurno(List<TurnoSie> listaTurno) {
		this.listaTurno = listaTurno;
	}

	/**
	 * @return the objTurnoSie
	 */
	public TurnoSie getObjTurnoSie() {
		return objTurnoSie;
	}

	/**
	 * @param objTurnoSie the objTurnoSie to set
	 */
	public void setObjTurnoSie(TurnoSie objTurnoSie) {
		this.objTurnoSie = objTurnoSie;
	}

	/**
	 * @return the idturno
	 */
	public int getIdturno() {
		return idturno;
	}

	/**
	 * @param idturno the idturno to set
	 */
	public void setIdturno(int idturno) {
		this.idturno = idturno;
	}

	/**
	 * @return the horaIngreso
	 */
	public Date getHoraIngreso() {
		return horaIngreso;
	}

	/**
	 * @param horaIngreso the horaIngreso to set
	 */
	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	/**
	 * @return the horaSalida
	 */
	public Date getHoraSalida() {
		return horaSalida;
	}

	/**
	 * @param horaSalida the horaSalida to set
	 */
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

}
