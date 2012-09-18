package com.edicsem.pe.sie.client.action.mantenimiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;
import com.edicsem.pe.sie.util.redirections.Redirections;

@ManagedBean(name="empresaSearch")
@SessionScoped
public class MantenimientoEmpresaSearchAction extends BaseMantenimientoAbstractAction{

	private EmpresaSie empresaSie;
	private Log log = LogFactory.getLog(MantenimientoEmpresaSearchAction.class);
	private List<EmpresaSie> empresaList;
	
	@EJB
	private EmpresaService empresaService;
	
	public MantenimientoEmpresaSearchAction() {
		init();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		if (log.isInfoEnabled()) {
			log.info("Inicializando los atributos 'MantenimientoEmpresaSearchAction'");
		}
		empresaSie = new EmpresaSie();
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public void listarEmpresas() {
		log.info("listarEmpresas 'MantenimientoEmpresaSearchAction' ");
		empresaList = empresaService.listarEmpresas();
		if (empresaList == null) {
			empresaList = new ArrayList<EmpresaSie>();
		}
		Redirections.redirectionsPage("mantenimiento", "mantenimientoEmpresaFormList");
	}
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public void consultar() throws Exception {
		Map<String, String> paramMap = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		
		/*<p:commandButton title="Actualizar"
				actionListener="#{manUsuarioBM.actionListener}"
				onclick="confirmationUsuario.show()">
				<f:param name="codUsuarioE" value="#{d.idUsuario}"
					id="codUsuarioE" />
			</p:commandButton>*/
		/**
		 * Esto es una columna de un datatable en donde se envia el codigo del usuario
		 * url: https://sie-pac.googlecode.com/svn/trunk/ahoraSoft/WebContent/manUsuarios.xhtml
		 * **/
		// donde codUsuarioE es tu ID
		int strCodigoParametro = Integer.parseInt(paramMap.get("codUsuarioE"));
		empresaSie = empresaService.findProducto(strCodigoParametro);
	}

	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#delete()
	 */
	public void delete() throws Exception {
		
		Map<String, String> paramMap = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		
		/*<p:commandButton title="Eliminar"
				actionListener="#{manUsuarioBM.actionListener}"
				onclick="confirmationUsuario.show()">
				<f:param name="codUsuarioE" value="#{d.idUsuario}"
					id="codUsuarioE" />
			</p:commandButton>*/
		/**
		 * Esto es una columna de un datatable en donde se envia el codigo del usuario
		 * url: https://sie-pac.googlecode.com/svn/trunk/ahoraSoft/WebContent/manUsuarios.xhtml
		 * **/
		// donde codUsuarioE es tu ID
		int strCodigoParametro = Integer.parseInt(paramMap.get("codUsuarioE"));
		
		//empresaService.eliminarEmpresa(strCodigoParametro);
	}

	//Declaracion Getter and Setters
	/**
	 * @return the empresaSie
	 */
	public EmpresaSie getEmpresaSie() {
		return empresaSie;
	}

	/**
	 * @param empresaSie the empresaSie to set
	 */
	public void setEmpresaSie(EmpresaSie empresaSie) {
		this.empresaSie = empresaSie;
	}

	/**
	 * @return the empresaList
	 */
	public List<EmpresaSie> getEmpresaList() {
		return empresaList;
	}

	/**
	 * @param empresaList the empresaList to set
	 */
	public void setEmpresaList(List<EmpresaSie> empresaList) {
		this.empresaList = empresaList;
	}

}
