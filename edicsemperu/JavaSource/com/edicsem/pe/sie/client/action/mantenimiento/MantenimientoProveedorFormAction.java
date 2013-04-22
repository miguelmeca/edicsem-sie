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

import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoProveedorFormAction")
@SessionScoped
public class MantenimientoProveedorFormAction extends BaseMantenimientoAbstractAction {
    /*Se crean los objetos de las entidades proveedor*/	
	private ProveedorSie objProveedor;
	/*variables*/
	private String mensaje;
	private int TipoDocumento;
	/*variable boolean necesaria*/
	private boolean newRecord =false;
	private  List<ProveedorSie> lista;
		
	@ManagedProperty(value = "#{mantenimientoProveedorSearchAction}")
	private MantenimientoProveedorSearchAction mantenimientoProveedorSearch;
	
	@EJB 
	private ProveedorService objProveedorService;
	@EJB
	private TipoDocumentoService objTipoDocService;
	@EJB
	private EstadogeneralService objEstadoService;
		
	public static Log log = LogFactory.getLog(MantenimientoProveedorFormAction.class);
	
	public MantenimientoProveedorFormAction() {
		log.info("inicializando mi constructor");
		init();
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
	} 
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */ 
	public String agregar() {
		log.info("insertar");
		setTipoDocumento(0);
		objProveedor = new ProveedorSie();
		setNewRecord(true);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update()" + objProveedor.getIdproveedor());
        setTipoDocumento(objProveedor.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad()); 
		setNewRecord(false);
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#delete()
	 */
	public String deshabilitar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'DESHABILITAR()'");
			}
			/*Estado del Proveedor: deshabilitado(10)*/
			objProveedor.setTbEstadoGeneral(objEstadoService.findEstadogeneral(10));
			objProveedorService.actualizarProveedor(objProveedor);
		} catch (Exception e2) {
			e2.printStackTrace();
			mensaje = e2.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e2.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objProveedor = new ProveedorSie();
		return mantenimientoProveedorSearch.listar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		String paginaRetorno="";
		mensaje=null;
		objProveedor.setTbTipoDocumentoIdentidad(objTipoDocService.buscarTipoDocumento(TipoDocumento));
		
		try {
			lista = mantenimientoProveedorSearch.getProveedorList();
			int error = 0;
			if (isNewRecord()) {
				for (int i = 0; i < lista.size(); i++) {
					log.info(lista.get(i).getCodproveedor()+" y "+objProveedor.getCodproveedor());
					if (lista.get(i).getCodproveedor().equalsIgnoreCase(objProveedor.getCodproveedor())) { 
						log.info(lista.get(i).getCodproveedor()+" y "+objProveedor.getCodproveedor());
						log.info("Error ... Ya se encuentra un código igual");
						mensaje ="El código se encuentra asignado con otro proveedor";
						paginaRetorno =mantenimientoProveedorSearch.getViewMant();
						error = 1;
						break;
					}
				}
			}
				if (error == 0) {
					if (isNewRecord()) {
					objProveedorService.insertarProveedor(objProveedor);
					mensaje ="Se registró el proveedor correctamente";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.MESSAGE_INFO_TITULO, mensaje);
					}else {
						objProveedorService.actualizarProveedor(objProveedor);
						mensaje ="Se atualizó el proveedor correctamente";
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								Constants.MESSAGE_INFO_TITULO, mensaje);
					}
				objProveedor = new ProveedorSie();
				paginaRetorno =mantenimientoProveedorSearch.listar();
		}else{
			// Mostrar Validacion
			log.info("mensaje de error");
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		}
		}
		 catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);	
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MANT_PROVEEDOR_FORM_PAGE;
	}
	
	/**
	 * @return the objProveedor
	 */
	public ProveedorSie getObjProveedor() {
		return objProveedor;
	}

	/**
	 * @param objProveedor the objProveedor to set
	 */
	public void setObjProveedor(ProveedorSie objProveedor) {
		this.objProveedor = objProveedor;
	}
	
	/**
	 * @return the tipoDocumento
	 */
	public int getTipoDocumento() {
		return TipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(int tipoDocumento) {
		TipoDocumento = tipoDocumento;
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
	 * @return the mantenimientoProveedorSearch
	 */
	public MantenimientoProveedorSearchAction getMantenimientoProveedorSearch() {
		return mantenimientoProveedorSearch;
	}

	/**
	 * @param mantenimientoProveedorSearch the mantenimientoProveedorSearch to set
	 */
	public void setMantenimientoProveedorSearch(
			MantenimientoProveedorSearchAction mantenimientoProveedorSearch) {
		this.mantenimientoProveedorSearch = mantenimientoProveedorSearch;
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
	 * @return the lista
	 */
	public List<ProveedorSie> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<ProveedorSie> lista) {
		this.lista = lista;
	}
	
}
