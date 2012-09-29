package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.model.dao.TipoKardexProductoDAO;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
 
/**
 * @author karen
 *
 */
@Stateless
public class TipoKardexServiceImpl implements TipoKardexService {

	@EJB
	private  TipoKardexProductoDAO objTipoKardexDao;
  
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoKardexService#listaTipoKardex()
	 */
	public List listaTipoKardex() {
		return objTipoKardexDao.listaTipoKardex();
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoKardexService#findTipoKardex(int)
	 */
	public TipoKardexProductoSie findTipoKardex(int id) {
		return objTipoKardexDao.findTipoKardex(id); 
	}
}
