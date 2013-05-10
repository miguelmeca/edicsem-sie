package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ConfigPuntajeSie;
@Local
public interface ConfigPuntajeService {

	public abstract void insertConfigPuntaje(ConfigPuntajeSie c, int idparametro, int idtipocliente, int idcargo);
	public abstract void updateConfigPuntaje(ConfigPuntajeSie c, int idparametro, int idtipocliente, int idcargo);
	public abstract ConfigPuntajeSie findConfigPuntaje (int id);
	public abstract List  listarConfigPuntaje();
	
}
