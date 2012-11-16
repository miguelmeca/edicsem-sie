package com.edicsem.pe.sie.client.action.mantenimiento;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

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
	HorarioPersonalSie objHorario;
	
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
		horarioPersonalList= new ArrayList<HorarioPersonalSie>();
		objHorario= new HorarioPersonalSie();
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
		
		// Los horarios son generados por una semana
		while(cDesde.before(cHasta)||cDesde.equals(cHasta)){
			
			log.info(" "+ cDesde.getTime());
			
			for (int i = 0; i < horariopv.size(); i++) {
				log.info(" fech "+ cDesde.get(Calendar.DAY_OF_WEEK) +"  "+ horariopv.get(i).getTbFecha().getIdFecha());
				
					log.info(" depende de los acuerdos ");
					log.info(" depende de las objeciones ");
					log.info(" depende de las preferencias ");
					
					// se debe cumplir que los vendedores tengan un dia libre (descanso)
					Time hora1,hora2, horaAuxi;
					Calendar hingreso = new GregorianCalendar();
					Calendar hingreso2 = new GregorianCalendar();
					if(cDesde.get(Calendar.DAY_OF_WEEK) == horariopv.get(i).getTbFecha().getIdFecha() ){
						log.info(" entro  ");
						List<Integer> arreglo = new ArrayList<Integer>();
						//guardamos en un arreglo los id de los vendedores
						for (int j = 0; j < vendedores.size(); j++) {
							arreglo.add(vendedores.get(j).getIdempleado());
							log.info(" vende  " + vendedores.get(j).getIdempleado());
						}
						
						log.info(" arrreglo  " + arreglo.size());
						for (int j = 0; j < arreglo.size(); j++) {
							log.info("for  "+ arreglo.size());
							
							//se debe seleccionar los vendedores aleatoriamente
							Random r = new Random();
							int f = (int) (Math.random() * ((arreglo.size()-1)-0+1));
							
							log.info("random " + f +" tama�o  "+ arreglo.size());
							log.info("agregado  "+arreglo);
							
							log.info("random " + f +" tama�o  "+ arreglo.size());
							log.info("agregado --> "+arreglo.get(f));
							// ALEATORIAMENE las horas 
							int arr[] = { 4, 6, 8, 12 };
							
							int rho = (int) (Math.random() * (arr.length-1 + 1));
							log.info("tama�o  "+arr.length+"  rho "+arr[rho]);
							hingreso.setTime(horariopv.get(i).getHoraIngreso());
							hora1 = new Time(hingreso.getTimeInMillis());
							hingreso.add(Calendar.HOUR, arr[rho]);
							hora2 = new Time(hingreso.getTimeInMillis());
							log.info("hora 1 "+ hora1+" hora2 "+hora2);
							objHorario.setHoraIngreso(hora1);
							objHorario.setHoraSalida(hora2);
							objHorario.setTbEmpleado(objVendedoresService.buscarEmpleado(arreglo.get(f)));
							objHorario.setDiainicio(cDesde.getTime());
							horarioPersonalList.add(objHorario);
							arreglo.remove(f);
							log.info(arreglo);
							
							log.info("hora sal primer turno  :D "+hora1+ "hora sal pv "+hora2);
							log.info("primer turno 1 "+horarioPersonalList.size());
							while(hora2.before(horariopv.get(i).getHoraSalida())){
								objHorario= new HorarioPersonalSie();
								objHorario.setDiainicio(cDesde.getTime());
								log.info("hora *** "+hora2);
								objHorario.setHoraIngreso(hora2);
								hingreso2.setTime(cDesde.getTime());
								String[] a1= (hora2+"").split(":");
								hingreso2.set(Calendar.HOUR, Integer.parseInt(a1[0]));
								hingreso2.set(Calendar.MINUTE, Integer.parseInt(a1[1]));
								log.info("h ingreso 2 " + hingreso2.getTime());
								long v= horariopv.get(i).getHoraSalida().getTime()- hora2.getTime();
								log.info("veee  ... :D "+ v);
								horaAuxi= new Time(v);
								Time e= new Time(v);
								String[] a= (e+"").split(":");
								log.info(hingreso2.getTime() + " + "+ e);
								hingreso2.add(Calendar.HOUR, Integer.parseInt(a[0]) );
								hingreso2.add(Calendar.MINUTE, Integer.parseInt(a[1]) );
								hora2 = new Time(hingreso2.getTimeInMillis());
								log.info("hora ingreso 2 "+ hora2);
								objHorario.setHoraSalida(hora2);
								hora2=objHorario.getHoraSalida();
								objHorario.setTbEmpleado(vendedores.get((int) (Math.random() * (arreglo.size()-0+1))));
								horarioPersonalList.add(objHorario);
								log.info("h  "+hora2+" h sal  "+horariopv.get(i).getHoraSalida());
							}
							log.info("primer turno "+horarioPersonalList.size());
							for (int k = 0; k < horarioPersonalList.size(); k++) {
								log.info("primer turno "+horarioPersonalList.get(k).getDiainicio()+ " " +horarioPersonalList.get(k).getHoraIngreso()+" - "+horarioPersonalList.get(k).getHoraSalida()+" empleado "+  horarioPersonalList.get(k).getTbEmpleado().getIdempleado());
							}
						}
					}
				}
			//tomar en cuenta que un solo punto pueden haber m�s de un vendedor en el mismo horario, m�ximo 3
			
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
