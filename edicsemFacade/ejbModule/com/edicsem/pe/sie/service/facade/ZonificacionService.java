package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ZonificacionSie;
@Local
public interface ZonificacionService {
	
	public abstract void insertZonificacion(ZonificacionSie c);
	public abstract void updateZonificacion(ZonificacionSie c);
	public abstract ZonificacionSie findZonificacion (int id);
	public abstract List  listarZonificacion();
	public abstract List listarZonificacionXDistrito(String idUbigeo);
	public abstract List listarZonificacionXPlano(String idUbigeo,List<String> planoList);
	public abstract List listarZonificacionXPlanoXLetra(String idUbigeo,List<String> planoList, List<String> letraList);
	
}
