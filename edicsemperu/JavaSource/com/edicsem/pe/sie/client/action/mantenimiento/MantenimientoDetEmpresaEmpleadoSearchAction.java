//package com.edicsem.pe.sie.client.action.mantenimiento;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
//import com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService;
//import com.edicsem.pe.sie.util.constants.Constants;
//import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
//
//@ManagedBean(name="detEmpresaEmpleadoSearch")
//@SessionScoped
//public class MantenimientoDetEmpresaEmpleadoSearchAction extends BaseMantenimientoAbstractAction{
//
//	private Log log = LogFactory.getLog(MantenimientoDetEmpresaEmpleadoSearchAction.class);
//	
//	private List<DetEmpresaEmpleadoSie> detEmpresaEmplList;
//	private int idempresa;
//	@EJB
//	private DetEmpresaEmpleadoService detempresaEmplService;
//	
//	public MantenimientoDetEmpresaEmpleadoSearchAction() {
//		init();
//	}
//
//	/* (non-Javadoc)
//	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
//	 */
//	public void init() {
//		if (log.isInfoEnabled()) {
//			log.info("Inicializando 'MantenimientoDetEmpresaEmpleadoSearchAction'");
//		}
//		idempresa =0;
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
//	 */
//	public String listar() {
//		listarXEmpresa();
//		return getViewList();
//	}
//	public String listarXEmpresa() {
//		log.info("listar() ' x empresa' " + idempresa);
//		detEmpresaEmplList = detempresaEmplService.listarDetEmpresaEmpleadoSieXEmpresa(idempresa);
//		if (detEmpresaEmplList == null) {
//			detEmpresaEmplList = new ArrayList<DetEmpresaEmpleadoSie>();
//		}
//		return getViewList();
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
//	 */
//	public String getViewList() {
//		return Constants.MANT_DET_EMPRESA_EMPLEADO_FORM_LIST_PAGE;
//	}
//
//	/**
//	 * @return the detEmpresaEmplList
//	 */
//	public List<DetEmpresaEmpleadoSie> getDetEmpresaEmplList() {
//		return detEmpresaEmplList;
//	}
//
//	/**
//	 * @param detEmpresaEmplList the detEmpresaEmplList to set
//	 */
//	public void setDetEmpresaEmplList(List<DetEmpresaEmpleadoSie> detEmpresaEmplList) {
//		this.detEmpresaEmplList = detEmpresaEmplList;
//	}
//
//	/**
//	 * @return the idempresa
//	 */
//	public int getIdempresa() {
//		return idempresa;
//	}
//
//	/**
//	 * @param idempresa the idempresa to set
//	 */
//	public void setIdempresa(int idempresa) {
//		this.idempresa = idempresa;
//	}
//}
