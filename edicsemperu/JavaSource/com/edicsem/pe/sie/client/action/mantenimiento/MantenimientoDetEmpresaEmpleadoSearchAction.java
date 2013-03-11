package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.edicsem.pe.sie.beans.GrupoEmpleadoDTO;
import com.edicsem.pe.sie.beans.MenuDTO;
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
	private int idGrupo, idempleado;
	private String grupoEscogido;
	@EJB
	private DetGrupoEmpleadoService detgrupoemplService;
	@EJB
	private GrupoVentaService grupoventaService;
	private MenuModel mimenu;
	
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
		setMimenu(new DefaultMenuModel());
		idGrupo=0;
		idGrupo =0;
		idempleado=0;
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
		setMimenu(new DefaultMenuModel());
		MenuItem item = new MenuItem();
		List<MenuDTO> lstMenu = new ArrayList<MenuDTO>();
		grupoVentasieList = grupoventaService.listarGrupoVenta();
		for (int i = 0; i < grupoVentasieList.size(); i++) {
			GrupoEmpleadoDTO g = new GrupoEmpleadoDTO();
			g.setTbGrupoVenta(grupoVentasieList.get(i));
			grupoVentaList.add(g);
			MenuDTO e = new MenuDTO();
			e.setNombreMenu(grupoVentaList.get(i).getTbGrupoVenta().getDescripcion());
			e.setNombreActionListener("#{grupo.update}");
			log.info("grupo:  "+grupoVentaList.get(i).getTbGrupoVenta().getDescripcion());
			lstMenu.add(e);
		}
		log.info("tamañito  "+lstMenu.size());
		for (MenuDTO a : lstMenu) {
			item = new MenuItem();
			item.setValue(a.getNombreMenu());
			item.setAjax(false);
			
			if (a.getNombreActionListener() != null && a.getNombreActionListener().isEmpty() == false) {
				log.info("cambio "+a.getNombreActionListener());
				item.setActionExpression(getMethod(a.getNombreActionListener()));
				log.info("sss "+item.getActionExpression());
				item.setUrl(null);
			}
			mimenu.addMenuItem(item);
			log.info("nomb menu: "+a.getNombreMenu()+" url "+a.getUrlMenu()+" action  "+a.getNombreActionListener());
		}
		return getViewList();
	}
	
	public MethodExpression getMethod(String actionListenerName) {
		ExpressionFactory context = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		MethodExpression mExp = context.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), actionListenerName, String.class, new Class[]{});
		return mExp;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
	 */
	public String update(){
		log.info("update!!! ");
		log.info("empleado "+idempleado);
		if(idempleado!=0){
        	log.info("!= null  "+idempleado);
        	List<GrupoEmpleadoDTO> listaNueva = new ArrayList<GrupoEmpleadoDTO>();
        	for (int i = 0; i < grupoEmplList.size(); i++) {
				if(grupoEmplList.get(i).getTbempleado().getIdempleado()==idempleado){
					log.info("encontrado ");
					listaNueva.add(grupoEmplList.get(i));
		        	grupoVentaList.get(0).setDetalle(listaNueva);
		        	grupoEmplList.remove(i);
		        	break;
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
	public List<GrupoEmpleadoDTO> getGrupoEmplList() {
		return grupoEmplList;
	}

	/**
	 * @param grupoEmplList the grupoEmplList to set
	 */
	public void setGrupoEmplList(List<GrupoEmpleadoDTO> grupoEmplList) {
		this.grupoEmplList = grupoEmplList;
	}

	/**
	 * @return the mimenu
	 */
	public MenuModel getMimenu() {
		return mimenu;
	}

	/**
	 * @param mimenu the mimenu to set
	 */
	public void setMimenu(MenuModel mimenu) {
		this.mimenu = mimenu;
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
	
}
