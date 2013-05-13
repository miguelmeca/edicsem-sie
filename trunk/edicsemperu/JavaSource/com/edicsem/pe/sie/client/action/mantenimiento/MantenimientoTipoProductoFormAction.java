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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		objTipoProductoSie = new TipoProductoSie();
		nuevo = new TipoProductoSie();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		objTipoProductoSie = new TipoProductoSie();
		codtipoproductoUpdate=null;
		setNewRecord(true);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()" + objTipoProductoSie.getIdtipoproducto());
		objTipoProductoSie = objTipoProductoService.findTipoProducto(objTipoProductoSie.getIdtipoproducto());
		setIdtipoproducto(objTipoProductoSie.getIdtipoproducto().toString());
		codtipoproductoUpdate = objTipoProductoSie.getCodtipoproducto();
		objTipoProductoSie.setNombretipoproducto(objTipoProductoSie.getNombretipoproducto());
		setNewRecord(false);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() {
		log.info("insertar()");
		String paginaRetorno="";
		mensaje =null;
		
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
						error = 1;
						break;
					}
			}
			if (error == 0) {
				if (isNewRecord()) {
					objTipoProductoSie.setUsuariocreacion(sessionUsuario.getUsuario());
					objTipoProductoService.insertTipoProducto(objTipoProductoSie);
					mensaje ="Se registró el paquete correctamente";
				} else {
					objTipoProductoSie.setUsuariomodifica(sessionUsuario.getUsuario());
					objTipoProductoSie.setFechamodifica(new Timestamp(DateUtil.getToday().getTime().getTime()));
					objTipoProductoService.updateTipoProducto(objTipoProductoSie);
					mensaje ="Se actualizó el paquete correctamente";
				}
				paginaRetorno= mantenimientoTipoProductoSearch.listar();
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);	
				
			} else {
					log.info("mensaje de error");
					mensaje ="Ya se encuentra un tipo de producto igual con el mismo nombre";
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);	
				}
			}catch (Exception e) {
					e.printStackTrace();
					mensaje = e.getMessage();
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
					log.error(e.getMessage());
			}
		objTipoProductoSie = new TipoProductoSie();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#delete()
	 */
	public String delete() throws Exception {
		mensaje =null;
		objTipoProductoSie = new TipoProductoSie();
		
		try {
			if (log.isInfoEnabled()) {
				log.info("Entrando a mi metodo Eliminar Tipo de Producto");
			}
			if(verificarTipoProducto(objTipoProductoSie.getIdtipoproducto())){
//				objTipoProductoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(73));
				objTipoProductoService.eliminarTipoProducto(objTipoProductoSie.getIdtipoproducto());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
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
		// verificar si este tipo de producto pertenece  a un producto registrado
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
