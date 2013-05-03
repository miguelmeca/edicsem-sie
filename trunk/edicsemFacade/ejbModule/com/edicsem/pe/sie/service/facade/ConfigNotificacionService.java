package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
@Local
public interface ConfigNotificacionService {
	
	public abstract void insertConfigNotificacion(ConfigNotificacionSie n);
	public abstract void updateConfigNotificacion(ConfigNotificacionSie n);
	public abstract ConfigNotificacionSie findConfigNotificacion(int id);
	public abstract List  listarConfigNotificacion();
}
