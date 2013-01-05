package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.ProductoService;

@Stateless
public class EmpleadoSieServiceImpl implements EmpleadoSieService{
	/*llamo a mi EJB y redirecciono todo al DAO*/
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;  
	@EJB
	private TipoDocumentoDAO objTipoDocDao;
	@EJB 
	private DomicilioEmpleadoDAO objDomicilioDao;
	@EJB 
	private TelefonoEmpleadoDAO objTelefonoDao;
	@EJB
	private ContratoEmpleadoDAO objContratoEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	@EJB
	private ProductoService objProductoService;
	
	@EJB
	private UbigeoDAO objUbigeoDao;
	@EJB
	private TipoCasaDAO objTipoCasaDao;
    
	public static Log log = LogFactory.getLog(EmpleadoSieServiceImpl.class);
	
	
	public void insertarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, int codigoTipoDocumento, int codigoCargoEmpleado,
		int idUbigeo, int tipo, int CargoEmpleado, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList){
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		log.info("empl  "+objEmpleado.getApematemp());
			/**Inserta el empleado**/
			objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
			/*Estado del empleado: habilitado(3)*/
			objEmpleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(3));
			objEmpleadoDao.insertarEmpleado(objEmpleado);
			
			/**Inserta el domicilio**/
			objDomicilio.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			log.info("empl id "+objEmpleado.getIdempleado());
			//objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(ubigeo));
			objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(idUbigeo));
			objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));
			/*Estado del domicilio: habilitado(15)*/
			objDomicilio.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(15));
			objDomicilioDao.insertarDomicilioEmpleado(objDomicilio);
			
			/**Inserta el telefono**/
			objTelefono.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			/*Estado del telefono: habilitado(17)*/
			objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
			objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
			
            /**Inserta el detallecargo**/
			log.info("sxsx   --->  "+contratoEmpleadoList.size());
			for(int i = 0; i < contratoEmpleadoList.size(); i++){
			log.info("YAAA  :D ");
			ContratoEmpleadoSie c = new ContratoEmpleadoSie();
			c=contratoEmpleadoList.get(i);
			log.info("empl id "+objEmpleado.getIdempleado());
			c.setTbEmpleado1(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			c.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(56));
			//insertamos en detalle contrato empleado
			objContratoEmpleadoDao.insertContratoEmpleado(c);
			}
			
			/**Inserta la lista de teléfonos**/
			for (int i = 0; i < TelefonoPersonaList.size(); i++) {
				if (TelefonoPersonaList.get(i).getNuevoT()==1) {
					//insertar
				TelefonoPersonaSie telefono=new TelefonoPersonaSie();
				telefono =	TelefonoPersonaList.get(i);
				telefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
				telefono.setIdempleado(objEmpleado);
				objTelefonoDao.insertarTelefonoEmpleado(telefono);	
				log.info("");
				}else{
					//actualizar	
					objTelefonoDao.actualizarTelefonoEmpleado(TelefonoPersonaList.get(i));
				}
				}
			
			log.info("insertando..... ");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#actualizarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie, com.edicsem.pe.sie.entity.DomicilioPersonaSie, com.edicsem.pe.sie.entity.TelefonoPersonaSie, int, int, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, int, java.lang.String, int, int, int, int, int, int)
	 */
	public void actualizarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, int codigoTipoDocumento, int codigoCargoEmpleado, 
		    int estado, int idUbigeo, int tipo, int CargoEmpleado, 
			int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado,  List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList) {			
			try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'actualizar()'"+ objDomicilio.getIddomiciliopersona());
					log.info("Entering my method 'actualizar()'"+ objTelefono.getIdtelefonopersona());
					log.info("Entering my method 'actualizar()'"+ objEmpleado.getIdempleado());
				}
					
				/**Actualiza el empleado**/
				objEmpleado.setIdempleado(objEmpleado.getIdempleado());
				//objEmpleado.setTbCargoEmpleado(objCargoEmpDao.buscarCargoEmpleado(CargoEmpleado));
				objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
				objEmpleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(3));
				objEmpleadoDao.actualizarEmpleado(objEmpleado);
				
				/**Actualiza el domicilio**/
				objDomicilio.setIddomiciliopersona(objDomicilio.getIddomiciliopersona());
				objDomicilio.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
				//objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(ubigeo));
				objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(idUbigeo));
				objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));
				objDomicilio.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(15));
				objDomicilioDao.actualizarDomicilioEmpleado(objDomicilio);
				
				/**Actualiza el telefono**/
				objTelefono.setIdtelefonopersona(objTelefono.getIdtelefonopersona());
				objTelefono.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
				objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
				objTelefonoDao.actualizarTelefonoEmpleado(objTelefono);
				
				/**Actualiza el detallecargo**/
				for(int i = 0; i < contratoEmpleadoList.size(); i++){
				
				log.info("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				}
				log.info("actualizando..... ");
				
				/**Actualiza la lista de teléfonos**/
				for (int i = 0; i < TelefonoPersonaList.size(); i++) {
					if (TelefonoPersonaList.get(i).getNuevoT()==1) {
						//insertar
					TelefonoPersonaSie telefono=new TelefonoPersonaSie();
					telefono =	TelefonoPersonaList.get(i);
					telefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
					telefono.setIdempleado(objEmpleado);
					objTelefonoDao.insertarTelefonoEmpleado(telefono);	
					log.info("");
					}else{
						//actualizar	
						objTelefonoDao.actualizarTelefonoEmpleado(TelefonoPersonaList.get(i));
					}
					}
				
				//Agregen esto a tus redirecciones parece que esta referenciando a otra cosa verifiquen a donde estan 
				//llenando los datos 
				//Redirections.redirectionsPage(Constants.PAGE_MODULE, Constants.LISTA_CARGO_PAGE);
			   }catch (Exception e) {
				e.printStackTrace();
			}
			objEmpleado = new EmpleadoSie();
			objTelefono = new TelefonoPersonaSie();
			objDomicilio = new DomicilioPersonaSie();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#eliminarEmpleado(int)
	 */
	public void eliminarEmpleado(int id) {
		objEmpleadoDao.eliminarEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#buscarEmpleado(int)
	 */
	public EmpleadoSie buscarEmpleado(int id) {
		return objEmpleadoDao.buscarEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarEmpleados()
	 */
	public List listarEmpleados() {
		return objEmpleadoDao.listarEmpleados();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarEmpleadosXCargo(int)
	 */
	public List listarEmpleadosXCargo(int idCargo) {
		return objEmpleadoDao.listarEmpleadosXCargo(idCargo);
	}


	
	public List listarEmpleadoxEmpresas(int parametroObtenido) {
		log.info("dentro del servicio listar Empleado x Empresas ");
		return objEmpleadoDao.listarEmpleadoxEmpresas(parametroObtenido);
	}



	
}
