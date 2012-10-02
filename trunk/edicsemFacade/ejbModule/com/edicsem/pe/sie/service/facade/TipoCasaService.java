package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;

@Local
public interface TipoCasaService {
	

	public abstract void insertTipoCasa(TipoCasaSie tipocasa);
	public abstract void updateTipoCasa(TipoCasaSie tipocasa);
	public abstract void eliminarTipoCasa(int id);
	public abstract TipoCasaSie findTipoCasa(int id);
	public abstract List  listarTipoCasa(); 
	
}
