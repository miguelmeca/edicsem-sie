package com.edicsem.pe.sie.client.servlet;

import java.sql.Connection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;


@Stateless
public class Reporting extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static Log log = LogFactory.getLog(Reporting.class);
	
	@PersistenceContext(name="edicsemJPASie")
	private EntityManager manager;
	 
	public Reporting() {
		log.info("Reporting()**");
	}

	public Connection getMap(){
		log.info("Reporting()** :D - -S ");
		
		Connection connectionObj=null;
		try {
			Session session = (Session) manager.unwrap(Session.class);
			
			  connectionObj = session.connection();
		//	HibernateEntityManager hem = manager.unwrap(HibernateEntityManager.class);
		//	Session session = manager.unwrap(Session.class);
		//	Session session = hem.getSession(); 
		//	connectionObj = session.connection();
//			Session session = ((EntityManagerImpl) manager).getSession();
//			Connection connectionObj = session.connection();
//			parametros.put("REPORT_CONNECTION", connectionObj);
			
//			Connection conexion = manager.unwrap(Connection.class);
//			parametros.put("REPORT_CONNECTION", conexion);
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return connectionObj;
	}
}