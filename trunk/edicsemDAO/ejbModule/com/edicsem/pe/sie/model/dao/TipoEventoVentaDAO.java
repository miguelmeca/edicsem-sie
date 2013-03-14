package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
 

@Local
public interface TipoEventoVentaDAO {
	
	public abstract void insertTipoEventoVenta(TipoEventoVentaSie t);
	public abstract void updateTipoEventoVenta(TipoEventoVentaSie t);
	public abstract TipoEventoVentaSie findTipoEventoVenta (int id);
	public abstract List  listarTipoEventoVenta();
	public abstract TipoEventoVentaSie findTipoEventoVenta(String evento);
	
}
