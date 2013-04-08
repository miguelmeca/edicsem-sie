package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetTurnoEmplSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.entity.TurnoSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.ContratoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.FechaService;
import com.edicsem.pe.sie.service.facade.HorarioPersonalService;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.service.facade.TurnoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="distribucion")
@SessionScoped
public class DistribuciónHorarioMasivoSearchAction extends BaseMantenimientoAbstractAction {
	/*variables*/
	private HorarioPersonalSie objHorarioPersonal;
	private List<DetTurnoEmplSie> listaDetTurnoEmpl;
	private List<HorarioPersonalSie> listaHorario;
	private List<String> diaList;
	private List<String> expositorList;
	private List<String> cerradorList;
	private List<String> vendedorList;
	private MetaMesSie objMetaMesSie;
	private Calendar cal;
	private String fechaInicio, fechaFin;
	private int idMes, tipoVenta, idturno;
	private ScheduleModel eventModel;
	private List<ScheduleEvent> eventos;
	private Date  horaIngreso,horaSalida;
	private int idhorario;
	private String mensaje, rangoTurno, rangoFecha, dias;
	private int idempleado;
	private boolean newRecord =false;
	private int ide;
	private Date dDate, dDate2, dhoy;
	private EmpleadoSie objEmpleadoSie;
	private int cerrador, expositor, vendedor;
	private TurnoSie objTurno;
	String expo="";
	private String tittle;
	private ScheduleEvent event = new DefaultScheduleEvent();
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	@EJB
	private CargoEmpleadoService objCargoService;
	@EJB
	private MetaMesService objMetaMesService;
	@EJB
	private HorarioPersonalService objHorarioPersonalService;
	@EJB
	private EmpleadoSieService objEmpleadoSieService;
	@EJB
	private FechaService objFechaService;
	@EJB
	private TurnoService objTurnoService;
	@EJB 
	private ContratoEmpleadoService objContratoEmpleadoService;
	
	public static Log log = LogFactory.getLog(DistribuciónHorarioMasivoSearchAction.class);
	
	public DistribuciónHorarioMasivoSearchAction() {
		log.info("inicializando mi constructor");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		idMes=0;
		objEmpleadoSie = new EmpleadoSie();
		objHorarioPersonal = new HorarioPersonalSie();
		eventModel = new DefaultScheduleModel();
		idempleado=0;
		diaList= new  ArrayList<String>();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("agregar()");
		idMes=0;
		idempleado=0;
		newRecord=true;
		objHorarioPersonal  = new HorarioPersonalSie();
		eventos = new ArrayList<ScheduleEvent>();
		expositorList = new ArrayList<String>();
		cerradorList = new ArrayList<String>();
		vendedorList = new ArrayList<String>();
		objMetaMesSie = new MetaMesSie();
		eventModel = new DefaultScheduleModel();
		diaList = new  ArrayList<String>();
		listaDetTurnoEmpl = new ArrayList<DetTurnoEmplSie>();
		listaHorario = new ArrayList<HorarioPersonalSie>();
		event = new DefaultScheduleEvent();
		idturno=0;
		idMes=0;
		dias="";
		fechaInicio = "";
		fechaFin = "";
		rangoTurno="";
		rangoFecha="";
		tipoVenta=0;
		CargoEmpleadoSie ccerrador =  objCargoService.buscarCargoEmpleado(Constants.CARGO_CERRADOR);
		CargoEmpleadoSie cvendedor =  objCargoService.buscarCargoEmpleado(Constants.CARGO_VENDEDOR);
		CargoEmpleadoSie cexpositor =  objCargoService.buscarCargoEmpleado(Constants.CARGO_EXPOSITOR);
		cerrador = ccerrador.getIdcargoempleado();
		expositor= cexpositor.getIdcargoempleado();
		vendedor= cvendedor.getIdcargoempleado();
		return getViewList();
	}
	
