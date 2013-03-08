package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
@Local
public interface TipoEventoVentaService {
	
	public abstract void insertTipoEventoVenta(TipoEventoVentaSie t);
	public abstract void updateTipoEventoVenta(TipoEventoVentaSie t);
	public abstract TipoEventoVentaSie findTipoEventoVenta (int id);
	public abstract List  listarTipoEventoVenta();
	
}
