/**
 * 
 */
package com.edicsem.pe.sie.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.edicsem.pe.sie.util.constants.Constants;

/**
 * @author Jorge Velasquez
 * Clase que brindará los metodos de encriptacion de datos 
 * como es el password del usuario.
 */
public class SecurityLogin {

		/***
		 * método que sirve para encriptar en MD5
		 */
		public static String getMD5(String cadena) throws Exception {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(cadena.getBytes());
			int size = b.length;
			StringBuilder h = new StringBuilder(size);
			for (int i = 0; i < size; i++) {
			int u = b[i] & 255;          
		    if (u < 16){
			h.append("0").append(Integer.toHexString(u));
			}
			else{
			h.append(Integer.toHexString(u));
			}
			}
			return h.toString();
		}
		
		/***
	     * Convierte un arreglo de bytes a String usando valores hexadecimales
	     * @param digest arreglo de bytes a convertir
	     * @return String creado a partir de <code>digest</code>
	     */
	    private static String toHexadecimal(byte[] digest){
	        String hash = "";
	        for(byte aux : digest) {
	            int b = aux & 0xff;
	            if (Integer.toHexString(b).length() == 1) hash += "0";
	            hash += Integer.toHexString(b);
	        }
	        return hash;
	    }
	    
	    /***
	     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
	     * @param message texto a encriptar
	     * @param algorithm algoritmo de encriptacion, será : SHA-512
	     * @see Este metodo brindará 128 caracteres en las cuales se comparará con el password del usuario
	     * @return mensaje encriptado
	     */
	    public static String getStringMessageDigest(String message){
	        byte[] digest = null;
	        byte[] buffer = message.getBytes();
	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance(Constants.ENCRYPTION_SHA_512);//algorithm
	            messageDigest.reset();
	            messageDigest.update(buffer);
	            digest = messageDigest.digest();
	        } catch (NoSuchAlgorithmException ex) {
	            System.out.println("Error creando Digest");
	        }
	        return toHexadecimal(digest);
	    }
	    
}
