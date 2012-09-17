package com.edicsem.pe.sie.client.action;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.LoginService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean
@SessionScoped
/* colocarlo en public class LoginBean extends SessionsSie 
 * 
 * extends BaseMantenimientoAbstractAction
 * */
public class LoginBean  {

	private String usuario;
	private String contrasena;
	private EmpleadoSie objEmpleado;
	public static Log log = LogFactory.getLog(LoginBean.class);
	
	@EJB
	private LoginService objLoginService;

	public  EmpleadoSie getObjEmpleado() {
		return objEmpleado;
	}

	public void setObjEmpleado( EmpleadoSie objEmpleado) {
		this.objEmpleado = objEmpleado;
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		LoginBean.log = log;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	/*
	public String verificarLogin(){
		
		String redireccion = "";
		if (objLoginService.validacionLogin(usuario, contrasena)) {
			redireccion="demo";
		}else {
			redireccion="logeo";
		}
		return redireccion;  //solo el nombre de la pagina ya que el web.xml hace el mappin de todo lo que retorna
		
	}
	*/
	public void verificarLogin(ActionEvent e) throws IOException {
		//cuando utilizars ActionListener tienes que llamar al metodo ActionEvent para que el JSF lo reconosca
		//pero si lo quieres para un login, no sera tan fuert, como un metodo con redireccion.
		//me explico si quieres hacer un login este devería retornar un String(nombre de la pagina)
	FacesContext contex = FacesContext.getCurrentInstance();
	try {
			//Declara una variable
		if (objLoginService.validacionLogin(usuario, contrasena)) {
				// contex.getExternalContext().redirect("/inbound/bien.xhtml");
			contex.getExternalContext().redirect("/edicsemperu/menu.jsf");//y si quieres redireccionar no es mnue.xhtml,
					//por lo que es un formato xml, y nunca te va a mostrar nada, pero si pones con jsf de seguro
			//que te mostrará algo.
		} else {
			contex.getExternalContext().redirect("/edicsemperu/demo.jsf");
		}
	} catch (Exception e1) {
		e1.printStackTrace();
	contex.getExternalContext().redirect("/edicsemperu/mal.jsf");
	}
}
	
	  public void savePerson(ActionEvent actionEvent) {  
	    	
	    	
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + usuario + " " + contrasena + "!"));  
	    }

	  
	  
	  
	//@Override
	//public String getViewList() {
		
		
		
		
		// TODO Auto-generated method stub
		//return "demo";
	//} 

}
