package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoLlamadaSie;

@Local
public interface TipoLLamadaService {
	

	public abstract void insertTipoLLamada(TipoLlamadaSie tipollamada);
	public abstract void updateTipoLLamada(TipoLlamadaSie tipollamada);
	public abstract void eliminarTipoLLamada(int id);
	public abstract TipoLlamadaSie findTipoLLamada(int id);
	public abstract List  listarTipoLLamada(); 
	
}
