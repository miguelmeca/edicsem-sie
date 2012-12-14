package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.mantenimiento.MantenimientoPagoVentaSearchAction;
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.DetSancionEmpleadoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetSancionCargoService;
import com.edicsem.pe.sie.service.facade.DetSancionEmpleadoService;
import com.edicsem.pe.sie.service.facade.FactorSancionService;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "PagoVentaForm")
@SessionScoped
public class PagoVentaAction  extends BaseMantenimientoAbstractAction {
	private String mensaje;
	private boolean editMode;
	private int idFactor, idEmpleado, idSancion,idcargo, idMes;
	private int idTipoImporte, idImporte;
	private Log log = LogFactory.getLog(MovimientoAction.class);
	private DetSancionEmpleadoSie objDetSancionEmpleado;
	private EmpleadoSie objEmpleado;
	private boolean newRecord = false;
	private Map<String, Integer> factorItems = new HashMap<String, Integer>();
	private Map<String, Integer> SancionItems = new HashMap<String, Integer>();
	private List<DetContratoEmpleadoSie> contratoXEmpleadoList;
	private List<ContratoEmpleadoSie> patrocinadosList;
	private MetaMesSie objMetaMesSie;
	private String fechaInicio ="",fechaFin="";
	Calendar cal;
	@EJB
	private DetSancionEmpleadoService objDetSancionempleadoService;
	@EJB
	private FactorSancionService objFactorService;
	@EJB
	private DetSancionCargoService objDetSancionCargoService;
	@EJB
	private DetContratoEmpleadoService objDetContratoEmpleadoService;
	@EJB
	private ContratoEmpleadoService objContratoEmpleadoService;
	@EJB 
	private MetaMesService objMetaMesService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	@ManagedProperty(value = "#{PagoVentaSearch}")
	private MantenimientoPagoVentaSearchAction pagoVentaSearchManager;
	
	public PagoVentaAction() {
		log.info("inicializando constructor PagoVentaAction");
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init() XD :D");
		idFactor=0;
		idSancion=0;
		idEmpleado=0;
		idcargo =0;
		objEmpleado= new EmpleadoSie();
		objDetSancionEmpleado = new DetSancionEmpleadoSie();
		contratoXEmpleadoList = new ArrayList<DetContratoEmpleadoSie>();
		patrocinadosList = new ArrayList<ContratoEmpleadoSie>();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		setNewRecord(true);
		
		return pagoVentaSearchManager.getViewMant();
	}

