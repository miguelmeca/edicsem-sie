package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.service.facade.EmpresaService;

@Stateless
public class EmpresaServiceImpl implements EmpresaService {

	@EJB
	private  EmpresaDAO objEmpresaDao;
	
	private Log log = LogFactory.getLog(EmpresaServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#insertEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void insertEmpresa(EmpresaSie empresa) {
		objEmpresaDao.insertEmpresa(empresa);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#updateEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void updateEmpresa(EmpresaSie empresa) {
		objEmpresaDao.updateEmpresa(empresa);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#listarEmpresas()
	 */
	 
	public List<EmpresaSie> listarEmpresas() {
		log.info("Entering method listarEmpresas 'EmpresaServiceImpl' ");
		return objEmpresaDao.listarEmpresas();
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#findProducto(java.lang.String)
	 */
	 
	public EmpresaSie findEmpresa(int id) {
		return objEmpresaDao.findEmpresa(id); 
	}
}
