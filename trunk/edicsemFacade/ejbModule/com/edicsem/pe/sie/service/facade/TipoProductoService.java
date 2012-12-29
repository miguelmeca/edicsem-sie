package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local; 
import com.edicsem.pe.sie.entity.TipoProductoSie;

@Local
public interface TipoProductoService {
	 
	public abstract List listarTipo ();
	public abstract void insertTipoProducto (TipoProductoSie producto);
	public abstract TipoProductoSie findTipoProducto (int idtipoproducto);
	public abstract void updateTipoProducto(TipoProductoSie producto);
}
