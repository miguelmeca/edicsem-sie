package com.edicsem.pe.sie.model.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConexionBD {

	public static Log log = LogFactory.getLog(ConexionBD.class);
	/**
	 *Permite Cargar en Memoria los Drivers 
	 */
	static {
		try {
			Class.forName("org.postgresql.Driver");
			log.info("*Driver*");
		} catch (Exception e) {
			log.info(e.getMessage()+"  cause "+e.getCause());
		}
	}

	/**
	 * Obtiene una conexión a la Base de Datos.
	 */
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
			connection =
			DriverManager.getConnection("jdbc:postgres://192.168.1.2:5432/bd_sie","postgres","Edicsem2011");

		} catch (Exception e) {
			log.info("msj  "+e.getMessage()+"  cause "+e.getCause());
		}
		return connection;
	}
}
