package com.edicsem.pe.sie.model.dao;

import java.util.List;
import javax.ejb.Local;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;

@Local
public interface TipoKardexProductoDAO {
		
	public abstract List  listaTipoKardex();
	public abstract TipoKardexProductoSie findTipoKardex(int id);
}
