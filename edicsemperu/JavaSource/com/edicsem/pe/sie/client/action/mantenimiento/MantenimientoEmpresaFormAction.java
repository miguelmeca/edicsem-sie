package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.client.action.MantenimientoCargoEmpleadoSearchAction;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "empresaForm")
@SessionScoped
public class MantenimientoEmpresaFormAction extends
		BaseMantenimientoAbstractAction {

	public int idempresa;
	public String descripcion;
	private int ide, idEstadoGeneral;
	public String razonsocial;
	public String numruc;
	public String numtel;
	public String email;

	private boolean editMode;
	private String mensaje;

	private Log log = LogFactory.getLog(MantenimientoEmpresaFormAction.class);
	private boolean newRecord = false;
	private int estado;
	private EmpresaSie objEmpresaSie;
	private EmpresaSie nuevo;

	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerEmpresa;

	@ManagedProperty(value = "#{empresaSearch}")
	private MantenimientoEmpresaSearchAction mantenimientoEmpresaSearch;

	@EJB
	private EmpresaService empresaService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	@EJB
	private DetEmpresaEmpleadoService objDetEmpresaEmpleadoService;
	
	@EJB
	private KardexService objKardexService;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #init()
	 */
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoEmpresaFormAction'");
		objEmpresaSie = new EmpresaSie();
		nuevo = new EmpresaSie();
	}

	public String agregar() {
		log.info("agregar()");
		editMode = true;
		objEmpresaSie = new EmpresaSie();
		setNewRecord(true);
		
		return getViewList();
	}

	public String update() throws Exception {
		log.info("update()" + objEmpresaSie.getIdempresa());

		EmpresaSie s = empresaService.findEmpresa(objEmpresaSie.getIdempresa());

		log.info(" id cargo " + s.getIdempresa() + " des " + s.getDescripcion());

		setIdempresa(s.getIdempresa());
		setDescripcion(s.getDescripcion());
		setRazonsocial(s.getRazonsocial());
		setNumruc(s.getNumruc());
		setNumtel(s.getNumtelefono());		
		setEmail(s.getEmail());
		setEstado(s.getTbEstadoGeneral().getIdestadogeneral());
		setNewRecord(false);
		editMode = false;
		return getViewList();

	}

	public String Eliminarempresa() throws Exception {
		mensaje = null;
		objEmpresaSie = new EmpresaSie();
		int parametroObtenido;
		EmpresaSie em = new EmpresaSie();
		  RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'" + getIde());
			}
			
			parametroObtenido = getIde();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
			
//aqui declaro la condicional para poder eliminar empresa y empleados.
			
if((verificarEmpleadoConEmpresa(parametroObtenido)) == (verificarProductoConEmpresa(parametroObtenido))){

	// valida si el ID parametroObtenido no pertenece a ninguna de las dos Tablas Detalle EMpleado y Kardex
	//se ejecutara el DESHABILITAR
			em = empresaService.findEmpresa(parametroObtenido);
			log.info(" ------Emilinando ");

			objEmpresaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(8));
			objEmpresaSie.setIdempresa(em.getIdempresa());
			objEmpresaSie.setDescripcion(em.getDescripcion());
			objEmpresaSie.setRazonsocial(em.getRazonsocial());
			objEmpresaSie.setNumruc(em.getNumruc());
			objEmpresaSie.setNumtelefono(em.getNumtelefono());
			objEmpresaSie.setEmail(em.getEmail());

			log.info("-----Android1>>>"+ objEmpresaSie.getTbEstadoGeneral().getIdestadogeneral());
			log.info("actualizando ESTADO..... ");

			empresaService.updateEmpresa(objEmpresaSie);
			log.info("actualizando..... ");
			
			context.execute(":frmEmpresaFormList:empresaTable");
//return mantenimientoEmpresaSearch.listar();
//context.execute("confirmationEmpresa.hide()"); FALTA poner el el popud ajax="false"
//context.execute("mantenimientoEmpresaFormList.jsf");
//action="#{empresaSearch.listar}"
//@ManagedProperty(value = "#{mantenimientoCargoEmpleadoSearchAction}")
//private MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch;
//			 
			}
