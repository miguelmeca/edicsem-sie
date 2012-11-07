package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.service.facade.ClienteService;

@Stateless
public class ClienteServiceImpl implements ClienteService {

	@EJB
	private  ClienteDAO objClienteDao;
	
	
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	
	@EJB
	private TelefonoEmpleadoDAO objTelefonoDao;

	
	


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#updateCliente(com.edicsem.pe.sie.entity.ClienteSie, java.util.List)
	 */
	public void updateCliente(ClienteSie Cliente, List<TelefonoPersonaSie> TelefonoPersonaList ) {
		
		objClienteDao.updateCliente(Cliente);
		
		for (int i = 0; i < TelefonoPersonaList.size(); i++) {
			if (TelefonoPersonaList.get(i).getNuevoT()==1) {
				//insertar
				TelefonoPersonaSie telefono=new TelefonoPersonaSie();
			telefono =	TelefonoPersonaList.get(i);
			telefono.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
			telefono.setIdcliente(Cliente);
			objTelefonoDao.insertarTelefonoEmpleado(telefono);	
			
			}else{
				//actualizar	
				objTelefonoDao.actualizarTelefonoEmpleado(TelefonoPersonaList.get(i));
			}
		}
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
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#insertCliente(com.edicsem.pe.sie.entity.ClienteSie)
	 */
	public void insertCliente(ClienteSie Cliente) {
		objClienteDao.insertCliente(Cliente);
	}

	@Override
	public void updateCliente(ClienteSie Cliente,
			List<TelefonoPersonaSie> TelefonoPersonaList,
			List<UbigeoSie> DomicilioPersonaList) {
		// TODO Auto-generated method stub
		
	}
}
