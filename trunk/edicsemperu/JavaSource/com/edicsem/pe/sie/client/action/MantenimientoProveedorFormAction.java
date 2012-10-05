package com.edicsem.pe.sie.client.action;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="mantenimientoProveedorFormAction")
@SessionScoped
public class MantenimientoProveedorFormAction extends BaseMantenimientoAbstractAction {
    /*Se crean los objetos de las entidades empleado, domicilio y telefono*/	
	private ProveedorSie objProveedor;
	/*variables para empleado*/
	private String nombre;
	private int TipoDocumento;
	//private int codigoEmpleado;
	//private int codigoTipoDocumento;
	/*variable bolean necesaria*/
	private boolean newRecord =false;
		
	@ManagedProperty(value = "#{mantenimientoProveedorSearchAction}")
	private MantenimientoProveedorSearchAction mantenimientoProveedorSearch;
	
	@EJB 
	private ProveedorService objProveedorService;
	@EJB
	private TipoDocumentoService objTipoDocService;
		
	public static Log log = LogFactory.getLog(MantenimientoProveedorFormAction.class);
	
	public MantenimientoProveedorFormAction() {
		System.out.println("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		objProveedor = new ProveedorSie();
	} 
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	/*método que se ejecuta haciendo click en el link NUEVO de la lista*/
	public String agregar() {
		log.info("insertar");
		setTipoDocumento(0);
		objProveedor = new ProveedorSie();
		setNewRecord(true);
		return getViewMant();
	}
	
	/*método que se ejecuta al hacer click en el botón EDITAR de la lista*/
	public String update() throws Exception {
	    log.info("actualizar");
		log.info("update()" + objProveedor.getIdproveedor());
		/*busca el empleado por medio del id del ¿datatable?*/
		ProveedorSie p = objProveedorService.findProveedor(objProveedor.getIdproveedor());
		log.info(" id " + p.getIdproveedor()+ " nombre " + p.getCodproveedor()); 
		/*Seteo para mostrar los datos en el form*/
		objProveedor.setIdproveedor(p.getIdproveedor());
		objProveedor.setCodproveedor(p.getCodproveedor());
        objProveedor.setNombreempresa(p.getNombreempresa());
        objProveedor.setPersonacontacto(p.getPersonacontacto());
        setTipoDocumento(p.getTbTipoDocumentoIdentidad().getIdtipodocumentoidentidad()); 
        objProveedor.setNumdocumentoproveedor(p.getNumdocumentoproveedor());
        objProveedor.setDireccion(p.getDireccion());
        /*método bolean necesario para actualizar que retorna al form */  
		setNewRecord(false);
		return getViewMant();
	}
	
	/*método del botón GUARDAR(inserta o actualiza el empleado, domicilio y telefono)*/
	public String insertar() throws Exception {
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar(registrar, actualizar)'"+ objProveedor.getCodproveedor());
				}
				objProveedor.setTbTipoDocumentoIdentidad(objTipoDocService.buscarTipoDocumento(TipoDocumento));
				/*if: inserta al empleado, domicilio y telefono
				  else: actualiza al empleado, domicilio y telefono*/
				if (isNewRecord()) {
					log.info("insertando..... ");
					objProveedorService.insertarProveedor(objProveedor);
					log.info("insertando..... ");
					setNewRecord(false);
				} else {
					log.info("actualizando..... ");
					objProveedorService.actualizarProveedor(objProveedor);
					log.info("insertando..... ");
				}
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objProveedor = new ProveedorSie();
		return getViewList();
	}

	/*métodos GET y SET*/
	
	public String getViewList() {
		return "mantenimientoProveedorList";
	}
	
	public String getViewMant() {
		return "mantenimientoProveedorForm";
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the log
	 */
	public static Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public static void setLog(Log log) {
		MantenimientoProveedorSearchAction.log = log;
	}
	   	
}
