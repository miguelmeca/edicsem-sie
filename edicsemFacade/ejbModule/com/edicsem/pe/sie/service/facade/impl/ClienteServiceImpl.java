package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.service.facade.ClienteService;


@Stateless
public class ClienteServiceImpl implements ClienteService {

	private Log log = LogFactory.getLog(ClienteServiceImpl.class);
	
	@EJB
	private  ClienteDAO objClienteDao;
	
	
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	
	@EJB
	private TelefonoEmpleadoDAO objTelefonoDao;
	
	@EJB
	private DomicilioEmpleadoDAO objDomicilioEmpleadoDao;
	
	@EJB
	private TipoCasaDAO objTipoCasaDao;

	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#insertCliente(com.edicsem.pe.sie.entity.ClienteSie)
	 */
	public void insertCliente(ClienteSie Cliente) {
		objClienteDao.insertCliente(Cliente);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#updateCliente(com.edicsem.pe.sie.entity.ClienteSie, java.util.List)
	 */
	public void updateCliente(ClienteSie Cliente, List<TelefonoPersonaSie> TelefonoPersonaList, List<DomicilioPersonaSie> DomicilioPersonaList  ) {
		log.info("ClienteServiceImpl ");
		objClienteDao.updateCliente(Cliente);
		
		for (int i = 0; i < TelefonoPersonaList.size(); i++) {
			if (TelefonoPersonaList.get(i).getNuevoT()==1) {
				//insertar
			TelefonoPersonaSie telefono=new TelefonoPersonaSie();
			telefono =	TelefonoPersonaList.get(i);
			telefono.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
			telefono.setIdcliente(Cliente);
			objTelefonoDao.insertarTelefonoEmpleado(telefono);	
			log.info("");
			}else{
				//actualizar	
				objTelefonoDao.actualizarTelefonoEmpleado(TelefonoPersonaList.get(i));
			}
			}
	
		for (int j = 0; j < DomicilioPersonaList.size(); j++) {
			if (DomicilioPersonaList.get(j).getNuevoD()==1) {
				log.info("insertar el nuevo domicilio");
				//insertar
				DomicilioPersonaSie domicilioJ=new DomicilioPersonaSie();
				domicilioJ =	DomicilioPersonaList.get(j);
				domicilioJ.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));

				log.info("ID CLiente SERVICE"+ Cliente.getIdcliente());
				
				domicilioJ.setIdcliente(Cliente);

				
			objDomicilioEmpleadoDao.insertarDomicilioEmpleado(domicilioJ);
			
			log.info("objDomicilioEmpleadoDao JOSELITO"+ domicilioJ);
			
			}else{
				log.info(" actualizar domicilio ");
				objDomicilioEmpleadoDao.actualizarDomicilioEmpleado(DomicilioPersonaList.get(j));
				
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
	
	
	
}
