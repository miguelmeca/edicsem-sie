package com.edicsem.pe.sie.client.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "PagoVenta")
@SessionScoped
public class PagoVentaAction  extends BaseMantenimientoAbstractAction {
	private String mensaje;
	private boolean editMode;
	private int idFactor, idEmpleado, idSancion;
	private Log log = LogFactory.getLog(MovimientoAction.class);
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	public PagoVentaAction() {
		log.info("inicializando constructor PagoVentaAction");
		init();
	}

	public void init() {
		log.info("init()");
		idFactor=0;
		comboManager= new ComboAction();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		log.info("idfactor  " +idFactor );
		comboManager.setIdCargo(2);
		comboManager.setIdFactor(idFactor);
		return getViewList();
	}
	
	/**
	* @return the editMode
	*/
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	* @return the mensaje
	*/
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
		 * @return the idFactor
		 */
		public int getIdFactor() {
			log.info("get   ");
			return idFactor;
		}

		/**
		 * @param idFactor the idFactor to set
		 */
		public void setIdFactor(int idFactor) {
			log.info("idfactor  " +idFactor );
			comboManager.setIdFactor(idFactor);
			this.idFactor = idFactor;
		}

		/**
		 * @return the idEmpleado
		 */
		public int getIdEmpleado() {
			return idEmpleado;
		}

		/**
		 * @param idEmpleado the idEmpleado to set
		 */
		public void setIdEmpleado(int idEmpleado) {
			this.idEmpleado = idEmpleado;
		}

		/**
		 * @return the idSancion
		 */
		public int getIdSancion() {
			return idSancion;
		}

		/**
		 * @param idSancion the idSancion to set
		 */
		public void setIdSancion(int idSancion) {
			this.idSancion = idSancion;
		}

		/* (non-Javadoc)
		 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewList()
		 */
		public String getViewList() {
			return Constants.MANT_PAGO_VENDEDOR_FORM_PAGE;
		}

		/* (non-Javadoc)
		 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
		 */
		public String insertar() throws Exception {
			// TODO Auto-generated method stub
			return super.insertar();
		}

		/* (non-Javadoc)
		 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#update()
		 */
		public String update() throws Exception {
			return super.update();
		}

		/* (non-Javadoc)
		 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
		 */
		public String listar() {
			return super.listar();
		}

}