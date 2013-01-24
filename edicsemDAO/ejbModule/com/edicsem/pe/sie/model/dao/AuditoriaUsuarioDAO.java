package com.edicsem.pe.sie.model.dao;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.AuditoriaUsuarioSie;
 

@Local
public interface AuditoriaUsuarioDAO {
	
	public abstract void insertAuditoriaUsuario(AuditoriaUsuarioSie a);
	public abstract AuditoriaUsuarioSie  listarAuditoriaUsuario(String usuario);
}