else {

	  if (verificarEmpleadoConEmpresa(parametroObtenido) == false) {
		  context.execute("someDialog.show()");
	}
	  else if(verificarProductoConEmpresa(parametroObtenido) == false) {
		  context.execute("someDialog2.show()");
	}
	
//	context.execute("someDialog.show()");
	
	
//	mensaje = "tiene relacion : ";
//	RequestContext.getCurrentInstance().addCallbackParam("showDialog", false);
//    FaceMessage.FaceMessageError("ALERTA", "La Empresa no se puede elminar ya que se encontraron empleados con esta empresa");
//	FaceMessage.FaceMessageInfo("ALERTA", "La Empresa no se puede elminar ya que se encontraron productos con esta empresa");
}

		} catch (Exception e) {
			e.printStackTrace();

			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objEmpresaSie = new EmpresaSie();

		return mantenimientoEmpresaSearch.listar();
	}

	

	private boolean verificarEmpleadoConEmpresa(int idcargo) {
		// Aqui verificaremos si esta empresa pertenece a un empleado en la TB.DetalleEmpresaEmpleado
		return objDetEmpresaEmpleadoService.verificarEmpleadoConEmpresa(idcargo) ;
	}

	private boolean verificarProductoConEmpresa(int idcargo) {
		// Aqui verificaremos si esta empresa pertenece a un empleado en la TB.DetalleEmpresaEmpleado
		return objKardexService.verificarProductoConEmpresa(idcargo) ;
	}
	
	
	
	
	
	public String insertar() {

		log.info("insertar() " + isNewRecord() + " desc "
				+ objEmpresaSie.getDescripcion() +"  "+ " razon social" +"  "+ "ruc" + objEmpresaSie.getNumruc()
				+ objEmpresaSie.getRazonsocial() +"  "+ "EMail"
				+ objEmpresaSie.getEmail()  );

		/* CODIGO DURO ---> */objEmpresaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(7));

		/* Esto se setea cuando pertenece a una segunda tabla (--->) */
		try {

			if (isNewRecord()) {

				objEmpresaSie.getRazonsocial();
				objEmpresaSie.getDescripcion();
				objEmpresaSie.getNumruc();
				objEmpresaSie.getNumtelefono();
				objEmpresaSie.getEmail();
				
				int error = 0;
				List<EmpresaSie> lista = mantenimientoEmpresaSearch.getEmpresaList();
				

				for (int i = 0; i < lista.size(); i++) {
					 EmpresaSie s = lista.get(i);
					if ( 		s.getRazonsocial().equalsIgnoreCase(objEmpresaSie.getRazonsocial())
							 && s.getDescripcion().equalsIgnoreCase(objEmpresaSie.getDescripcion())							
							 && s.getNumruc().equalsIgnoreCase(objEmpresaSie.getNumruc())
							 && s.getNumtelefono().equalsIgnoreCase(objEmpresaSie.getNumtelefono())
							 && s.getEmail().equalsIgnoreCase(objEmpresaSie.getEmail()))  {
					log.info("Error ... Ya se encuentra una empresa igual");
						error = 1;
						break;
					}
				}

				if (error == 0) {

					empresaService.insertEmpresa(objEmpresaSie);

				} else {
					// Mostrar Validacion
					log.info("mensaje de error");
				}
				log.info("aqui validando si existe o no");

			} else {

				objEmpresaSie.setIdempresa(getIdempresa());

				objEmpresaSie.setDescripcion(getDescripcion());
				objEmpresaSie.setRazonsocial(getRazonsocial());
				objEmpresaSie.setNumruc(getNumruc());
				objEmpresaSie.setNumtelefono(getNumtel());
				objEmpresaSie.setEmail(getEmail());
				objEmpresaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(7));

				log.info("----->>>>"
						+ objEmpresaSie.getDescripcion()+ "  " + objEmpresaSie.getNumruc()
						+ objEmpresaSie.getDescripcion() + objEmpresaSie.getEmail());
				log.info("actualizando EMPRESA..... ");

	
				
				empresaService.updateEmpresa(objEmpresaSie);

				log.info("actualizando..... ");
				log.info("objEmpresaSie.isNewRecord() : ");
			}
		} catch (Exception e) {

			e.printStackTrace();
			descripcion = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objEmpresaSie = new EmpresaSie();

		return mantenimientoEmpresaSearch.listar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_EMPRESA_FORM_LIST_PAGE;
	}

	/**
	 * @return the empresaService
	 */
	public EmpresaService getEmpresaService() {
		return empresaService;
	}

	/**
	 * @param empresaService the empresaService to set
	 */
	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public String getViewMant() {
		return Constants.MANT_EMPRESA_FORM_LIST_PAGE;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * /**
	 * 
	 * @return the mantenimientoEmpresaSearch
	 */
	public MantenimientoEmpresaSearchAction getMantenimientoEmpresaSearch() {
		return mantenimientoEmpresaSearch;
	}

	/**
	 * @return the idempresa
	 */

	/**
	 * @param mantenimientoEmpresaSearch
	 *            the mantenimientoEmpresaSearch to set
	 */
	public void setMantenimientoEmpresaSearch(
			MantenimientoEmpresaSearchAction mantenimientoEmpresaSearch) {
		this.mantenimientoEmpresaSearch = mantenimientoEmpresaSearch;
	}

	/**
	 * @return the idempresa
	 */
	public int getIdempresa() {
		return idempresa;
	}

	/**
	 * @param idempresa
	 *            the idempresa to set
	 */
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	/**
	 * @return the comboManagerEmpresa
	 */
	public ComboAction getComboManagerEmpresa() {
		return comboManagerEmpresa;
	}

	/**
	 * @param comboManagerEmpresa
	 *            the comboManagerEmpresa to set
	 */
	public void setComboManagerEmpresa(ComboAction comboManagerEmpresa) {
		comboManagerEmpresa.setCodigoEstado(Constants.COD_ESTADO_TB_EMPRESA);
		log.info("aqui--->>" + Constants.COD_ESTADO_TB_EMPRESA);
		this.comboManagerEmpresa = comboManagerEmpresa;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the razonsocial
	 */
	public String getRazonsocial() {
		return razonsocial;
	}

	/**
	 * @param razonsocial
	 *            the razonsocial to set
	 */
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	/**
	 * @return the numruc
	 */
	public String getNumruc() {
		return numruc;
	}

	/**
	 * @param numruc
	 *            the numruc to set
	 */
	public void setNumruc(String numruc) {
		this.numruc = numruc;
	}

	/**
	 * @return the numtel
	 */
	public String getNumtel() {
		return numtel;
	}

	/**
	 * @param numtel
	 *            the numtel to set
	 */
	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide
	 *            the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	public MantenimientoEmpresaFormAction() {
		init();
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */

	/**
	 * @return the nuevo
	 */
	public EmpresaSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo
	 *            the nuevo to set
	 */
	public void setNuevo(EmpresaSie nuevo) {
		this.nuevo = nuevo;
	}

	public EmpresaSie getObjEmpresaSie() {
		return objEmpresaSie;
	}

	/**
	 * @param objEmpresaSie
	 *            the objEmpresaSie to set
	 */
	public void setObjEmpresaSie(EmpresaSie objEmpresaSie) {
		this.objEmpresaSie = objEmpresaSie;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
