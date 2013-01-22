/**
 * @author FUCKING
 *
 */

package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.FaceMessage.FaceMessage;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "mantenimientoCargoEmpleadoFormAction")
@SessionScoped
public class MantenimientoCargoEmpleadoFormAction extends
		BaseMantenimientoAbstractAction {

	public String idcargoempleado;
	
	private int idEstadoGeneral, idc;
	private String descripcionUpdate;
	private String mensaje;
	private CargoEmpleadoSie nuevo;
	private CargoEmpleadoSie objCargoEmpleadoSie;
	private CargoEmpleadoSie selectedCargoEmpleado;
	private boolean newRecord = false;
	private boolean editMode;
	private ContratoEmpleadoSie objContratoEmpleadoSie;
	
	private int ide;
	
	private List<EmpleadoSie> cargoempleadoList;
	
	@ManagedProperty(value = "#{mantenimientoCargoEmpleadoSearchAction}")
	private MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch;

	private static Log log = LogFactory.getLog(MantenimientoCargoEmpleadoSearchAction.class);

	@EJB
	private CargoEmpleadoService objCargoEmpleadoService;
	@EJB
	private EmpleadoSieService objEmpleadoSieService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	@EJB
	private ContratoEmpleadoService objContratoEmpleadoService;

	public void init() {
		log.info("init()");
		// Colocar valores inicializados
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		nuevo = new CargoEmpleadoSie();
	}

	public String agregar() {
		log.info("agregar()");
		editMode = true;
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		descripcionUpdate=null;
		
		setNewRecord(true);
		return getViewList();
	}
	
	
	
	public List<EmpleadoSie> getCargoempleadoList() {
		return cargoempleadoList;
	}
	
	
public String derivarEmpleado() throws Exception {	
		
	
	
	ContratoEmpleadoSie em = new ContratoEmpleadoSie();
	int parametroObtenido;
		try {
			if (log.isInfoEnabled()) {
			
			}
							
			parametroObtenido = getIde();
em = objContratoEmpleadoService.findContratoEmpleado(parametroObtenido);
			

for (EmpleadoSie miempleado : cargoempleadoList ) {
	log.info("entre al forech");
	
	List<ContratoEmpleadoSie> lista  = objContratoEmpleadoService.listarCargoXEmp(miempleado.getIdempleado());
	for (ContratoEmpleadoSie contratoEmpleadoSie : lista) {
		
	contratoEmpleadoSie.setTbCargoempleado(objCargoEmpleadoService.buscarCargoEmpleado(191));
	log.info("cerca al updateContratoEmpleado");
	objContratoEmpleadoService.updateContratoEmpleado(contratoEmpleadoSie);
	 FaceMessage.FaceMessageError("Se Derivo correctamente","prosiga ah eliminar dicho Cargo Empleado");
	}
   }
	 
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return Constants.MANT_CARGO_EMPLEADO_FORM_LIST_PAGE;
	
}
	
	
	
	
	
	

	public String update() throws Exception {
		log.info("update()" + objCargoEmpleadoSie.getIdcargoempleado());
		objCargoEmpleadoSie = objCargoEmpleadoService.buscarCargoEmpleado(objCargoEmpleadoSie.getIdcargoempleado());
		log.info(" id cargo " +objCargoEmpleadoSie.getIdcargoempleado() + " des "+ objCargoEmpleadoSie.getDescripcion());		
		setIdcargoempleado(objCargoEmpleadoSie.getIdcargoempleado().toString());
		descripcionUpdate = objCargoEmpleadoSie.getDescripcion();

		setIdEstadoGeneral(objCargoEmpleadoSie.getTbEstadoGeneral().getIdestadogeneral());
		setNewRecord(false);
		editMode = false;
		return getViewList();

	}

	public String insertar() {
		mensaje =null;
		log.info("insertar() " + isNewRecord() + " desc "
				+ objCargoEmpleadoSie.getDescripcion());	
		objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(1));
		/* Esto se setea cuando pertenece a una segunda tabla (--->) */
		try {

			log.info("aqui validadndo si existe o no" + objCargoEmpleadoSie.getDescripcion());
			int error = 0;
			List<CargoEmpleadoSie> lista = mantenimientoCargoEmpleadoSearch.getCargoEmpleadomodel();
			
			for (int i = 0; i < lista.size(); i++) {
				log.info("2  "+ lista.get(i).getDescripcion()+"  "+objCargoEmpleadoSie.getDescripcion());
				if(descripcionUpdate!=null){
					if ( lista.get(i).getDescripcion().equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion().trim())&&
						(!descripcionUpdate.equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion().trim()))) {
						log.info("Error ... Ya se encuentra un cargo igual");
						mensaje ="Ya se encuentra un cargo con el mismo nombre";
						error = 1;
						break;
					}
				}
				else if ( lista.get(i).getDescripcion().equalsIgnoreCase(objCargoEmpleadoSie.getDescripcion().trim())) {
						log.info("Error ... Ya se encuentra un cargo igual");
						mensaje ="Ya se encuentra un cargo con el mismo nombre";
						error = 1;
						break;
					}
			}
			if (error == 0) {
			if (isNewRecord()) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.MESSAGE_REGISTRO_TITULO, mensaje);
					objCargoEmpleadoSie.setDescripcion(	objCargoEmpleadoSie.getDescripcion().trim());
				objCargoEmpleadoService.insertarCargoEmpleado(objCargoEmpleadoSie);
			}
			else {

				objCargoEmpleadoSie.setIdcargoempleado(Integer.parseInt(getIdcargoempleado()));

				objCargoEmpleadoSie.setDescripcion(objCargoEmpleadoSie.getDescripcion().trim());
				objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(getIdEstadoGeneral()));
				
				log.info("----->>>"
						+ objCargoEmpleadoSie.getTbEstadoGeneral().getIdestadogeneral()+ objCargoEmpleadoSie.getDescripcion());
				log.info("actualizando..... ");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_REGISTRO_TITULO, mensaje);			
				objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);				
				log.info("actualizando..... ");				
				log.info("objCargoEmpleadoSie.isNewRecord() : ");	
			}
			} else {
				// Mostrar Validacion
				log.info("mensaje de error");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
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
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		return mantenimientoCargoEmpleadoSearch.listar();
	}
	
	
	
	
	
	
	

	public class WatermarkBean { 
	 private String keyword;  
	  
	    public String getKeyword() {  
	        return keyword;  
	    }  
	  
	    public void setKeyword(String keyword) {  
	        this.keyword = keyword;  
	    }  
	  
	    public void search() {  
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"No results found with ", "'" + keyword + "'"));  
	    }  
	    
	}
	/**}
	 * @return the mayuscula
	 */

	/**
	 * @return the objCargoEmpleadoSie
	 */
	public CargoEmpleadoSie getObjCargoEmpleadoSie() {
		return objCargoEmpleadoSie;
	}

	/**DerivarEmpleado
	 * @param ae
	 * @return
	 * @throws Exception
	 */
	public String updateDeshabilitar() throws Exception {
		mensaje =null;

		objCargoEmpleadoSie = new CargoEmpleadoSie();
		int parametroObtenido;
		CargoEmpleadoSie c = new CargoEmpleadoSie();

		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'updateDESHABILITAR()'");
			}

			parametroObtenido = getIdc();
			log.info(" ------>>>>>>aqui cactura el parametro ID "+ parametroObtenido);
			
				if(verificarEmpleadoConCargo(parametroObtenido)){
					
					c = objCargoEmpleadoService.buscarCargoEmpleado(parametroObtenido);
					log.info(" ------Android>" + c.getDescripcion() + " "+ c.getIdcargoempleado());
					
					objCargoEmpleadoSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(2));
					objCargoEmpleadoSie.setIdcargoempleado(c.getIdcargoempleado());
					objCargoEmpleadoSie.setDescripcion(c.getDescripcion());

					log.info("-----ID estado general>>>"	+ objCargoEmpleadoSie.getTbEstadoGeneral().getIdestadogeneral());
					log.info("actualizando ESTADO..... ");

					objCargoEmpleadoService.actualizarCargoEmpleado(objCargoEmpleadoSie);
					log.info("actualizando..... ");	
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.MESSAGE_DESHABILITAR_TITULO, mensaje);
					mensaje ="WDF";
				}	
				
				else {
					if (verificarEmpleadoConCargo(parametroObtenido)== false) {

						listarEmpleadosXcargo(parametroObtenido);
						
						  
						  FaceMessage.FaceMessageError("Primero Derive los empleados de la lista haciendo clic en el boton 'DERIVAR' y luego prosiga eliminar el cargo","");
						return Constants.MANT_CARGOEMPLEADO_FORM_LIST_PAGE;
					}
					
					FaceMessage.FaceMessageError("ALERTA", "FUCK2");
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
		objCargoEmpleadoSie = new CargoEmpleadoSie();
		return mantenimientoCargoEmpleadoSearch.listar();
	}

	
	
	

	private void listarEmpleadosXcargo(int parametroObtenido) {
	
		log.info("entrando en el bean listarEmpleadosXcargo  "+parametroObtenido);	
		cargoempleadoList = objEmpleadoSieService.listarEmpleadoxCargo(parametroObtenido);		
	}

	private boolean verificarEmpleadoConCargo(int idcargo) {
		// TODO Auto-generated method stub
		return objContratoEmpleadoService.verificarEmpleadoConCargo(idcargo);
	}
	




	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return
	 */
	public int getIdc() {
		log.info("IDC *** " + idc);
		return idc;
	}

	/**
	 * @param idc
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}

	/**
	 * @return the idcargoempleado
	 */
	public String getIdcargoempleado() {
		return idcargoempleado;
	}

	/**
	 * @param idcargoempleado
	 *            the idcargoempleado to set
	 */
	public void setIdcargoempleado(String idcargoempleado) {
		this.idcargoempleado = idcargoempleado;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
	}


	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	public void setObjCargoEmpleadoSie(CargoEmpleadoSie objCargoEmpleadoSie) {
		this.objCargoEmpleadoSie = objCargoEmpleadoSie;
	}

	public CargoEmpleadoSie getSelectedCargoEmpleado() {
		return selectedCargoEmpleado;
	}

	public void setSelectedCargoEmpleado(CargoEmpleadoSie selectedCargoEmpleado) {
		this.selectedCargoEmpleado = selectedCargoEmpleado;
	}

	/**
	 * @return
	 */
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * @param nuevo
	 */
	public void setNuevo(CargoEmpleadoSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return
	 */
	public CargoEmpleadoSie getNuevo() {
		return nuevo;
	}

	/**
	 * @return
	 */
	public static Log getLog() {
		return log;
	}

	/**
	 * @param log
	 */
	public static void setLog(Log log) {
		MantenimientoCargoEmpleadoSearchAction.log = log;
	}

	
	/**
	 * @return
	 */
	public CargoEmpleadoService getObjCargoEmpleadoService() {
		return objCargoEmpleadoService;
	}

	public void setObjCargoEmpleadoService(
			CargoEmpleadoService objCargoEmpleadoService) {
		this.objCargoEmpleadoService = objCargoEmpleadoService;
	}

	public MantenimientoCargoEmpleadoFormAction() {
		log.info("inicializando constructor MantenimientoProducto");
		init();
	}

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
	 * @return the mantenimientoCargoEmpleadoSearch
	 */
	public MantenimientoCargoEmpleadoSearchAction getMantenimientoCargoEmpleadoSearch() {
		return mantenimientoCargoEmpleadoSearch;
	}

	/**
	 * @param mantenimientoCargoEmpleadoSearch
	 *            the mantenimientoCargoEmpleadoSearch to set
	 */
	public void setMantenimientoCargoEmpleadoSearch(
			MantenimientoCargoEmpleadoSearchAction mantenimientoCargoEmpleadoSearch) {
		this.mantenimientoCargoEmpleadoSearch = mantenimientoCargoEmpleadoSearch;
	}

	/**
	 * @return the descripcionUpdate
	 */
	public String getDescripcionUpdate() {
		return descripcionUpdate;
	}

	/**
	 * @param descripcionUpdate the descripcionUpdate to set
	 */
	public void setDescripcionUpdate(String descripcionUpdate) {
		this.descripcionUpdate = descripcionUpdate;
	}

	/**
	 * @return the cargoempleadoList
	 */


	/**
	 * @param cargoempleadoList the cargoempleadoList to set
	 */
	public void setCargoempleadoList(List<EmpleadoSie> cargoempleadoList) {
		this.cargoempleadoList = cargoempleadoList;
	}

	/**
	 * @return the ide
	 */
	public int getIde() {
		log.info("id -->"+ ide);
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(int ide) {
		this.ide = ide;
	}

}
