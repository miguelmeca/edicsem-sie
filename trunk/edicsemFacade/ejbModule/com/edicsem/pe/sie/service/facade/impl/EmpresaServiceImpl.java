package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.service.facade.EmpresaService;

@Stateless
public class EmpresaServiceImpl implements EmpresaService {

	@EJB
	private  EmpresaDAO objEmpresaDao;
	
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
	 
	public List listarEmpresas() {
		return objEmpresaDao.listarEmpresas();
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpresaService#findProducto(java.lang.String)
	 */
	 
	public EmpresaSie findProducto(String id) {
		return objEmpresaDao.findProducto(id); 
	}
}
