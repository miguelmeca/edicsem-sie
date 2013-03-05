package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.DragDropEvent;

import com.edicsem.pe.sie.beans.GrupoEmpleadoDTO;
import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService;
import com.edicsem.pe.sie.service.facade.GrupoVentaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="grupo")
@SessionScoped
public class MantenimientoDetEmpresaEmpleadoSearchAction extends BaseMantenimientoAbstractAction{

	private Log log = LogFactory.getLog(MantenimientoDetEmpresaEmpleadoSearchAction.class);
	private List<DetGrupoEmpleadoSie> detGrupoEmplList;
	private List<GrupoEmpleadoDTO>  grupoEmplList;
	private List<GrupoVentaSie> grupoVentasieList;
	private List<GrupoEmpleadoDTO> grupoVentaList;
	private int idGrupo;
	@EJB
	private DetGrupoEmpleadoService detgrupoemplService;
	@EJB
	private GrupoVentaService grupoventaService;
	
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
		grupoEmplList = new ArrayList<GrupoEmpleadoDTO>();
		idGrupo=0;
		idGrupo =0;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() ' x grupo' " + idGrupo);
		grupoVentaList= new ArrayList<GrupoEmpleadoDTO>();
		grupoEmplList = new ArrayList<GrupoEmpleadoDTO>();
		detGrupoEmplList = detgrupoemplService.listarDetGrupoEmpleado();
		if (detGrupoEmplList == null) {
			detGrupoEmplList = new ArrayList<DetGrupoEmpleadoSie>();
		}
		for (int i = 0; i < detGrupoEmplList.size(); i++) {
			GrupoEmpleadoDTO g = new GrupoEmpleadoDTO();
			g.setTbGrupoVenta(detGrupoEmplList.get(i).getTbGrupoVenta());
			g.setTbempleado(detGrupoEmplList.get(i).getTbempleado());
			grupoEmplList.add(g);
		}
		
		grupoVentasieList = grupoventaService.listarGrupoVenta();
		for (int i = 0; i < grupoVentasieList.size(); i++) {
			GrupoEmpleadoDTO g = new GrupoEmpleadoDTO();
			g.setTbGrupoVenta(grupoVentasieList.get(i));
			grupoVentaList.add(g);
		}
		return getViewList();
	}
	
	/**
	 * @param ddEvent
	 */
	public void onDrop(DragDropEvent ddEvent){
		log.info(" onDrop() "+ddEvent.getDropId()+" "+ddEvent.getDragId());
        GrupoEmpleadoDTO d = ((GrupoEmpleadoDTO) ddEvent.getData());
        if(d!=null)
        {
        	log.info("!= null");
        	if(grupoVentaList.get(0).getDetalle()==null){
        		log.info("detalle  = null");
        		List<GrupoEmpleadoDTO> listaNueva = new ArrayList<GrupoEmpleadoDTO>();
        		listaNueva.add(d);
        		grupoVentaList.get(0).setDetalle(listaNueva);
        	}else
        	{
        		log.info("detalle  es diferente");
        		grupoVentaList.get(0).getDetalle().add(d);
        	}
        }
        grupoEmplList.remove(d);
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
	public List<GrupoEmpleadoDTO> getGrupoEmplList() {
		return grupoEmplList;
	}

	/**
	 * @param grupoEmplList the grupoEmplList to set
	 */
	public void setGrupoEmplList(List<GrupoEmpleadoDTO> grupoEmplList) {
		this.grupoEmplList = grupoEmplList;
	}
	
}
