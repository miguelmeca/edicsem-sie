/**
 * @author FUCKING
 *
 */
package com.edicsem.pe.sie.client.action.mantenimiento;

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

import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoHorarioPuntoVentaSearchAction")
@SessionScoped
public class MantenimientoHorarioPuntoVentaSearchAction extends	BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(MantenimientoHorarioPuntoVentaSearchAction.class);
	/*Variables de Almacen Punto Venta*/
	private List<PuntoVentaSie> PuntoVentaList;
	private PuntoVentaSie objPuntoVentaSie;
	/*Variables de Horario Punto Venta*/
	private List<HorarioPuntoVentaSie> listaHorario;
	private HorarioPuntoVentaSie objHorarioPuntoVentaSie;
	/*schedule*/
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent(); 
    private String theme; 
    /*VARIABLES PARA MOSTRAR EN EL CALENDARIO*/
	private Date dDate, dDate2, dhoy;
	private int idpuntoventa;
	
	/*VARIABLES PARA NUEVO Y EDITAR */
	private boolean newRecord =false;
	private Date  horaIngreso,horaSalida;
	private String mensaje;
	private List<String> diaList;
	private boolean editMode;
	
	@EJB
	private AlmacenService objAlmacenService; 
	
	@EJB 
	private HorarioPuntoVentaService objHorarioPuntoVentaService;

	public List<PuntoVentaSie> getPuntoVentaList() {
		return PuntoVentaList;
	}
	
	public List<HorarioPuntoVentaSie> getListaHorario() {
		return listaHorario;
	}

	public MantenimientoHorarioPuntoVentaSearchAction(){
		log.info("inicializando mi constructor");
		init();
		
	}
	
	public String agregarhorario(){
		newRecord=true;
		return getViewList();
	}
	public String update() throws Exception {
		
		newRecord=false;		
		setHoraIngreso(objHorarioPuntoVentaSie.getHoraIngreso());
		setHoraSalida(objHorarioPuntoVentaSie.getHoraSalida());
		return getViewList();
	}
	
	public void init() {
		log.info("dentro del init");
		objPuntoVentaSie = new PuntoVentaSie();
		objHorarioPuntoVentaSie= new HorarioPuntoVentaSie();
		eventModel = new DefaultScheduleModel();
		idpuntoventa=0;
		log.info("despues de inicializar");
	}
