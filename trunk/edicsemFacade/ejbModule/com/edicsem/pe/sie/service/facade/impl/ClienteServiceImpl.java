package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.service.facade.ClienteService;

@Stateless
public class ClienteServiceImpl implements ClienteService {

	@EJB
	private  ClienteDAO objClienteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#insertCliente(com.edicsem.pe.sie.entity.ClienteSie)
	 */
	public void insertCliente(ClienteSie Cliente) {
		objClienteDao.insertCliente(Cliente);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#updateCliente(com.edicsem.pe.sie.entity.ClienteSie)
	 */
	public void updateCliente(ClienteSie Cliente) {
		objClienteDao.updateCliente(Cliente);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#findCliente(int)
	 */
	public ClienteSie findCliente(int id) {
		return objClienteDao.findCliente(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#listarClientes()
	 */
	public List listarClientes() {
		return objClienteDao.listarClientes();
	}
	
	
}
