package com.edicsem.pe.sie.util.redirections;

import javax.faces.context.FacesContext;
/**
 * Metodo que realiza la interaccion de redireccionamiento a un modulo del sistema
 * que esta contenida en una carpeta
 * 
 * */
public class Redirections {

	public static final void redirectionsPage(String modulo, String page) {
		try {
			FacesContext contex = FacesContext.getCurrentInstance();
			contex.getExternalContext().redirect("edicsemperu/"+modulo+"/"+ page+".jsf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
