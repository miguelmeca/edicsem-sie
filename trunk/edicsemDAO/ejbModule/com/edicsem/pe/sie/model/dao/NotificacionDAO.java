package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.NotificacionSie;
 

@Local
public interface NotificacionDAO {
	
	public abstract void insertNotificacion(NotificacionSie n);
	public abstract void updateNotificacion(NotificacionSie n);
	public abstract NotificacionSie findNotificacion(int id);
	public abstract List  listarNotificacion();
	
}
