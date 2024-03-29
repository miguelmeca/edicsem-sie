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

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "empresaForm")
@SessionScoped
public class MantenimientoEmpresaFormAction extends
		BaseMantenimientoAbstractAction {

	private String mensaje;
	private EmpresaSie nuevo;
	
	private EmpresaSie objEmpresaSie;
	private boolean newRecord = false;
	private int ide, idEstadoGeneral;
	public String razonsocial;
	private List<EmpresaSie> lista;	
	
	public int idempresa;
	private Log log = LogFactory.getLog(MantenimientoEmpresaFormAction.class);
	
	private int estado;
	//EMPLEADO
	private EmpleadoSie objEmpleadoSie;
	private List<EmpleadoSie> empleadoList;
	
	//PRODUCTO
	private ProductoSie objProductoSie;
	private List<ProductoSie> productoList;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManagerEmpresa;

	@ManagedProperty(value = "#{empresaSearch}")
	private MantenimientoEmpresaSearchAction mantenimientoEmpresaSearch;
	
	//LISTA DE EMPRESA Y MANTEMINIENTO EMPRESA
	@EJB
	private EmpresaService empresaService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	//EMPLEADO X EMPRESA
	@EJB
	private ContratoEmpleadoService objcontratoEmpleadoService;
	@EJB
	private EmpleadoSieService objEmpleadoSieService;
	//PRODUCTO X EMPRESA
	@EJB
	private KardexService objKardexService;
	@EJB
	private ProductoService objProductoService;
		
	
	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoEmpresaFormAction'");
		objEmpresaSie = new EmpresaSie();
		
		//EMPLEADO X EMPRESA
		objEmpleadoSie= new EmpleadoSie();
		objEmpleadoSie.setNombreemp("");
		
		//PRODUCTO X EMPRESA
		objProductoSie= new ProductoSie();
		objProductoSie.setDescripcionproducto("");
		
		
		
		nuevo = new EmpresaSie();
	}
	//EMPRESA X EMPLEADO
	public List<EmpleadoSie> getEmpleadoList() {
		return empleadoList;
	}
	
	//PRODUCTO x EMPLEADO
	public List<ProductoSie> getProductoList() {
		return productoList;
	}
	

	public String agregar() {
		log.info("agregar()");
		objEmpresaSie = new EmpresaSie();
		setNewRecord(true);
		
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()" + objEmpresaSie.getIdempresa());

		objEmpresaSie = empresaService.findEmpresa(objEmpresaSie.getIdempresa());

		log.info(" id cargo " + objEmpresaSie.getIdempresa() + " des " + objEmpresaSie.getDescripcion());
		
		setIdempresa(objEmpresaSie.getIdempresa());
		setIdEstadoGeneral(objEmpresaSie.getTbEstadoGeneral().getIdestadogeneral());
		setNewRecord(false);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {

		log.info("insertar() " + isNewRecord() + " desc "	+ objEmpresaSie.getDescripcion() +"  "+ " razon social" +"  "+ "ruc" + objEmpresaSie.getNumruc()	+ objEmpresaSie.getRazonsocial() +"  "+ "EMail"	+ objEmpresaSie.getEmail()  );
		String paginaRetorno="";
		mensaje =null;
	
		objEmpresaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(7));

		/* Esto se setea cuando pertenece a una segunda tabla (--->) */
		try {
			
			lista = mantenimientoEmpresaSearch.getEmpresaList();
			int error= 0;

			if (isNewRecord()) {
				
			for (int i = 0; i < lista.size(); i++) {

					if (lista.get(i).getRazonsocial().equalsIgnoreCase(objEmpresaSie.getRazonsocial())){
							log.info("Error... Ya se Encuentra una empresa con ese mismo nombre");
							mensaje = "Ya se Encunetra una Empresa con ese mismo nombre";
							paginaRetorno= mantenimientoEmpresaSearch.listar();
							error = 1;
							break;
							}
						}
					}

				if (error == 0) {

				if (isNewRecord()) {
					log.info("insertando");
					
					empresaService.insertEmpresa(objEmpresaSie);
					setNewRecord(false);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.MESSAGE_REGISTRO_TITULO, mensaje);
					mensaje ="Se registr� el paquete correctamente";
					
				}
				else {
					
				
				objEmpresaSie.setIdempresa(getIdempresa());

				objEmpresaSie.setDescripcion(objEmpresaSie.getDescripcion().trim());
				objEmpresaSie.setRazonsocial(objEmpresaSie.getRazonsocial().trim());
				objEmpresaSie.setNumcuenta(objEmpresaSie.getNumcuenta());
				objEmpresaSie.setNumruc(objEmpresaSie.getNumruc());
				objEmpresaSie.setNumtelefono(objEmpresaSie.getNumtelefono());
				objEmpresaSie.setEmail(objEmpresaSie.getEmail().trim());
				objEmpresaSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getIdEstadoGeneral()));
				log.info("actualizando EMPRESA..... ");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_REGISTRO_TITULO, mensaje);	
				
				empresaService.updateEmpresa(objEmpresaSie);
				mensaje ="Se Actualiz� la Empresa";
				log.info("actualizando..... ");
				
			}
		}
				
				else {
					log.info("mensaje de error");
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			
			} 	FacesContext.getCurrentInstance().addMessage(null, msg);

	}
		catch (Exception e) {

			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objEmpresaSie = new EmpresaSie();

		return mantenimientoEmpresaSearch.listar();
	}

	public String Eliminarempresa() throws Exception {
		mensaje = null;
		objEmpresaSie = new EmpresaSie();
		int parametroObtenido;
		EmpresaSie em = new EmpresaSie();
		
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
			objEmpresaSie.setNumcuenta(em.getNumcuenta());
			objEmpresaSie.setNumruc(em.getNumruc());
			objEmpresaSie.setNumtelefono(em.getNumtelefono());
			objEmpresaSie.setEmail(em.getEmail());

			log.info("-----Android1>>>"+ objEmpresaSie.getTbEstadoGeneral().getIdestadogeneral());
			log.info("actualizando ESTADO..... ");
			
			empresaService.updateEmpresa(objEmpresaSie);
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
			mensaje ="Se deshabilito correctamente";
			log.info("actualizando..... ");
//			return mantenimientoEmpresaSearch.listar();
			
			
			
//			return Constants.MANT_EMPRESA_FORM_LIST_PAGE;
//return mantenimientoEmpresaSearch.listar();
//context.execute("confirmationEmpresa.hide()"); FALTA poner el el popud ajax="false"
//context.execute("mantenimientoEmpresaFormList.jsf");
//action="#{empresaSearch.listar}"
//@ManagedProperty(value = "#{mantenimientoCargoEmpleadoSearchAction}")
//private MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch;
//			 
			}
