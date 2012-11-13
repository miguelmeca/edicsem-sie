package com.edicsem.pe.sie.client.action.mantenimiento;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.ScheduleModel;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService;
import com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="horarioVendedorForm")
@SessionScoped
public class MantenimientoHorarioVendedorFormAction extends BaseMantenimientoAbstractAction {
	private boolean newRecord;
	private String mensaje;
	private int idPunto;
	private List<HorarioPersonalSie> horarioPersonalList;
	private ScheduleModel eventModel;
	
	public static Log log = LogFactory.getLog(MantenimientoHorarioVendedorFormAction.class);
	@EJB
	private HorarioPuntoVentaService objHorariopvService;
	@EJB
	private FiltroHorarioVentaService objFiltroHorarioService;
	@EJB
	private EmpleadoSieService objVendedoresService;
	
	public MantenimientoHorarioVendedorFormAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init()");
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		return getViewList();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		
		return getViewList();
	}
	
    private static int nextMonday(int iDia) {
        if (iDia == 1)
            return 1; //domingo
        else
            return 9 - iDia;
    }
    
    //Punto de Venta
	public void generarHorario() {
		log.info("generarHorario() ");
		try {
		//buscamos el horario de los punto de venta
		List<HorarioPuntoVentaSie> horariopv=  objHorariopvService.listarHorarioPuntoVentaXidPV(idPunto);
		List<FiltroHorarioVentaSie> filtros = objFiltroHorarioService.listarFiltroHorarioVentaVigentes();
		List<EmpleadoSie> vendedores  = objVendedoresService.listarEmpleadosXCargo(2);
		HorarioPersonalSie objHorario= new HorarioPersonalSie();
		Calendar cDesde = new GregorianCalendar();
		Calendar cHasta= new GregorianCalendar();
		//el horario solo es generado por 1 semana  
		Calendar diaActual;
		diaActual = DateUtil.getToday();
		int dia =nextMonday(diaActual.get(Calendar.DAY_OF_WEEK));
		cDesde.setTime(diaActual.getTime());
		log.info("dia actual " + cDesde.getTime() );
		cDesde.add(Calendar.DAY_OF_WEEK, dia);
		log.info(" dia proximo lunes  "+ cDesde.getTime());
		cHasta.setTime(cDesde.getTime());
		cHasta.add(Calendar.DAY_OF_WEEK,6);
		log.info(" fecha lunes  --> "+ 		cDesde.getTime());
		log.info(" fecha domingo  --> "+ 	cHasta.getTime());
		
		while(cDesde.before(cHasta)||cDesde.equals(cHasta)){
			
			log.info(" "+ cDesde.getTime());
			
			//buscamos horario del personal de ventas
			//buscamos los acuerdos
			
			for (int i = 0; i < horariopv.size(); i++) {
				log.info(" fech "+ cDesde.DATE +"  "+ horariopv.get(i).getTbFecha().getIdFecha());
				log.info("comparando "+horariopv.get(i).getHoraIngreso().getTime()+"  "+ horariopv.get(i).getHoraSalida().getTime());
				
				if( horariopv.get(i).getHoraSalida().getTime()-horariopv.get(i).getHoraIngreso().getTime()> 40000000 ){
					log.info(" doa horarios ");
					//esos horarios pueden ser de 4 horas, 6 horas y 8 horas
					
					//dividimos el horario de punto de venta en dos horarios
					if(cDesde.DATE == horariopv.get(i).getTbFecha().getIdFecha() ){
						log.info(" if  ");
						for (int j = 0; j < vendedores.size(); j++) {
						//buscamos las objecciones , permisos y descansos
						int f = (int) (Math.floor(Math.random() * ((vendedores.size()-1) - 0 + 1)) +0);
						objHorario.setTbEmpleado(vendedores.get(f));
						horarioPersonalList.add(objHorario);
						log.info("agregado  ");
						vendedores.remove(f);
						}
					}
				}else{
					
				}
			}
			//tomar en cuenta que un solo punto pueden haber más de un vendedor en el mismo horario, máximo 2
			
			cDesde.add(Calendar.DAY_OF_WEEK, 1);
		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		try {
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar'");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_HORARIO_PERSONAL_VENTAS;
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
	 * @return the idPunto
	 */
	public int getIdPunto() {
		return idPunto;
	}

	/**
	 * @param idPunto the idPunto to set
	 */
	public void setIdPunto(int idPunto) {
		this.idPunto = idPunto;
	}

	/**
	 * @return the horarioPersonalList
	 */
	public List<HorarioPersonalSie> getHorarioPersonalList() {
		return horarioPersonalList;
	}

	/**
	 * @param horarioPersonalList the horarioPersonalList to set
	 */
	public void setHorarioPersonalList(List<HorarioPersonalSie> horarioPersonalList) {
		this.horarioPersonalList = horarioPersonalList;
	}

	/**
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	/**
	 * @param eventModel the eventModel to set
	 */
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
}