	public List<String> completeVend(String query){
		log.info(" completeVend() "+query+"  "+comboManager.getVendedorItems().size());
		List<String> results = new ArrayList<String>();
		
		for (int j = 0; j < comboManager.getVendedorItems().size(); j++) {
			log.info(""+comboManager.getVendedorItems());
		}
        Iterator it=comboManager.getVendedorItems().entrySet().iterator();
        
        while(it.hasNext()){
            Map.Entry m =(Map.Entry)it.next();
            log.info("Key :"+m.getKey()+"  Value :"+m.getValue());
            int key=(Integer)m.getValue();
            String value=(String)m.getKey();
            log.info("Key :"+key+"  Value :"+value);
            if((value.toUpperCase()).startsWith(query.toUpperCase().trim())){
            	log.info("Key--> :"+key+"  Value :"+value);
            	results.add(value);
            }
        }
	    return results;
	}
	
	public List<String> completeExpo(String query){
		log.info(" completeExpo() "+query+"  ");
		log.info(" completeExpo() "+comboManager.getExpositorItems().size());
		List<String> results = new ArrayList<String>();
		
		for (int j = 0; j < comboManager.getExpositorItems().size(); j++) {
			log.info(""+comboManager.getExpositorItems());
		}
        Iterator it=comboManager.getExpositorItems().entrySet().iterator();
        
        while(it.hasNext()){
            Map.Entry m =(Map.Entry)it.next();
            log.info("Key :"+m.getKey()+"  Value :"+m.getValue());
            int key=(Integer)m.getValue();
            String value=(String)m.getKey();
            log.info("Key :"+key+"  Value :"+value);
            if((value.toUpperCase()).startsWith(query.toUpperCase().trim())){
            	log.info("Key--> :"+key+"  Value :"+value);
            	results.add(value);
            }
        }
	    return results;
	}
	
	public List<String> completeCerrad(String query){
		log.info(" completeCerrad() "+query+"  "+comboManager.getCerradorItems().size());
		List<String> results = new ArrayList<String>();
		
		for (int j = 0; j < comboManager.getCerradorItems().size(); j++) {
			log.info(""+comboManager.getCerradorItems());
		}
        Iterator it=comboManager.getCerradorItems().entrySet().iterator();
        
        while(it.hasNext()){
            Map.Entry m =(Map.Entry)it.next();
            log.info("Key :"+m.getKey()+"  Value :"+m.getValue());
            int key=(Integer)m.getValue();
            String value=(String)m.getKey();
            log.info("Key :"+key+"  Value :"+value);
            if((value.toUpperCase()).startsWith(query.toUpperCase().trim())){
            	log.info("Key--> :"+key+"  Value :"+value);
            	results.add(value);
            }
        }
	    return results;
	}
	
