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
		log.info("inicio del m�todo insertar empleado");
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
	String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList, List<TelefonoPersonaSie> TelefonoDeshabilitado, List<ContratoEmpleadoSie> ContratoDeshabilitado, List<DetEmpresaEmpleadoSie> detEmpresaEmpList) {			
			try {
				if (log.isInfoEnabled()) {
					log.info("inicio del m�todo insertar empleado");
				}
				/**Actualiza el empleado**/
				
				objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
				
				objEmpleadoDao.actualizarEmpleado(objEmpleado);
				/**Actualiza telefono(s)**/
				log.info("bien!! :d ");
				for (TelefonoPersonaSie objTelefono : TelefonoPersonaList) {
					log.info("bien!! por agregar ");
					if(objTelefono.getItem().equalsIgnoreCase("Por Agregar")){
					objTelefono.setIdempleado(objEmpleado);
					objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
					objTelefonoDao.insertarTelefonoEmpleado(objTelefono);
					}
				}
				for (TelefonoPersonaSie objTelefono :  TelefonoDeshabilitado) {
					log.info("deshabilitar ");
					objTelefono.setIdempleado(objEmpleado);
					objTelefono.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(18));
					objTelefonoDao.actualizarTelefonoEmpleado(objTelefono);
				}
				 log.info(" tipo casa "+ tipo);
				/**Actualiza el domicilio**/
				objDomicilio.setIddomiciliopersona(objDomicilio.getIddomiciliopersona());
				objDomicilio.setIdempleado(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
				objDomicilio.setTbUbigeo(objUbigeoDao.findUbigeo(Integer.parseInt(idUbigeo)));
				objDomicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(tipo));
				/*Estado del domicilio: habilitado(15)*/
				objDomicilio.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(15));
				objDomicilioDao.actualizarDomicilioEmpleado(objDomicilio);
				/**Actualiza Contrato(s)**/
				for (ContratoEmpleadoSie objContrato : contratoEmpleadoList) {
					if(objContrato.getTipo()!=null && objContrato.getTipo().equalsIgnoreCase("Por Agregar")){
						objContrato.setIdempleado(objEmpleado.getIdempleado());
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
				}
				log.info("actualizando..... ");
			   }catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#eliminarEmpleado(int)
	 */
	public void eliminarEmpleado(int id) {
		EmpleadoSie empleado = objEmpleadoDao.buscarEmpleado(id);
		empleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(4));
		objEmpleadoDao.actualizarEmpleado(empleado);
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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarEmpleadoxEmpresas(int)
	 */
	public List listarEmpleadoxEmpresas(int parametroObtenido) {
		log.info("dentro del servicio listar Empleado x Empresas ");
		return objEmpleadoDao.listarEmpleadoxEmpresas(parametroObtenido);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarEmpleadoxCargo(int)
	 */
	public List listarEmpleadoxCargo(int parametroObtenido) {
		log.info("dentro del servicio listar Empleado x Cargo ");
		
		return objEmpleadoDao.listarEmpleadoxCargo(parametroObtenido);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarExpositor(int)
	 */
	public List listarExpositor(int idEmpresa) {
		return objEmpleadoDao.listarExpositor(idEmpresa);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarDni()
	 */
	public List listarDni() {
		return objEmpleadoDao.listarDni();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarUsuario()
	 */
	public List listarUsuario() {
		return objEmpleadoDao.listarUsuario();
	}


	public EmpleadoSie buscarEmpleadoVendedor(String nombreCompleto) {
log.info("dentro del servicio en busca del nombre y Id empleado--> SERVICEIMPLE-->"+ nombreCompleto);
		return objEmpleadoDao.buscarEmpleadoVendedor(nombreCompleto);
	}
}
