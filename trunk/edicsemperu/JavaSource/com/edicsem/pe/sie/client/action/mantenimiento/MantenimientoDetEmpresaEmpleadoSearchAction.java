package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
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
import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="grupo")
@SessionScoped
public class MantenimientoDetEmpresaEmpleadoSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoDetEmpresaEmpleadoSearchAction.class);
	private List<DetGrupoEmpleadoSie> detGrupoEmplList;
	private List<EmpleadoDTO>  grupoEmplList;
	private List<GrupoVentaSie> grupoVentasieList;
	private List<GrupoEmpleadoDTO> grupoVentaList;
	private int idGrupo, idempleado;
	private String grupoEscogido, mensaje;
	private ArrayList<MenuDTO> lstMenu ;
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
	
	public MantenimientoDetEmpresaEmpleadoSearchAction() {
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoDetEmpresaEmpleadoSearchAction'");
		}
		detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
		grupoEmplList = new ArrayList<EmpleadoDTO>();
		idGrupo=0;
		idGrupo =0;
		idempleado=0;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() ' x grupo' " + idGrupo);
		mensaje =null;
		grupoVentaList= new ArrayList<GrupoEmpleadoDTO>();
		grupoEmplList = new ArrayList<EmpleadoDTO>();
		//Se debe listar los empleados con cargo de expositor
		detGrupoEmplList = detgrupoemplService.listarDetGrupoEmpleado();
		if (detGrupoEmplList == null) {
			detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
		}
		for (int i = 0; i < detGrupoEmplList.size(); i++) {
			EmpleadoDTO g = new EmpleadoDTO();
			g.setTbempleado(detGrupoEmplList.get(i).getTbempleado());
			CargoEmpleadoSie c= objCargoService.buscarCargoEmpleado("EXPOSITOR");
			if(c==null){
				setMensaje("el cargo de expositor no se encuentra en la base de datos, verifiquelo");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				break;
			}
			int cargo =c.getIdcargoempleado();
			g.setFacturada(contratoService.findcantContratoFacturadoEntregado(detGrupoEmplList.get(i).getTbempleado().getIdempleado(),cargo));
			//g.setEntregada(entregada);
			if(g.getEntregada()!=null)
			g.setTotalentregada(g.getEntregada());
			if(g.getFacturada()!=null)
			g.setTotalfacturada(g.getFacturada()*2);
			grupoEmplList.add(g);
		}
		lstMenu = new ArrayList<MenuDTO>();
		grupoVentasieList = grupoventaService.listarGrupoVenta();
		for (int i = 0; i < grupoVentasieList.size(); i++) {
			GrupoEmpleadoDTO g = new GrupoEmpleadoDTO();
			g.setTbGrupoVenta(grupoVentasieList.get(i));
			grupoVentaList.add(g);
			MenuDTO e = new MenuDTO();
			e.setNombreMenu(grupoVentaList.get(i).getTbGrupoVenta().getDescripcion());
			log.info("grupo:  "+grupoVentaList.get(i).getTbGrupoVenta().getDescripcion());
			lstMenu.add(e);
		}
		log.info("tamañito  "+lstMenu.size()); 
		return getViewList();
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
	
}
