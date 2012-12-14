package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoImporteSie;
import com.edicsem.pe.sie.model.dao.TipoImporteDAO;
import com.edicsem.pe.sie.service.facade.TipoImporteService;
@Stateless
public class TipoImporteServiceImpl implements TipoImporteService{
	
	public static Log log = LogFactory.getLog(TipoImporteServiceImpl.class);
	
	@EJB
	private TipoImporteDAO objTipoImporteDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoImporteService#insertTipoImporte(com.edicsem.pe.sie.entity.TipoImporteSie)
	 */
	public void insertTipoImporte(TipoImporteSie a) {
		objTipoImporteDao.insertTipoImporte(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoImporteService#updateTipoImporte(com.edicsem.pe.sie.entity.TipoImporteSie)
	 */
	public void updateTipoImporte(TipoImporteSie a) {
		objTipoImporteDao.updateTipoImporte(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoImporteService#findTipoImporte(int)
	 */
	public TipoImporteSie findTipoImporte(int id) {
		return objTipoImporteDao.findTipoImporte(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoImporteService#listarTipoImporte()
	 */
	public List listarTipoImporte() {
		return objTipoImporteDao.listarTipoImporte();
	}
}
