package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "movimientoMercaderia")
@SessionScoped
public class MovimientoSieAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private int idproducto, idtipokardexproducto, idAlmacen, idAlmacenEntrada=0;
	private KardexSie objKardexSie = new KardexSie();
	private List<SelectItem> tipoKardexItems;
	private List<KardexSie> data;
	
	public Map<String, Object> lasession;
	private boolean editMode;
	private Log log = LogFactory.getLog(MovimientoSieAction.class);

	@EJB
	private TipoKardexService objTipoKardexService;

	@EJB
	private KardexService objKardexService;

	public MovimientoSieAction() {
		log.info("inicializando constructor MovimientoSieAction");
		init();
	}

	/*public void cambiar() {
		log.info("idTipoKardex " + idtipokardexproducto);
		if (idtipokardexproducto == 1 || idtipokardexproducto == 2)
			editMode = true;
		else
			editMode = false;
		log.info("Bolean  " + editMode);

	}*/

	public void init() {
		log.info("init()");
		data = new ArrayList<KardexSie>();
		objKardexSie = new KardexSie();
		tipoKardexItems = new ArrayList<SelectItem>();
	}

	public void cambioTipo(ValueChangeEvent evento) {
		log.info("TipoKardex " + evento.getNewValue().toString());
		if ("1".equalsIgnoreCase(evento.getNewValue().toString()))
			editMode = true;
		else if ("2".equalsIgnoreCase(evento.getNewValue().toString()))
			editMode = false;
		else
			editMode = true;
	}

	/**
	 * @return the tipoKardexItems
	 */
	public List<SelectItem> getTipoKardexItems() {

		tipoKardexItems = new ArrayList<SelectItem>();
		List lista = new ArrayList<TipoProductoSie>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'getTipoKardexItems()'");
			}
			lista = objTipoKardexService.listaTipoKardex();

			for (int i = 0; i < lista.size(); i++) {
				TipoKardexProductoSie tipo = new TipoKardexProductoSie();
				tipo = (TipoKardexProductoSie) lista.get(i);
				tipoKardexItems.add(new SelectItem(tipo
						.getIdtipokardexproducto(), tipo.getDescripcion()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return tipoKardexItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	
	public String insertar() throws Exception {
		boolean validado=false;
		try {

			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' :D  "+ objKardexSie.getCantentrada()+objKardexSie.getCantsalida());
			}
			if(!(idAlmacenEntrada>0)){
				log.info("   nu  " +idAlmacenEntrada );
				idAlmacenEntrada =0;
			}
			if (objKardexSie.getCantentrada() == null){
				log.info("   nul entr");
				objKardexSie.setCantentrada(0);
			}if (objKardexSie.getCantsalida() == null){
				log.info("   nul sal");
				objKardexSie.setCantsalida(0);

			} if (objKardexSie.getCantentrada() == 0
					&& objKardexSie.getCantsalida() == 0) {
				mensaje = "Debe ingresar cantidad";
			}
			 if (objKardexSie.getCantentrada() >0
					&& objKardexSie.getCantsalida() >0) {
				log.info("  >0");
				mensaje = "Debe ingresar solol cantidad de entrada o salida";
			}
			 if (objKardexSie.getCantentrada() == null
					&& objKardexSie.getCantsalida() == null) {
				mensaje = "Debe ingresar cantidad de entrada o salida";
			}else {
				log.info("   antes de la consulta");
				KardexSie k =  objKardexService.ConsultaStockActual(idproducto);
				int stkmaximo,stkminimo,cantexistencia;
				
				if(k!=null){
					
				log.info("   despues de la consulta");
				 
					stkmaximo= k.getTbProducto().getStkmaximo();
					stkminimo=  k.getTbProducto().getStkminimoproducto();
					cantexistencia= k.getCantexistencia();
					
					if(objKardexSie.getCantsalida()>0){
						if(cantexistencia-objKardexSie.getCantsalida()<stkminimo){
							mensaje=" No puede excederse del stock minimo, el cual es " + stkminimo ;
						}
						else if(cantexistencia-objKardexSie.getCantsalida()<0){
							mensaje=" No puede exceder la cantidad de salida al stock actual del producto, el cual es " + cantexistencia ;
						}
					}
					else if(objKardexSie.getCantentrada()>0){
						if(cantexistencia+objKardexSie.getCantentrada()>stkmaximo){
							mensaje=" No puede excederse del stock máximo, el cual es " + stkmaximo ;
						}
						// validar la cantidad existente por almacen no quede menor que 0
						//que la cantidad existente en un almacen - la cantidad de salida de dicho almacen sea menor q 0
						//total 100 min 30 y kito 50 mientras en dicho almacen solo tenia 40  = -10
						
						
					}else{
						validado=true;
					}
					log.info(  " **  "  + cantexistencia +" "+ stkminimo+" "+ stkmaximo +" " +validado);
				}else
				{
					log.info(" * null *");
					k= new KardexSie();
					validado=true;
				}
				if(validado==true ){
					log.info(" *************** INSERTAR *********");
					objKardexService.insertMovimiento(objKardexSie.getCantsalida(),
							objKardexSie.getCantentrada(), objKardexSie.getDetallekardex(), idproducto,
							idtipokardexproducto, idAlmacen, idAlmacenEntrada);

					mensaje = "Se registro";
				}
				log.info(" *valor *" + validado);
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
		objKardexSie = new KardexSie();
		return getViewList();
	}

	/**
	 * @return the idtipokardexproducto
	 */
	public int getIdtipokardexproducto() {
		
		return idtipokardexproducto;
	}
	 public void cambiar() {  
		log.info("idTipoKardex " + idtipokardexproducto );
		lasession=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		lasession.put("tipoKardex", idtipokardexproducto);
		log.info("tipo :D  --- "   + idtipokardexproducto );  
		if(idtipokardexproducto ==1||idtipokardexproducto ==3){
			editMode=false;
		}else
			editMode=true;
		log.info("EDitmode  :D  --- "   +idtipokardexproducto+"   "+ editMode );  
	 }
	 
	/**
	 * @param idtipokardexproducto
	 *            the idtipokardexproducto to set
	 */
	public void setIdtipokardexproducto(int idtipokardexproducto) {
		
		this.idtipokardexproducto = idtipokardexproducto;
		
	}

	/**
	 * @param tipoKardexItems
	 *            the tipoKardexItems to set
	 */
	public void setTipoKardexItems(List<SelectItem> tipoKardexItems) {
		this.tipoKardexItems = tipoKardexItems;
	}

	/**
	 * @return the data
	 */
	public List<KardexSie> getData() {
		
		
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<KardexSie> data) {
		this.data = data;
	}

	/**
	 * @return the idAlmacen
	 */
	public int getIdAlmacen() {
		return idAlmacen;
	}

	/**
	 * @param idAlmacen
	 *            the idAlmacen to set
	 */
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	/**
	 * @return the idproducto
	 */
	public int getIdproducto() {
		return idproducto;
	}

	/**
	 * @param idproducto
	 *            the idproducto to set
	 */
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	/**
	 * @return the objKardexSie
	 */
	public KardexSie getObjKardexSie() {
		return objKardexSie;
	}

	/**
	 * @param objKardexSie
	 *            the objKardexSie to set
	 */
	public void setObjKardexSie(KardexSie objKardexSie) {
		this.objKardexSie = objKardexSie;
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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	
	public String listar() {
		log.info("listarProductos 'MantenimientoProductoSearchAction' ");
		data = objKardexService.ConsultaKardexDiario();
		if (data == null) {
			data = new ArrayList<KardexSie>();
		}
		return getViewList();
	}

	/**
	 * @return the idAlmacenEntrada
	 */
	public int getIdAlmacenEntrada() {
		return idAlmacenEntrada;
	}

	/**
	 * @param idAlmacenEntrada the idAlmacenEntrada to set
	 */
	public void setIdAlmacenEntrada(int idAlmacenEntrada) {
		this.idAlmacenEntrada = idAlmacenEntrada;
	}
	
}
