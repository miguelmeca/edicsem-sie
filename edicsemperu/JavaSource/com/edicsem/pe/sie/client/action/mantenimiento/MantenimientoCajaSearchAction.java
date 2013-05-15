package com.edicsem.pe.sie.client.action.mantenimiento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CajaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.CajaService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="cajaSearch")
@SessionScoped
public class MantenimientoCajaSearchAction extends BaseMantenimientoAbstractAction{
	
	private Log log = LogFactory.getLog(MantenimientoCajaSearchAction.class);
	private List<CajaSie> cajaList;
	private List<CajaSie> cajaActualList;
	HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
	private BigDecimal saldoTotal;
	
	@EJB
	private CajaService objCajaService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	
	public MantenimientoCajaSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando 'MantenimientoCajaSearchAction'");
		}
		cajaActualList= new ArrayList<CajaSie>();
	
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() 'MantenimientoCajaSearchAction' ");
		cajaList = objCajaService.listarCajaPorEmpleado(sessionUsuario.getIdempleado());
		if (cajaList == null) {
			cajaList = new ArrayList<CajaSie>();
		}
		for (int i = 0; i < cajaList.size(); i++) {
			cajaList.get(i).setEmpleadocreacion(objEmpleadoService.buscarEmpleadosPorUsuario(cajaList.get(i).getUsuariocreacion()).getNombresCompletos());
			cajaList.set(i, cajaList.get(i));
		}
		if(cajaList.size()>=1){
			setSaldoTotal(cajaList.get(cajaList.size()-1).getSaldo());
		}
		return getViewList();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("consultar()");
		cajaActualList= new ArrayList<CajaSie>();
		cajaList = objCajaService.listarCaja();
		List<Integer> empleados= new ArrayList<Integer>(); 
		if (cajaList == null) {
			cajaList = new ArrayList<CajaSie>();
		}
		for (int i = 0; i < cajaList.size(); i++) {
			//Ultima entrada
			if(cajaList.get(i).getIdtipo()==1 && (!empleados.contains(cajaList.get(i).getTbEmpleado().getIdempleado()))){
				CajaSie obj= new CajaSie();
				obj.setDescripcion(cajaList.get(i).getDescripcion());
				obj.setTbEmpleado(cajaList.get(i).getTbEmpleado());
				obj.setFechapago(cajaList.get(i).getFechapago());
				obj.setSaldo(cajaList.get(i).getSaldo());
				obj.setIdtipo(cajaList.get(i).getIdtipo());
				obj.setEmpleadocreacion(objEmpleadoService.buscarEmpleadosPorUsuario(cajaList.get(i).getUsuariocreacion()).getNombresCompletos());
				cajaActualList.add(obj);
				empleados.add(cajaList.get(i).getTbEmpleado().getIdempleado());
			}
		}if(cajaList.size()>=1){
			setSaldoTotal(cajaList.get(cajaList.size()-1).getSaldo());
		}
		
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
	 */
	public String getViewList() {
		return Constants.MANT_CAJA_FORM_LIST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MOV_CAJA_FORM_LIST_PAGE;
	}

	/**
	 * @return the cajaList
	 */
	public List<CajaSie> getCajaList() {
		return cajaList;
	}

	/**
	 * @param cajaList the cajaList to set
	 */
	public void setCajaList(List<CajaSie> cajaList) {
		this.cajaList = cajaList;
	}

	public List<CajaSie> getCajaActualList() {
		return cajaActualList;
	}

	public void setCajaActualList(List<CajaSie> cajaActualList) {
		this.cajaActualList = cajaActualList;
	}

	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	
}
