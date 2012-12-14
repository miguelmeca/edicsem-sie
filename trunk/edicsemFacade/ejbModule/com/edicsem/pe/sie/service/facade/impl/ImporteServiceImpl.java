package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ImporteSie;
import com.edicsem.pe.sie.model.dao.ImporteDAO;
import com.edicsem.pe.sie.service.facade.ImporteService;
@Stateless
public class ImporteServiceImpl implements ImporteService{
	
	public static Log log = LogFactory.getLog(ImporteServiceImpl.class);
	
	@EJB
	private ImporteDAO objImporteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ImporteService#insertImporte(com.edicsem.pe.sie.entity.ImporteSie)
	 */
	public void insertImporte(ImporteSie a) {
		objImporteDao.insertImporte(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ImporteService#updateImporte(com.edicsem.pe.sie.entity.ImporteSie)
	 */
	public void updateImporte(ImporteSie a) {
		objImporteDao.updateImporte(a);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ImporteService#findImporte(int)
	 */
	public ImporteSie findImporte(int id) {
		return objImporteDao.findImporte(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ImporteService#listarImporte()
	 */
	public List listarImporte(int tipo) {
		return objImporteDao.listarImporte(tipo);
	}
}
