package com.edicsem.pe.sie.util.constants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

public class StringUtil {
	private final static Log log = LogFactory.getLog(StringUtil.class);

	/**
	 * Encode a string using algorithm specified in web.xml and return the
	 * resulting encrypted password. If exception, the plain credentials string
	 * is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * 
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	public static List parseToListOfSets(String[] data, String separators) {
		List result = new ArrayList();
		for (int i = 0; i < data.length; i++) {
			StringTokenizer st = new StringTokenizer(data[i], separators);

			for (int j = 0; st.hasMoreTokens(); j++) {
				if (j >= result.size())
					result.add(new HashSet());
				((Set) result.get(j)).add(st.nextToken());
			}
		}
		return result;
	}
	
	public static List parseToList(String data, String separators) {
		List result = new ArrayList();
			StringTokenizer st = new StringTokenizer(data, separators);
			while (st.hasMoreElements()) {
				result.add(st.nextElement());
			}
		return result;
	}
	
	
	
	/**
	 * Obtiene un string del arreglo <code>cadena</code> separado por una coma
	 * (,)
	 * 
	 * @param cadena
	 * @return
	 */
	public static String deArrayAStringToken(String[] cadena) {
		StringBuffer retorno = new StringBuffer();
		for (int i = 0; cadena != null && i < cadena.length; i++) {
			if (i == (cadena.length - 1)) {
				retorno.append(cadena[i]);
			} else {
				retorno.append(cadena[i] + ",");
			}
		}
		return retorno.toString();
	}
	
	/**
	 * Obtiene un string del arreglo <code>cadena</code> separado por el valor
	 * de <code>token</code>
	 * 
	 * @param cadena
	 * @param token
	 * @return
	 */
	public static String deArrayAStringToken(String[] cadena, String token) {
		StringBuffer retorno = new StringBuffer();
		for (int i = 0; cadena != null && i < cadena.length; i++) {
			if (i == (cadena.length - 1)) {
				retorno.append(cadena[i]);
			} else {
				retorno.append(cadena[i] + token);
			}
		}
		return retorno.toString();
	}
	
	
	/**
     * Obtiene array de String a partir de una cadena con un separador. 
     * @param cadena
     * @param separador
     * @return
	 */
    public static String[] obtenerArreglo(String cadena, String separador) {
		ArrayList campos = new ArrayList();
		int j = -1;
		int longitudSeparador;
		int posicion;
		longitudSeparador = separador.length();
		do {
			posicion = cadena.indexOf(separador);
			if (posicion != -1) {
				j++;
				campos.add(cadena.substring(0, posicion));
				cadena = cadena.substring(posicion + longitudSeparador);
			} else {
				campos.add(cadena);
			}
		} while (posicion != -1);

		Object aux[] = campos.toArray();
		int tamanio = aux.length;

		String arreglo[] = new String[tamanio];

		for (int i = 0; i < tamanio; i++) {
			arreglo[i] = (String) aux[i];
		}

		return arreglo;
	}
    
    /**
     * Reemplaza una cadena por otra. 
     * @param str
     * @param pattern
     * @param replace
     * @return
	 */
    public static String reemplazar(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}
	
	/**
	 * Obtiene en forma de cadena separado por comilla una lista de valores
	 * 
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	public static String obtieneListaCodigos(String[] lista, String parametro,
			String comilla ) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ") ";
			return resultado;
		}
	}
	
    /**
     * @autor Sergio Buchelli
     * Se encarga de validar el xml, si hay errero retona la cadena de al excption caso contario 
     * cadenaz vacia
     * @param textoXml
     * @return
     */
	public static String validaXml(String textoXml) {
        StringBuffer sb= new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
        sb.append("<SICC-VALIDACION_XML>");
        sb.append(textoXml);
        sb.append("</SICC-VALIDACION_XML>");
        String retorno=string2DOM(sb.toString());
        return retorno;
    
    }

    /**
     * Realiza la conversion de String a un documento xml
     * @param s
     * @return
     */
    private static String string2DOM(String s)
      {
          Document tmpX=null;
          DocumentBuilder builder = null;
          String msgerror="";
          try{
              builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
          }catch(javax.xml.parsers.ParserConfigurationException error){
              msgerror="Error creando factory  "+error.getMessage();
              return msgerror;
          }
          try{
              tmpX=builder.parse(new ByteArrayInputStream(s.getBytes()));
          }catch(org.xml.sax.SAXException error){
              msgerror="Error parseo "+error.getMessage();
              return msgerror;
          }catch(IOException error){
              msgerror="Error generando "+error.getMessage();
              return msgerror;
          }
          return (tmpX!=null?"":msgerror);
      }
}
