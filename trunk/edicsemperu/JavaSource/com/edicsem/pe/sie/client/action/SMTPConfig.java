package com.edicsem.pe.sie.client.action;

import java.security.Security;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "SMTPConfig")
@SessionScoped
public class SMTPConfig extends BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(SMTPConfig.class);

	/**
	 * @param titulo : titulo del mensaje
	 * @param mensaje : Cuerpo del Mensaje
	 * @param paraEmail : Email receptor del mensaje
	 * @return true si el envío es conforme y false si no es así.
	 */

	public static synchronized boolean sendMail(String titulo, String mensaje, String paraEmail) {
		log.info("sendMail!! .. mail ");
		boolean envio = false;
		// Capturando el empleado en session
		HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie) session1.getAttribute(Constants.USER_KEY);
		
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			
			// Propiedades de la conexion
			Properties propiedades = new Properties();
			propiedades.put("mail.smtp.host", "mail.edicsem.com");
			propiedades.put("mail.smtp.port", "25");
			propiedades.setProperty("mail.smtp.starttls.enable", "false");
			propiedades.setProperty("mail.smtp.auth", "true");
			propiedades.setProperty("mail.smtp.user", sessionUsuario.getCorreo());

			// Preparamos la Sesion autenticando al usuario
			Session session = Session.getDefaultInstance(propiedades);

			// Preparamos el Mensaje
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sessionUsuario.getCorreo()));
			message.setSubject(titulo);
			message.addRecipient(RecipientType.TO, new InternetAddress(paraEmail));
			message.setContent(mensaje, "text/html; charset=utf-8");

			// envío del mensaje
			Transport transporte = session.getTransport("smtp");
			log.info("correo: "+sessionUsuario.getCorreo()+" contraseña: "+ sessionUsuario.getContrasenacorreo());
			transporte.connect(sessionUsuario.getCorreo(), sessionUsuario.getContrasenacorreo());
			transporte.sendMessage(message, message.getAllRecipients());
			transporte.close();
			envio = true;

		} catch (Exception e) {
			log.info("Mensaje: "+e.getMessage()+" Causa: "+e.getMessage());
			envio = false;
			e.printStackTrace();
		}
		return envio;
	}

}
