package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigPuntajeSie;

@Local
public interface ConfigPuntajeDAO {
	
	public abstract void insertConfigPuntaje(ConfigPuntajeSie c);
	public abstract void updateConfigPuntaje(ConfigPuntajeSie c);
	public abstract ConfigPuntajeSie findConfigPuntaje (int id);
	public abstract List  listarConfigPuntaje();
	
}
