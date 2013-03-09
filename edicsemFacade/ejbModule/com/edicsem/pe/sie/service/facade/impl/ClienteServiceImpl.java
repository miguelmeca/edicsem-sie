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
	private TelefonoEmpleadoDAO objTelefonoEmpleadoDAO;	
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
	public void updateCliente(ClienteSie Cliente, DomicilioPersonaSie objDomicilio,String idUbigeo1,int idUbigeo , int tipo, int Tipocasanuevo, int TelefonoPersona, List<TelefonoPersonaSie> TelefonoPersonaList, List<TelefonoPersonaSie> TelefonoDeshabilitado, List<DomicilioPersonaSie> DomicilioPersonaList, List<DomicilioPersonaSie> DomicilioPersonaDeshabilitado) {
//		, List<TelefonoPersonaSie> TelefonoPersonaList,int tipo,DomicilioPersonaSie objDomicilio,String idUbigeo 
		
	try {
		if (log.isInfoEnabled()) {
			log.info("inicio del método insertar cliente en el SERVICIO");
		}
		
		objClienteDao.updateCliente(Cliente);
		
		UbigeoSie ubigeo = new UbigeoSie();
		UbigeoSie objubigeo24 = new UbigeoSie();
		TipoCasaSie tipoCasa = new TipoCasaSie();
		TipoCasaSie tipocasanew = new TipoCasaSie();
		EstadoGeneralSie estado = new EstadoGeneralSie();
		ClienteSie cliente = new ClienteSie();
		
		/*Estado (15) Habilitar Docimilio*/
		estado.setIdestadogeneral(15);
		ubigeo.setIdubigeo(Integer.parseInt(idUbigeo1));
		objubigeo24.setIdubigeo(idUbigeo);
		tipoCasa.setIdtipocasa(tipo);
		tipocasanew.setIdtipocasa(Tipocasanuevo);
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
		
				/***************TELEFONO CLIENTE********************/
		
		
		log.info("ANTES DE FOR LINEA 101--->   "+ TelefonoPersonaList.size());
		for (TelefonoPersonaSie objTelefono1 : TelefonoPersonaList) {
			//GUIARME DE CONTRATO ACTUALIZAR LINEA 304
			TelefonoPersonaSie tel = new TelefonoPersonaSie();
			log.info("bien!! por agregar ");
			if(objTelefono1.getItem().equalsIgnoreCase("Por Agregar")){
			tel.setIdcliente(Cliente);
			log.info("ID_CLIENTE-SERVICIO"+ tel.getIdcliente());
			tel.setTelefono(objTelefono1.getTelefono());
			log.info("Nº TELEFONO o CELULAR "+tel.getTelefono());
			tel.setTipotelefono(objTelefono1.getTipotelefono());
			log.info("TIPO DE TELEFONO--->  "+ tel.getTipotelefono());
			tel.setDescTelefono(objTelefono1.getDescTelefono());
			log.info("DESCRICCION-TELeFONO... "+ tel.getDescTelefono());			
			tel.setOperadorTelefonico(objTelefono1.getOperadorTelefonico());
			log.info("OPERADOR TELEFONICO-->   "+ tel.getOperadorTelefonico());
			tel.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
			objTelefonoEmpleadoDAO.insertarTelefonoEmpleado(tel);
			}
		}
		
		
		log.info("guardo- TelefonoDeshabilitado : " + TelefonoDeshabilitado.size());
	for(TelefonoPersonaSie objTelefono2 : TelefonoDeshabilitado){
		TelefonoPersonaSie desa = new TelefonoPersonaSie();
		
		desa.setIdtelefonopersona(objTelefono2.getIdtelefonopersona());
		log.info("ID-TEL-CLIENTE--> "+ desa.getIdtelefonopersona());
		desa.setIdcliente(Cliente);
		log.info("ID-CLIENTE-->  "+ desa.getIdcliente());
		desa.setTipotelefono(objTelefono2.getTipotelefono());
		log.info("TIPO-TELEFONO-->  "+ desa.getTipotelefono());
		desa.setTelefono(objTelefono2.getTelefono());
		log.info("Nº TELEFONICO-->  "+ desa.getTelefono());
		desa.setDescTelefono(objTelefono2.getDescTelefono());
		log.info("DESCRICCION-tELEFONO-->  "+ desa.getDescTelefono());
		desa.setOperadorTelefonico(objTelefono2.getOperadorTelefonico());
		log.info("OPERADOR-TELEFONICO-->  "+ desa.getOperadorTelefonico());
		/* Estado (18) Deshabilitar Telefono*/
		estado.setIdestadogeneral(18);
		log.info("se asigna 18");
		desa.setTbEstadoGeneral(estado);
		log.info("Serca al Actualizar Cliente SERVICIO IMPLE");
		objTelefonoEmpleadoDAO.actualizarTelefonoEmpleado(desa);			
		} 				
	
					/***************DOMICILIO CLIENTE********************/
	
	log.info("ANTES INSERTAR DOMICILIO NUEVO DE FOR LINEA 197--->   "+ DomicilioPersonaList.size());
	for (DomicilioPersonaSie objDomicilio1 : DomicilioPersonaList) {
		DomicilioPersonaSie dom = new DomicilioPersonaSie();
//		UbigeoSie objubigeo24 = new UbigeoSie();
		log.info("bien!! por agregar domicilionew");		
	if (objDomicilio1.getItem().equalsIgnoreCase("Por Agregar")) {			
			dom.setIdcliente(Cliente);
			log.info("ID_CLIENTE-SERVICIO-DOMICILIO"+ dom.getIdcliente());
			
			dom.setDomicilio(objDomicilio1.getDomicilio());
			log.info("DOMICILIO-New--->  "+dom.getDomicilio());
			
			dom.setReferencia(objDomicilio1.getReferencia());
			log.info("REFERENCIA---> "+ dom.getReferencia());
			
			dom.setTbTipoCasa(tipocasanew);
			log.info("TIPO-CASA-NUEVO-->  "+ dom.getTbTipoCasa().getIdtipocasa());
			
			dom.setTbUbigeo(objubigeo24);
			log.info("UBIGEO NEW DOMICILIO--->  "+ dom.getTbUbigeo().getIdubigeo());
			
			dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
			log.info("apunto de Entrar ah Implementacion DAO -- DOMICILIO");
			
			objDomicilioEmpleadoDao.insertarDomicilioEmpleado(dom);			
		}
		
	}
	
	log.info("guardo- DomicilioDeshabilitado : " + DomicilioPersonaDeshabilitado.size());
	for(DomicilioPersonaSie objDomicilio2 : DomicilioPersonaDeshabilitado){
		DomicilioPersonaSie desado = new DomicilioPersonaSie();
		
		desado.setIddomiciliopersona(objDomicilio2.getIddomiciliopersona());
		log.info("ID-DOMICILIO-CLIENTE--> "+ desado.getIddomiciliopersona());
		
		desado.setDomicilio(objDomicilio2.getDomicilio());
		log.info("DOMICILIO--->  "+ desado.getDomicilio());
		
		desado.setIdcliente(Cliente);
		log.info("ID-CLIENTE-->  "+ desado.getIdcliente());
		
		desado.setReferencia(objDomicilio2.getReferencia());
		log.info("REFERENCIA--->  "+desado.getReferencia());
		
		desado.setTbTipoCasa(tipoCasa);
		log.info("TIPO-CASA-->  "+ desado.getTbTipoCasa().getIdtipocasa());
		
		desado.setTbUbigeo(ubigeo);
		log.info("UBIGEO-->  "+desado.getTbUbigeo().getIdubigeo());
		
		/* Estado (16) Deshabilitar Domicilio*/
		estado.setIdestadogeneral(16);
		log.info("se asigna 16");
		desado.setTbEstadoGeneral(estado);
		log.info("Serca al Actualizar Cliente SERVICIO IMPLE");
		objDomicilioEmpleadoDao.actualizarDomicilioEmpleado(desado);
						
		} 
	
	
//	log.info("ANTES DE FOR LINEA 101--->   "+ TelefonoPersonaList.size());
//	for (TelefonoPersonaSie objTelefono1 : TelefonoPersonaList) {
//		//GUIARME DE CONTRATO ACTUALIZAR LINEA 304
//		TelefonoPersonaSie tel = new TelefonoPersonaSie();
//		log.info("bien!! por agregar ");
//	if(objTelefono1.getItem().equalsIgnoreCase("Por Agregar")){
//		tel.setIdcliente(Cliente);
//		log.info("ID_CLIENTE-SERVICIO"+ tel.getIdcliente());
//		tel.setTelefono(objTelefono1.getTelefono());
//		log.info("Nº TELEFONO o CELULAR "+tel.getTelefono());
//		tel.setTipotelefono(objTelefono1.getTipotelefono());
//		log.info("TIPO DE TELEFONO--->  "+ tel.getTipotelefono());
//		tel.setDescTelefono(objTelefono1.getDescTelefono());
//		log.info("DESCRICCION-TELeFONO... "+ tel.getDescTelefono());			
//		tel.setOperadorTelefonico(objTelefono1.getOperadorTelefonico());
//		log.info("OPERADOR TELEFONICO-->   "+ tel.getOperadorTelefonico());
//		tel.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
//		objTelefonoEmpleadoDAO.insertarTelefonoEmpleado(tel);
//		}
//	}
	

	} catch (Exception e) {
		e.printStackTrace();
	}	
		
		
	Cliente = new ClienteSie();	
	objDomicilio = new DomicilioPersonaSie();
	TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
	TelefonoDeshabilitado = new ArrayList<TelefonoPersonaSie>();
		
		
		
		
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
