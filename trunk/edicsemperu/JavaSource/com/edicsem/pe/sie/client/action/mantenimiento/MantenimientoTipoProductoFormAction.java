package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoTipoProductoFormAction")
@SessionScoped
public class MantenimientoTipoProductoFormAction extends
		BaseMantenimientoAbstractAction {

	public String idtipoproducto;
	public String descripcion;
	private String codtipoproductoUpdate;
	private int idEstadoGeneral;
	private int idc;
	private String mensaje;
	private TipoProductoSie nuevo;
	private TipoProductoSie objTipoProductoSie;
	private TipoProductoSie selectedTipoProducto;
	private boolean newRecord = false;
	private boolean editMode;
	private List<TipoProductoSie> lista;
	
	@ManagedProperty(value = "#{tipoProductoSearch}")
	private MantenimientoTipoProductoSearchAction mantenimientoTipoProductoSearch;

	private static Log log = LogFactory.getLog(MantenimientoTipoProductoFormAction.class);

	@EJB
	private TipoProductoService objTipoProductoService;

	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	@EJB
	private ProductoService objProductoService;

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objTipoProductoSie = new TipoProductoSie();
		nuevo = new TipoProductoSie();
	}

	public String agregar() {
		log.info("agregar()");
		editMode = true;
		objTipoProductoSie = new TipoProductoSie();
		codtipoproductoUpdate=null;
		setNewRecord(true);
		return getViewList();
	}

	public String update() throws Exception {
		log.info("update()" + objTipoProductoSie.getIdtipoproducto());
		objTipoProductoSie = objTipoProductoService.findTipoProducto(objTipoProductoSie.getIdtipoproducto());
		log.info(" id tipoproducto " + objTipoProductoSie.getIdtipoproducto() + " cod "+ objTipoProductoSie.getCodtipoproducto());		
		setIdtipoproducto(objTipoProductoSie.getIdtipoproducto().toString());
		
		codtipoproductoUpdate = objTipoProductoSie.getCodtipoproducto();
	
		objTipoProductoSie.setNombretipoproducto(objTipoProductoSie.getNombretipoproducto());
		//setIdEstadoGeneral(c.getTbEstadoGeneral().getIdestadogeneral());
		setNewRecord(false);
		editMode = false;
		return getViewList();

	}

	public String insertar() {
		
		String paginaRetorno="";
		mensaje =null;
		log.info("insertar()");
		
		objTipoProductoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(72));
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {

			log.info("aqui validadndo si existe o no" + objTipoProductoSie.getCodtipoproducto());
			lista = mantenimientoTipoProductoSearch.getTipoProdList();
			
		int error =0;
			
			
			for (int i = 0; i < lista.size(); i++) {
				if (codtipoproductoUpdate!=null) {
					if (lista.get(i).getCodtipoproducto().equalsIgnoreCase(objTipoProductoSie.getCodtipoproducto())&&
							(!codtipoproductoUpdate.equalsIgnoreCase(objTipoProductoSie.getCodtipoproducto()))){
					
						log.info("Error ... Ya se encuentra un tipo de producto igual");
						mensaje ="Ya se encuentra un tipo de producto igual con el mismo nombre";
						error = 1;
						break;
					}
				}
				else if ( lista.get(i).getCodtipoproducto().equalsIgnoreCase(objTipoProductoSie.getCodtipoproducto())) {
						log.info("Error ... Ya se encuentra un cargo igual");
						mensaje ="Ya se encuentra un tipo de producto igual con el mismo nombre";
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								Constants.MESSAGE_INFO_TITULO, mensaje);
						mensaje ="Se registr� el paquete correctamente";
						error = 1;
						break;
					}
			}
			if (error == 0) {
				if (isNewRecord()) {
					objTipoProductoSie.setUsuariocreacion(sessionUsuario.getUsuario());
					objTipoProductoService.insertTipoProducto(objTipoProductoSie);
					setNewRecord(false);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_REGISTRO_TITULO, mensaje);
					mensaje ="Se registr� el paquete correctamente";
			} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_REGISTRO_TITULO, mensaje);	
					objTipoProductoSie.setUsuariomodifica(sessionUsuario.getUsuario());
					objTipoProductoSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
					objTipoProductoService.updateTipoProducto(objTipoProductoSie);				
					log.info("actualizando..... ");
			}
			} else {
					log.info("mensaje de error");
					mensaje ="Ya se encuentra un tipo de producto igual con el mismo nombre";
					msg = new FacesMessage(mensaje);
				}
				    FacesContext.getCurrentInstance().addMessage(null, msg);
			}catch (Exception e) {

					e.printStackTrace();
					mensaje = e.getMessage();
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
					log.error(e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
		objTipoProductoSie = new TipoProductoSie();
		return mantenimientoTipoProductoSearch.listar();
	}
			
			
			
			
			
//
//	public String updateDeshabilitar() throws Exception {
//
//		objTipoProductoSie = new TipoProductoSie();
//		int parametroObtenido;
//		TipoProductoSie tp = new TipoProductoSie();
//
//		try {
//			if (log.isInfoEnabled()) {
//				log.info("Entering my method 'updateDESHABILITAR()'");
//			}
//
//			parametroObtenido = getIdc();
//			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
//			
//				//if(verificar(parametroObtenido)){//true - no hay empleado con cargo, tonce procede
//					
//					tp = objTipoProductoService.findTipoProducto(parametroObtenido);
//					
//					//objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(2));
//					//objCargoEmpleadoSie.setIdcargoempleado(c.getIdcargoempleado());
//					//objCargoEmpleadoSie.setDescripcion(c.getDescripcion());
//
//					objTipoProductoService.updateTipoProducto(objTipoProductoSie);
//					log.info("actualizando..... ");	
//				//}	
//				
//				//else {
//					
//					//FaceMessage.FaceMessageError("ALERTA", "El cargo no se puede elminar ya que se encontraron usuarios con ese cargo");
//				//}	
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			descripcion = e.getMessage();
//			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
//					Constants.MESSAGE_ERROR_FATAL_TITULO, descripcion);
//			log.error(e.getMessage());
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
//		objTipoProductoSie = new TipoProductoSie();
//		return mantenimientoTipoProductoSearch.listar();
//	}
//	
//	
//	
	
	
	
	public String Eliminar() throws Exception {
		mensaje =null;
		objTipoProductoSie = new TipoProductoSie();
		int parametroObtenido;
		
		TipoProductoSie c = new TipoProductoSie();
		
		try {
			if (log.isInfoEnabled()) {
				log.info("Entrando a mi metodo Eliimar Tipo de Producto");
			}
			
			parametroObtenido = getIdc();
log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
			
if(verificarTipoProducto(parametroObtenido)){
	
	c = objTipoProductoService.findTipoProducto(parametroObtenido);
	
	
	objTipoProductoSie.setIdtipoproducto(c.getIdtipoproducto());
	objTipoProductoSie.setCodtipoproducto(c.getCodtipoproducto());
	objTipoProductoSie.setNombretipoproducto(c.getNombretipoproducto());
	objTipoProductoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(73));

	

objTipoProductoService.eliminarTipoProducto(parametroObtenido);
	
	log.info("actualizando..... ");	
	msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
			Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
	mensaje ="WDF";
}			
			
			
else {
	
	
	FaceMessage.FaceMessageError("No se puede Eliminar, por que dicho Tipo de Producto Pertenece a un Producto","");
		return Constants.MANT_TIPO_PRODUCTO_FORM_LIST_PAGE;	

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
		
		objTipoProductoSie = new TipoProductoSie();
		return mantenimientoTipoProductoSearch.listar();		
	}

	private boolean verificarTipoProducto(int tipoProducto) {
//entra para verificar si este tipo de producto pertenece  aun producto registrado
		return objProductoService.verificarTipoProducto(tipoProducto);
	}

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}

	/**
	 * @return the idc
	 */
	public int getIdc() {
		log.info("id--->" +idc );
		return idc;
	}

	/**
	 * @param idc the idc to set
	 */
	public void setIdc(int idc) {
		this.idc = idc;
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
	 * @return the nuevo
	 */
	public TipoProductoSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(TipoProductoSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the objTipoProductoSie
	 */
	public TipoProductoSie getObjTipoProductoSie() {
		return objTipoProductoSie;
	}

	/**
	 * @param objTipoProductoSie the objTipoProductoSie to set
	 */
	public void setObjTipoProductoSie(TipoProductoSie objTipoProductoSie) {
		this.objTipoProductoSie = objTipoProductoSie;
	}

	/**
	 * @return the selectedTipoProducto
	 */
	public TipoProductoSie getSelectedTipoProducto() {
		return selectedTipoProducto;
	}

	/**
	 * @param selectedTipoProducto the selectedTipoProducto to set
	 */
	public void setSelectedTipoProducto(TipoProductoSie selectedTipoProducto) {
		this.selectedTipoProducto = selectedTipoProducto;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @return the mantenimientoTipoProductoSearch
	 */
	public MantenimientoTipoProductoSearchAction getMantenimientoTipoProductoSearch() {
		return mantenimientoTipoProductoSearch;
	}

	/**
	 * @param mantenimientoTipoProductoSearch the mantenimientoTipoProductoSearch to set
	 */
	public void setMantenimientoTipoProductoSearch(
			MantenimientoTipoProductoSearchAction mantenimientoTipoProductoSearch) {
		this.mantenimientoTipoProductoSearch = mantenimientoTipoProductoSearch;
	}

	/**
	 * @return the idtipoproducto
	 */
	public String getIdtipoproducto() {
		return idtipoproducto;
	}

	/**
	 * @param idtipoproducto the idtipoproducto to set
	 */
	public void setIdtipoproducto(String idtipoproducto) {
		this.idtipoproducto = idtipoproducto;
	}
	
	public String getViewList() {
		return Constants.MANT_TIPO_PRODUCTO_FORM_LIST_PAGE;
	}

	/**
	 * @return the codtipoproductoUpdate
	 */
	public String getCodtipoproductoUpdate() {
		return codtipoproductoUpdate;
	}

	/**
	 * @param codtipoproductoUpdate the codtipoproductoUpdate to set
	 */
	public void setCodtipoproductoUpdate(String codtipoproductoUpdate) {
		this.codtipoproductoUpdate = codtipoproductoUpdate;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the lista
	 */
	public List<TipoProductoSie> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<TipoProductoSie> lista) {
		this.lista = lista;
	}
	
}
