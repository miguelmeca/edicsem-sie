package com.edicsem.pe.sie.util.FaceMessage;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FaceMessage {
	
	public static void FaceMessageInfo(String titulo, String mensaje) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage("Información", msg);
	}
	
	public static void FaceMessageError(String titulo, String mensaje) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void FaceMessageWarning(String titulo, String mensaje) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void FaceMessageErrorFatal(String titulo, String mensaje) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
