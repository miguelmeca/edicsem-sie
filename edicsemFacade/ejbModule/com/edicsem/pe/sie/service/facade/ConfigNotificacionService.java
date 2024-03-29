package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
@Local
public interface ConfigNotificacionService {
	
	public abstract void insertConfigNotificacion(ConfigNotificacionSie n, int idtipocliente,int idCalificacion,int idNotificacion);
	public abstract void updateConfigNotificacion(ConfigNotificacionSie n, int idtipocliente,int idCalificacion,int idNotificacion);
	public abstract ConfigNotificacionSie findConfigNotificacion(int id);
	public abstract List  listarConfigNotificacion();
	public abstract List listarConfigNotificacionXNotificacion(int idnotifica);
}
