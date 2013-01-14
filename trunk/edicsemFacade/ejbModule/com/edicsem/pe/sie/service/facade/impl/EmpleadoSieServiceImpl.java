package com.edicsem.pe.sie.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.model.dao.TipoPagoDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.ProductoService;

@Stateless
public class EmpleadoSieServiceImpl implements EmpleadoSieService{
	/*llamo a mi EJB y redirecciono todo al DAO*/
	@EJB
	private EmpresaDAO objEmpresaDao;  
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;  
	@EJB
	private TipoDocumentoDAO objTipoDocDao;
	@EJB
	private CargoEmpleadoDAO objCargoEmpDao;
	@EJB 
	private DomicilioEmpleadoDAO objDomicilioDao;
	@EJB 
	private TelefonoEmpleadoDAO objTelefonoDao;
	@EJB
	private ContratoEmpleadoDAO objContratoEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	@EJB
	private TipoPagoDAO objTipoPagoDao;
	@EJB
	private UbigeoDAO objUbigeoDao;
	@EJB
	private TipoCasaDAO objTipoCasaDao;
	@EJB
	private DetEmpresaEmpleadoDAO objDetEmpresaEmpDao;
	   
	public static Log log = LogFactory.getLog(EmpleadoSieServiceImpl.class);
		
	public void insertarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, int codigoTipoDocumento, int codigoCargoEmpleado,
		String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList){
		log.info("inicio del método insertar empleado");
		/**Inserta el empleado**/
		objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
		/**Estado del empleado: habilitado(3)**/
		objEmpleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(3));
		objEmpleadoDao.insertarEmpleado(objEmpleado);
		/**Inserta telefono(s)**/
		for (TelefonoPersonaSie objTelefono : TelefonoPersonaList) {
			objTelefono.setIdempleado(objEmpleado);
			objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
			objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
		}
	    /**Inserta el domicilio**/
		objDomicilio.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
		objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(Integer.parseInt(idUbigeo)));
		objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));
		/**Estado del domicilio: habilitado(15)**/
		objDomicilio.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(15));
		objDomicilioDao.insertarDomicilioEmpleado(objDomicilio);
		/**Insertar Detalle Empleado(s)**/
		
		/**Inserta Contrato(s)**/
		for (ContratoEmpleadoSie objContrato : contratoEmpleadoList) {
			objContrato.setIdempleado(objEmpleado.getIdempleado());
			/**Insertar Detalle empresa del empleado**/
			DetEmpresaEmpleadoSie detempemp=new DetEmpresaEmpleadoSie();
			detempemp.setTbEmpresa(objEmpresaDao.findEmpresa(objContrato.getEmpresa()));
			detempemp.setTbEmpleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			detempemp.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(64));
			objDetEmpresaEmpDao.insertDetEmpresaEmpleadoSie(detempemp);
			objContrato.setTbDetEmpresaEmpleado(objDetEmpresaEmpDao.findDetEmpresaEmpleadoSie(detempemp.getIdDetEmpresaEmpl()));
			objContrato.setTbCargoempleado(objCargoEmpDao.buscarCargoEmpleado(idCargo));
			objContrato.setTbTipoPago(objTipoPagoDao.findTipoPago(idTipoPago));
			objContrato.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
			objContratoEmpleadoDao.insertContratoEmpleado(objContrato);
		}
		/**fin Inseta Contrato(s)**/
		log.info("insertando..... ");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#actualizarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie, com.edicsem.pe.sie.entity.DomicilioPersonaSie, com.edicsem.pe.sie.entity.TelefonoPersonaSie, int, int, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, int, java.lang.String, int, int, int, int, int, int)
	 */
	public void actualizarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, int codigoTipoDocumento, int codigoCargoEmpleado,
	String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList) {			
			try {
				if (log.isInfoEnabled()) {
					log.info("inicio del método insertar empleado");
				}
				/**Actualiza el empleado**/
				objEmpleado.setIdempleado(objEmpleado.getIdempleado());
				objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
				objEmpleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(3));
				objEmpleadoDao.actualizarEmpleado(objEmpleado);
				/**Actualiza telefono(s)**/
				for (TelefonoPersonaSie objTelefono : TelefonoPersonaList) {
					objTelefono.setIdtelefonopersona(objTelefono.getIdtelefonopersona());
					objTelefono.setIdempleado(objEmpleado);
					objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
					objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
				}
				/**Actualiza el domicilio**/
				objDomicilio.setIddomiciliopersona(objDomicilio.getIddomiciliopersona());
				objDomicilio.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
				objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(Integer.parseInt(idUbigeo)));
				objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));
				/*Estado del domicilio: habilitado(15)*/
				objDomicilio.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(15));
				objDomicilioDao.insertarDomicilioEmpleado(objDomicilio);
				/**Actualiza Contrato(s)**/
				for (ContratoEmpleadoSie objContrato : contratoEmpleadoList) {
					objContrato.setIdContratoEmpl(objContrato.getIdContratoEmpl());
					objContrato.setIdempleado(objEmpleado.getIdempleado());
					DetEmpresaEmpleadoSie detempemp=new DetEmpresaEmpleadoSie();
					detempemp.setIdDetEmpresaEmpl(detempemp.getIdDetEmpresaEmpl());
					detempemp.setTbEmpresa(objEmpresaDao.findEmpresa(objContrato.getEmpresa()));
					detempemp.setTbEmpleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
					detempemp.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(64));
					objDetEmpresaEmpDao.insertDetEmpresaEmpleadoSie(detempemp);
					objContrato.setTbDetEmpresaEmpleado(objDetEmpresaEmpDao.findDetEmpresaEmpleadoSie(detempemp.getIdDetEmpresaEmpl()));
					objContrato.setTbCargoempleado(objCargoEmpDao.buscarCargoEmpleado(idCargo));
					objContrato.setTbTipoPago(objTipoPagoDao.findTipoPago(idTipoPago));
					objContrato.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
					objContratoEmpleadoDao.insertContratoEmpleado(objContrato);
				}	
				log.info("actualizando..... ");				
				//Agregen esto a tus redirecciones parece que esta referenciando a otra cosa verifiquen a donde estan 
				//llenando los datos 
				//Redirections.redirectionsPage(Constants.PAGE_MODULE, Constants.LISTA_CARGO_PAGE);
			   }catch (Exception e) {
				e.printStackTrace();
			}
			objEmpleado = new EmpleadoSie();
			TelefonoPersonaList = new ArrayList<TelefonoPersonaSie>();
			objDomicilio = new DomicilioPersonaSie();
			contratoEmpleadoList = new ArrayList<ContratoEmpleadoSie>();
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
