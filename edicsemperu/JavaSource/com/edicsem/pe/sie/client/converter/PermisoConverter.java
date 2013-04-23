package com.edicsem.pe.sie.client.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import com.edicsem.pe.sie.entity.PermisoSie;

@FacesConverter(value = "permisoConverter")
public class PermisoConverter implements Converter {
	
	private static Log log = LogFactory.getLog(PermisoConverter.class);
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		PermisoSie p =null;
		if (value.trim().equals("")) {  
           return null;  
       }
		else {  
           try {
           	 final DualListModel<PermisoSie> dualList;
                    dualList = (DualListModel<PermisoSie>) ((PickList)uic).getValue();
                   p = getObjectFromList(dualList.getSource(),Integer.valueOf(value));
                    if(p==null){
                        p = getObjectFromList(dualList.getTarget(),Integer.valueOf(value));
                    }
                   
                return p;
           }catch(Exception e) {
           	log.info("Mensaje: "+ e.getMessage()+" cause: "+e.getCause());
           }
        }
		return p;
	}
		
	private PermisoSie getObjectFromList(final List<?> list, final Integer identifier) {
		for(final Object object:list){
			final PermisoSie p = (PermisoSie) object;
			if(p.getIdpermiso().equals(identifier)){
				return p;
			}
		}
		return null;
	}
	
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o == null || o.equals("")) {  
           return "";  
       }
		else {
			return String.valueOf(((PermisoSie) o).getIdpermiso()); 
		}
	}
}