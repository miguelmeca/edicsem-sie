package com.edicsem.pe.sie.util.constants;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase general utilizada para acceder a la Session
 * <p>
 * <a href="Constants.java.html"> <i>View Source </i>    </a>
 * </p>
 * 
 * @author <a href="mailto:jvelasquez@edicsem.com">Jorge Luis Velasquez</a>
 */
public class SessionUtil {

	/**
	 * Busca cadena en una Lista que se encuentre en Session
	 * @param id
	 * @param cadenaBuscar
	 * @param request
	 * @return
	 *    i  Valor del indice en donde se encuentra la cadena en la lista
	 *    -1 En caso no encontro la cadena en la lista 
	 */
	public static int buscarListEnSession(String id, String cadenaBuscar, HttpServletRequest request) {
		ArrayList lista = new ArrayList();
		try {
			lista = (ArrayList) request.getSession(true).getAttribute(id);
		}	
		catch(Exception e) {
			//No se encontro la variable en session
			return -2;
		}	
		if (lista == null)
			return -2;
		
		/* Buscando cadena en la lista */
		for (int i= 0; i < lista.size(); i++) {
			String cadena = (String)lista.get(i);
			if (cadena.equals(cadenaBuscar)) {
				return i;
			}
		}
		
		/* No encontro el valor en la lista */
		return -1;
	}
	
	
	/**
	 * Inserta cadena en una Lista que se encuentre en Session
	 * @param id
	 * @param cadenaBuscar
	 * @param request
	 * @return
	 *    i  Valor del indice en donde se encuentra la cadena en la lista
	 *    -1 En caso no inserto dicha cadena en la lista  
	 */
	public static int insertarListEnSession(String id, String cadenaBuscar, HttpServletRequest request) {
		int indice = buscarListEnSession(id, cadenaBuscar, request);
		if (indice == -1) {
			ArrayList lista = (ArrayList) request.getSession(true).getAttribute(id);
			lista.add(cadenaBuscar);
			request.getSession(true).removeAttribute(id);
			request.getSession(true).setAttribute(id, lista);
			return lista.size();
		}
		if (indice == -2) {
			ArrayList lista = new ArrayList();
			lista.add(cadenaBuscar);
			request.getSession(true).setAttribute(id, lista);
			return lista.size();
		}
		return -1;
	}
	
	/**
	 * Elimina cadena en una Lista que se encuentre en Session
	 * @param id
	 * @param cadenaBuscar
	 * @param request
	 * @return
	 */
	public static int eliminarListEnSession(String id, String cadenaBuscar, HttpServletRequest request) {
		int indice = buscarListEnSession(id, cadenaBuscar, request);
		if (indice >= 0) {
			ArrayList lista = (ArrayList) request.getSession(true).getAttribute(id);
			lista.remove(indice);
			request.getSession(true).removeAttribute(id);
			request.getSession(true).setAttribute(id, lista);
			return indice;
		}
		return -1;
	}
	
}