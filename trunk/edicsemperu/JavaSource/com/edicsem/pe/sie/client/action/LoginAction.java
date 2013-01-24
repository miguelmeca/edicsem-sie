package com.edicsem.pe.sie.client.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.AuditoriaUsuarioSie;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.AuditoriaUsuarioService;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService;
import com.edicsem.pe.sie.service.facade.LoginService;
import com.edicsem.pe.sie.service.facade.ModuloOpcionService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import com.edicsem.pe.sie.util.property.PropertyFile;
import com.edicsem.pe.sie.util.security.SecurityLogin;
import com.edicsem.pe.sie.util.sessions.SessionsSie;

@ManagedBean(name="login")
@SessionScoped
public class LoginAction extends BaseMantenimientoAbstractAction{
	
	private EmpleadoSie objLogin = new EmpleadoSie();
	private EmpleadoSie objEmpleado;
	private static Log log = LogFactory.getLog(LoginAction.class);
	private Properties property ;
	private String usuario, passActual, passConfirma1, passConfirma2;
	private String contrasenia;
	private MenuModel mimenu;
	private List<CargoEmpleadoSie> cargo;
	private String fechaHoraIngreso, mensaje;
	
	@EJB
	private AuditoriaUsuarioService auditoriaService;
	@EJB
	private LoginService loginService;
	@EJB
	private DetPermisoEmpleadoService detPermisoEmplService;
	@EJB
	private ModuloOpcionService moduloService;
	@EJB
	private CargoEmpleadoService cargoService;
	
	public LoginAction() {
		log.info("Entrando al Login...");
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init ...");
		mimenu = new DefaultMenuModel();
		property = new PropertyFile().getProperties();
		cargo=new ArrayList<CargoEmpleadoSie>();
	}
	
	public String ingresarUsuario() {
		String redireccion = Constants.INDEX_PAGE;
		List<MenuDTO> lstMenu = new ArrayList<MenuDTO>();
		List<String> lstTipo = new ArrayList<String>();
		try {
			log.info("usuario  :D ...  "+usuario+" contraseña "+contrasenia);
			//Captura del Usuario y contraseña, Validando el Usuario
			objEmpleado= loginService.validacionLogin(usuario,SecurityLogin.getMD5(contrasenia));
			if (objEmpleado!=null) {
				//Se valida si la contraseña del usuario encontrado esta en lo cierto.
					log.info("usuario correcto...");
					log.info("Nombres completos : "+ objEmpleado.getNombresCompletos());
					log.info("contraseña: "+ objEmpleado.getContrasena());
					SessionsSie.putObjectInSession(Constants.USER_KEY, objEmpleado);

					//Se lista los menus del Usuario
					lstMenu = detPermisoEmplService.listarPermisoXUsuario(objEmpleado.getUsuario());
					
					// Listar Tipos de Modulo
					lstTipo = moduloService.listarModuloOpcion();
					
					mimenu = new DefaultMenuModel();
					MenuItem item = new MenuItem();
					for (String q : lstTipo) {
						Submenu submenu= new Submenu();
						submenu.setLabel(q.toString().trim());
						for (MenuDTO a : lstMenu) {
							if (a.getTipodeMenu().equals(q.substring(0))) {
								item = new MenuItem();
								item.setValue(a.getNombreMenu());
								item.setAjax(false);
								
								if (a.getNombreActionListener() != null && a.getNombreActionListener().isEmpty() == false) {
									item.setActionExpression(getMethod(a.getNombreActionListener()));
									item.setUrl(null);
								}else if(a.getUrlMenu()!= null && a.getUrlMenu().isEmpty() == false){
									item.setUrl(a.getUrlMenu());
								}
								log.info(" action: "+a.getNombreActionListener());
								log.info("nomb menu: "+a.getNombreMenu()+" url: "+a.getUrlMenu());
								submenu.getChildren().add(item);
							}
						}
						if (submenu.getChildCount()!=0) {
							mimenu.addSubmenu(submenu);
						}
					}
					Date n= new Date();
					AuditoriaUsuarioSie a =auditoriaService.listarAuditoriaUsuario(usuario);
					
					if(a==null){
						a = new AuditoriaUsuarioSie();
						a.setUsuario(usuario);
						auditoriaService.insertAuditoriaUsuario(a);
					}else{
						log.info("fecha de creacion*  "+ a.getFechacreacion());
						n= a.getFechacreacion();
						log.info("fecha de n*  "+ n);
						a = new AuditoriaUsuarioSie();
						a.setUsuario(usuario);
						auditoriaService.insertAuditoriaUsuario(a);
					}
					
					fechaHoraIngreso = DateUtil.getDateTime("dd/MM/yyyy HH:mm:ss", n);
					log.info(" fecha hora " + fechaHoraIngreso);
					
					log.info(" listando cargos " +objEmpleado.getIdempleado()   );
					cargo = cargoService.listarCargosXEmpleado(objEmpleado.getIdempleado());
					for (int i = 0; i < cargo.size(); i++) {
						log.info(" ccc " +cargo.get(i).getIdcargoempleado());
					}
					SessionsSie.putObjectInSession(Constants.CARGO_USER, cargo);
					
			}else {
				log.info("usuario incorrecto!! ...  ");
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						Constants.MESSAGE_ERROR_TITULO, "usuario y/o contraseña incorrecta");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				redireccion = Constants.LOGIN_PAGE;
				
//				FacesContext.getCurrentInstance().addMessage(
//						null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//								property.getProperty("titulo.mensaje.error"),
//								property.getProperty("login.usuario.noExiste")));
//				redireccion = Constants.LOGIN_PAGE;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			redireccion = Constants.LOGIN_PAGE;
		}
		return redireccion;
	}
	
