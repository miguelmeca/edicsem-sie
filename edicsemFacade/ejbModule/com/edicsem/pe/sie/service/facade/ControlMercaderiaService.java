package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ControlKardexSie;
 

@Local
public interface ControlMercaderiaService {
	
	public abstract void insertControlKardex(List<ControlKardexSie> lstControl, int idalmacen, int idEmpleado);
	public abstract void updateControlKardex(ControlKardexSie c);
	public abstract ControlKardexSie findControlKardex (int id);
	public abstract List  listarControlKardex();
	
}
