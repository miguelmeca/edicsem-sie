package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
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
	List<HorarioPuntoVentaSie> horariopv;
	private List<String> vendedorList;
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();  
	HorarioPersonalSie objHorario;
	Calendar cDesde ;
	Calendar cHasta ;
	String vendedor="";
	
	public static Log log = LogFactory.getLog(MantenimientoHorarioVendedorFormAction.class);
	@EJB
	private HorarioPuntoVentaService objHorariopvService;
	@EJB
	private FiltroHorarioVentaService objFiltroHorarioService;
	@EJB
	private EmpleadoSieService objVendedoresService;
	@EJB
	private HorarioPersonalService objHorarioService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	public MantenimientoHorarioVendedorFormAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	public void init() {
		log.info("init()");
		eventModel = new DefaultScheduleModel(); 
		horarioPersonalList= new ArrayList<HorarioPersonalSie>();
		objHorario= new HorarioPersonalSie();
		vendedorList = new ArrayList<String>();
		comboManager= new ComboAction();
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
    
	/**
	 * generarHorario()
	 */
	public void generarHorario() {
		log.info("generarHorario() ");

		comboManager.setCargoEmpleado(2);
		horarioPersonalList = new ArrayList<HorarioPersonalSie>();
		eventModel = new DefaultScheduleModel();
		try {
		//buscamos el horario de los punto de venta
		horariopv=  objHorariopvService.listarHorarioPuntoVentaXidPV(idPunto);
		List<FiltroHorarioVentaSie> filtros = objFiltroHorarioService.listarFiltroHorarioVentaVigentes();
		List<EmpleadoSie> vendedores  = objVendedoresService.listarEmpleadosXCargo(2);
		
		cDesde = new GregorianCalendar();
		cHasta= new GregorianCalendar();
		//el horario solo es generado por 1 semana  
		Calendar diaActual;
		diaActual = DateUtil.getToday();
		int dia =nextMonday(diaActual.get(Calendar.DAY_OF_WEEK));
		cDesde.setTime(diaActual.getTime());
		cDesde.add(Calendar.DAY_OF_WEEK, dia);
		cHasta.setTime(cDesde.getTime());
		cHasta.add(Calendar.DAY_OF_WEEK,6);
		log.info(" fecha lunes  --> "+ 		cDesde.getTime());
		log.info(" fecha domingo  --> "+ 	cHasta.getTime());
		
		
			log.info("tamaño   "+horariopv.size());
			if(horariopv.size()>0){
			
				// Los horarios son generados por una semana
				while(cDesde.before(cHasta)||cDesde.equals(cHasta)){
					for (int i = 0; i < horariopv.size(); i++) {
					
					log.info(" desde  :D  "+ cDesde.getTime() +" hasta  "+ cHasta.getTime());
					log.info(" fech "+ cDesde.get(Calendar.DAY_OF_WEEK) +"  "+ horariopv.get(i).getTbFecha().getIdFecha());
					
					// se debe cumplir que los vendedores tengan un dia libre (descanso)
					Time hora1,hora2, haux;
					Calendar hingreso = new GregorianCalendar();
					Calendar hAuxi = new GregorianCalendar();
					int f=0 ;
						List<Integer> arreglo = new ArrayList<Integer>();
						//guardamos en un arreglo los id de los vendedores
						for (int j = 0; j < vendedores.size(); j++) {
							arreglo.add(vendedores.get(j).getIdempleado());
							log.info(" vende  " + vendedores.get(j).getIdempleado());
						}
						
						log.info(" arrreglo  " + arreglo.size());
						log.info(" ==  " + cDesde.get(Calendar.DAY_OF_WEEK)+ "  "+ horariopv.get(i).getTbFecha().getIdFecha());
							if(cDesde.get(Calendar.DAY_OF_WEEK) == horariopv.get(i).getTbFecha().getIdFecha() ){
							objHorario= new HorarioPersonalSie();
							log.info("for  "+ arreglo.size());
							
							//se debe seleccionar los vendedores aleatoriamente
							f = (int) (Math.random() * ((arreglo.size()-1)-0+1));
							
							log.info("random " + f +" tamaño  "+ arreglo.size());
							log.info("agregado  "+arreglo);
							log.info("agregado --> "+arreglo.get(f));
							// ALEATORIAMENE las horas 
							int arr[] = { 4, 6, 8, 10 };
							
							int rho = (int) (Math.random() * (arr.length-1 + 1));
							log.info("tamaño  "+arr.length+"  rho "+arr[rho]);
							hingreso.setTime(horariopv.get(i).getHoraIngreso());
							hAuxi.setTime(horariopv.get(i).getHoraIngreso());
							hora1 = new Time(hingreso.getTimeInMillis());
							hAuxi.add(Calendar.HOUR, arr[rho]);
							haux = new Time(hAuxi.getTimeInMillis());
							log.info(" 1  "+haux+"  2  "+horariopv.get(i).getHoraSalida());
							if(haux.after(horariopv.get(i).getHoraSalida())){
								
								hingreso.add(Calendar.HOUR, 4);
								log.info(" hora ingreso  " +hingreso.getTime());
								log.info("auxi   1 "+hingreso.getTime());
								
							}else{
								hingreso.add(Calendar.HOUR,arr[rho]);
								log.info("auxi   2  "+hingreso.getTime());
							}
							List<Integer> auxiList= new ArrayList<Integer>();
							hora2 = new Time(hingreso.getTimeInMillis());
							log.info("hora 1 "+ hora1+" hora2 "+hora2);
							objHorario.setHoraIngreso(hora1);
							objHorario.setHoraSalida(hora2);
							objHorario.setTbEmpleado(objVendedoresService.buscarEmpleado(arreglo.get(f)));
							objHorario.setDiainicio(cDesde.getTime());
							
							for (int s = 0; s < filtros.size(); s++) {
								log.info(" FILTROS : "+filtros.size());
								
								log.info(filtros.get(s).getTbFecha().getIdFecha()+"  "+cDesde.get(Calendar.DAY_OF_WEEK)+"  "+
										arreglo.get(f)+ "    "+ filtros.get(s).getTbEmpleado().getIdempleado());
								if( filtros.get(s).getTbFecha().getIdFecha()==cDesde.get(Calendar.DAY_OF_WEEK)){
									log.info(" ** FILTROS ** ");
									
									//Acuerdo (horario previamente establecido) //Permiso
									if(filtros.get(s).getTbTipoFiltro().getIdtipofiltro()==1||filtros.get(s).getTbTipoFiltro().getIdtipofiltro()==2){
										log.info(" ** 1 ** "+filtros.get(s).getObservacion());
										// Acuerdo (no debería registrarse otro horario para dicha persona en dicho día, porque ya se registro 1)
										//permiso (Se debe enviar otro vendedor) 
										log.info(" ** 2 *2* "+filtros.get(s).getObservacion());
										auxiList.add(filtros.get(s).getTbEmpleado().getIdempleado());//luego volver a isertarlo
									}
									//Descanso
									else if(filtros.get(s).getTbTipoFiltro().getIdtipofiltro()==3){
										log.info(" ** 3 ** "+filtros.get(s).getObservacion());
									}
								}log.info("otra vez  ");
							}
							
							for (int j = 0; j < auxiList.size(); j++) {
									log.info(" --> xd  "+ (auxiList.get(j)));
									arreglo.remove(auxiList.get(j)); 
							}
							
							if(auxiList.size()>0){
							f = (int) (Math.random() * ((arreglo.size()-1)-0+1));
							objHorario.setTbEmpleado(objVendedoresService.buscarEmpleado(arreglo.get(f)));
							log.info("f   "+f );
							}
							
							horarioPersonalList.add(objHorario);
							log.info(arreglo);
							arreglo.remove(f);
							log.info(arreglo);
							
							log.info("hora sal primer turno  :D "+hora1+ "hora sal pv "+hora2+ ""+horariopv.get(i).getHoraSalida());
							
							while(hora2.before(horariopv.get(i).getHoraSalida())){
								objHorario= new HorarioPersonalSie();
								objHorario.setDiainicio(cDesde.getTime());
								log.info("hora *** "+hora2);
								objHorario.setHoraIngreso(hora2);
								log.info("***** " + horariopv.get(i).getHoraSalida()+"  - "+hora2);
								objHorario.setHoraSalida(horariopv.get(i).getHoraSalida());
								hora2=objHorario.getHoraSalida();
								log.info("hora 2 "+hora2);
								log.info(arreglo.size());
								int rho2 =(int) (Math.random() * ((arreglo.size()-1)-0+1));
								log.info("rho2  "+rho2);
								log.info(" arreglo    "+arreglo.get(rho2));
								objHorario.setTbEmpleado(objVendedoresService.buscarEmpleado(arreglo.get(rho2)));
								horarioPersonalList.add(objHorario);
								log.info("h  "+hora2+" h sal  "+horariopv.get(i).getHoraSalida());
								if(auxiList.size()>0){
									for (int j = 0; j < auxiList.size(); j++) {
										arreglo.add(auxiList.get(j));
									}
								}
							}
						}
					}
					cDesde.add(Calendar.DAY_OF_WEEK, 1);
				}
				for (int k = 0; k < horarioPersonalList.size(); k++) {
					log.info("*** "+horarioPersonalList.get(k).getDiainicio()+ " " +horarioPersonalList.get(k).getHoraIngreso()+" - "+horarioPersonalList.get(k).getHoraSalida()+" empleado "+  horarioPersonalList.get(k).getTbEmpleado().getIdempleado());
					  Date dDate = new Date(), dDate2= new Date();
					Calendar db1 = new GregorianCalendar();
					Calendar db2 = new GregorianCalendar();
					db1.setTime(horarioPersonalList.get(k).getDiainicio());
					db2.setTime(horarioPersonalList.get(k).getDiainicio());
					String[] a= (horarioPersonalList.get(k).getHoraIngreso()+"").split(":");
					db1.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a[0]));
					db1.add(Calendar.MINUTE, Integer.parseInt(a[1]));
					dDate = db1.getTime();
					String[] a2= (horarioPersonalList.get(k).getHoraSalida()+"").split(":");
					db2.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a2[0]));
					db2.add(Calendar.MINUTE, Integer.parseInt(a2[1]));
					
					dDate2 = db2.getTime();
					
					log.info("FECHA 1   --> "+ dDate +"  FECHA 2 --> "+ dDate2 );
 	            	
					eventModel.addEvent(new DefaultScheduleEvent(horarioPersonalList.get(k).getHoraIngreso()+" - "+ 
					horarioPersonalList.get(k).getHoraSalida() + " , "+horarioPersonalList.get(k).getTbEmpleado().getNombresCompletos() , dDate, dDate2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
		 log.info("onEventSelect ");
		 vendedorList= new ArrayList<String>();
		 event = selectEvent.getScheduleEvent();
		 //Actualizar el registro
		 //La lista debe estar seleccionada con todos los vendedores 
		 String rr[]=event.getTitle().split(", ");
		 log.info(" rr |"+rr[0]);
		 for (int i = 1; i < rr.length; i++) {
				Iterator it = comboManager.getEmpleadoxcargo().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry e = (Map.Entry) it.next();
					log.info("key " + e.getKey() + " value " + e.getValue());
					if (e.getKey().toString().equals(rr[i])) {
						 log.info(" rr |"+rr[i]+ "  "+   e.getValue().toString());
						vendedorList.add(e.getValue().toString());
						log.info("vendedor " + vendedor);
						break;
					}
				}
		}
	     newRecord=false;
	 }
	 
	 public void onDateSelect(DateSelectEvent selectEvent) {
		 log.info("onDateSelect ");
		 mensaje=null;
		 cDesde = new GregorianCalendar();
		 cHasta= new GregorianCalendar();
		 vendedorList= new ArrayList<String>();
		 newRecord=false;
		 RequestContext context = RequestContext.getCurrentInstance();
		 try {
			 	Calendar diaActual = DateUtil.getToday(), daux = new GregorianCalendar();
				int dia =nextMonday(diaActual.get(Calendar.DAY_OF_WEEK));
				cDesde.setTime(diaActual.getTime());
				cDesde.add(Calendar.DAY_OF_WEEK, dia);
				cHasta.setTime(cDesde.getTime());
				cHasta.add(Calendar.DAY_OF_WEEK,6);
				log.info(" fecha lunes  --> "+ 		cDesde.getTime());
				log.info(" fecha domingo  --> "+ 	cHasta.getTime());
				
					for (int i = 0; i < horariopv.size(); i++) {
						daux.setTime(selectEvent.getDate());
						log.info(" desde  :D  "+ cDesde.getTime() +" hasta  "+ cHasta.getTime());
						log.info(" 1  "+daux.get(Calendar.DAY_OF_WEEK) +" 2  "+ horariopv.get(i).getTbFecha().getIdFecha());
						if(daux.get(Calendar.DAY_OF_WEEK) == horariopv.get(i).getTbFecha().getIdFecha() ){
							log.info(" entro  ");
							event = new DefaultScheduleEvent("", selectEvent.getDate(), selectEvent.getDate());
							context.execute("horarioVendedorDialog.show()");
							newRecord=true;
							break;
						}
					}
					if(newRecord==false){
							log.info("error en hor punto ");
							context.execute("horarioVendedorDialog.hide()");
					mensaje = "Debe seleccionar una fecha disponible en el horario del punto de venta";
					msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		try {
			Date fecha1, fecha2;
			Time hora1, hora2;
				if (log.isInfoEnabled()) {
					log.info("Entering my method 'insertar'");
				}
				if(vendedorList.size()==0){
					mensaje="Debe seleccionar un vendedor a cargo";
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				}
				else{
				//insertar
				if(isNewRecord()){ 
					//validar las fechas y horas
					fecha1=event.getStartDate();
					fecha2=event.getEndDate();
					hora1= new Time(fecha1.getTime());
					hora2= new Time(fecha2.getTime());
					for (int i = 0; i < vendedorList.size(); i++) {
						log.info(vendedorList.get(i).toString()+" --> ");
					
						Iterator it = comboManager.getEmpleadoxcargo().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry e = (Map.Entry) it.next();
							log.info("key " + e.getKey() + " value " + e.getValue());
							if (e.getValue().toString().equals(vendedorList.get(i))) {
								vendedor +=" , "+(String) e.getKey();
								log.info("vendedor " + vendedor);
								break;
							}
						}
					}
					log.info("vendedores " +vendedor+"  "+hora1 +" - "+ hora2+ "  "+fecha1+" "+fecha2);
					eventModel.addEvent(new DefaultScheduleEvent(hora1 +" - "+ hora2 +vendedor , fecha1, fecha2));					
				}
				else{
					//modificar
					
					for (int i = 0; i < vendedorList.size(); i++) {
						log.info(vendedorList.get(i).toString()+" --> ");
					
						Iterator it = comboManager.getEmpleadoxcargo().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry e = (Map.Entry) it.next();
							log.info("key " + e.getKey() + " value " + e.getValue());
							if (e.getValue().toString().equals(vendedorList.get(i))) {
								vendedor +=" , "+(String) e.getKey();
								log.info("vendedor " + vendedor);
								break;
							}
						}
					}
					log.info("vendedores " +vendedor+"  "+event.getStartDate());
				    eventModel.updateEvent(event);
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				//objHorarioService.insertHorarioVenta(horarioPersonalList);
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

	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	/**
	 * @return the vendedorList
	 */
	public List<String> getVendedorList() {
		return vendedorList;
	}

	/**
	 * @param vendedorList the vendedorList to set
	 */
	public void setVendedorList(List<String> vendedorList) {
		this.vendedorList = vendedorList;
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
}