/*METODO INSERTAR Y EDITAR*/
	public String insertar() throws Exception {
		mensaje="";
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar'  xd ");
				}
				if(idpuntoventa!=idpuntoventa){
					mensaje="Debe seleccionar un empleado";
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				else{
					if(newRecord){
					log.info("fecha pp  " + getHoraIngreso() );
					Time hora1 = new Time( getHoraIngreso().getTime());
					Time hora2= new Time( getHoraSalida().getTime());
					log.info(" g   "+ hora1 + " g   "+ hora2);
					objHorarioPuntoVentaSie.setHoraIngreso(hora1);
					objHorarioPuntoVentaSie.setHoraSalida(hora2);
					
					/** Validation **/
		
					//para que no hayan cruces de horario 
					for (int j = 0; j < diaList.size();j++) {
						for (int i = 0; i < listaHorario.size(); i++) {
							log.info("  verificando ");
							if( listaHorario.get(i).getTbFecha().getIdFecha()== Integer.parseInt(diaList.get(j))
								 && (objHorarioPuntoVentaSie.getDiainicio().after(listaHorario.get(i).getDiainicio()) && 
										 objHorarioPuntoVentaSie.getDiainicio().before(listaHorario.get(i).getDiafin() ) ||
										 objHorarioPuntoVentaSie.getDiafin().after(listaHorario.get(i).getDiainicio()) && 
										 objHorarioPuntoVentaSie.getDiafin().before(listaHorario.get(i).getDiafin() ) ||
										 objHorarioPuntoVentaSie.getDiafin().equals(listaHorario.get(i).getDiafin()) ||
										 objHorarioPuntoVentaSie.getDiafin().equals(listaHorario.get(i).getDiainicio())||
										 objHorarioPuntoVentaSie.getDiainicio().equals(listaHorario.get(i).getDiafin())||
										 objHorarioPuntoVentaSie.getDiainicio().equals(listaHorario.get(i).getDiainicio()))){
								log.info("fechas con horario registrado   ");
								if(	objHorarioPuntoVentaSie.getHoraIngreso().equals(listaHorario.get(i).getHoraIngreso()) ||
										objHorarioPuntoVentaSie.getHoraIngreso().after(listaHorario.get(i).getHoraIngreso())
										&& objHorarioPuntoVentaSie.getHoraIngreso().before(listaHorario.get(i).getHoraSalida())){
									log.info(objHorarioPuntoVentaSie.getHoraIngreso() +" --->  getHoraIngreso "+ listaHorario.get(i).getHoraIngreso());
									mensaje = "La hora de Ingreso es errónea";
									break;
								}else if(objHorarioPuntoVentaSie.getHoraSalida().equals(listaHorario.get(i).getHoraSalida()) ||
										objHorarioPuntoVentaSie.getHoraSalida().after(listaHorario.get(i).getHoraIngreso())
										&& objHorarioPuntoVentaSie.getHoraSalida().before(listaHorario.get(i).getHoraSalida())){
									log.info(objHorarioPuntoVentaSie.getHoraSalida()+" --->  getHoraSalida "+listaHorario.get(i).getHoraSalida());
									mensaje = "La hora de Salida es errónea";
									break;
									}
								}
							}
						}
					 if(mensaje.equals("")){
						 objHorarioPuntoVentaService.insertHorarioPunto(diaList,objHorarioPuntoVentaSie,objPuntoVentaSie.getIdpuntoventa());
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
					objHorarioPuntoVentaSie.setHoraIngreso(hora1);
					objHorarioPuntoVentaSie.setHoraSalida(hora2);
					
					objHorarioPuntoVentaService.updateHorarioPunto(objHorarioPuntoVentaSie);
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
		objHorarioPuntoVentaSie= new HorarioPuntoVentaSie();
		return mostrar();
	}	
	
	
/*LISTA LOS ALMACENES*/
	public String listar() {
		log.info("listarAlmacen en Search' ");
		PuntoVentaList =  objAlmacenService.listarPuntoVenta();

		if (PuntoVentaList == null) {
			PuntoVentaList = new ArrayList<PuntoVentaSie>();
		}
		return getViewList();
	}
	
/*LISTA LOS HORARIOS DEL ALMACEN SELECCIONADO*/
	public String mostrar() throws Exception {
		eventModel = new DefaultScheduleModel(); 
		log.info("mostrar horario de PUNTO DE VENTA");
				log.info("listarHorario del personal");
				listaHorario = objHorarioPuntoVentaService.listarHorarioPuntoVentaXidPV(objPuntoVentaSie.getIdpuntoventa());
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPuntoVentaSie>();
				}
				for(int i=0;i < listaHorario.size(); i++){
					objHorarioPuntoVentaSie= listaHorario.get(i);
	               
					 Calendar cal = new GregorianCalendar();
		            	log.info(" xd "+ objHorarioPuntoVentaSie.getDiainicio());
		            	cal.setTime( objHorarioPuntoVentaSie.getDiainicio());
		                
		                Calendar cal3 = new GregorianCalendar();
		            	cal3.setTime(objHorarioPuntoVentaSie.getDiainicio());
		            	
		            	Calendar cal4 = new GregorianCalendar();
		            	cal4.setTime(objHorarioPuntoVentaSie.getDiafin());
		                log.info(" fecha  " + cal.getTime() +"  time fin "+cal4.getTime());
		                
		                while (cal.before(cal4)||cal.equals(cal4)){
		                	log.info("en el primer while  fecha inicio ");
		                	log.info(" ***   getime  " + cal.getTime());
			              if(cal.get(Calendar.DAY_OF_WEEK) == objHorarioPuntoVentaSie.getTbFecha().getIdFecha()) {
			                	log.info("en el seggundo  while");
			                	dDate = cal.getTime();
			                	Calendar cal0 = new GregorianCalendar();
			                	Calendar cal33 = new GregorianCalendar();
			 	            	String[] a= (objHorarioPuntoVentaSie.getHoraIngreso()+"").split(":");
			 	            	cal0.setTime(dDate);
			 	            	cal0.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a[0]));
			 	            	cal0.add(Calendar.MINUTE, Integer.parseInt(a[1]));
			 	            	dDate = cal0.getTime();
			 	            	dDate2 = cal3.getTime();
			 	            	String[] a2= (objHorarioPuntoVentaSie.getHoraSalida()+"").split(":");
			 	            	cal33.setTime(dDate2);
			 	            	cal33.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a2[0]));
			 	            	cal33.add(Calendar.MINUTE, Integer.parseInt(a2[1]));
			 	            	dDate2 = cal33.getTime();
			 	                log.info(" dia 1 " +dDate);
			 	                log.info(" dia 2 " +dDate2);
		 	                
		                eventModel.addEvent(new DefaultScheduleEvent(objHorarioPuntoVentaSie.getObservacion()+" de "+ objHorarioPuntoVentaSie.getHoraIngreso()+" hasta "+ 
		                		objHorarioPuntoVentaSie.getHoraSalida(), dDate, dDate2));  
		                }
		                
			              cal.add(Calendar.DAY_OF_WEEK,1);
		                	cal3.add(Calendar.DAY_OF_WEEK,1);
		                }
		             
				}
		return getViewList();
	}
	
	
	public String getViewList() {
		return Constants.MANT_HORARIO_PUNTO_VENTA;
	}
	

	/**
	 *METODOS GET Y SET
	 */

/**
 * @param puntoVentaList the puntoVentaList to set
 */
public void setPuntoVentaList(List<PuntoVentaSie> puntoVentaList) {
	PuntoVentaList = puntoVentaList;
}
/**
 * @return the objPuntoVentaSie
 */
public PuntoVentaSie getObjPuntoVentaSie() {
	return objPuntoVentaSie;
}
/**
 * @param objPuntoVentaSie the objPuntoVentaSie to set
 */
public void setObjPuntoVentaSie(PuntoVentaSie objPuntoVentaSie) {
	this.objPuntoVentaSie = objPuntoVentaSie;
}

/**
 * @return the listaHorario
 */

/**
 * @param listaHorario the listaHorario to set
 */
public void setListaHorario(List<HorarioPuntoVentaSie> listaHorario) {
	this.listaHorario = listaHorario;
}

/**
 * @return the objHorarioPuntoVentaSie
 */
public HorarioPuntoVentaSie getObjHorarioPuntoVentaSie() {
	return objHorarioPuntoVentaSie;
}

/**
 * @param objHorarioPuntoVentaSie the objHorarioPuntoVentaSie to set
 */
public void setObjHorarioPuntoVentaSie(
		HorarioPuntoVentaSie objHorarioPuntoVentaSie) {
	this.objHorarioPuntoVentaSie = objHorarioPuntoVentaSie;
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
 * @return the idpuntoventa
 */
public int getIdpuntoventa() {
	return idpuntoventa;
}

/**
 * @param idpuntoventa the idpuntoventa to set
 */
public void setIdpuntoventa(int idpuntoventa) {
	this.idpuntoventa = idpuntoventa;
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
	



}
