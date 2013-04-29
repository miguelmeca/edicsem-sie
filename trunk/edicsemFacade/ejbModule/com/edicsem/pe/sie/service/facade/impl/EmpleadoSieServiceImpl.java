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
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;
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
	
	public static Log log = LogFactory.getLog(EmpleadoSieServiceImpl.class);
		
	public void insertarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, int codigoTipoDocumento, int codigoCargoEmpleado,
		String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList){
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
		
		/**Inserta Contrato(s)**/
		for (ContratoEmpleadoSie objContrato : contratoEmpleadoList) {
			objContrato.setTbEmpleado1(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
			objContrato.setTbEmpresa(objEmpresaDao.findEmpresa(objContrato.getEmpresa()));
			objContrato.setTbCargoempleado(objCargoEmpDao.buscarCargoEmpleado(idCargo));
			objContrato.setTbTipoPago(objTipoPagoDao.findTipoPago(idTipoPago));
			objContrato.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
			objContratoEmpleadoDao.insertContratoEmpleado(objContrato);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#actualizarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie, com.edicsem.pe.sie.entity.DomicilioPersonaSie, com.edicsem.pe.sie.entity.TelefonoPersonaSie, int, int, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, int, java.lang.String, int, int, int, int, int, int)
	 */
	public void actualizarEmpleado(EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, int codigoTipoDocumento, int codigoCargoEmpleado,
	String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList, List<TelefonoPersonaSie> TelefonoDeshabilitado, List<ContratoEmpleadoSie> ContratoDeshabilitado) {			
			objEmpleado.setTbTipoDocumentoIdentidad(objTipoDocDao.buscarTipoDocumento(TipoDocumento));
			objEmpleadoDao.actualizarEmpleado(objEmpleado);
			/**Actualiza telefono(s)**/
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
					objContrato.setTbEmpresa(objEmpresaDao.findEmpresa(objContrato.getEmpresa()));
					objContrato.setTbEmpleado1(objEmpleadoDao.buscarEmpleado(objEmpleado.getIdempleado()));
					objContrato.setTbCargoempleado(objCargoEmpDao.buscarCargoEmpleado(idCargo));
					objContrato.setTbTipoPago(objTipoPagoDao.findTipoPago(idTipoPago));
					objContrato.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(17));
					objContratoEmpleadoDao.insertContratoEmpleado(objContrato);
				}
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
		return objEmpleadoDao.listarEmpleadoxEmpresas(parametroObtenido);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#listarEmpleadoxCargo(int)
	 */
	public List listarEmpleadoxCargo(int parametroObtenido) {
		return objEmpleadoDao.listarEmpleadosXCargo(parametroObtenido);
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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#buscarEmpleadoPorNombreCompleto(java.lang.String)
	 */
	public EmpleadoSie buscarEmpleadoPorNombreCompleto(String nombreCompleto) {
		return objEmpleadoDao.buscarEmpleadoPorNombreCompleto(nombreCompleto);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.EmpleadoSieService#buscarEmpleadosPorUsuario(java.lang.String)
	 */
	public EmpleadoSie buscarEmpleadosPorUsuario(String usuario) {
		return objEmpleadoDao.buscarEmpleadosPorUsuario(usuario);
	}
}
