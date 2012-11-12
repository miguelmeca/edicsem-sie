/**
 * @author FUCKING
 *
 */
package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
import com.edicsem.pe.sie.service.facade.HorarioPuntoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "MantenimientoHorarioPuntoVentaSearchAction")
@SessionScoped
public class MantenimientoHorarioPuntoVentaSearchAction extends	BaseMantenimientoAbstractAction {

	public static Log log = LogFactory.getLog(MantenimientoHorarioPuntoVentaSearchAction.class);
	/*Variables de Almacen Punto Venta*/
	private List<PuntoVentaSie> PuntoVentaList;
	private PuntoVentaSie objPuntoVentaSie;
	/*Variables de Horario Punto Venta*/
	private List<HorarioPuntoVentaSie> listaHorario;
	private HorarioPuntoVentaSie objHorarioPuntoVentaSie;
	/*schedule*/
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent(); 
    private String theme; 
    /*FIN*/
	private Date dDate, dDate2, dhoy;
	private int idpuntoventa;
	
	@EJB
	private AlmacenService objAlmacenService; 
	
	@EJB 
	private HorarioPuntoVentaService objHorarioPuntoVentaService;

	public List<PuntoVentaSie> getPuntoVentaList() {
		return PuntoVentaList;
	}
	
	public List<HorarioPuntoVentaSie> getListaHorario() {
		return listaHorario;
	}

	public MantenimientoHorarioPuntoVentaSearchAction(){
		log.info("inicializando mi constructor");
		init();
		
	}
	public void init() {
		log.info("dentro del init");
		objPuntoVentaSie = new PuntoVentaSie();
		eventModel = new DefaultScheduleModel();
		idpuntoventa=0;
		log.info("despues de inicializar");
	}

/*LISTA LOS ALMACENES*/
	public String listar() {
		log.info("listarAlmacen en Search' ");
		PuntoVentaList =  objAlmacenService.listarPuntoVenta();

		if (PuntoVentaList == null) {
			PuntoVentaList = new ArrayList<PuntoVentaSie>();
		}
		return getViewList();
	}
	
/*LISTA LOS HORARIOS DEL ALMACEN SELECCIONADO*/
	public String mostrar() throws Exception {
		eventModel = new DefaultScheduleModel(); 
		log.info("mostrar horario de PUNTO DE VENTA");
				log.info("listarHorario del personal");
				listaHorario = objHorarioPuntoVentaService.listarHorarioPuntoVentaXidPV(objPuntoVentaSie.getIdpuntoventa());
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPuntoVentaSie>();
				}
				for(int i=0;i < listaHorario.size(); i++){
					objHorarioPuntoVentaSie= listaHorario.get(i);
	               
					 Calendar cal = new GregorianCalendar();
		            	log.info(" xd "+ objHorarioPuntoVentaSie.getDiainicio());
		            	cal.setTime( objHorarioPuntoVentaSie.getDiainicio());
		                
		                Calendar cal3 = new GregorianCalendar();
		            	cal3.setTime(objHorarioPuntoVentaSie.getDiainicio());
		            	
		            	Calendar cal4 = new GregorianCalendar();
		            	cal4.setTime(objHorarioPuntoVentaSie.getDiafin());
		                log.info(" fecha  " + cal.getTime() +"  time fin "+cal4.getTime());
		                
		                while (cal.before(cal4)||cal.equals(cal4)){
		                	log.info("en el primer while  fecha inicio ");
		                	log.info(" ***   getime  " + cal.getTime());
			              if(cal.get(Calendar.DAY_OF_WEEK) == objHorarioPuntoVentaSie.getTbFecha().getIdFecha()) {
			                	log.info("en el seggundo  while");
			                	dDate = cal.getTime();
			                	Calendar cal0 = new GregorianCalendar();
			                	Calendar cal33 = new GregorianCalendar();
			 	            	String[] a= (objHorarioPuntoVentaSie.getHoraIngreso()+"").split(":");
			 	            	cal0.setTime(dDate);
			 	            	cal0.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a[0]));
			 	            	cal0.add(Calendar.MINUTE, Integer.parseInt(a[1]));
			 	            	dDate = cal0.getTime();
			 	            	dDate2 = cal3.getTime();
			 	            	String[] a2= (objHorarioPuntoVentaSie.getHoraSalida()+"").split(":");
			 	            	cal33.setTime(dDate2);
			 	            	cal33.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a2[0]));
			 	            	cal33.add(Calendar.MINUTE, Integer.parseInt(a2[1]));
			 	            	dDate2 = cal33.getTime();
			 	                log.info(" dia 1 " +dDate);
			 	                log.info(" dia 2 " +dDate2);
		 	                
		                eventModel.addEvent(new DefaultScheduleEvent(objHorarioPuntoVentaSie.getObservacion()+" de "+ objHorarioPuntoVentaSie.getHoraIngreso()+" hasta "+ 
		                		objHorarioPuntoVentaSie.getHoraSalida(), dDate, dDate2));  
		                }
		                
			              cal.add(Calendar.DAY_OF_WEEK,1);
		                	cal3.add(Calendar.DAY_OF_WEEK,1);
		                }
		             
				}
		return getViewList();
	}
	
	
	public String getViewList() {
		return Constants.MANT_HORARIO_PUNTO_VENTA;
	}
	

	/**
	 *METODOS GET Y SET
	 */

/**
 * @param puntoVentaList the puntoVentaList to set
 */
public void setPuntoVentaList(List<PuntoVentaSie> puntoVentaList) {
	PuntoVentaList = puntoVentaList;
}
/**
 * @return the objPuntoVentaSie
 */
public PuntoVentaSie getObjPuntoVentaSie() {
	return objPuntoVentaSie;
}
/**
 * @param objPuntoVentaSie the objPuntoVentaSie to set
 */
public void setObjPuntoVentaSie(PuntoVentaSie objPuntoVentaSie) {
	this.objPuntoVentaSie = objPuntoVentaSie;
}

/**
 * @return the listaHorario
 */

/**
 * @param listaHorario the listaHorario to set
 */
public void setListaHorario(List<HorarioPuntoVentaSie> listaHorario) {
	this.listaHorario = listaHorario;
}

/**
 * @return the objHorarioPuntoVentaSie
 */
public HorarioPuntoVentaSie getObjHorarioPuntoVentaSie() {
	return objHorarioPuntoVentaSie;
}

/**
 * @param objHorarioPuntoVentaSie the objHorarioPuntoVentaSie to set
 */
public void setObjHorarioPuntoVentaSie(
		HorarioPuntoVentaSie objHorarioPuntoVentaSie) {
	this.objHorarioPuntoVentaSie = objHorarioPuntoVentaSie;
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
 * @return the dhoy
 */
public Date getDhoy() {
	return dhoy;
}

/**
 * @param dhoy the dhoy to set
 */
public void setDhoy(Date dhoy) {
	this.dhoy = dhoy;
}

/**
 * @return the idpuntoventa
 */
public int getIdpuntoventa() {
	return idpuntoventa;
}

/**
 * @param idpuntoventa the idpuntoventa to set
 */
public void setIdpuntoventa(int idpuntoventa) {
	this.idpuntoventa = idpuntoventa;
}	
	



}
