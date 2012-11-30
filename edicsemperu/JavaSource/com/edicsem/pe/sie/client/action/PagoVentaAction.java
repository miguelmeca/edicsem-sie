package com.edicsem.pe.sie.client.action;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "PagoVenta")
@SessionScoped
public class PagoVentaAction  extends BaseMantenimientoAbstractAction {
		private String mensaje;
		
		private boolean editMode;
		private Log log = LogFactory.getLog(MovimientoAction.class);

		@EJB
		private KardexService objKardexService;
		
		public PagoVentaAction() {
			log.info("inicializando constructor PagoVentaAction");
			init();
		}

		public void init() {
			log.info("init()");
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
}