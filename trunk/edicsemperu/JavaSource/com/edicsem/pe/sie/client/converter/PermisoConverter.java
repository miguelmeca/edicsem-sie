package com.edicsem.pe.sie.client.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PermisoSie;
import com.edicsem.pe.sie.service.facade.PermisoService;

@FacesConverter(value = "permisoConverter")
public class PermisoConverter implements Converter {
	@EJB
	private PermisoService service;
	private static Log log = LogFactory.getLog(PermisoConverter.class);
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		log.info("Converter String "+string);
		PermisoSie p = service.findPermiso(string);
		return p;
	}
	
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		log.info("getAsString");
		PermisoSie p = new PermisoSie();
		p = (PermisoSie) o;
		log.info(" nombre "+p.getNombrePermiso());
		return p.getNombrePermiso();
	}
}