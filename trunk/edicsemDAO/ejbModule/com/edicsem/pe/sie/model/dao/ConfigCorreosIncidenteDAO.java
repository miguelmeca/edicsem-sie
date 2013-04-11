package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie;
 
@Local
public interface ConfigCorreosIncidenteDAO {
	
	public abstract void insertConfigCorreos(ConfigCorrreosIncidenteSie c);
	public abstract void updateConfigCorreos(ConfigCorrreosIncidenteSie c);
	public abstract ConfigCorrreosIncidenteSie findConfigCorreo (int id);
	public abstract List  listarConfigCorreos();
	
}
