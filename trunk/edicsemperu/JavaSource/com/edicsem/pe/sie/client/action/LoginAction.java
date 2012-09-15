package com.edicsem.pe.sie.client.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.edicsem.pe.sie.beans.UsuarioDTO;

@ManagedBean(name="login")
@SessionScoped
public class LoginAction {
	
	public UsuarioDTO objUsuarioBean;
	
	public LoginAction() {
		// TODO Auto-generated constructor stub
	}
	
}
