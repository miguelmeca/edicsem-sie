package com.edicsem.pe.sie.client.action;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;


@ManagedBean(name = "shedulecontroller")
@SessionScoped
public class ScheduleController implements Serializable {

	private Date horaingreso;
	private Date horasalida;
	private ScheduleModel eventModel;
	
	private ScheduleModel lazyEventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();
	
	private String theme;
	
	private Map<String, String> dias = new HashMap<String, String>();
	
	public ScheduleController() {
		eventModel = new DefaultScheduleModel();
		dias = new HashMap<String, String>();
		dias.put("lunes", "lunes");
		dias.put("martes", "martes");
		dias.put("miercoles", "miercoles");
		dias.put("jueves", "jueves");
		dias.put("viernes", "viernes");
		dias.put("sabado", "sabado");
		dias.put("domingo", "domingo");
	}

	public void addEvent(ActionEvent actionEvent) {
		if(event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);
		
		event = new DefaultScheduleEvent();
	}
	
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
		event = selectEvent.getScheduleEvent();
	}
	
	public void onDateSelect(DateSelectEvent selectEvent) {
		event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());
	}
	
	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		
		addMessage(message);
	}
	
	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		
		addMessage(message);
	}
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
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
	 * @return the dias
	 */
	public Map<String, String> getDias() {
		return dias;
	}

	/**
	 * @param dias the dias to set
	 */
	public void setDias(Map<String, String> dias) {
		this.dias = dias;
	}

	/**
	 * @return the horaingreso
	 */
	public Date getHoraingreso() {
		return horaingreso;
	}

	/**
	 * @param horaingreso the horaingreso to set
	 */
	public void setHoraingreso(Date horaingreso) {
		this.horaingreso = horaingreso;
	}

	/**
	 * @return the horasalida
	 */
	public Date getHorasalida() {
		return horasalida;
	}

	/**
	 * @param horasalida the horasalida to set
	 */
	public void setHorasalida(Date horasalida) {
		this.horasalida = horasalida;
	}


	
	//Getters and Setters
	
	
	
}
