package com.edicsem.pe.sie.client.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.IdClass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="horarioPersonal")
@SessionScoped
public class HorarioPersonalSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private HorarioPersonalSie objHorarioPersonal;
	private List<HorarioPersonalSie> listaHorario;
	private Date fecPagoNull;
	private int tipollamada; 
	private boolean editMode;
	private String nombre;
	private int idempleado;
	private boolean newRecord =false;
	/*variable que capta el id del proveedor*/
	private int ide;
	
	@EJB 
	private HorarioPersonalService objHorarioPersonalService;
	@EJB 
	private CobranzaService objCobranzaService;
	
	public static Log log = LogFactory.getLog(HorarioPersonalSearchAction.class);
	
	public HorarioPersonalSearchAction() {
		System.out.println("ESTOY EN MI CONSTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}

	/*inicializamos los  objetos utilizados*/
	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objHorarioPersonal = new HorarioPersonalSie();
		//objtelefono = new TelefonoPersonaSie();
		log.info("despues de inicializar  ");
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	
	/*método que lista al hacer click en el menú del template*/
	public String listar() {
		// TODO Auto-generated method stub
		return getViewList();
	}
	
	public String mostrar() throws Exception {
		log.info("mostrar horario de empleados");
				log.info("listarHorario del personal");
				listaHorario = objHorarioPersonalService.listarHorarioPersonalXempleado(idempleado);
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPersonalSie>();
				}
	    // mostramos los datos del cliente
		//ClienteSie c = objClienteService.findCliente(objCobranzaOpera.getTbCobranza().getIdcliente()); 		
		//objcliente.setIdcliente(c.getIdcliente());
		return getViewList();
	}
	
	public String insertar() throws Exception {
		/*try {
				if (log.isInfoEnabled()) {
					//log.info("Entering my method 'insertar(registrar, actualizar)'"+ objProveedor.getCodproveedor());
				}
				
				objCobranzaOpera.setObservaciones(objCobranzaOpera.getObservaciones());
				objCobranzaOpera.setTbTipoLlamada(objTipoLLamadaService.findTipoLLamada(tipollamada));
				
					log.info("actualizando..... ");
					objCobranzaOperaService.updateCobranzaOpera(objCobranzaOpera);
					log.info("insertando..... ");
				
		} catch (Exception e) {
			e.printStackTrace();
			nombre = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}*/
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return "horarioPersonal";
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
	 * @return the ide
	 */
	public int getIde() {
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

	/**
	 * @return the fecPagoNull
	 */
	public Date getFecPagoNull() {
		return fecPagoNull;
	}

	/**
	 * @param fecPagoNull the fecPagoNull to set
	 */
	public void setFecPagoNull(Date fecPagoNull) {
		this.fecPagoNull = fecPagoNull;
	}

	/**
	 * @return the tipollamada
	 */
	public int getTipollamada() {
		return tipollamada;
	}

	/**
	 * @param tipollamada the tipollamada to set
	 */
	public void setTipollamada(int tipollamada) {
		this.tipollamada = tipollamada;
	}

	/**
	 * @return the idempleado
	 */
	public int getIdempleado() {
		return idempleado;
	}

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	/**
	 * @return the objHorarioPersonal
	 */
	public HorarioPersonalSie getObjHorarioPersonal() {
		return objHorarioPersonal;
	}

	/**
	 * @param objHorarioPersonal the objHorarioPersonal to set
	 */
	public void setObjHorarioPersonal(HorarioPersonalSie objHorarioPersonal) {
		this.objHorarioPersonal = objHorarioPersonal;
	}

	/**
	 * @return the listaHorario
	 */
	public List<HorarioPersonalSie> getListaHorario() {
		return listaHorario;
	}

	/**
	 * @param listaHorario the listaHorario to set
	 */
	public void setListaHorario(List<HorarioPersonalSie> listaHorario) {
		this.listaHorario = listaHorario;
	}
		
	public void onEdit(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getModel());

	        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	    
	public void onCancel(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Car Cancelled", ((Car) event.getObject()).getModel());

	        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
}