	public void cerrarSesion() {
		log.info("cerrarSesion() ");
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constants.USER_KEY, false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constants.CARGO_USER, false);
        try {
            context.getExternalContext().redirect("/edicsemperu/login.jsf");
        } catch (IOException ex) {
        	log.error(ex.getMessage());
        	ex.printStackTrace();
        }
    }
	
	public void cambiarContrasena(){
		log.info("actual  "+passActual+" confirma nueva1  "+passConfirma1+"  confirma nueva2 "+passConfirma2);
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		try {
			String pass =SecurityLogin.getMD5(passConfirma1);
			EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
			//Validar la contraseña actual
			if(sessionUsuario.getContrasena().equals(SecurityLogin.getMD5(passActual))){
			
			loginService.updatePassword(sessionUsuario.getIdempleado(), pass);
			sessionUsuario.setContrasena(pass);
			SessionsSie.putObjectInSession(Constants.USER_KEY, sessionUsuario);
			setMensaje("Se actualizó correctamente su contraseña");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Constants.MESSAGE_INFO_TITULO, getMensaje());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				setMensaje("Contraseña actual incorrecta");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					Constants.MESSAGE_INFO_TITULO, getMensaje());
					FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Constants.MESSAGE_ERROR_TITULO, e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public MethodExpression getMethod(String actionListenerName) {
		ExpressionFactory context = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		MethodExpression mExp = context.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), actionListenerName, String.class, new Class[]{});
		return mExp;
	}
//	
//	public MethodExpressionActionListener getActionListenerExp(String actionListenerName) {
//		FacesContext context = FacesContext.getCurrentInstance();
//		MethodExpression mExp = context
//				.getApplication()
//				.getExpressionFactory()
//				.createMethodExpression(context.getELContext(),
//						actionListenerName, null,
//						new Class[] { ActionEvent.class });
//		return new MethodExpressionActionListener(mExp);
//	}
	
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


	/**
	 * @return the mimenu
	 */
	public MenuModel getMimenu() {
		return mimenu;
	}


	/**
	 * @param mimenu the mimenu to set
	 */
	public void setMimenu(MenuModel mimenu) {
		this.mimenu = mimenu;
	}

	/**
	 * @return the passActual
	 */
	public String getPassActual() {
		return passActual;
	}

	/**
	 * @param passActual the passActual to set
	 */
	public void setPassActual(String passActual) {
		this.passActual = passActual;
	}

	/**
	 * @return the passConfirma1
	 */
	public String getPassConfirma1() {
		return passConfirma1;
	}

	/**
	 * @param passConfirma1 the passConfirma1 to set
	 */
	public void setPassConfirma1(String passConfirma1) {
		this.passConfirma1 = passConfirma1;
	}

	/**
	 * @return the passConfirma2
	 */
	public String getPassConfirma2() {
		return passConfirma2;
	}

	/**
	 * @param passConfirma2 the passConfirma2 to set
	 */
	public void setPassConfirma2(String passConfirma2) {
		this.passConfirma2 = passConfirma2;
	}

	/**
	 * @return the fechaHoraIngreso
	 */
	public String getFechaHoraIngreso() {
		return fechaHoraIngreso;
	}

	/**
	 * @param fechaHoraIngreso the fechaHoraIngreso to set
	 */
	public void setFechaHoraIngreso(String fechaHoraIngreso) {
		this.fechaHoraIngreso = fechaHoraIngreso;
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

}
