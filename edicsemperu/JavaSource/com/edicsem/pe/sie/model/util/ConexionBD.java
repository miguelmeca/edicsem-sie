package com.edicsem.pe.sie.model.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConexionBD {

	public static Log log = LogFactory.getLog(ConexionBD.class);
	
	/**
	 * Obtiene una conexión a la Base de Datos.
	 */
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection =
			DriverManager.getConnection("jdbc:postgres://localhost:5432/bd_sie","postgres","admin");

		} catch (Exception e) {
			log.info("msj  "+e.getMessage()+"  cause "+e.getCause());
		}
		return connection;
	}
}
