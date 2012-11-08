package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoFiltroSie;
import com.edicsem.pe.sie.model.dao.TipoFiltroDAO;
import com.edicsem.pe.sie.service.facade.TipoFiltroService;

@Stateless
public class TipoFiltroServiceImpl implements TipoFiltroService {

	@EJB
	private  TipoFiltroDAO objTipopFiltroDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoFiltroService#findTipoFiltro(int)
	 */
	public TipoFiltroSie findTipoFiltro(int id) {
		return objTipopFiltroDao.findTipoFiltro(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoFiltroService#listarTipoFiltro()
	 */
	public List listarTipoFiltro() {
		return objTipopFiltroDao.listarTipoFiltro();
	}
  
}
