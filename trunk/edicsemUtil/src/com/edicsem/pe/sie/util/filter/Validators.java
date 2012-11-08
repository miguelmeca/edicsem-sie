package com.edicsem.pe.sie.util.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@ManagedBean
public class Validators{
	
	public void validateEmail(FacesContext context, UIComponent validate, Object value){
        String email = (String)value;
        Pattern p = Pattern.compile("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();

        if(!matchFound){
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage("Email Inválido");
            context.addMessage(validate.getClientId(context), msg);
        }
	}
}
