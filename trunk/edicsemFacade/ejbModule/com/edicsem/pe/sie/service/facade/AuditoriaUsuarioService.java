package com.edicsem.pe.sie.service.facade;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.AuditoriaUsuarioSie;
@Local
public interface AuditoriaUsuarioService {
	
	public abstract void insertAuditoriaUsuario(AuditoriaUsuarioSie a);
	public abstract AuditoriaUsuarioSie  listarAuditoriaUsuario(String usuario);
}
