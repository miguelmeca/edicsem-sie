package com.edicsem.pe.sie.client.action;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "metaMesSearchAction")
@SessionScoped
public class MetaMesSearchAction extends BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(MetaMesSearchAction.class);

//	private List<MetaMesSie> MetaMesList;
	private MetaMesSie objMetaMesSie;
	private int codmes;
	private MetaMesSie nuevo;	
	private String mes;	

	@EJB
	private MetaMesService metaMesService;

	public void mostrarfecha() {
		objMetaMesSie = metaMesService.findMetaMes(codmes);
		


	}



	public MetaMesSie getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo
	 *            the nuevo to set
	 */
	public void setNuevo(MetaMesSie nuevo) {
		this.nuevo = nuevo;
	}

	/**
	 * @return the metaMesService
	 */
	public MetaMesService getMetaMesService() {
		return metaMesService;
	}

	/**
	 * @param metaMesService
	 *            the metaMesService to set
	 */
	public void setMetaMesService(MetaMesService metaMesService) {
		this.metaMesService = metaMesService;
	}



	/**
	 * @return the objMetaMesSie
	 */
	public MetaMesSie getObjMetaMesSie() {
		return objMetaMesSie;
	}

	/**
	 * @param objMetaMesSie
	 *            the objMetaMesSie to set
	 */
	public void setObjMetaMesSie(MetaMesSie objMetaMesSie) {
		this.objMetaMesSie = objMetaMesSie;
	}

	/**
	 * @return the codmes
	 */
	public int getCodmes() {
		return codmes;
	}

	/**
	 * @param codmes
	 *            the codmes to set
	 */
	public void setCodmes(int codmes) {
		this.codmes = codmes;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the mes
	 */

	/********************************************************/

}
