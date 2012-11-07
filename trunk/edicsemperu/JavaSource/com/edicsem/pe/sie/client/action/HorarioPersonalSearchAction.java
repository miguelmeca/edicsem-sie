package com.edicsem.pe.sie.client.action;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

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
    /*schedule*/
	private ScheduleModel eventModel;  
    private ScheduleModel lazyEventModel;  
    private ScheduleEvent event = new DefaultScheduleEvent(); 
    private String theme;  
    private HorarioPersonalSie h; 
	/*fin schedule*/
	private List<String> diaList;
	private Date fecPagoNull, horaIngreso,horaSalida;
	private int tipollamada; 
	private boolean editMode;
	private String mensaje;
	private int idempleado;
	private boolean newRecord =false;
	private int ide;
	private Date dDate, dDate2;
	
	@EJB 
	private HorarioPersonalService objHorarioPersonalService;
	
	public static Log log = LogFactory.getLog(HorarioPersonalSearchAction.class);
	
	public HorarioPersonalSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init() xxxxxxxx :D ");
		// Colocar valores inicializados
		objHorarioPersonal = new HorarioPersonalSie();
		h= new HorarioPersonalSie();
		eventModel = new DefaultScheduleModel(); 
		log.info("despues de inicializar  ");
	}
	
	public void addEvent(ActionEvent actionEvent) { 
		log.info("addEvent");
        if(event.getId() == null)  
            eventModel.addEvent(event);  
        else  
            eventModel.updateEvent(event);  
          
        event = new DefaultScheduleEvent();  
    }  
      
    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
    	log.info("onEventSelect");
    	event = selectEvent.getScheduleEvent();  
    }  
      
    public void onDateSelect(DateSelectEvent selectEvent) {  
    	log.info("onDateSelect");
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());  
    }  
      
    public void onEventMove(ScheduleEntryMoveEvent event) {  
    	log.info("onEventMove");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
    	log.info("onEventResize");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }
	
    private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}
	
	private Date previousDay8Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);
		
		return t.getTime();
	}
	
	private Date previousDay11Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 11);
		
		return t.getTime();
	}
	
	private Date today1Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 1);
		
		return t.getTime();
	}
	
	private Date theDayAfter3Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);		
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 3);
		
		return t.getTime();
	}

	private Date today6Pm() {
		Calendar t = (Calendar) today().clone(); 
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 6);
		
		return t.getTime();
	}
	
	private Date nextDay9Am() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 9);
		
		return t.getTime();
	}
	
	private Date nextDay11Am() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 11);
		
		return t.getTime();
	}
	
	private Date fourDaysLater3pm() {
		Calendar t = (Calendar) today().clone(); 
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
		t.set(Calendar.HOUR, 3);
		//objtelefono = new TelefonoPersonaSie();
		log.info("despues de inicializar  ");
		return t.getTime();
	}
	
	public String mostrar() throws Exception {
		eventModel = new DefaultScheduleModel(); 
		log.info("mostrar horario de empleados");
				log.info("listarHorario del personal");
				listaHorario = objHorarioPersonalService.listarHorarioPersonalXempleado(idempleado);
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPersonalSie>();
				}
				log.info(" ------------ xxxxxx :D ");
				for(int i=0;i < listaHorario.size(); i++){
					objHorarioPersonal.setDescripcion(listaHorario.get(i).getDescripcion());
					objHorarioPersonal.setDiainicio(listaHorario.get(i).getDiainicio());
					objHorarioPersonal.setDiafin(listaHorario.get(i).getDiafin());
					objHorarioPersonal.setHoraIngreso(listaHorario.get(i).getHoraIngreso());
					objHorarioPersonal.setHoraSalida(listaHorario.get(i).getHoraSalida());
			
					Date d =new Date();
					d.setTime(listaHorario.get(i).getDiainicio().getTime());
					d.setTime(listaHorario.get(i).getHoraIngreso().getTime());		
					Calendar c = new GregorianCalendar();
					c.setTime(listaHorario.get(i).getDiainicio());
					
					Calendar c2 = new GregorianCalendar();
					c2.setTime(listaHorario.get(i).getDiafin());
					c2.setTime(listaHorario.get(i).getHoraIngreso());
					
					eventModel.addEvent(new DefaultScheduleEvent(objHorarioPersonal.getDescripcion(), c.getTime(), c2.getTime()));  
					objHorarioPersonal= listaHorario.get(i);
	                dDate = objHorarioPersonal.getDiainicio();
	                Calendar cal = new GregorianCalendar();
	            	String[] a= (objHorarioPersonal.getHoraIngreso()+"").split(":");
	            	log.info(" xd "+dDate);
	            	cal.setTime(dDate);
	            	log.info(" fecha xxx "+cal.getTime());
	            	cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a[0]));
	            	cal.add(Calendar.MINUTE, Integer.parseInt(a[1]));
	            	dDate = cal.getTime();
	            	log.info(" fecha 1 "+dDate);
	                dDate2 = objHorarioPersonal.getDiainicio();
	                Calendar cal3 = new GregorianCalendar();
	            	String[] a2= (objHorarioPersonal.getHoraSalida()+"").split(":");
	            	log.info(" xd "+dDate2);
	            	cal3.setTime(dDate2);
	            	cal3.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a2[0]));
	            	cal3.add(Calendar.MINUTE, Integer.parseInt(a2[1]));
	            	dDate2 = cal3.getTime();
	            	log.info(" fecha 2 "+dDate2);
	                log.info(" dia 1 " +dDate+ " dia 2 " +dDate2);
	                log.info("  cccccc  " + dDate.getDate()+" "   +  dDate.getDay());
					eventModel.addEvent(new DefaultScheduleEvent(objHorarioPersonal.getDescripcion()+" de "+ objHorarioPersonal.getHoraIngreso()+" hasta "+ objHorarioPersonal.getHoraSalida(), dDate, dDate2));  
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
									mensaje = "La hora de Ingreso es err�nea";
									break;
								}else if(objHorarioPersonal.getHoraSalida().equals(listaHorario.get(i).getHoraSalida()) ||
										objHorarioPersonal.getHoraSalida().after(listaHorario.get(i).getHoraIngreso())
										&& objHorarioPersonal.getHoraSalida().before(listaHorario.get(i).getHoraSalida())){
									log.info(objHorarioPersonal.getHoraSalida()+" --->  getHoraSalida "+listaHorario.get(i).getHoraSalida());
									mensaje = "La hora de Salida es err�nea";
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
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		
		
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);	//set random day of month

		return date.getTime();
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
	 * @return the lazyEventModel
	 */
	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	/**
	 * @param lazyEventModel the lazyEventModel to set
	 */
	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
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
	 * @return the h
	 */
	public HorarioPersonalSie getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(HorarioPersonalSie h) {
		this.h = h;
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

}
