package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.model.dao.TipoKardexProductoDAO;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
 
@Stateless
public class TipoKardexServiceImpl implements TipoKardexService {

	@EJB
	private  TipoKardexProductoDAO objTipoKardexDao;
  
	public List listaTipoKardex() {
		return objTipoKardexDao.listaTipoKardex();
	}
 
	public TipoKardexProductoSie findTipoKardex(int id) {
		return objTipoKardexDao.findTipoKardex(id); 
	}
}
