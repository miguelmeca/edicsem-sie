package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoClienteFormAction")
@SessionScoped
public class MantenimientoClienteFormAction extends
		BaseMantenimientoAbstractAction {

	private int ide, idEstadoGeneral;
	private int estado;
	public String nombrecliente;
	private ClienteSie objClienteSie;
	private boolean newRecord = true;
	private ClienteSie nuevo;
	private Log log = LogFactory.getLog(MantenimientoClienteFormAction.class);

	@ManagedProperty(value = "#{MantenimientoClienteSearchAction}")
	private MantenimientoClienteSearchAction mantenimientoClienteSearch;

	@EJB
	private ClienteService objClienteService;

	@EJB
	private EstadogeneralService objEstadoGeneralService;


	public void init() {
		log.info("Inicializando el Constructor de 'MantenimientoClienteFormAction'");

		objClienteSie = new ClienteSie();

		nuevo = new ClienteSie();
	}

	public String update() throws Exception {
		log.info("update()");

		objClienteSie = objClienteService.findCliente(objClienteSie.getIdcliente());

		log.info(" id cliente " + objClienteSie.getIdcliente() + " nombre "
				+ objClienteSie.getNombrecliente());

		
		setNewRecord(true);

		return getViewMant();

	}

	public String insertar() throws Exception {

		log.info("insertar() " + isNewRecord() + " inicio del insert "
				+ objClienteSie.getNombrecliente());

		/* ---> */
		objClienteSie.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(23));
		/* Esto se setea cuando pertenece a una segunda tabla (--->) */
		try {
			if (isNewRecord()) {

				objClienteSie.getNombrecliente();

				log.info("--> antes de Cliente Service"
						+ " " + objClienteSie.getIdcliente()
						+ " " + objClienteSie.getNombrecliente());
				
				log.info("actualizando..... ");

				objClienteService.updateCliente(objClienteSie);
				

				log.info("insertando..... ");

			} else {

				log.info("elseeeeee..... ");

			}
		} catch (Exception e) {

			e.printStackTrace();
			nombrecliente = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, nombrecliente);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		objClienteSie = new ClienteSie();

		return getViewList();
	}

	public String getViewList() {
		return Constants.MANT_CLIENTE_FORM_LIST_PAGE;
	}

	public String getViewMant() {
		return Constants.MANT_CLIENTE_FORM_PAGE;
	}

	/**
	 * @return the objClienteSie
	 */
	public ClienteSie getObjClienteSie() {
		return objClienteSie;
	}

	/**
	 * @param objClienteSie
	 *            the objClienteSie to set
	 */
	public void setObjClienteSie(ClienteSie objClienteSie) {
		this.objClienteSie = objClienteSie;
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
	 * @return the nuevo
	 */
	public ClienteSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo
	 *            the nuevo to set
	 */
	public void setNuevo(ClienteSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the mantenimientoClienteSearch
	 */
	public MantenimientoClienteSearchAction getMantenimientoClienteSearch() {
		return mantenimientoClienteSearch;
	}

	/**
	 * @param mantenimientoClienteSearch
	 *            the mantenimientoClienteSearch to set
	 */
	public void setMantenimientoClienteSearch(
			MantenimientoClienteSearchAction mantenimientoClienteSearch) {
		this.mantenimientoClienteSearch = mantenimientoClienteSearch;
	}

	/**
	 * @return the nombrecliente
	 */
	public String getNombrecliente() {
		return nombrecliente;
	}

	/**
	 * @param nombrecliente
	 *            the nombrecliente to set
	 */
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
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

	/**
	 * @return the idEstadoGeneral
	 */
	public int getIdEstadoGeneral() {
		return idEstadoGeneral;
	}

	/**
	 * @param idEstadoGeneral
	 *            the idEstadoGeneral to set
	 */
	public void setIdEstadoGeneral(int idEstadoGeneral) {
		this.idEstadoGeneral = idEstadoGeneral;
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


}