else {

	  if (verificarEmpleadoConEmpresa(parametroObtenido) == false || verificarProductoConEmpresa(parametroObtenido) == false) {
		  listarEmpleadosXempresa(parametroObtenido);
		  listarProductoXempresa(parametroObtenido);
		  FaceMessage.FaceMessageError("ALERTA", "El cargo no se puede elminar ya que se encontraron usuarios con ese cargo");
		return Constants.MANT_EMPRESA_EMPLEADO_PRODUCTO_FORM_LIST_PAGE;
	}
	  else if(verificarProductoConEmpresa(parametroObtenido) == false && verificarEmpleadoConEmpresa(parametroObtenido) == false) {
		  listarEmpleadosXempresa(parametroObtenido);
		  listarProductoXempresa(parametroObtenido);
		  FaceMessage.FaceMessageError("ALERTA", "El cargo no se puede elminar ya que se encontraron usuarios con ese cargo");
		return Constants.MANT_EMPRESA_EMPLEADO_PRODUCTO_FORM_LIST_PAGE;
	}
	
//	context.execute("someDialog.show()");
	  FaceMessage.FaceMessageError("ALERTA", "El cargo no se puede elminar ya que se encontraron usuarios con ese cargo");
	
//	mensaje = "tiene relacion : ";
//	RequestContext.getCurrentInstance().addCallbackParam("showDialog", false);
//    FaceMessage.FaceMessageError("ALERTA", "La Empresa no se puede elminar ya que se encontraron empleados con esta empresa");
//	FaceMessage.FaceMessageInfo("ALERTA", "La Empresa no se puede elminar ya que se encontraron productos con esta empresa");
}
FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();

			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objEmpresaSie = new EmpresaSie();

		return mantenimientoEmpresaSearch.listar();
	}

	
	private void listarProductoXempresa(int parametroObtenido) {
		log.info("captura idParametro para poder listar PRODUCTO X EMPRESA en el Bean dentro del metodo Eliminar "+parametroObtenido);
		productoList = objProductoService.listarProductoxEmpresas(parametroObtenido);
	}
	
	private void listarEmpleadosXempresa(int parametroObtenido) {
		log.info("captura idParametro para poder listar EMPLEADOS X EMPRESA en el Bean dentro del metodo Eliminar  "+parametroObtenido);
		empleadoList = objEmpleadoSieService.listarEmpleadoxEmpresas(parametroObtenido);
	}
	
	private boolean verificarEmpleadoConEmpresa(int idcargo) {
		// Aqui verificaremos si esta empresa pertenece a un empleado en la TB.DetalleEmpresaEmpleado
		return objcontratoEmpleadoService.verificarEmpleadoConEmpresa(idcargo) ;
	}

	private boolean verificarProductoConEmpresa(int idcargo) {
		// Aqui verificaremos si esta empresa pertenece a un empleado en la TB.DetalleEmpresaEmpleado
		return objKardexService.verificarProductoConEmpresa(idcargo) ;
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

	/**
	 * @return the objEmpleadoSie
	 */
	public EmpleadoSie getObjEmpleadoSie() {
		return objEmpleadoSie;
	}

	/**
	 * @param objEmpleadoSie the objEmpleadoSie to set
	 */
	public void setObjEmpleadoSie(EmpleadoSie objEmpleadoSie) {
		this.objEmpleadoSie = objEmpleadoSie;
	}

	/**
	 * @return the empleadoList
	 */
	
	/**
	 * @param empleadoList the empleadoList to set
	 */
	public void setEmpleadoList(List<EmpleadoSie> empleadoList) {
		this.empleadoList = empleadoList;
	}
	/**
	 * @return the objProductoSie
	 */
	public ProductoSie getObjProductoSie() {
		return objProductoSie;
	}
	/**
	 * @param objProductoSie the objProductoSie to set
	 */
	public void setObjProductoSie(ProductoSie objProductoSie) {
		this.objProductoSie = objProductoSie;
	}
	/**
	 * @param productoList the productoList to set
	 */
	public void setProductoList(List<ProductoSie> productoList) {
		this.productoList = productoList;
	}

	/**
	 * @return the razonsocial
	 */
	public String getRazonsocial() {
		return razonsocial;
	}
	/**
	 * @param razonsocial the razonsocial to set
	 */
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

}
