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
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
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
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
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
    /*schedule*/
	private ScheduleModel eventModel;
	/*fin schedule*/
	private Date  horaIngreso,horaSalida;
	private int idhorario;
	private String mensaje, rangoTurno, rangoFecha;
	private int idempleado;
	private boolean newRecord =false;
	private int ide;
	private Date dDate, dDate2, dhoy;
	private EmpleadoSie objEmpleadoSie;
	private int cerrador, expositor, vendedor;
	private TurnoSie objTurno;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	@EJB
	private CargoEmpleadoService objCargoService;
	@EJB
	private MetaMesService objMetaMesService;
	@EJB
	private HorarioPersonalService objHorarioPersonalService;
	@EJB
	private EstadogeneralService objEstadoGeneralService;
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
		expositorList = new ArrayList<String>();
		cerradorList = new ArrayList<String>();
		objMetaMesSie = new MetaMesSie();
		eventModel = new DefaultScheduleModel();
		diaList = new  ArrayList<String>();
		listaDetTurnoEmpl = new ArrayList<DetTurnoEmplSie>();
		fechaInicio = "";
		fechaFin = "";
		CargoEmpleadoSie ccerrador =  objCargoService.buscarCargoEmpleado(Constants.CARGO_CERRADOR);
		CargoEmpleadoSie cvendedor =  objCargoService.buscarCargoEmpleado(Constants.CARGO_VENDEDOR);
		CargoEmpleadoSie cexpositor =  objCargoService.buscarCargoEmpleado(Constants.CARGO_EXPOSITOR);
		cerrador = ccerrador.getIdcargoempleado();
		expositor= cexpositor.getIdcargoempleado();
		vendedor= cvendedor.getIdcargoempleado();
		return getViewList();
	}
	
	/**lista los horarios del trabajador**/
	public String mostrar() throws Exception {
		log.info(" eventModel --->");
		eventModel = new DefaultScheduleModel();
		log.info("listarHorario del personal");
		listaHorario = objHorarioPersonalService.listarHorarioPersonalXempleado(idempleado);
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
	                	log.info("en el primer while  fecha inicio ");
	                	log.info(" ***   getime  " + cal.getTime());
		              if(cal.get(Calendar.DAY_OF_WEEK) == objHorarioPersonal.getTbFecha().getIdFecha()) {
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
		 	                eventModel.addEvent(new DefaultScheduleEvent(objHorarioPersonal.getDescripcion()+" de "+ objHorarioPersonal.getHoraIngreso()+" hasta "+ objHorarioPersonal.getHoraSalida(), dDate, dDate2));  
		                }
		              cal.add(Calendar.DAY_OF_WEEK,1);
		              cal3.add(Calendar.DAY_OF_WEEK,1);
	                }
				}
		objHorarioPersonal = new HorarioPersonalSie();
		return getViewList();
	}
	
	public List<String> completeVend(String query){
		log.info(" completeExpo() "+query+"  "+comboManager.getVendedorItems().size());
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
		log.info(" completeExpo() "+query+"  "+comboManager.getExpositorItems().size());
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
	
	public List<String> complete(String query){
		log.info(" complete() "+query+"  "+comboManager.getVendedorItems().size());
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
	
	public String agregarhorario(){
		log.info("agregarhorario()");

		
		
		if(tipoVenta==1||tipoVenta>2){//Masivo
			for (int i = 0; i < expositorList.size(); i++) {//expositores
				objHorarioPersonal= new HorarioPersonalSie();
				objHorarioPersonal.setHoraIngreso(objTurno.getHoraInicio());
				objHorarioPersonal.setHoraSalida(objTurno.getHoraFin());
				DetTurnoEmplSie det = new DetTurnoEmplSie();
				det.setTbTurno(objTurnoService.findTurno(idturno));
				det.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(expositorList.get(i)));
				det.setTbCargoempleado(objCargoService.buscarCargoEmpleado(expositor));
				listaDetTurnoEmpl.add(det);
				objHorarioPersonal.setTbEmpleado(det.getTbEmpleado());
				for (int j = 0; j < diaList.size(); j++) {
					//objHorarioPersonal.setTbFecha(objFechaService.findFecha(diaList.get(j)));
					listaHorario.add(objHorarioPersonal);
				}
			}
//			 actualizando grupos add cerradores
//			for (int i = 0; i < vendedorList.size(); i++) {//cerradores
//				DetTurnoEmplSie det = new DetTurnoEmplSie();
//				det.setTbTurno(objTurnoService.findTurno(idturno));
//				det.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(vendedorList.get(i)));
//				det.setTbCargoempleado(objCargoService.buscarCargoEmpleado(cerrador));
//				listaDetTurnoEmpl.add(det);
//			}
		}else if(tipoVenta==2){//Punto de Venta
			for (int i = 0; i < vendedorList.size(); i++) {//vendedores
				DetTurnoEmplSie det = new DetTurnoEmplSie();
				det.setTbTurno(objTurnoService.findTurno(idturno));
				det.setTbEmpleado(objEmpleadoSieService.buscarEmpleadoVendedor(vendedorList.get(i)));
				det.setTbCargoempleado(objCargoService.buscarCargoEmpleado(vendedor));
				listaDetTurnoEmpl.add(det);
			}
		}
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
	
	
	public String insertar() throws Exception {
		mensaje=null;
		log.info("entrando a insertar");
		
		objHorarioPersonal.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(36));
		objHorarioPersonal.setHoraIngreso(new Time(horaIngreso.getTime()));
		objHorarioPersonal.setHoraSalida(new Time(horaSalida.getTime()));
		
		try {
					
			//para que no hayan cruces de horario 
			for (int j = 0; j < diaList.size();j++) {
				for (int i = 0; i < listaHorario.size(); i++) {
					log.info("  verificando ");
					if( listaHorario.get(i).getTbFecha().getIdFecha()== Integer.parseInt(diaList.get(j))
						 && (objHorarioPersonal.getDiainicio().after(listaHorario.get(i).getDiainicio()) && 
								 objHorarioPersonal.getDiainicio().before(listaHorario.get(i).getDiafin() ) ||
								 objHorarioPersonal.getDiafin().after(listaHorario.get(i).getDiainicio()) && 
								 objHorarioPersonal.getDiafin().before(listaHorario.get(i).getDiafin() ) ||
								 objHorarioPersonal.getDiafin().equals(listaHorario.get(i).getDiafin()) ||
								 objHorarioPersonal.getDiafin().equals(listaHorario.get(i).getDiainicio())||
								 objHorarioPersonal.getDiainicio().equals(listaHorario.get(i).getDiafin())||
								 objHorarioPersonal.getDiainicio().equals(listaHorario.get(i).getDiainicio()))){
						log.info("fechas con horario registrado   ");
						 
						log.info(" hora ingreso "+objHorarioPersonal.getHoraIngreso());
						log.info(" hora ingreso "+objHorarioPersonal.getHoraSalida());
						log.info(" hora 2 "+listaHorario.get(i).getHoraIngreso());
						log.info(" hora sal "+listaHorario.get(i).getHoraSalida());
						log.info(" hora 2 "+listaHorario.get(i).getHoraIngreso());
						if(	objHorarioPersonal.getHoraIngreso().equals(listaHorario.get(i).getHoraIngreso()) ||
								objHorarioPersonal.getHoraIngreso().after(listaHorario.get(i).getHoraIngreso())
								&& objHorarioPersonal.getHoraIngreso().before(listaHorario.get(i).getHoraSalida())){
							log.info(objHorarioPersonal.getHoraIngreso() +" --->  getHoraIngreso "+ listaHorario.get(i).getHoraIngreso());
							mensaje = "La hora de Ingreso es errónea del día "+ listaHorario.get(i).getTbFecha().getDia();
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
									Constants.MESSAGE_INFO_TITULO, mensaje);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							break;
						}else if(objHorarioPersonal.getHoraSalida().equals(listaHorario.get(i).getHoraSalida()) ||
								objHorarioPersonal.getHoraSalida().after(listaHorario.get(i).getHoraIngreso())
								&& objHorarioPersonal.getHoraSalida().before(listaHorario.get(i).getHoraSalida())){
							log.info(objHorarioPersonal.getHoraSalida()+" --->  getHoraSalida "+listaHorario.get(i).getHoraSalida());
							mensaje = "La hora de Salida es errónea del día "+ listaHorario.get(i).getTbFecha().getDia();
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
									Constants.MESSAGE_INFO_TITULO, mensaje);
							FacesContext.getCurrentInstance().addMessage(null, msg);
							break;
							}
						}
					}
				}	
			
					if(isNewRecord()&& mensaje==null){
					Time hora1 = new Time(getHoraIngreso().getTime());
					Time hora2= new Time(getHoraSalida().getTime());
					
					objHorarioPersonal.setHoraIngreso(hora1);
					objHorarioPersonal.setHoraSalida(hora2);
					
					objHorarioPersonal.setDiainicio(objHorarioPersonal.getDiainicio());
					objHorarioPersonal.setDiafin(objHorarioPersonal.getDiafin());
					objHorarioPersonal.setTbEmpleado(objEmpleadoSie);
					objHorarioPersonal.setDescripcion(objHorarioPersonal.getDescripcion().trim());
					log.info("APUNTO DE INSERTAR");
		
					objHorarioPersonalService.insertHorarioPersonal(objHorarioPersonal,diaList, idempleado);
					
					 log.info("insertando..... ");
					 mensaje ="Se Registró el horario Correctamente";
					 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO, mensaje);
					 FacesContext.getCurrentInstance().addMessage(null, msg);
				
				}
			else if (isNewRecord()==false){
					log.info("actualizando " + getHoraIngreso()+"  hora salida  "+getHoraSalida() );
					Time hora1 = new Time( getHoraIngreso().getTime());
					Time hora2= new Time( getHoraSalida().getTime());
					log.info(" g   "+ hora1);
					objHorarioPersonal.setHoraIngreso(hora1);
					objHorarioPersonal.setHoraSalida(hora2);
					objHorarioPersonal.setDescripcion(objHorarioPersonal.getDescripcion().trim());
					
					objHorarioPersonalService.updateHorarioPersonal(objHorarioPersonal);
					
					mensaje ="Se Actualiazó el horario Correctamente";
					 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							 Constants.MESSAGE_INFO_TITULO, mensaje);
					 FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return mostrar();
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
	 * @return the objEstadoGeneralService
	 */
	public EstadogeneralService getObjEstadoGeneralService() {
		return objEstadoGeneralService;
	}

	/**
	 * @param objEstadoGeneralService the objEstadoGeneralService to set
	 */
	public void setObjEstadoGeneralService(
			EstadogeneralService objEstadoGeneralService) {
		this.objEstadoGeneralService = objEstadoGeneralService;
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
		comboManager.setTipoVenta(tipoVenta);
		comboManager.setTipoAlmacen(tipoVenta);
		Map<String, Integer> vendedorxcargo = new HashMap<String, Integer>();
		Map<String, Integer> expositorxcargo = new HashMap<String, Integer>();
		List listaP = new ArrayList<EmpleadoSie>();
		comboManager.setVendedorItems(null);
		comboManager.setExpositorItems(null);
		if(tipoVenta==1){//Masivo
			//busca cerradores
			listaP = (List<EmpleadoSie>) objEmpleadoSieService.listarEmpleadosXCargo(cerrador);
		}
		else if(tipoVenta==2){//Punto de Venta
			//busca vendedores
			listaP = (List<EmpleadoSie>)  objEmpleadoSieService.listarEmpleadosXCargo(vendedor);
		}
		vendedorxcargo = new HashMap<String, Integer>();
			for (int i = 0; i < listaP.size(); i++) {
				EmpleadoSie c = new EmpleadoSie();
				c = (EmpleadoSie) listaP.get(i);
				vendedorxcargo.put(c.getNombresCompletos(),
						c.getIdempleado());
			}
			comboManager.setVendedorItems(vendedorxcargo);
		if(tipoVenta>2){
			//Iglesia u eventos
			//listar todos los cerradores
			List<EmpleadoSie> listaempleados = objEmpleadoSieService.listarEmpleadosXCargo(vendedor);
			vendedorxcargo = new HashMap<String, Integer>();
			for (int i = 0; i < listaempleados.size(); i++) {
				EmpleadoSie c = new EmpleadoSie();
				c = (EmpleadoSie) listaempleados.get(i);
				vendedorxcargo.put(c.getNombresCompletos(), c.getIdempleado());
			}
			comboManager.setVendedorItems(vendedorxcargo);
			//listar todos los expositores
			listaempleados = objEmpleadoSieService.listarEmpleadosXCargo(expositor);
			expositorxcargo =  new HashMap<String, Integer>();
			for (int j = 0; j < listaempleados.size(); j++) {
				EmpleadoSie c = new EmpleadoSie();
				c = (EmpleadoSie) listaempleados.get(j);
				expositorxcargo.put(c.getNombresCompletos(), c.getIdempleado());
			}
			comboManager.setExpositorItems(expositorxcargo);
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
		setRangoTurno("  ("+objTurno.getHoraInicio()+ " - "+objTurno.getHoraFin()+" hrs.)");
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

}
