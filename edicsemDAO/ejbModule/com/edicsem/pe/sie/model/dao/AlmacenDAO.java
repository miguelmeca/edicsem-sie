package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.PuntoVentaSie;
 

@Local
public interface AlmacenDAO {
	
	public abstract void insertAlmacen( PuntoVentaSie almacen);
	public abstract void updateAlmacen(PuntoVentaSie almacen);
	public abstract PuntoVentaSie findAlmacen (int id);
	public abstract List  listarAlmacenes();
}
