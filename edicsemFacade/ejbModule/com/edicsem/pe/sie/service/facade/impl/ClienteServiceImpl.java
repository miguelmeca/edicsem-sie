package com.edicsem.pe.sie.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.ClienteService;


@Stateless
public class ClienteServiceImpl implements ClienteService {

	private Log log = LogFactory.getLog(ClienteServiceImpl.class);
	
	@EJB
	private  ClienteDAO objClienteDao;
	@EJB
	private UbigeoDAO objUbigeoDao;
	
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
	public void updateCliente(ClienteSie Cliente, DomicilioPersonaSie objDomicilio,String idUbigeo, int tipo, int TelefonoPersona, List<TelefonoPersonaSie> TelefonoPersonaList, List<TelefonoPersonaSie> TelefonoDeshabilitado) {
		
	try {
		if (log.isInfoEnabled()) {
			log.info("inicio del m�todo insertar cliente en el SERVICIO");
		}
		
		objClienteDao.updateCliente(Cliente);
		
		UbigeoSie ubigeo = new UbigeoSie();
		TipoCasaSie tipoCasa = new TipoCasaSie();
		EstadoGeneralSie estado = new EstadoGeneralSie();
		ClienteSie cliente = new ClienteSie();
		
		/*Estado (15) Habilitar Docimilio*/
		estado.setIdestadogeneral(15);
		ubigeo.setIdubigeo(Integer.parseInt(idUbigeo));
		tipoCasa.setIdtipocasa(tipo);
		cliente.setIdcliente(Cliente.getIdcliente());
		
		/**Actualiza el domicilio**/
		objDomicilio.setIddomiciliopersona(objDomicilio.getIddomiciliopersona());
		objDomicilio.setIdcliente(Cliente);
		objDomicilio.setTbUbigeo(ubigeo);
		objDomicilio.setTbTipoCasa(tipoCasa);
		objDomicilio.setTbEstadoGeneral(estado);
		objDomicilioEmpleadoDao.actualizarDomicilioEmpleado(objDomicilio);
		
		/**Actualiza telefono(s)**/
		/** for (TelefonoPersonaSie objTelefono : TelefonoPersonaList) {
			objTelefono.setIdcliente(cliente); **/
			/* Estado (17) Habilitar Telefono*/
	/**		estado.setIdestadogeneral(17);
			objTelefono.setTbEstadoGeneral(estado);
			
			objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
		}**/
		for (TelefonoPersonaSie objTelefono : TelefonoPersonaList) {
			log.info("bien!! por agregar ");
			if(objTelefono.getItem().equalsIgnoreCase("Por Agregar")){
			objTelefono.setIdcliente(cliente);
			objTelefono.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
			objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
			}
		}
	/**	for(TelefonoPersonaSie objTelefono2 : TelefonoDeshabilitado){
			objTelefono2.setIdtelefonopersona(objTelefono2.getIdtelefonopersona());
			objTelefono2.setIdcliente(Cliente);
			/* Estado (18) Deshabilitar Telefono*/
		/**	estado.setIdestadogeneral(18);
			objTelefono2.setTbEstadoGeneral(estado);
			objTelefonoDao.actualizarTelefonoEmpleado(objTelefono2);					
		} 	**/	
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
		
	Cliente = new ClienteSie();	
	objDomicilio = new DomicilioPersonaSie();
	TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
		
		
		
		
	}
	/************	for (int i = 0; i < TelefonoPersonaList.size(); i++) {
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
		
		***********/
		 /**Inserta el domicilio**/
		
		
		/***********************************
		objDomicilio.setIdcliente(objClienteDao.findCliente(Cliente.getIdcliente()));
		objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(Integer.parseInt(idUbigeo)));		
		objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));		
		/**Estado del domicilio: habilitado(15)**/		
//		objDomicilio.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
//		
//		
//		objDomicilioEmpleadoDao.insertarDomicilioEmpleado(objDomicilio);
		
		
		/*************************************
//	
//		for (int j = 0; j < DomicilioPersonaList.size(); j++) {
//			if (DomicilioPersonaList.get(j).getNuevoD()==1) {
//				log.info("insertar el nuevo domicilio");
//				//insertar
//				DomicilioPersonaSie domicilioJ=new DomicilioPersonaSie();
//				domicilioJ =	DomicilioPersonaList.get(j);
//				domicilioJ.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
//
//				log.info("ID CLiente SERVICE"+ Cliente.getIdcliente());
//				
//				domicilioJ.setIdcliente(Cliente);
//
//				
//			objDomicilioEmpleadoDao.insertarDomicilioEmpleado(domicilioJ);
//			
//			log.info("objDomicilioEmpleadoDao "+ domicilioJ);
//			
//			}else{
//				log.info(" actualizar domicilio ");
//				objDomicilioEmpleadoDao.actualizarDomicilioEmpleado(DomicilioPersonaList.get(j));
//				
//			}
//		
//	
//		}
		
		
		
		
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
	 * @see com.edicsem.pe.sie.service.facade.ClienteService#listarClientesXTipo(int)
	 */
	public List listarClientesXTipo(int tipoCliente) {
		return objClienteDao.listarClientesXTipo(tipoCliente);
	}
}
