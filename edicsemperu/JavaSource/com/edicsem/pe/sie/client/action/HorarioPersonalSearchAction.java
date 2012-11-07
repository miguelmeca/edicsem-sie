package com.edicsem.pe.sie.client.action;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="horarioPersonal")
@SessionScoped
public class HorarioPersonalSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private HorarioPersonalSie objHorarioPersonal;
	private List<HorarioPersonalSie> listaHorario;
    /*schedule*/
	private ScheduleModel eventModel;  
  
    private ScheduleEvent event = new DefaultScheduleEvent(); 
    private String theme;  
	/*fin schedule*/
	private List<String> diaList;
	private Date  horaIngreso,horaSalida;
	private int idhorario; 
	private boolean editMode;
	private String mensaje;
	private int idempleado;
	private boolean newRecord =false;
	private int ide;
	private Date dDate, dDate2, dhoy;
	
	@EJB 
	private HorarioPersonalService objHorarioPersonalService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;

	public static Log log = LogFactory.getLog(HorarioPersonalSearchAction.class);
	
	public HorarioPersonalSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init()");
		idempleado=0;
		objHorarioPersonal = new HorarioPersonalSie();
		eventModel = new DefaultScheduleModel(); 
		log.info("despues de inicializar  ");
	}
	
	public String mostrar() throws Exception {
		eventModel = new DefaultScheduleModel(); 
		log.info("mostrar horario de empleados");
				log.info("listarHorario del personal");
				listaHorario = objHorarioPersonalService.listarHorarioPersonalXempleado(idempleado);
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPersonalSie>();
				}
				for(int i=0;i < listaHorario.size(); i++){
					objHorarioPersonal= listaHorario.get(i);
	               
	                Calendar cal = new GregorianCalendar();
	            	log.info(" xd "+ objHorarioPersonal.getDiainicio());
	            	cal.setTime( objHorarioPersonal.getDiainicio());
	                
	                Calendar cal3 = new GregorianCalendar();
	            	cal3.setTime(objHorarioPersonal.getDiainicio());
	            	
	            	Calendar cal4 = new GregorianCalendar();
	            	cal4.setTime(objHorarioPersonal.getDiafin());
	                log.info(" fecha  " + cal.getTime() +"  time fin "+cal4.getTime());
	                
	                while (cal.before(cal4)||cal.equals(cal4)){
	                	log.info("en el primer while  fecha inicio ");
	                	log.info(" ***   getime  " + cal.getTime());
		              if(cal.get(Calendar.DAY_OF_WEEK) == objHorarioPersonal.getTbFecha().getIdFecha()) {
		                	log.info("en el seggundo  while");
		                	dDate = cal.getTime();
		                	Calendar cal0 = new GregorianCalendar();
		                	Calendar cal33 = new GregorianCalendar();
		 	            	String[] a= (objHorarioPersonal.getHoraIngreso()+"").split(":");
		 	            	cal0.setTime(dDate);
		 	            	cal0.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a[0]));
		 	            	cal0.add(Calendar.MINUTE, Integer.parseInt(a[1]));
		 	            	dDate = cal0.getTime();
		 	            	dDate2 = cal3.getTime();
		 	            	String[] a2= (objHorarioPersonal.getHoraSalida()+"").split(":");
		 	            	cal33.setTime(dDate2);
		 	            	cal33.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a2[0]));
		 	            	cal33.add(Calendar.MINUTE, Integer.parseInt(a2[1]));
		 	            	dDate2 = cal33.getTime();
		 	                log.info(" dia 1 " +dDate);
		 	                log.info(" dia 2 " +dDate2);
		 	                
		                eventModel.addEvent(new DefaultScheduleEvent(objHorarioPersonal.getDescripcion()+" de "+ objHorarioPersonal.getHoraIngreso()+" hasta "+ 
		                objHorarioPersonal.getHoraSalida(), dDate, dDate2));  
		                }
		              cal.add(Calendar.DAY_OF_WEEK,1);
	                	cal3.add(Calendar.DAY_OF_WEEK,1);
	                }
				}
		return getViewList();
	}
    
	public String agregarhorario(){
		newRecord=true;
		return getViewList();
	}
	public String updateDeshabilitar() throws Exception{
		 
		if (log.isInfoEnabled()) {
			log.info("Entering my method 'DeshabilitarHorarioPersonal()'" + getIde());
		}
			
			log.info(" parametro ID "+ getIde());
			objHorarioPersonal = objHorarioPersonalService.findHorarioPersonal(getIde());
			objHorarioPersonal.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(37));

			objHorarioPersonalService.updateHorarioPersonal(objHorarioPersonal);
			log.info("actualizando..... ");
 
		return mostrar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	
	public String update() throws Exception {
		newRecord=false;
		
		setHoraIngreso(objHorarioPersonal.getHoraIngreso());
		setHoraSalida(objHorarioPersonal.getHoraSalida());
		return getViewList();
	}
	
	public String insertar() throws Exception {
		mensaje="";
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar'  xd ");
				}
				if(idempleado==0){
					mensaje="Debe seleccionar un empleado";
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				else{
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
					log.info("actualizando " + getHoraIngreso()+"  hora salida  "+getHoraSalida() );
					Time hora1 = new Time( getHoraIngreso().getTime());
					Time hora2= new Time( getHoraSalida().getTime());
					log.info(" g   "+ hora1);
					objHorarioPersonal.setHoraIngreso(hora1);
					objHorarioPersonal.setHoraSalida(hora2);
					
					objHorarioPersonalService.updateHorarioPersonal(objHorarioPersonal);
				}
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

	/**
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	/**
	 * @param eventModel the eventModel to set
	 */
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the dDate
	 */
	public Date getdDate() {
		return dDate;
	}

	/**
	 * @param dDate the dDate to set
	 */
	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}

	/**
	 * @return the dDate2
	 */
	public Date getdDate2() {
		return dDate2;
	}

	/**
	 * @param dDate2 the dDate2 to set
	 */
	public void setdDate2(Date dDate2) {
		this.dDate2 = dDate2;
	}

	/**
	 * @return the dhoy
	 */
	public Date getDhoy() {
		try {
			dhoy = DateUtil.getToday().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dhoy;
	}

	/**
	 * @param dhoy the dhoy to set
	 */
	public void setDhoy(Date dhoy) {
		this.dhoy = dhoy;
	}

	/**
	 * @return the idhorario
	 */
	public int getIdhorario() {
		return idhorario;
	}

	/**
	 * @param idhorario the idhorario to set
	 */
	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}

}
