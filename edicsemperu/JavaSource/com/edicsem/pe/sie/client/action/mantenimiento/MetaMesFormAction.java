package com.edicsem.pe.sie.client.action.mantenimiento;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
@ManagedBean(name = "MetaMesFormAction")
@SessionScoped
public class MetaMesFormAction extends
		BaseMantenimientoAbstractAction {

	
	private int ide,idmetames;
	private String fechafin, fechainicio;
	private MetaMesSie objMetaMesSietmp;
	private boolean editMode;
	private MetaMesSie objMetaMesSie;

	private Log log = LogFactory.getLog(MetaMesFormAction.class);
	private boolean newRecord = false;
	private int estado;
	
	private MetaMesSie nuevo;
	
	private Date date2, date1; 
	

//	@ManagedProperty(value = "#{metaMesSearchAction}")
//	private MetaMesSearchAction MetaMesSearchAction;

	@EJB
	
	private MetaMesService metaMesService;
	
	
	



	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoEmpresaFormAction'");
		objMetaMesSietmp = new MetaMesSie();
		nuevo = new MetaMesSie();
		ide = 0;
	}


	public String update() throws Exception {
		
		log.info("update()" );
		log.info(" id mes  "+getIde());
		
		objMetaMesSietmp = metaMesService.findMetaMes(getIde());
		log.info(" id"+objMetaMesSietmp.getIdmetames());

		setNewRecord(true);
		editMode = true;
		return getViewList();

	}

	
	public String insertar() {

		log.info("actualizar() mes");
		try {

			if (isNewRecord()) {
				
				String fecha= DateUtil.formatoString(getDate1(), "dd/MM/yyyy");
				String fecha2= DateUtil.formatoString(getDate2(), "dd/MM/yyyy");
				
				 fecha=fecha.substring(0, 5);
				 log.info("--> " + fecha);
				
				fecha2=fecha2.substring(0, 5);
				
				
				objMetaMesSietmp.setFechainicio(fecha);
				objMetaMesSietmp.setFechafin(fecha2);
				
				log.info("--> antes de mesService"+ objMetaMesSietmp.getIdmetames()
						+ objMetaMesSietmp.getFechainicio() + objMetaMesSietmp.getFechafin());
				
				metaMesService.updateMetaMes(objMetaMesSietmp);
				
				log.info("actualizando..... ");
				log.info("objCargoEmpleadoSie.isNewRecord() : ");
				
			} else {

				
				log.info("elseeeeee..... ");

				
			}
		} catch (Exception e) {

			e.printStackTrace();
			fechafin = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, fechafin);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

			

objMetaMesSie = new MetaMesSie();
objMetaMesSietmp = new MetaMesSie();

	

		return getViewList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_META_LIST_PAGE;
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

	



	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
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

	public MetaMesFormAction() {
		init();
	}

	/**
	 * @return the fechafin
	 */
	public String getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechafin the fechafin to set
	 */
	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	/**
	 * @return the fechainicio
	 */
	public String getFechainicio() {
		return fechainicio;
	}

	/**
	 * @param fechainicio the fechainicio to set
	 */
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	/**
	 * @return the objMetaMesSietmp
	 */
	public MetaMesSie getObjMetaMesSietmp() {
		return objMetaMesSietmp;
	}

	/**
	 * @param objMetaMesSietmp the objMetaMesSietmp to set
	 */
	public void setObjMetaMesSietmp(MetaMesSie objMetaMesSietmp) {
		this.objMetaMesSietmp = objMetaMesSietmp;
	}

	/**
	 * @return the nuevo
	 */
	public MetaMesSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(MetaMesSie nuevo) {
		this.nuevo = nuevo;
	}

//	/**
//	 * @return the metaMesSearchAction
//	 */
//	public MetaMesSearchAction getMetaMesSearchAction() {
//		return MetaMesSearchAction;
//	}
//
//	/**
//	 * @param metaMesSearchAction the metaMesSearchAction to set
//	 */
//	public void setMetaMesSearchAction(MetaMesSearchAction metaMesSearchAction) {
//		MetaMesSearchAction = metaMesSearchAction;
//	}

	/**
	 * @return the idmetames
	 */
	public int getIdmetames() {
		return idmetames;
	}

	/**
	 * @param idmetames the idmetames to set
	 */
	public void setIdmetames(int idmetames) {
		this.idmetames = idmetames;
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
	 * @return the date2
	 */
	public Date getDate2() {
		return date2;
	}


	/**
	 * @param date2 the date2 to set
	 */
	public void setDate2(Date date2) {
		this.date2 = date2;
	}


	/**
	 * @return the date1
	 */
	public Date getDate1() {
		return date1;
	}


	/**
	 * @param date1 the date1 to set
	 */
	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	
	

}
