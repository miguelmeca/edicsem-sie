package com.edicsem.pe.sie.client.action;

import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.LoginService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.property.PropertyFile;
import com.edicsem.pe.sie.util.sessions.SessionsSie;

@ManagedBean(name="login")
@SessionScoped
public class LoginAction {
	
	private EmpleadoSie objLogin = new EmpleadoSie();
	private EmpleadoSie objEmpleado;
	private static Log log = LogFactory.getLog(LoginAction.class);
	private Properties property = new PropertyFile().getProperties();
	private String usuario;
	private String contrasenia;
	
	@EJB private LoginService loginService;
	
	public LoginAction() {
		log.info("Entrando al Login...");
	}
	
	
	public String ingresarUsuario() {
		String redireccion = Constants.INDEX_PAGE;
		try {
			//Captura del Usuario y contraseña, Validando el Usuario
			objEmpleado = loginService.getValidarUsuario(objLogin);
			if (objEmpleado!= null) {
				//Se valida si la contraseña del usuario encontrado esta en lo cierto.
				if (StringUtils.equals(usuario.trim(), contrasenia.trim())) {
					log.info("usuario correcto...");
					log.info("usuario: "+ objEmpleado.getUsuario());
					log.info("contraseña: "+ objEmpleado.getContrasena());
					SessionsSie.putObjectInSession(Constants.USER_KEY, objEmpleado);
					
					//Se lista los menus del Usuario
					
				}
				
			}else {
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								property.getProperty("titulo.mensaje.error"),
								property.getProperty("login.usuario.noExiste")));
				redireccion = Constants.LOGIN_PAGE;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			redireccion = Constants.LOGIN_PAGE;
		}
		return redireccion;
	}


	/**
	 * @return the objLogin
	 */
	public EmpleadoSie getObjLogin() {
		return objLogin;
	}

	/**
	 * @param objLogin the objLogin to set
	 */
	public void setObjLogin(EmpleadoSie objLogin) {
		this.objLogin = objLogin;
	}

	/**
	 * @return the objEmpleado
	 */
	public EmpleadoSie getObjEmpleado() {
		return objEmpleado;
	}

	/**
	 * @param objEmpleado the objEmpleado to set
	 */
	public void setObjEmpleado(EmpleadoSie objEmpleado) {
		this.objEmpleado = objEmpleado;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	
}
