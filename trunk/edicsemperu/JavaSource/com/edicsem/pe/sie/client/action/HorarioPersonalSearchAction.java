package com.edicsem.pe.sie.client.action;

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
import org.primefaces.event.RowEditEvent;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="horarioPersonal")
@SessionScoped
public class HorarioPersonalSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private HorarioPersonalSie objHorarioPersonal;
	private List<HorarioPersonalSie> listaHorario;
	private List<String> diaList;
	private Date fecPagoNull, horaIngreso,horaSalida;
	private int tipollamada; 
	private boolean editMode;
	private String mensaje;
	private int idempleado;
	private boolean newRecord =false;
	private int ide;
	
	@EJB 
	private HorarioPersonalService objHorarioPersonalService;
	
	public static Log log = LogFactory.getLog(HorarioPersonalSearchAction.class);
	
	public HorarioPersonalSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objHorarioPersonal = new HorarioPersonalSie();
		//objtelefono = new TelefonoPersonaSie();
		log.info("despues de inicializar  ");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		return getViewList();
	}
	
	public String mostrar() throws Exception {
		log.info("mostrar horario de empleados");
				log.info("listarHorario del personal");
				listaHorario = objHorarioPersonalService.listarHorarioPersonalXempleado(idempleado);
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPersonalSie>();
				}
		return getViewList();
	}
	
	public void agregarhorario(){
		newRecord=true;
	}
	
	public String insertar() throws Exception {
		mensaje="";
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar'  xd ");
				}
				if(newRecord){
					log.info("fecha pp  " + getHoraIngreso() );
					Time hora1 = new Time( getHoraIngreso().getTime());
					Time hora2= new Time( getHoraSalida().getTime());
					log.info(" g   "+ hora1);
					objHorarioPersonal.setHoraIngreso(hora1);
					objHorarioPersonal.setHoraSalida(hora2);
					
					/** Validation **/
		
					//para que no hayan cruces de horario 
					for (int j = 0; j < diaList.size();j++) {
						for (int i = 0; i < listaHorario.size(); i++) {
							log.info("  verificando ");
							if( listaHorario.get(i).getTbFecha().getIdFecha()== Integer.parseInt(diaList.get(j))
								 && (objHorarioPersonal.getDiainicio().after(listaHorario.get(i).getDiainicio()) && 
										objHorarioPersonal.getDiainicio().before(listaHorario.get(i).getDiafin() ) ||
										objHorarioPersonal.getDiafin().after(listaHorario.get(i).getDiainicio()) && 
										objHorarioPersonal.getDiafin().before(listaHorario.get(i).getDiafin() ) ||
										objHorarioPersonal.getDiafin().equals(listaHorario.get(i).getDiafin()) ||
										objHorarioPersonal.getDiafin().equals(listaHorario.get(i).getDiainicio())||
										objHorarioPersonal.getDiainicio().equals(listaHorario.get(i).getDiafin())||
										objHorarioPersonal.getDiainicio().equals(listaHorario.get(i).getDiainicio()))){
								log.info("fechas con horario registrado   ");
								if(	objHorarioPersonal.getHoraIngreso().equals(listaHorario.get(i).getHoraIngreso()) ||
										objHorarioPersonal.getHoraIngreso().after(listaHorario.get(i).getHoraIngreso())
										&& objHorarioPersonal.getHoraIngreso().before(listaHorario.get(i).getHoraSalida())){
									log.info(objHorarioPersonal.getHoraIngreso() +" --->  getHoraIngreso "+ listaHorario.get(i).getHoraIngreso());
									mensaje = "La hora de Ingreso es errónea";
									break;
								}else if(objHorarioPersonal.getHoraSalida().equals(listaHorario.get(i).getHoraSalida()) ||
										objHorarioPersonal.getHoraSalida().after(listaHorario.get(i).getHoraIngreso())
										&& objHorarioPersonal.getHoraSalida().before(listaHorario.get(i).getHoraSalida())){
									log.info(objHorarioPersonal.getHoraSalida()+" --->  getHoraSalida "+listaHorario.get(i).getHoraSalida());
									mensaje = "La hora de Salida es errónea";
									break;
									}
								}
							}
						}
					 if(mensaje.equals("")){
						 objHorarioPersonalService.insertHorarioPersonal(diaList,objHorarioPersonal,idempleado);
						 log.info("insertando..... ");
						 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
						 FacesContext.getCurrentInstance().addMessage(null, msg);
					 }else{
						msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					 }
				}else{
					
				}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objHorarioPersonal= new HorarioPersonalSie();
		return mostrar();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_HORARIO_PERSONAL;
	}

	/**
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
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

	/**
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the fecPagoNull
	 */
	public Date getFecPagoNull() {
		return fecPagoNull;
	}

	/**
	 * @param fecPagoNull the fecPagoNull to set
	 */
	public void setFecPagoNull(Date fecPagoNull) {
		this.fecPagoNull = fecPagoNull;
	}

	/**
	 * @return the tipollamada
	 */
	public int getTipollamada() {
		return tipollamada;
	}

	/**
	 * @param tipollamada the tipollamada to set
	 */
	public void setTipollamada(int tipollamada) {
		this.tipollamada = tipollamada;
	}

	/**
	 * @return the idempleado
	 */
	public int getIdempleado() {
		return idempleado;
	}

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	/**
	 * @return the objHorarioPersonal
	 */
	public HorarioPersonalSie getObjHorarioPersonal() {
		return objHorarioPersonal;
	}

	/**
	 * @param objHorarioPersonal the objHorarioPersonal to set
	 */
	public void setObjHorarioPersonal(HorarioPersonalSie objHorarioPersonal) {
		this.objHorarioPersonal = objHorarioPersonal;
	}

	/**
	 * @return the listaHorario
	 */
	public List<HorarioPersonalSie> getListaHorario() {
		return listaHorario;
	}

	/**
	 * @param listaHorario the listaHorario to set
	 */
	public void setListaHorario(List<HorarioPersonalSie> listaHorario) {
		this.listaHorario = listaHorario;
	}
		
	public void onEdit(RowEditEvent event) {
	        /*FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getModel());

	        FacesContext.getCurrentInstance().addMessage(null, msg);*/
	}
	    
	public void onCancel(RowEditEvent event) {
	        /*FacesMessage msg = new FacesMessage("Car Cancelled", ((Car) event.getObject()).getModel());

	        FacesContext.getCurrentInstance().addMessage(null, msg);*/
	}

	/**
	 * @return the diaList
	 */
	public List<String> getDiaList() {
		return diaList;
	}

	/**
	 * @param diaList the diaList to set
	 */
	public void setDiaList(List<String> diaList) {
		this.diaList = diaList;
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
	
}
