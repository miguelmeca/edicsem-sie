package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.UbigeoSie;

@Local
public interface UbigeoService {
	

	public abstract void insertUbigeo(UbigeoSie ubigeo);
	public abstract void updateUbigeo(UbigeoSie ubigeo);
	public abstract void eliminarUbigeo(int id);
	public abstract UbigeoSie findUbigeo(int id);
	public abstract List  listarUbigeo(); 
	
}
