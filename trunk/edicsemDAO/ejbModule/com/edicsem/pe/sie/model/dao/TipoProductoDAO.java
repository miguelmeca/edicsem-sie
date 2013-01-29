package com.edicsem.pe.sie.model.dao;

import java.util.List;
import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoProductoSie; 

@Local
public interface TipoProductoDAO {
	 
	public abstract List listarTipoProductos();
	public abstract void insertTipoProducto (TipoProductoSie  producto);
	public abstract TipoProductoSie findTipoProducto (int idtipoproducto);
	public abstract void updateTipoProducto(TipoProductoSie producto);
	public abstract void eliminarTipoProducto (int parametroObtenido);
	
}
