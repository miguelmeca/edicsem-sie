package com.edicsem.pe.sie.client.action;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;




@ManagedBean
@SessionScoped
/* colocarlo en public class LoginBean extends SessionsSie */
public class personBean {

	private String firstname;  
    
    private String surname;  
  
    public String getFirstname() {  
        return firstname;  
    }  
    public void setFirstname(String firstname) {  
        this.firstname = firstname;  
    }  
  
    public String getSurname() {  
        return surname;  
    }  
    public void setSurname(String surname) {  
        this.surname = surname;  
    }  
      
    public void savePerson(ActionEvent actionEvent) {  
    	
    	
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + firstname + " " + surname + "!"));  
    }  
}
