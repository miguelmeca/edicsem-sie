package com.edicsem.pe.sie.client.action;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.edicsem.pe.sie.entity.Usuario;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.DemoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean
@SessionScoped
public class MantenimientoDemo extends BaseMantenimientoAbstractAction{
	
	private String mensaje;
	private TipoProductoSie objTipo;
//	private Usuario objUsuario;
	
	public static Log log = LogFactory.getLog(MantenimientoDemo.class);
	
	@EJB
	private TipoProductoService objTipoService;
 
	
	public MantenimientoDemo() {
		System.out.println("ESTOY EN MI CONSNTRUCTOR");
		log.info("inicializando mi constructor");
		init();
	}
	
	
	public void init(){
		log.info("init()");
	 objTipo= new TipoProductoSie();
	 //objTipo.setIdtipoproducto(null);
		objTipo.setCodtipoproducto("");
		objTipo.setNombretipoproducto("");
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
	 try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()'");
			} 
			 
				log.info("objTipo.isNewRecord() : "+ objTipo.getCodtipoproducto());
//			if (objTipo.isNewRecord()){
//				log.info("insertando..... ");
				//insertarValidation(objTipo);
				objTipoService.insertTipoProducto(objTipo);
//				objTipo.setNewRecord(false);
//			} 
//			else {
//				log.info("objUsuario.isNewRecord() : "+ objTipo.isNewRecord());
			//	objTipoService.updateDemo(objTipo);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO,
					mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
	 	return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return "demo";
	}


	/**
	 * @return the objTipo
	 */
	public TipoProductoSie getObjTipo() {
		return objTipo;
	}


	/**
	 * @param objTipo the objTipo to set
	 */
	public void setObjTipo(TipoProductoSie objTipo) {
		this.objTipo = objTipo;
	}
	 

}
