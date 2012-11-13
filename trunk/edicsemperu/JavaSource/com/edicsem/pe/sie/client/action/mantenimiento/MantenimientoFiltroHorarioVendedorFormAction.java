package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.service.facade.DetEmpresaEmpleadoService;
import com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="filtroHorarioVendedorForm")
@SessionScoped
public class MantenimientoFiltroHorarioVendedorFormAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private FiltroHorarioVentaSie objFiltroHorario;
	private List<HorarioPersonalSie> listaHorario;
	private List<String> diaList;
	private Date  horaIngreso,horaSalida;
	private int idhorario, idtipoFiltro,idvendedor, idPuntoVenta; 
	private boolean editMode,tipoventa;
	private String mensaje;
	private boolean newRecord =true;
	private int ide;
	
	@EJB
	private FiltroHorarioVentaService objFiltroHorarioService;
	
	@EJB
	private DetEmpresaEmpleadoService objDetEmpresaEmpleadoService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;

	public static Log log = LogFactory.getLog(MantenimientoFiltroHorarioVendedorFormAction.class);
	
	public MantenimientoFiltroHorarioVendedorFormAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init()");
		idvendedor=0;
		idPuntoVenta=0;
		objFiltroHorario= new FiltroHorarioVentaSie();
		log.info("despues de inicializar  ");
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		comboManager.setCargoEmpleado(2);
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		newRecord=false;
		setHoraIngreso(objFiltroHorario.getHoraIngreso());
		setHoraSalida(objFiltroHorario.getHoraSalida());
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		mensaje="";
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar'  xd ");
				}
				
					if(newRecord){
					log.info("fecha pp  " + getHoraIngreso() );
					Time hora1 = new Time( getHoraIngreso().getTime());
					Time hora2= new Time( getHoraSalida().getTime());
					log.info(" g   "+ hora1);
					objFiltroHorario.setHoraIngreso(hora1);
					objFiltroHorario.setHoraSalida(hora2);
					
					/** Validation **/
					
					 if(mensaje.equals("")){
						 objFiltroHorarioService.insertFiltroHorarioVenta(objFiltroHorario,idPuntoVenta,idvendedor,idtipoFiltro,diaList);
						 log.info("insertando..... ");
						 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
						 FacesContext.getCurrentInstance().addMessage(null, msg);
					 }else{
						msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					 }
				}else{
					log.info("actualizando " + getHoraIngreso()+"  hora salida  "+getHoraSalida() );
					Time hora1 = new Time( getHoraIngreso().getTime());
					Time hora2= new Time( getHoraSalida().getTime());
					objFiltroHorario.setHoraIngreso(hora1);
					objFiltroHorario.setHoraSalida(hora2);
					
					objFiltroHorarioService.updateFiltroHorarioVenta(objFiltroHorario);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		objFiltroHorario = new FiltroHorarioVentaSie();
		return getViewList();
	}
	
	public String vertipovendedor(){
		int i = objDetEmpresaEmpleadoService.filtrartipoventaPersonal(idvendedor);
		if(i==1){
		 tipoventa= true;
		}else{
		 tipoventa = false;
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_FILTRO_HORARIO_PERSONAL_VENTAS;
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
	
	/**
	 * @return the diaList
	 */
	public List<String> getDiaList() {
		return diaList;
	}

	/**
	 * @param diaList the diaList to set
	 */
	public void setDiaList(List<String> diaList) {
		this.diaList = diaList;
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
	 * @return the horaIngreso
	 */
	public Date getHoraIngreso() {
		return horaIngreso;
	}

	/**
	 * @param horaIngreso the horaIngreso to set
	 */
	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	/**
	 * @return the horaSalida
	 */
	public Date getHoraSalida() {
		return horaSalida;
	}

	/**
	 * @param horaSalida the horaSalida to set
	 */
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	/**
	 * @return the objFiltroHorario
	 */
	public FiltroHorarioVentaSie getObjFiltroHorario() {
		return objFiltroHorario;
	}

	/**
	 * @param objFiltroHorario the objFiltroHorario to set
	 */
	public void setObjFiltroHorario(FiltroHorarioVentaSie objFiltroHorario) {
		this.objFiltroHorario = objFiltroHorario;
	}

	/**
	 * @return the idhorario
	 */
	public int getIdhorario() {
		return idhorario;
	}

	/**
	 * @param idhorario the idhorario to set
	 */
	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}

	/**
	 * @return the idtipoFiltro
	 */
	public int getIdtipoFiltro() {
		return idtipoFiltro;
	}

	/**
	 * @param idtipoFiltro the idtipoFiltro to set
	 */
	public void setIdtipoFiltro(int idtipoFiltro) {
		this.idtipoFiltro = idtipoFiltro;
	}

	/**
	 * @return the idvendedor
	 */
	public int getIdvendedor() {
		return idvendedor;
	}

	/**
	 * @param idvendedor the idvendedor to set
	 */
	public void setIdvendedor(int idvendedor) {
		this.idvendedor = idvendedor;
	}

	/**
	 * @return the idPuntoVenta
	 */
	public int getIdPuntoVenta() {
		return idPuntoVenta;
	}

	/**
	 * @param idPuntoVenta the idPuntoVenta to set
	 */
	public void setIdPuntoVenta(int idPuntoVenta) {
		this.idPuntoVenta = idPuntoVenta;
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
	 * @return the tipoventa
	 */
	public boolean isTipoventa() {
		return tipoventa;
	}

	/**
	 * @param tipoventa the tipoventa to set
	 */
	public void setTipoventa(boolean tipoventa) {
		this.tipoventa = tipoventa;
	}
}