	public String agregarhorario() throws Exception{
		log.info("agregarhorario() :)");
		mensaje=null;
		expo=null;
		listaHorario = new ArrayList<HorarioPersonalSie>();
		if(expositorList.size()==0&&vendedorList.size()==0){
			mensaje="Debe seleccionar mínimo un vendedor o expositor";
		}
		if(tipoVenta>=1&& mensaje==null){//Masivo	
				objHorarioPersonal= new HorarioPersonalSie();
				objHorarioPersonal.setHoraIngreso(objTurno.getHoraInicio());
				objHorarioPersonal.setHoraSalida(objTurno.getHoraFin());
				objHorarioPersonal.setDiainicio(DateUtil.convertStringToDate(fechaInicio));
				objHorarioPersonal.setDiafin(DateUtil.convertStringToDate(fechaFin));
				listaHorario.add(objHorarioPersonal);
				
				for (int i = 0; i < expositorList.size(); i++) {//expositores
				DetTurnoEmplSie det = new DetTurnoEmplSie();
				det.setTbTurno(objTurnoService.findTurno(idturno));
				det.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(expositorList.get(i)));
				det.setTbCargoempleado(objCargoService.buscarCargoEmpleado(expositor));
				listaDetTurnoEmpl.add(det);
				log.info(expositorList+" -- >  "+expositorList.get(i));
	            	if(expo==null){
	            		expo =" \n - "+expositorList.get(i);
	            	}else{
	            		expo = expo+" \n - "+expositorList.get(i);
	            	}
				}	
				
				for (int i = 0; i < vendedorList.size(); i++) {//vendedores
				DetTurnoEmplSie det = new DetTurnoEmplSie();
				det.setTbTurno(objTurnoService.findTurno(idturno));
				det.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(vendedorList.get(i)));
				det.setTbCargoempleado(objCargoService.buscarCargoEmpleado(vendedor));
				listaDetTurnoEmpl.add(det);
				log.info(expositorList+" -- >  "+vendedorList.get(i));
            	if(expo==null){
            		expo =" \n - "+vendedorList.get(i);
            	}else{
            		expo = expo+" \n - "+vendedorList.get(i);
            	}
			}
				return mostrar();
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}
	
	/**lista los horarios del trabajador**/
	public String mostrar() throws Exception {
		log.info(" eventModel --->");
		log.info("listarHorario del personal "+listaHorario.size());
				if (listaHorario == null) {
					listaHorario = new ArrayList<HorarioPersonalSie>();
				}
                
				for(int i=0;i < listaHorario.size(); i++){
					objHorarioPersonal= listaHorario.get(i);
	               
	                Calendar cal = new GregorianCalendar();
	            	log.info(" xd "+ objHorarioPersonal.getDiainicio());
	            	cal.setTime( objHorarioPersonal.getDiainicio());
	                
	                Calendar cal3 = new GregorianCalendar();
	            	cal3.setTime(objHorarioPersonal.getDiainicio());
	            	
	            	Calendar cal4 = new GregorianCalendar();
	            	cal4.setTime(objHorarioPersonal.getDiafin());
	                log.info(" fecha  " + cal.getTime() +"  time fin "+cal4.getTime());
	                
	                while (cal.before(cal4)||cal.equals(cal4)){
	                	log.info(" ***   getime  " + cal.getTime());
	                for (int j = 0; j < diaList.size() ; j++) {
	                	log.info(" ***   dia numerico " +Integer.parseInt(diaList.get(j)));
	                	if(cal.get(Calendar.DAY_OF_WEEK) == Integer.parseInt(diaList.get(j))) {
		                	log.info("en el seggundo  while");
		                	dDate = cal.getTime();
		                	Calendar cal0 = new GregorianCalendar();
		                	Calendar cal33 = new GregorianCalendar();
		 	            	String[] a= (objHorarioPersonal.getHoraIngreso()+"").split(":");
		 	            	cal0.setTime(dDate);
		 	            	cal0.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a[0]));
		 	            	cal0.add(Calendar.MINUTE, Integer.parseInt(a[1]));
		 	            	dDate = cal0.getTime();
		 	            	dDate2 = cal3.getTime();
		 	            	String[] a2= (objHorarioPersonal.getHoraSalida()+"").split(":");
		 	            	cal33.setTime(dDate2);
		 	            	cal33.add(Calendar.HOUR_OF_DAY, Integer.parseInt(a2[0]));
		 	            	cal33.add(Calendar.MINUTE, Integer.parseInt(a2[1]));
		 	            	dDate2 = cal33.getTime();
		 	                log.info(" dia 1 " +dDate);
		 	                log.info(" dia 2 " +dDate2);
		 	               
		 	                objHorarioPersonal.setDescripcion("\n Expositores:" +	expo);
		 	                
		 	                log.info(objHorarioPersonal.getDescripcion());
		 	                boolean existeMismoHorario =false;
		 	                
		 	                //Comprobar si el expositor ya se encuentra en un horario de dicha fecha
		 	                
	 						log.info("cantidad "+eventModel.getEventCount()+"  cant "+eventModel.getEvents().size());
		 					for (int m = 0; m < eventModel.getEventCount(); m++) {
		 						for (int k = 0; k < expositorList.size(); k++) {
			 						log.info("e "+expositorList.get(k)+ " H "+eventModel.getEvents().get(m).getStartDate()+" "+
			 								dDate+" "+eventModel.getEvents().get(m).getEndDate()+" "+dDate2);
		 							if( eventModel.getEvents().get(m).getTitle().contains(expositorList.get(k))){
		 								log.info("equals");
		 								if( eventModel.getEvents().get(m).getStartDate().equals(dDate) && 
		 									eventModel.getEvents().get(m).getEndDate().equals(dDate2)){
			 								log.info(" TRUE ");
					 						existeMismoHorario=true;
					 						break;
				 						}
		 							}
								}
		 						for (int k = 0; k < vendedorList.size(); k++) {
			 						log.info("e "+vendedorList.get(k)+ " H "+eventModel.getEvents().get(m).getStartDate()+" "+
			 								dDate+" "+eventModel.getEvents().get(m).getEndDate()+" "+dDate2);
		 							if( eventModel.getEvents().get(m).getTitle().contains(vendedorList.get(k))){
		 								log.info("equals");
		 								if( eventModel.getEvents().get(m).getStartDate().equals(dDate) && 
		 									eventModel.getEvents().get(m).getEndDate().equals(dDate2)){
			 								log.info(" TRUE ");
					 						existeMismoHorario=true;
					 						break;
				 						}
		 							}
								}
		 	                }
		 					
		 					if(existeMismoHorario){
		 						mensaje="Dicho expositor ya se encuentra en el mismo horario";
		 						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
		 						break;
		 					}else{
		 						event = new DefaultScheduleEvent();
		 						eventModel.addEvent(new DefaultScheduleEvent(objHorarioPersonal.getDescripcion(), dDate, dDate2));
			 					mensaje="Horario agregado correctamente ";
			 					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		 					}
		                }
	                } cal.add(Calendar.DAY_OF_WEEK,1);
		              cal3.add(Calendar.DAY_OF_WEEK,1);
	               }
				}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update() throws Exception {
		log.info("update() **");
		diaList= new  ArrayList<String>();
		newRecord=false;	
		diaList.add("" + objHorarioPersonal.getTbFecha().getIdFecha());
		setHoraIngreso(objHorarioPersonal.getHoraIngreso());
		setHoraSalida(objHorarioPersonal.getHoraSalida());
		return getViewList();
	}
	
	/**
	 * @param Event 'selectEvent'
	 */
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
		 log.info("onEventSelect ");
		 cerradorList= new ArrayList<String>();
		 event = selectEvent.getScheduleEvent();
		 log.info(event.getTitle());
			tittle= event.getTitle();
		 String rr[]=event.getTitle().split("Cerradores:");
		 if(rr.length>=2){
			 log.info(" rr0 "+rr[0]);
			 log.info(" rr1 "+rr[1]);
			 String rr2[] = rr[1].split("\n-");
			 for (int i = 1; i < rr2.length; i++) {
				//si ya tiene seleccionados los cerradores
				log.info(" -->"+rr2[i]);
				cerradorList.add(rr2[i]);
			}
		}
	    newRecord=false;
	}
	
	/**
	 * @return 'agregarCerrador'
	 */
	public String agregarCerrador() {
		RequestContext context = RequestContext.getCurrentInstance();
		log.info("agregarCerrador() ");
		log.info("tittle "+tittle);
		
		for (int i = 0; i < eventos.size(); i++) {
			if(event.getId() != null){
				log.info("tittle--->* "+eventos.get(i).getTitle());
				eventModel.updateEvent(eventos.get(i));
			}
		}
		log.info("total de eventos * "+eventModel.getEvents().size()+" canti "+eventModel.getEventCount());
		event = new DefaultScheduleEvent(); 
		context.execute("horarioVentaDialog.hide()");
		context.update("formGrupo");
		log.info("total de eventos 2* "+eventModel.getEvents().size()+" canti "+eventModel.getEventCount());
        return null;
    }
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar()");
		mensaje=null;
		log.info("total de eventos "+eventModel.getEvents().size()+" canti "+eventModel.getEventCount());
		try {
			if(mensaje==null){
				for (int j = 0; j < eventModel.getEventCount();j++) {
					objHorarioPersonal.setDescripcion("Horario");
					objHorarioPersonal.setDiainicio(eventModel.getEvents().get(j).getStartDate());
					objHorarioPersonal.setDiafin(eventModel.getEvents().get(j).getEndDate());
					objHorarioPersonal.setHoraIngreso(new Time(eventModel.getEvents().get(j).getStartDate().getTime()));
					objHorarioPersonal.setHoraSalida(new Time(eventModel.getEvents().get(j).getEndDate().getTime()));
					log.info("title "+eventModel.getEvents().get(j).getTitle());
					if(eventModel.getEvents().get(j).getTitle().contains("Expositores:")==true){
						
						String rr[]= eventModel.getEvents().get(j).getTitle().split("Expositores:");
						log.info(" dentro de expositore "+rr[0]);
						log.info(" dentro de expositore "+rr[1]);
						if(rr.length>0){
							 String rr2[] = rr[1].split("\n- ");
							 log.info(" "+rr2.length);
							  
							for (int i = 1; i < rr2.length; i++) {
								log.info(" --->"+rr2[i]);
//							objHorarioPersonal.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(rr2[i]));
//							objHorarioPersonalService.insertHorarioPersonal(objHorarioPersonal,diaList, idempleado);
							}
						 }
					}
					if(eventModel.getEvents().get(j).getTitle().contains("Cerradores:")==true){
						String rr3[]= eventModel.getEvents().get(j).getTitle().split("\nCerradores:");
						 if(rr3.length>=2){
							 String rr2[] = rr3[1].split("\n-");
							 for (int i = 1; i < rr2.length; i++) {
								log.info(" -->"+rr2[i]);
//								objHorarioPersonal.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(rr2[i]));
//								objHorarioPersonalService.insertHorarioPersonal(objHorarioPersonal,diaList, idempleado);
							}
						}
					}
				}
				mensaje = Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
			}
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewList();
	}
	
	public void listarFechas() throws ParseException{
		cal= DateUtil.getToday();
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
			setRangoFecha("  ( "+ fechaInicio +" - "+fechaFin+")");
	}
	
	/*GETs Y SETs*/
	
	public String getViewList() {
		return Constants.MANT_DISTRIBUCION_HORARIO_PERSONAL;
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
	 * @return the objHorarioPersonal
	 */
	public HorarioPersonalSie getObjHorarioPersonal() {
		return objHorarioPersonal;
	}

	/**
	 * @param objHorarioPersonal the objHorarioPersonal to set
	 */
	public void setObjHorarioPersonal(HorarioPersonalSie objHorarioPersonal) {
		this.objHorarioPersonal = objHorarioPersonal;
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
		setDias("( ");
		for (int i = 0; i < diaList.size(); i++) {
			 Iterator it = comboManager.getDiasItems().entrySet().iterator();
		     while(it.hasNext()){
		    	 Map.Entry m =(Map.Entry)it.next();
		    	 String value=(String)m.getKey();
		    	 if(m.getValue().toString().equals(diaList.get(i))){
		    	 if(i==0)
		    		 setDias(dias+value);
		    	 else
		    		 setDias(dias+", "+value);
		    	 }
		      }
		}
		setDias(dias+" )");
		if(diaList.size()==0){
			setDias("");
		}
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
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		log.info("GetEventModel!!  XD ");
		return eventModel;
	}

	/**
	 * @param eventModel the eventModel to set
	 */
	public void setEventModel(ScheduleModel eventModel) {
		log.info("seteando EventModel!! ** ");
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
		try {
			dhoy = DateUtil.getToday().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dhoy;
	}

	/**
	 * @param dhoy the dhoy to set
	 */
	public void setDhoy(Date dhoy) {
		this.dhoy = dhoy;
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
	 * @return the objEmpleadoSie
	 */
	public EmpleadoSie getObjEmpleadoSie() {
		return objEmpleadoSie;
	}

	/**
	 * @param objEmpleadoSie the objEmpleadoSie to set
	 */
	public void setObjEmpleadoSie(EmpleadoSie objEmpleadoSie) {
		this.objEmpleadoSie = objEmpleadoSie;
	}

	/**
	 * @return the expositorList
	 */
	public List<String> getExpositorList() {
		return expositorList;
	}

	/**
	 * @param expositorList the expositorList to set
	 */
	public void setExpositorList(List<String> expositorList) {
		this.expositorList = expositorList;
	}

	/**
	 * @return the cerradorList
	 */
	public List<String> getCerradorList() {
		return cerradorList;
	}

	/**
	 * @param cerradorList the cerradorList to set
	 */
	public void setCerradorList(List<String> cerradorList) {
		//capturar los eventos del schedule que tengan la misma fecha de inicio y fin.
		//setear sus tittle's
		eventos = new ArrayList<ScheduleEvent>();
		log.info("total de eventos "+eventModel.getEvents().size()+" canti "+eventModel.getEventCount());
		for (int i = 0; i < eventModel.getEvents().size(); i++) {
			//buscar los eventos que esten en el mismo horario (fecha de inicio y fecha final)
			Calendar calEvent= new GregorianCalendar();
			calEvent.setTime(event.getStartDate());
			
			Calendar calEventE= new GregorianCalendar();
			calEventE.setTime(event.getEndDate());
			
			Calendar calS= new GregorianCalendar();
			calS.setTime(eventModel.getEvents().get(i).getStartDate());
			
			Calendar calE= new GregorianCalendar();
			calE.setTime(eventModel.getEvents().get(i).getEndDate());
			//El mismo día (lunes, etc) y misma hora de inicio y fin
			//Con los mismos expositores
			if( calEvent.get(Calendar.DAY_OF_WEEK)==calS.get(Calendar.DAY_OF_WEEK) &&
				calEventE.get(Calendar.DAY_OF_WEEK)==calE.get(Calendar.DAY_OF_WEEK) &&
				calEvent.get(Calendar.HOUR)==calS.get(Calendar.HOUR) &&
				calEventE.get(Calendar.HOUR)==calE.get(Calendar.HOUR)&&
				eventModel.getEvents().get(i).getTitle().equalsIgnoreCase(event.getTitle())){
				log.info("agregar");
				eventos.add(eventModel.getEvents().get(i));
			}
		}
		log.info("total de eventos 2 "+eventModel.getEvents().size()+" canti "+eventModel.getEventCount());
		if(cerradorList!=null){
			if(event.getTitle() != null){
				if(event.getTitle().contains("Cerradores:")==true){
					String rr[] = event.getTitle().split("\nCerradores:");
					if(rr.length>=2){
						tittle =rr[0];
					}
				}
				if(cerradorList.size()==0||cerradorList==null){
					setTittle(tittle);
					log.info("tittlex "+tittle );
				}
				else{
					setTittle(tittle+"\nCerradores:");
					for (int i = 0; i < cerradorList.size(); i++) {
						setTittle(tittle +"\n-"+cerradorList.get(i));
					}
					log.info("tittle "+tittle );
				}
			}
		}
		log.info("total de eventos 3 "+eventModel.getEvents().size()+" canti "+eventModel.getEventCount());
		this.cerradorList = cerradorList;
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
	 * @return the cal
	 */
	public Calendar getCal() {
		return cal;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(Calendar cal) {
		this.cal = cal;
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
	 * @return the objMetaMesService
	 */
	public MetaMesService getObjMetaMesService() {
		return objMetaMesService;
	}

	/**
	 * @param objMetaMesService the objMetaMesService to set
	 */
	public void setObjMetaMesService(MetaMesService objMetaMesService) {
		this.objMetaMesService = objMetaMesService;
	}

	/**
	 * @return the objHorarioPersonalService
	 */
	public HorarioPersonalService getObjHorarioPersonalService() {
		return objHorarioPersonalService;
	}

	/**
	 * @param objHorarioPersonalService the objHorarioPersonalService to set
	 */
	public void setObjHorarioPersonalService(
			HorarioPersonalService objHorarioPersonalService) {
		this.objHorarioPersonalService = objHorarioPersonalService;
	}
	
	/**
	 * @return the objEmpleadoSieService
	 */
	public EmpleadoSieService getObjEmpleadoSieService() {
		return objEmpleadoSieService;
	}

	/**
	 * @param objEmpleadoSieService the objEmpleadoSieService to set
	 */
	public void setObjEmpleadoSieService(EmpleadoSieService objEmpleadoSieService) {
		this.objEmpleadoSieService = objEmpleadoSieService;
	}

	/**
	 * @return the objFechaService
	 */
	public FechaService getObjFechaService() {
		return objFechaService;
	}

	/**
	 * @param objFechaService the objFechaService to set
	 */
	public void setObjFechaService(FechaService objFechaService) {
		this.objFechaService = objFechaService;
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
	 * @return the tipoVenta
	 */
	public int getTipoVenta() {
		return tipoVenta;
	}

	/**
	 * @param tipoVenta the tipoVenta to set
	 */
	public void setTipoVenta(int tipoVenta) {
		log.info("SetTipoVenta");
		comboManager.setTipoVenta(tipoVenta);
		comboManager.setTipoAlmacen(tipoVenta);
		comboManager.setVendedorItems(null);
		comboManager.setExpositorItems(null);
		comboManager.setCerradorItems(null);
		cerradorList = new ArrayList<String>();
		vendedorList= new ArrayList<String>();
		expositorList= new ArrayList<String>();
		diaList= new ArrayList<String>(); 
		idturno=0;
		idMes=0;
		rangoTurno="";
		rangoFecha="";
		dias="";
		Map<String, Integer> vendedorxcargo = new HashMap<String, Integer>();
		Map<String, Integer> expositorxcargo = new HashMap<String, Integer>();
		List listaP = new ArrayList<EmpleadoSie>();
		
		if(tipoVenta>2){ //Iglesia u eventos
			//listar todos los cerradores
			List<EmpleadoSie> listaempleados = objEmpleadoSieService.listarEmpleadosXCargo(cerrador);
			log.info("CERRADOR  --> "+listaempleados.size());
			vendedorxcargo = new HashMap<String, Integer>();
			for (int i = 0; i < listaempleados.size(); i++) {
				EmpleadoSie c = new EmpleadoSie();
				c = (EmpleadoSie) listaempleados.get(i);
				vendedorxcargo.put(c.getNombresCompletos(), c.getIdempleado());
			}
			comboManager.setCerradorItems(vendedorxcargo);
			//listar todos los expositores
			listaempleados = objEmpleadoSieService.listarEmpleadosXCargo(expositor);
			log.info("EXPOSITORES --> "+listaempleados.size());
			expositorxcargo =  new HashMap<String, Integer>();
			for (int j = 0; j < listaempleados.size(); j++) {
				EmpleadoSie c = new EmpleadoSie();
				c = (EmpleadoSie) listaempleados.get(j);
				expositorxcargo.put(c.getNombresCompletos(), c.getIdempleado());
			}
			comboManager.setExpositorItems(expositorxcargo);
		}else{
			vendedorxcargo = new HashMap<String, Integer>();
			if(tipoVenta==1){//Masivo
				//busca cerradores
				listaP = (List<EmpleadoSie>) objEmpleadoSieService.listarEmpleadosXCargo(cerrador);
				log.info("CERRADORES --> "+listaP.size());
				for (int i = 0; i < listaP.size(); i++) {
					EmpleadoSie c = new EmpleadoSie();
					c = (EmpleadoSie) listaP.get(i);
					vendedorxcargo.put(c.getNombresCompletos(),c.getIdempleado());
				} 
				comboManager.setCerradorItems(vendedorxcargo);
				
				//listar todos los expositores
				listaP = objEmpleadoSieService.listarEmpleadosXCargo(expositor);
				log.info("EXPOSITORES --> "+listaP.size());
				expositorxcargo =  new HashMap<String, Integer>();
				for (int j = 0; j < listaP.size(); j++) {
					EmpleadoSie c = new EmpleadoSie();
					c = (EmpleadoSie) listaP.get(j);
					expositorxcargo.put(c.getNombresCompletos(), c.getIdempleado());
				}
				comboManager.setExpositorItems(expositorxcargo);
			}
			else if(tipoVenta==2){//Punto de Venta
				//busca vendedores
				listaP = (List<EmpleadoSie>) objEmpleadoSieService.listarEmpleadosXCargo(vendedor);
				vendedorxcargo = new HashMap<String, Integer>();
				log.info("VENDEDORES --> "+listaP.size());
				for (int i = 0; i < listaP.size(); i++) {
					EmpleadoSie c = new EmpleadoSie();
					c = (EmpleadoSie) listaP.get(i);
					vendedorxcargo.put(c.getNombresCompletos(),c.getIdempleado());
				}
				comboManager.setVendedorItems(vendedorxcargo);
			}
		}
		this.tipoVenta = tipoVenta;
	}
	
	/**
	 * @return the idturno
	 */
	public int getIdturno() {
		return idturno;
	}

	/**
	 * @param idturno the idturno to set
	 */
	public void setIdturno(int idturno) {
		objTurno = objTurnoService.findTurno(idturno);
		setRangoTurno("  ("+ objTurno.getHoraInicio()+ " - "+objTurno.getHoraFin()+" hrs.)");
		this.idturno = idturno;
	}

	/**
	 * @return the rangoTurno
	 */
	public String getRangoTurno() {
		return rangoTurno;
	}

	/**
	 * @param rangoTurno the rangoTurno to set
	 */
	public void setRangoTurno(String rangoTurno) {
		this.rangoTurno = rangoTurno;
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
	 * @return the rangoFecha
	 */
	public String getRangoFecha() {
		return rangoFecha;
	}

	/**
	 * @param rangoFecha the rangoFecha to set
	 */
	public void setRangoFecha(String rangoFecha) {
		this.rangoFecha = rangoFecha;
	}

	/**
	 * @return the listaDetTurnoEmpl
	 */
	public List<DetTurnoEmplSie> getListaDetTurnoEmpl() {
		return listaDetTurnoEmpl;
	}

	/**
	 * @param listaDetTurnoEmpl the listaDetTurnoEmpl to set
	 */
	public void setListaDetTurnoEmpl(List<DetTurnoEmplSie> listaDetTurnoEmpl) {
		this.listaDetTurnoEmpl = listaDetTurnoEmpl;
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
	 * @return the tittle
	 */
	public String getTittle() {
		return tittle;
	}

	/**
	 * @param tittle the tittle to set
	 */
	public void setTittle(String tittle) {
		log.info("eventos size  "+eventos.size()+" tittle "+tittle);
		this.tittle = tittle;
	}

	/**
	 * @return the eventos
	 */
	public List<ScheduleEvent> getEventos() {
		return eventos;
	}

	/**
	 * @param eventos the eventos to set
	 */
	public void setEventos(List<ScheduleEvent> eventos) {
		this.eventos = eventos;
	}

	/**
	 * @return the dias
	 */
	public String getDias() {
		return dias;
	}

	/**
	 * @param dias the dias to set
	 */
	public void setDias(String dias) {
		this.dias = dias;
	}

}
