package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.model.dao.MetaMesDAO;
import com.edicsem.pe.sie.service.facade.MetaMesService;

@Stateless
public class MetaMesServiceImpl implements MetaMesService {

	@EJB
	private  MetaMesDAO objMetaMesDao;
	
	private Log log = LogFactory.getLog(MetaMesServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#insertEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void insertMetaMes(MetaMesSie metames) {
		objMetaMesDao.insertMetaMes(metames);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#updateEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void updateMetaMes(MetaMesSie metames) {
		objMetaMesDao.updateMetaMes(metames);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#listarEmpresas()
	 */
	 
	public List<MetaMesSie> listarMetaMeses() {
		log.info("Entering method listarMetas  ");
		return objMetaMesDao.listarMetaMeses();
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#findProducto(java.lang.String)
	 */
	 
	public MetaMesSie findMetaMes(int id) {
		return objMetaMesDao.findMetaMes(id); 
	}
}
