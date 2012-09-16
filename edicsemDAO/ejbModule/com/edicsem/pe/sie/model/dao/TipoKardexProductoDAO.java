package com.edicsem.pe.sie.model.dao;

import java.util.List;
import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;

@Local
public interface TipoKardexProductoDAO {
		
	public abstract List  listaTipoKardex();
	public abstract TipoKardexProductoSie findTipoKardex(int id);
}
