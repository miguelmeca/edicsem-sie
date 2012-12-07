package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetCargoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie; 
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetalleCarEmpDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;

@Stateless
public class EmpleadoSieServiceImpl implements EmpleadoSieService{
	/*llamo a mi EJB y redirecciono todo al DAO*/
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;  
	@EJB
	private CargoEmpleadoDAO objCargoEmpDao;
	@EJB
	private TipoDocumentoDAO objTipoDocDao;
	@EJB 
	private DomicilioEmpleadoDAO objDomicilioDao;
	@EJB 
	private TelefonoEmpleadoDAO objTelefonoDao;
	@EJB 
	private DetalleCarEmpDAO objDetCargoDao;
	
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	
	@EJB
	private UbigeoDAO objUbigeoDao;
	@EJB
	private TipoCasaDAO objTipoCasaDao;
    
	public static Log log = LogFactory.getLog(EmpleadoSieServiceImpl.class);
	
	
	public void insertarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, DetCargoEmpleadoSie objDetCargo, int codigoTipoDocumento, int codigoCargoEmpleado, String mensaje, 
			String fijo, int estado, String direccion, int idUbigeo, int estado2, int tipo, String nombre, int CargoEmpleado, 
			int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado, int estadoe, List<String> listacargo){
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'"+ fijo);
			}
			
			/**Inserta el empleado**/
			//objEmpleado.setTbCargoEmpleado(objCargoEmpDao.buscarCargoEmpleado(CargoEmpleado));
			objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
			/*Estado del empleado: habilitado(3)*/
			objEmpleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(3));
			objEmpleadoDao.insertarEmpleado(objEmpleado);
			
			/**Inserta el domicilio**/
			objDomicilio.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			//objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(ubigeo));
			objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(idUbigeo));
			objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));
			/*Estado del domicilio: habilitado(15)*/
			objDomicilio.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(15));
			objDomicilio.setDomicilio(direccion);
			objDomicilioDao.insertarDomicilioEmpleado(objDomicilio);
			
			/**Inserta el telefono**/
			objTelefono.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			/*Estado del telefono: habilitado(17)*/
			objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
			objTelefono.setTelefono(fijo);
			objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
			
            
            /**Inserta el detallecargo**/
			log.info("sxsx   --->  "+listacargo.size());
			for(int i = 0; i < listacargo.size(); i++){
			objDetCargo= new DetCargoEmpleadoSie();
			objDetCargo.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(19));
			objDetCargo.setTbEmpleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			log.info("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			//listacargo.get(i).getIdcargoempleado();
			//listacargo.get();
			//int cargo;
			//listacargo.get(j);
			//int cargo= (int)listacargo.get(i);
			log.info("probemossssss :)  "+listacargo.get(i));
			int d = Integer.parseInt(listacargo.get(i)+"");
			objDetCargo.setTbCargoEmpleado(objCargoEmpDao.buscarCargoEmpleado(d));
			//int a = Integer.parseInt(listacargo.get(i).toString());
			//CargoEmpleadoSie ca =objCargoEmpDao.buscarCargoEmpleado(a);
			//log.info(" "+ ca.getDescripcion());
			//objDetCargo.setTbCargoEmpleado(ca);
			//objDetCargo.setTbCargoEmpleado(cargo);
			//log.info(" " + listacargo.get(i).getDescripcion()+"  "+ listacargo.get(i).getIdcargoempleado() );
			//objDetCargo.setTbCargoEmpleado(listacargo.get(i));
			//objDetCargo.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(19));
			objDetCargoDao.insertarDetalleCarEmp(objDetCargo);
			}
			
			log.info("insertando..... ");

			//Agregen esto a tus redirecciones parece que esta referenciando a otra cosa verifiquen a donde estan 
			//llenando los datos 
			//Redirections.redirectionsPage(Constants.PAGE_MODULE, Constants.LISTA_CARGO_PAGE);
		   }catch (Exception e) {
			e.printStackTrace();
			//nombre = e.getMessage();
			//msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					//Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			//log.error(e.getMessage());
			//FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objEmpleado = new EmpleadoSie();
		objTelefono = new TelefonoPersonaSie();
		objDomicilio = new DomicilioPersonaSie();
		objDetCargo = new DetCargoEmpleadoSie();
	}
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#actualizarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie, com.edicsem.pe.sie.entity.DomicilioPersonaSie, com.edicsem.pe.sie.entity.TelefonoPersonaSie, int, int, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, int, java.lang.String, int, int, int, int, int, int)
	 */
	public void actualizarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, DetCargoEmpleadoSie objDetCargo, int codigoTipoDocumento, int codigoCargoEmpleado, String mensaje, 
			String fijo, int estado, String direccion, int idUbigeo, int estado2, int tipo, String nombre, int CargoEmpleado, 
			int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado, int estadoe, List<String> listacargo) {			
			try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'actualizar()'"+ objDomicilio.getIddomiciliopersona());
					log.info("Entering my method 'actualizar()'"+ objTelefono.getIdtelefonopersona());
					log.info("Entering my method 'actualizar()'"+ objEmpleado.getIdempleado());
					log.info("Entering my method 'actualizar()'"+ direccion);
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
				objDomicilio.setDomicilio(direccion);
				objDomicilioDao.actualizarDomicilioEmpleado(objDomicilio);
				
				/**Actualiza el telefono**/
				objTelefono.setIdtelefonopersona(objTelefono.getIdtelefonopersona());
				objTelefono.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
				objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
				objTelefono.setTelefono(fijo);
				objTelefonoDao.actualizarTelefonoEmpleado(objTelefono);
				
				/**Actualiza el detallecargo**/
				for(int i = 0; i < listacargo.size(); i++){
				objDetCargo= new DetCargoEmpleadoSie();
				objDetCargo.setIddetcargoempl(objDetCargo.getIddetcargoempl());
				objDetCargo.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(19));
				objDetCargo.setTbEmpleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
				log.info("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				//listacargo.get(i).getIdcargoempleado();
				//listacargo.get();
				//CargoEmpleadoSie cargo = new CargoEmpleadoSie();
				//listacargo.get(j);
				objDetCargo.setTbCargoEmpleado(objCargoEmpDao.buscarCargoEmpleado(Integer.parseInt(listacargo.get(i))));
				//objDetCargo.setTbCargoEmpleado(cargo);
				//log.info(" " + listacargo.get(i).getDescripcion()+"  "+ listacargo.get(i).getIdcargoempleado() );
				//objDetCargo.setTbCargoEmpleado(listacargo.get(i));
				//objDetCargo.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(19));
				objDetCargoDao.insertarDetalleCarEmp(objDetCargo);
				}
				log.info("actualizando..... ");

				//Agregen esto a tus redirecciones parece que esta referenciando a otra cosa verifiquen a donde estan 
				//llenando los datos 
				//Redirections.redirectionsPage(Constants.PAGE_MODULE, Constants.LISTA_CARGO_PAGE);
			   }catch (Exception e) {
				e.printStackTrace();
				//nombre = e.getMessage();
				//msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						//Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
				//log.error(e.getMessage());
				//FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			objEmpleado = new EmpleadoSie();
			objTelefono = new TelefonoPersonaSie();
			objDomicilio = new DomicilioPersonaSie();
			objDetCargo = new DetCargoEmpleadoSie();
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

}
