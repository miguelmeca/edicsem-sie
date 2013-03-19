package com.edicsem.pe.sie.client.action.mantenimiento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.EmpleadoDTO;
import com.edicsem.pe.sie.beans.GrupoEmpleadoDTO;
import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;
import com.edicsem.pe.sie.service.facade.MetaMesService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="grupo")
@SessionScoped
public class MantenimientoGrupoEmpleadoSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoGrupoEmpleadoSearchAction.class);
	private List<DetGrupoEmpleadoSie> detGrupoEmplList;
	private List<EmpleadoDTO>  grupoEmplList;
	private List<GrupoVentaSie> grupoVentasieList;
	private List<GrupoEmpleadoDTO> grupoVentaList;
	
	/*** Lista Efectividad*/
	private List<EmpleadoDTO> listaEmpleado;
	private int idGrupo, idMes, idempleado, idtipoevento;
	private String grupoEscogido, mensaje, fechaInicio, fechaFin;
	private ArrayList<MenuDTO> lstMenu ;
	private Calendar cal;
	private MetaMesSie objMetaMesSie;
	
	@EJB
	private CargoEmpleadoService objCargoService;
	@EJB
	private DetGrupoEmpleadoService detgrupoemplService;
	@EJB
	private GrupoVentaService grupoventaService;
	@EJB
	private ContratoService contratoService;
	@EJB
	private CobranzaService cobranzaService;
	@EJB
	private MetaMesService objMetaMesService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	
	public MantenimientoGrupoEmpleadoSearchAction() {
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoGrupoEmpleadoSearchAction'");
		}
		detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
		grupoEmplList = new ArrayList<EmpleadoDTO>();
		idGrupo=0;
		idGrupo =0;
		idempleado=0;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("consultar() ' x grupo' " + idGrupo);
		mensaje =null;
		grupoVentaList= new ArrayList<GrupoEmpleadoDTO>();
		grupoEmplList = new ArrayList<EmpleadoDTO>();
		//Se debe listar los empleados con cargo de expositor
		
		detGrupoEmplList = detgrupoemplService.listarDetGrupoEmpleado(idtipoevento);
		
		if(detGrupoEmplList==null){
			detGrupoEmplList= new ArrayList<DetGrupoEmpleadoSie>();
		}
		if (detGrupoEmplList.size() == 0) {
			detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
			mensaje="No hay expositores registrados en éste tipo de evento";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		}
		else{
			CargoEmpleadoSie c= objCargoService.buscarCargoEmpleado("EXPOSITOR");
		for (int i = 0; i < detGrupoEmplList.size(); i++) {
			EmpleadoDTO g = new EmpleadoDTO();
			g.setTbempleado(detGrupoEmplList.get(i).getTbempleado());
			if(c==null){
				setMensaje("el cargo de expositor no se encuentra en la base de datos, verifiquelo");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				break;
			}else{
				int cargo =c.getIdcargoempleado();
				g.setFacturada(contratoService.findcantContratoFacturado(detGrupoEmplList.get(i).getTbempleado().getIdempleado(),cargo,fechaInicio, fechaFin));
				g.setEntregada(contratoService.findcantContratoEntregado(detGrupoEmplList.get(i).getTbempleado().getIdempleado(),cargo,fechaInicio, fechaFin));
				if(g.getEntregada()!=null)
				g.setTotalentregada(g.getEntregada());
				if(g.getFacturada()!=null)
				g.setTotalfacturada(g.getFacturada()*2);
				g.setPuntajeTotal(g.getTotalentregada()+g.getTotalfacturada());
				grupoEmplList.add(g);
			}
		}
		lstMenu = new ArrayList<MenuDTO>();
		grupoVentasieList = grupoventaService.listarGrupoVenta(idtipoevento);
		for (int i = 0; i < grupoVentasieList.size(); i++) {
			GrupoEmpleadoDTO g = new GrupoEmpleadoDTO();
			g.setTbGrupoVenta(grupoVentasieList.get(i));
			grupoVentaList.add(g);
			MenuDTO e = new MenuDTO();
			e.setNombreMenu(grupoVentaList.get(i).getTbGrupoVenta().getDescripcion());
			log.info("grupo:  "+grupoVentaList.get(i).getTbGrupoVenta().getDescripcion());
			lstMenu.add(e);
		}
		setMensaje("Consulta realizada con exito");
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() ");
		mensaje=null;
		EmpleadoDTO e = null;
		
		//Busqueda del Gerente como Punto de Efectividad base -- 100%
		EmpleadoSie gerente = objEmpleadoService.buscarEmpleadoVendedor(Constants.NOMBRE_GERENTE);
		if(gerente==null){
			mensaje = "No existe el trabajador "+Constants.NOMBRE_GERENTE;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		}else{
			List<CobranzaSie> lstCobranza = cobranzaService.calcularEfectividad(gerente.getIdempleado(), fechaInicio, fechaFin);
			for (int i = 0; i < lstCobranza.size(); i++) {
				CobranzaSie c = lstCobranza.get(i);
				e= new EmpleadoDTO();
				e.setCobro(lstCobranza.get(i).getImpcobrado().doubleValue());
				e.setDeberiaCobrar(lstCobranza.get(i).getImportemasmora().doubleValue());
				e.setPorcentajeRecuperado(e.getCobro()/e.getDeberiaCobrar());
				e.setPerdidaEfectiva(0.0);
				e.setPerdidaSoles(0.0);
				listaEmpleado.add(e);
			}
			//buscar expositores
			CargoEmpleadoSie car = objCargoService.buscarCargoEmpleado(Constants.CARGO_EXPOSITOR);
			if(car==null){
				mensaje = "No existe el cargo "+Constants.CARGO_EXPOSITOR;
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			}else {
				List<EmpleadoSie> lstExpositores= objEmpleadoService.listarEmpleadosXCargo(car.getIdcargoempleado());
				if(lstExpositores.size()==0){
					mensaje = "Csonulta realizada, No se encontraron expositores regitrados";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				}
				for (int j = 0; j < lstExpositores.size(); j++) {
					MetaMesSie objMetaMes = objMetaMesService.fechasEfectividad(idMes);
					List<CobranzaSie> lstCobranzaEx = cobranzaService.calcularEfectividad(lstExpositores.get(j).getIdempleado(), fechaInicio, fechaFin);
					EmpleadoDTO e2 = new EmpleadoDTO();
					for (int i = 0; i < lstCobranzaEx.size(); i++){
						CobranzaSie c = lstCobranzaEx.get(i);
						e2.setCobro(e2.getCobro()+c.getImpcobrado().doubleValue());
						e2.setDeberiaCobrar(e2.getDeberiaCobrar()+ c.getImportemasmora().doubleValue());
						
						if(i+1==lstCobranzaEx.size()){
							e2.setPorcentajeRecuperado(e2.getCobro()/e2.getDeberiaCobrar());
							e2.setPerdidaEfectiva(e.getPorcentajeRecuperado() - e2.getPorcentajeRecuperado());
							e2.setPerdidaSoles(e2.getDeberiaCobrar()*e2.getPerdidaEfectiva());
							listaEmpleado.add(e2);
						}
					}
				}
			}
		}
		if(mensaje==null){
			setMensaje("Consulta realizada con exito");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update(){
		log.info("update!!! ");
		if(idempleado!=0){
        	for (int i = 0; i < grupoEmplList.size(); i++) {
				if(grupoEmplList.get(i).getTbempleado().getIdempleado()==idempleado){
					for (int j = 0; j < grupoVentaList.size(); j++) {
						if(grupoVentaList.get(j).getTbGrupoVenta().getDescripcion().equalsIgnoreCase(grupoEscogido)){
							if(grupoVentaList.get(j).getDetalle()==null){
								List<EmpleadoDTO> ltNuevo = new ArrayList<EmpleadoDTO>();
								ltNuevo.add(grupoEmplList.get(i));
								grupoVentaList.get(j).setDetalle(ltNuevo);
							}else{
								grupoVentaList.get(j).getDetalle().add(grupoEmplList.get(i));
							}
							grupoEmplList.remove(i);
							break;
				        }
					}
					break;
				}
			}
        }
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
    }
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		if(grupoEmplList.size()!=0){
			detgrupoemplService.insertDetGrupoEmpleado(grupoVentaList);
			mensaje= Constants.MESSAGE_REGISTRO_TITULO;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		return null;
	}
	
	public String cambiarGrupo(){
		log.info("cambiarGrupo!!! :D ");
		if(idempleado!=0){
        for (int i = 0; i < grupoVentaList.size(); i++) {
        	if(grupoVentaList.get(i).getDetalle()!=null){
			for (int m = 0; m < grupoVentaList.get(i).getDetalle().size(); m++) {
				if(grupoVentaList.get(i).getDetalle().get(m).getTbempleado().getIdempleado()==idempleado){
					for (int j = 0; j < grupoVentaList.size(); j++) {
						if(grupoVentaList.get(j).getTbGrupoVenta().getDescripcion().equalsIgnoreCase(grupoEscogido)){
							if(grupoVentaList.get(j).getDetalle()==null){
								List<EmpleadoDTO> ltNuevo = new ArrayList<EmpleadoDTO>();
								ltNuevo.add(grupoVentaList.get(i).getDetalle().get(m));
								grupoVentaList.get(j).setDetalle(ltNuevo);
							}else{
								grupoVentaList.get(j).getDetalle().add(grupoVentaList.get(i).getDetalle().get(m));
							}
							grupoVentaList.get(i).getDetalle().remove(m);
							break;
				        }
					}
					break;
				}
				}
        	}
			}
        }
		return null;
    }
	
	public void listarFechas() throws ParseException{
		objMetaMesSie = objMetaMesService.fechasEfectividad(idMes);
		log.info("fecha ini* "+objMetaMesSie.getFechainicio()+" fech fin "+objMetaMesSie.getFechafin());
		fechaInicio = objMetaMesSie.getFechainicio();
		fechaFin = objMetaMesSie.getFechafin();
		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_ASIGNAR_GRUPO_VENTA;
	}

	/**
	 * @return the idGrupo
	 */
	public int getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	/**
	 * @return the grupoVentasieList
	 */
	public List<GrupoVentaSie> getGrupoVentasieList() {
		return grupoVentasieList;
	}

	/**
	 * @param grupoVentasieList the grupoVentasieList to set
	 */
	public void setGrupoVentasieList(List<GrupoVentaSie> grupoVentasieList) {
		this.grupoVentasieList = grupoVentasieList;
	}

	/**
	 * @return the grupoVentaList
	 */
	public List<GrupoEmpleadoDTO> getGrupoVentaList() {
		return grupoVentaList;
	}

	/**
	 * @param grupoVentaList the grupoVentaList to set
	 */
	public void setGrupoVentaList(List<GrupoEmpleadoDTO> grupoVentaList) {
		this.grupoVentaList = grupoVentaList;
	}

	/**
	 * @return the grupoEmplList
	 */
	public List<EmpleadoDTO> getGrupoEmplList() {
		return grupoEmplList;
	}

	/**
	 * @param grupoEmplList the grupoEmplList to set
	 */
	public void setGrupoEmplList(List<EmpleadoDTO> grupoEmplList) {
		this.grupoEmplList = grupoEmplList;
	}

	/**
	 * @return the idempleado
	 */
	public int getIdempleado() {
		log.info("idempleado *  "+idempleado );
		return idempleado;
	}

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(int idempleado) {
		log.info("idempleado *  "+idempleado );
		this.idempleado = idempleado;
	}

	/**
	 * @return the grupoEscogido
	 */
	public String getGrupoEscogido() {
		return grupoEscogido;
	}

	/**
	 * @param grupoEscogido the grupoEscogido to set
	 */
	public void setGrupoEscogido(String grupoEscogido) {
		this.grupoEscogido = grupoEscogido;
	}

	/**
	 * @return the lstMenu
	 */
	public ArrayList<MenuDTO> getLstMenu() {
		return lstMenu;
	}

	/**
	 * @param lstMenu the lstMenu to set
	 */
	public void setLstMenu(ArrayList<MenuDTO> lstMenu) {
		this.lstMenu = lstMenu;
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
	 * @return the idtipoevento
	 */
	public int getIdtipoevento() {
		return idtipoevento;
	}

	/**
	 * @param idtipoevento the idtipoevento to set
	 */
	public void setIdtipoevento(int idtipoevento) {
		this.idtipoevento = idtipoevento;
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
	 * @return the listaEmpleado
	 */
	public List<EmpleadoDTO> getListaEmpleado() {
		return listaEmpleado;
	}

	/**
	 * @param listaEmpleado the listaEmpleado to set
	 */
	public void setListaEmpleado(List<EmpleadoDTO> listaEmpleado) {
		this.listaEmpleado = listaEmpleado;
	}

}