	/* (non-Javadoc)
	* @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	*/
	public String insertar() throws Exception {
		log.info("insertar()");
		try {
			if(isNewRecord()){
				objDetSancionempleadoService.insertDetSancionEmpleado(objDetSancionEmpleado, idSancion, idEmpleado, idcargo);
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				log.info("insertando "  );
			}
			else{
				objDetSancionempleadoService.updateDetSancionEmpleado(objDetSancionEmpleado);
				mensaje="Se actualizó la sanción de dicho empleado";
				log.info("actualizado");
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objDetSancionEmpleado = new DetSancionEmpleadoSie();
		return getViewList();
	}
	
	public String generar(){
		
		
		return pagoVentaSearchManager.getViewMant();
	}
	
	/* (non-Javadoc)
	* @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	*/
	public String update() throws Exception {
		log.info("update()  :D");
		cal =  DateUtil.getToday();
		
		contratoXEmpleadoList = new ArrayList<DetContratoEmpleadoSie>();
		patrocinadosList = new ArrayList<ContratoEmpleadoSie>();
		try {
			
			log.info(" "+cal.getTime());
			idMes =cal.get(Calendar.MONTH)+1;
			log.info(" " +idMes);
			reuti();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagoVentaSearchManager.getViewMant();
	}
	
	public void  reuti(){
		objMetaMesSie = objMetaMesService.findMetaMes(idMes);
		log.info(" " +objMetaMesSie.getFechainicio() );
		if(idMes<=11){
			fechaInicio =objMetaMesSie.getFechainicio()+"/"+cal.get(Calendar.YEAR);
			fechaFin = objMetaMesSie.getFechafin()+"/"+cal.get(Calendar.YEAR);
			log.info(" fec i "+fechaInicio+" fec f "+fechaFin);
		}else
		{
			fechaInicio = objMetaMesSie.getFechainicio()+"/"+cal.get(Calendar.YEAR);
			fechaFin = objMetaMesSie.getFechafin()+"/"+(cal.get(Calendar.YEAR)+1);
			log.info(" fec i "+fechaInicio+" fec f "+fechaFin);
		}
		contratoXEmpleadoList = objDetContratoEmpleadoService.listarContratoXEmpleado(objEmpleado.getIdempleado(), fechaInicio ,fechaFin );
		patrocinadosList = objContratoEmpleadoService.listarPatrocinados(objEmpleado.getIdempleado(), fechaInicio, fechaFin);
		for (int i = 0; i < patrocinadosList.size(); i++) {
			log.info("-->   "+patrocinadosList.get(i).getTbEmpleado2().getNombresCompletos()+" "+  patrocinadosList.get(i).getCantContratoXPatrocinado());
		}
	}
	
	public String otrosMeses() throws Exception {
		cal =  DateUtil.getToday();
		reuti();
		return pagoVentaSearchManager.getViewMant();
	}
	
	/* (non-Javadoc)
	* @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	*/
	public String listar() {
		
		return pagoVentaSearchManager.getViewMant();
	}
	
	public void listarEmpleadoFactorXCargo(){
		
		//buscamos los factores de dichas sanciones  perteneciente a dicho cargo
		factorItems  = new HashMap<String, Integer>();
		List<FactorSancionSie> lista =  objFactorService.listarFactorSancionXcargo(idcargo);
		comboManager.setIdCargo(idcargo);
		for (int i = 0; i < lista.size(); i++) {
			FactorSancionSie entidad = new FactorSancionSie();
			entidad = (FactorSancionSie) lista.get(i);
			
			if(!factorItems.containsValue(entidad.getIdfactor())){
				factorItems.put(entidad.getDescripcion(), entidad.getIdfactor());
			}
		}
		log.info("tamaño   -> "+lista.size()+"  -  "+factorItems.size());
	}
	
	public void listarXFactor(){
		log.info("factor   -> "+idFactor+"  cargo   "+idcargo);
		//buscamos todas las sanciones de dicho factor y dicho cargo
		SancionItems  = new HashMap<String, Integer>();
		List<DetSancionCargoSie> listaSancion = objDetSancionCargoService.listarDetSancionXFactorXCargo(idFactor, idcargo);
		for (int i = 0; i < listaSancion.size(); i++) {
			DetSancionCargoSie entidad = new DetSancionCargoSie();
			entidad = (DetSancionCargoSie) listaSancion.get(i);
			SancionItems.put(entidad.getTbSancion().getDescripcion(), entidad.getTbSancion().getIdsancion());
			log.info("-->  "+entidad.getTbSancion().getDescripcion());
		}
		log.info("tamaño   -> "+listaSancion.size()+"  -  "+factorItems.size());
	}
	
	/**
	* @return the editMode
	*/
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	* @return the mensaje
	*/
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
	* @return the comboManager
	 */
	public ComboAction getComboManager() {
		return comboManager;
	}
	/**
	* @param comboManager the comboManager to set
	*/
	public void setComboManager(ComboAction comboManager) {
		this.comboManager = comboManager;
	}

	/**
	* @return the idFactor
	 */
	public int getIdFactor() {
		return idFactor;
	}

	/**
	* @param idFactor the idFactor to set
	*/
	public void setIdFactor(int idFactor) {
		this.idFactor = idFactor;
	}

	/**
	* @return the idEmpleado
	*/
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	* @param idEmpleado the idEmpleado to set
	*/
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	/**
	* @return the idSancion
	*/
	public int getIdSancion() {
		return idSancion;
	}

	/**
	* @param idSancion the idSancion to set
	*/
	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}

	/* (non-Javadoc)
	* @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	*/
	public String getViewList() {
		return Constants.MANT_PAGO_VENDEDOR_FORM_PAGE;
	}

	/**
	 * @return the idcargo
	 */
	public int getIdcargo() {
		return idcargo;
	}

	/**
	 * @param idcargo the idcargo to set
	 */
	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}

	/**
	 * @return the factorItems
	 */
	public Map<String, Integer> getFactorItems() {
		return factorItems;
	}

	/**
	 * @param factorItems the factorItems to set
	 */
	public void setFactorItems(Map<String, Integer> factorItems) {
		this.factorItems = factorItems;
	}

	/**
	 * @return the sancionItems
	 */
	public Map<String, Integer> getSancionItems() {
		return SancionItems;
	}

	/**
	 * @param sancionItems the sancionItems to set
	 */
	public void setSancionItems(Map<String, Integer> sancionItems) {
		SancionItems = sancionItems;
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
	 * @return the objDetSancionEmpleado
	 */
	public DetSancionEmpleadoSie getObjDetSancionEmpleado() {
		return objDetSancionEmpleado;
	}

	/**
	 * @param objDetSancionEmpleado the objDetSancionEmpleado to set
	 */
	public void setObjDetSancionEmpleado(DetSancionEmpleadoSie objDetSancionEmpleado) {
		this.objDetSancionEmpleado = objDetSancionEmpleado;
	}

	/**
	 * @return the objEmpleado
	 */
	public EmpleadoSie getObjEmpleado() {
		return objEmpleado;
	}

	/**
	 * @param objEmpleado the objEmpleado to set
	 */
	public void setObjEmpleado(EmpleadoSie objEmpleado) {
		this.objEmpleado = objEmpleado;
	}

	/**
	 * @return the idMes
	 */
	public int getIdMes() {
		return idMes;
	}

	/**
	 * @param idMes the idMes to set
	 */
	public void setIdMes(int idMes) {
		this.idMes = idMes;
	}

	/**
	 * @return the contratoXEmpleadoList
	 */
	public List<DetContratoEmpleadoSie> getContratoXEmpleadoList() {
		return contratoXEmpleadoList;
	}

	/**
	 * @param contratoXEmpleadoList the contratoXEmpleadoList to set
	 */
	public void setContratoXEmpleadoList(
			List<DetContratoEmpleadoSie> contratoXEmpleadoList) {
		this.contratoXEmpleadoList = contratoXEmpleadoList;
	}

	/**
	 * @return the patrocinadosList
	 */
	public List<ContratoEmpleadoSie> getPatrocinadosList() {
		return patrocinadosList;
	}

	/**
	 * @param patrocinadosList the patrocinadosList to set
	 */
	public void setPatrocinadosList(List<ContratoEmpleadoSie> patrocinadosList) {
		this.patrocinadosList = patrocinadosList;
	}

	/**
	 * @return the pagoVentaSearchManager
	 */
	public MantenimientoPagoVentaSearchAction getPagoVentaSearchManager() {
		return pagoVentaSearchManager;
	}

	/**
	 * @param pagoVentaSearchManager the pagoVentaSearchManager to set
	 */
	public void setPagoVentaSearchManager(
			MantenimientoPagoVentaSearchAction pagoVentaSearchManager) {
		this.pagoVentaSearchManager = pagoVentaSearchManager;
	}

	/**
	 * @return the objMetaMesSie
	 */
	public MetaMesSie getObjMetaMesSie() {
		return objMetaMesSie;
	}

	/**
	 * @param objMetaMesSie the objMetaMesSie to set
	 */
	public void setObjMetaMesSie(MetaMesSie objMetaMesSie) {
		this.objMetaMesSie = objMetaMesSie;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the idTipoImporte
	 */
	public int getIdTipoImporte() {
		return idTipoImporte;
	}

	/**
	 * @param idTipoImporte the idTipoImporte to set
	 */
	public void setIdTipoImporte(int idTipoImporte) {
		comboManager.setTipoImporte(idTipoImporte);
		this.idTipoImporte = idTipoImporte;
	}

	/**
	 * @return the idImporte
	 */
	public int getIdImporte() {
		return idImporte;
	}

	/**
	 * @param idImporte the idImporte to set
	 */
	public void setIdImporte(int idImporte) {
		log.info("importe  "+ idImporte);
		this.idImporte = idImporte;
	}
}