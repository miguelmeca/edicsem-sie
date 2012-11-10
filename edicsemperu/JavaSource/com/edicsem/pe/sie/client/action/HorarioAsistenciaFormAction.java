package com.edicsem.pe.sie.client.action;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;
import com.edicsem.pe.sie.entity.HorarioAsistenciaSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.HorarioAsistenciaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="horarioAsistencia")
@SessionScoped
public class HorarioAsistenciaFormAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private HorarioAsistenciaSie objHorarioAsistencia;
	private List<HorarioAsistenciaSie> listaAsistencia;
	private Date hora1, hora2, hora3, hora4;
	private int tipollamada; 
	private boolean editMode;
	private String mensaje;
	private int idempleado;
	private boolean newRecord =false;
	private boolean bandera =false;
	private int ide;
	private Date dDate, dDate2;
	
	@EJB 
	private HorarioAsistenciaService objHorarioAsistenciaService;
	@EJB 
	private EmpleadoSieService objEmpleadoService;
	
	public static Log log = LogFactory.getLog(HorarioAsistenciaFormAction.class);
	
	public HorarioAsistenciaFormAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init() xxxxxxxx :D ");
		// Colocar valores inicializados
		objHorarioAsistencia = new HorarioAsistenciaSie();
		log.info("despues de inicializar  ");
	}
	
	public String mostrar() throws Exception {
				log.info("listarHorarioAsistencia");
				listaAsistencia = objHorarioAsistenciaService.listarHorarioAsistenciaXempleado(idempleado);
				log.info("listaasistencia"+listaAsistencia.size());
				if (listaAsistencia.size() == 0) {
					log.info("listaasistencia"+listaAsistencia);
					listaAsistencia = new ArrayList<HorarioAsistenciaSie>();
				    bandera=true;
				}
				log.info(" ------------ xxxxxx :D ");
		return getViewList();
	}
    
	public void agregarhorario(){
		
		
		newRecord=true;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	@Override
	public String agregar() {
		// TODO Auto-generated method stub
		return super.agregar();
	}

	public String insertar() throws Exception {
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar(registrar, actualizar)'"+ objHorarioAsistencia.getObservaciones());
			}
			objHorarioAsistencia.setTbEmpleado(objEmpleadoService.buscarEmpleado(idempleado));
			if (isNewRecord()) {
				log.info("insertando..... ");
				Time hora1 = new Time( getHora1().getTime());
				Time hora2= new Time( getHora2().getTime());
				Time hora3 = new Time( getHora3().getTime());
				Time hora4= new Time( getHora4().getTime());
				log.info(" get   "+ hora1);
		
				Calendar fecha= new GregorianCalendar();
				if(bandera==false){
				log.info("if"+bandera);
				fecha.setTime((Date)objHorarioAsistenciaService.obtenerFechaFinalXempleado(idempleado).get(0));
				log.info("1 "+ fecha);
				fecha.add(Calendar.DATE, 1);}
				else{
			    log.info("if"+bandera);
				DateUtil du = new DateUtil();
			    fecha.setTime(du.getToday().getTime());
				}
				log.info("2 "+ fecha);
				objHorarioAsistencia.setHoraIngreso1(hora1);
				objHorarioAsistencia.setHoraSalida1(hora2);
				objHorarioAsistencia.setHoraIngreso2(hora3);
				objHorarioAsistencia.setHoraSalida2(hora4);
				objHorarioAsistencia.setFecha(fecha.getTime());
				objHorarioAsistenciaService.insertHorarioAsistencia(objHorarioAsistencia);
				log.info("insertando..... ");
				setNewRecord(false);
			} else {
				log.info("actualizando..... ");
				
				Time hora1 = new Time(getHora1().getTime());
				Time hora2= new Time( getHora2().getTime());
				Time hora3 = new Time(getHora3().getTime());
				Time hora4= new Time( getHora4().getTime());
				log.info(" get   "+ hora1);
				
				objHorarioAsistencia.setHoraIngreso1(hora1);
				objHorarioAsistencia.setHoraSalida1(hora2);
				objHorarioAsistencia.setHoraIngreso2(hora3);
				objHorarioAsistencia.setHoraSalida2(hora4);
				objHorarioAsistenciaService.updateHorarioAsistencia(objHorarioAsistencia);
				log.info("actualzando..... ");
			}
	} catch (Exception e) {
		e.printStackTrace();
		String nombre = e.getMessage();
		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, nombre);
		log.error(e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	objHorarioAsistencia = new HorarioAsistenciaSie();
		return mostrar();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	@Override
	public String update() throws Exception {
		   log.info("actualizar");
		   log.info("update()" + objHorarioAsistencia.getIdHorarioAsistencia());
		   HorarioAsistenciaSie h = objHorarioAsistenciaService.findHorarioAsistencia(objHorarioAsistencia.getIdHorarioAsistencia());
			/*Seteo para mostrar los datos*/
			objHorarioAsistencia.setIdHorarioAsistencia(h.getIdHorarioAsistencia());
		    setHora1(h.getHoraIngreso1());
		    setHora2(h.getHoraSalida1());
		    setHora3(h.getHoraIngreso2());
		    setHora4(h.getHoraSalida2());
			objHorarioAsistencia.setFecha(h.getFecha());
			objHorarioAsistencia.setObservaciones(h.getObservaciones());
	        objHorarioAsistencia.setTbEmpleado(h.getTbEmpleado());
	        /*método bolean necesario para actualizar*/  
			setNewRecord(false);
			return "";
	}

	public String onEdit(RowEditEvent event) throws Exception {
		
		setNewRecord(false);
		return insertar();
    }
    
    public void onCancel(RowEditEvent event) {
    }
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_HORARIO_ASISTENCIA;
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
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		
		
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);	//set random day of month

		return date.getTime();
	}

	/**
	 * @return the dDate
	 */
	public Date getdDate() {
		return dDate;
	}

	/**
	 * @param dDate the dDate to set
	 */
	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}

	/**
	 * @return the dDate2
	 */
	public Date getdDate2() {
		return dDate2;
	}

	/**
	 * @param dDate2 the dDate2 to set
	 */
	public void setdDate2(Date dDate2) {
		this.dDate2 = dDate2;
	}

	/**
	 * @return the objHorarioAsistencia
	 */
	public HorarioAsistenciaSie getObjHorarioAsistencia() {
		return objHorarioAsistencia;
	}

	/**
	 * @param objHorarioAsistencia the objHorarioAsistencia to set
	 */
	public void setObjHorarioAsistencia(HorarioAsistenciaSie objHorarioAsistencia) {
		this.objHorarioAsistencia = objHorarioAsistencia;
	}

	/**
	 * @return the listaAsistencia
	 */
	public List<HorarioAsistenciaSie> getListaAsistencia() {
		return listaAsistencia;
	}

	/**
	 * @param listaAsistencia the listaAsistencia to set
	 */
	public void setListaAsistencia(List<HorarioAsistenciaSie> listaAsistencia) {
		this.listaAsistencia = listaAsistencia;
	}

	/**
	 * @return the hora1
	 */
	public Date getHora1() {
		return hora1;
	}

	/**
	 * @param hora1 the hora1 to set
	 */
	public void setHora1(Date hora1) {
		this.hora1 = hora1;
	}

	/**
	 * @return the hora2
	 */
	public Date getHora2() {
		return hora2;
	}

	/**
	 * @param hora2 the hora2 to set
	 */
	public void setHora2(Date hora2) {
		this.hora2 = hora2;
	}

	/**
	 * @return the hora3
	 */
	public Date getHora3() {
		return hora3;
	}

	/**
	 * @param hora3 the hora3 to set
	 */
	public void setHora3(Date hora3) {
		this.hora3 = hora3;
	}

	/**
	 * @return the hora4
	 */
	public Date getHora4() {
		return hora4;
	}

	/**
	 * @param hora4 the hora4 to set
	 */
	public void setHora4(Date hora4) {
		this.hora4 = hora4;
	}

	/**
	 * @return the bandera
	 */
	public boolean isBandera() {
		return bandera;
	}

	/**
	 * @param bandera the bandera to set
	 */
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}	
		
}
