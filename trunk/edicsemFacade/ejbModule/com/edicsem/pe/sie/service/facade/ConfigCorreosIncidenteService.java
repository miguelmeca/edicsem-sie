package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie;
@Local
public interface ConfigCorreosIncidenteService {

	public abstract void insertConfigCorreos(ConfigCorrreosIncidenteSie c);
	public abstract void updateConfigCorreos(ConfigCorrreosIncidenteSie c);
	public abstract ConfigCorrreosIncidenteSie findConfigCorreo (int id);
	public abstract List  listarConfigCorreos();
	
}
