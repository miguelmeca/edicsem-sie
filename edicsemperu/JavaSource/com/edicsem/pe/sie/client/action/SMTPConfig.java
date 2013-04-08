package com.edicsem.pe.sie.client.action;

import java.security.Security;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="SMTPConfig")
@SessionScoped
public class SMTPConfig extends BaseMantenimientoAbstractAction {
	 
	/**
	  * @param titulo : titulo del mensaje
	  * @param mensaje : Cuerpo del Mensaje
	  * @param paraEmail : Email receptor del mensaje
	  * @return true si el envío es conforme y false si no es así.
	  */
	  
	 public static synchronized  boolean sendMail(String titulo, String mensaje, String paraEmail) { 
	 boolean envio=false;
	   
	  try {
	    
	   Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	    
	   //Propiedades de la conexion
	   Properties propiedades = new Properties();
	   propiedades.put("mail.smtp.host","smtp.gmail.com");
	   propiedades.put("mail.smtp.port","587");
	   propiedades.setProperty("mail.smtp.starttls.enable","true");
	   propiedades.setProperty("mail.smtp.auth","true");
	   propiedades.setProperty("mail.smtp.user","soporte.edicsem.karen@gmail.com");
	    
	   //Preparamos la Sesion autenticando al usuario
	   Session session = Session.getDefaultInstance(propiedades);
	    
	   //Preparamos el Mensaje
	   MimeMessage message = new MimeMessage(session);
	   message.setFrom(new InternetAddress("jkrlos2791@gmail.com"));
	   message.setSubject(titulo);
	   message.addRecipient(RecipientType.TO, new InternetAddress(paraEmail));
	   message.setContent(mensaje, "text/html; charset=utf-8");
	
	   //envío del mensaje
	   Transport transporte = session.getTransport("smtp");
	   transporte.connect("soporte.edicsem.karen@gmail.com","/12345678");
	   transporte.sendMessage(message,message.getAllRecipients());
       transporte.close();
	   envio = true;
	   
	  } catch (Throwable e) {
	   envio = false;
	   System.out.println(e.getMessage());
	   e.printStackTrace();
	   
	  }
	  
	  return envio;
	 }
	
}
