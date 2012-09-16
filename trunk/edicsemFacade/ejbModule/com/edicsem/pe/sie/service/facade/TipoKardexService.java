package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local; 

import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
 
@Local
public interface TipoKardexService {

	public abstract List  listaTipoKardex();
	public abstract TipoKardexProductoSie findTipoKardex(int id);
}
