package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local;  
import com.edicsem.pe.sie.entity.PuntoVentaSie;
@Local
public interface AlmacenService {
	
	public abstract void insertAlmacen( PuntoVentaSie almacen);
	public abstract void updateAlmacen(PuntoVentaSie almacen);
	public abstract PuntoVentaSie findAlmacen (int id);
	public abstract List listarAlmacenes();
	public abstract List listarAlmacenXtipo(String tipo);
	
}
