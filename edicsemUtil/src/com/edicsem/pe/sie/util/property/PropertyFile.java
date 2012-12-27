package com.edicsem.pe.sie.util.property;

import java.io.IOException;
import java.util.Properties;

public class PropertyFile {

	public Properties getProperties() {
		try {
			// se crea una instancia a la clase Properties
			Properties propiedades = new Properties();
			// se leen el archivo .properties
			propiedades.load(getClass().getResourceAsStream(
					"edicsemperu/resources/com/edicsem/pe/sie/client/properties/ApplicationResources.properties"));
			// si el archivo de propiedades NO esta vacio retornan las propiedes
			// leidas
			if (!propiedades.isEmpty()) {
				return propiedades;
			} else {// sino retornara NULL
				return null;
			}
		} catch (IOException ex) {
			return null;
		}
	}
}
