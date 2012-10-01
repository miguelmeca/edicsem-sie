package com.edicsem.pe.sie.client.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.service.facade.EmpresaService;
import com.edicsem.pe.sie.service.facade.KardexService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoKardexService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "movimientoMercaderia")
@SessionScoped
public class MovimientoSieAction extends BaseMantenimientoAbstractAction {

	private String mensaje;
	private int idproducto, idtipokardexproducto,idempresa, idAlmacen, idAlmacen2 = 0;
	private KardexSie objKardexSie = new KardexSie();
	private List<SelectItem> tipoKardexItems;
	private List<KardexSie> data;
	private boolean editMode;
	private Log log = LogFactory.getLog(MovimientoSieAction.class);

	@EJB
	private TipoKardexService objTipoKardexService;

	@EJB
	private KardexService objKardexService;
	
	@EJB
	private ProductoService objproductoService;
	
	@EJB
	private EmpresaService objEmpresaService;

	public MovimientoSieAction() {
		log.info("inicializando constructor MovimientoSieAction");
		init();
	}

	public void init() {
		log.info("init()");
		data = new ArrayList<KardexSie>();
		objKardexSie = new KardexSie();
		tipoKardexItems = new ArrayList<SelectItem>();
	}

	public void cambiar() {
 
		if (idtipokardexproducto == 1 || idtipokardexproducto == 3) {
			editMode = false;
		} else
			editMode = true;
		log.info("EDitmode  :D  --- " + idtipokardexproducto + "   " + editMode);
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
		boolean validado = false;
		mensaje=null;
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' :D  "
						+ objKardexSie.getCantentrada()
						+ objKardexSie.getCantsalida()+"  "+ idAlmacen+" - " + idAlmacen2);
			}
			if (objKardexSie.getCantentrada() == null) {
				objKardexSie.setCantentrada(0);
			}
			if (objKardexSie.getCantsalida() == null) {
				objKardexSie.setCantsalida(0);
			}
			if(idempresa!=0){
				objKardexSie.setTbEmpresa(objEmpresaService.findEmpresa(idempresa));
			}
			if(idAlmacen == idAlmacen2){
					mensaje = "No se puede realizar un movimiento de entrada y salida del mismo almacen";
			}
			else{
				log.info("   antes de la consulta");
				List<KardexSie> k = objKardexService.ConsultaStockActual(idproducto);

				if (k != null && k.size()!=0) {

					int cantExistenteTotalAlmacenes = 0, stkmaximo = 0, stkminimo = 0;

					log.info("   despues de la consulta");
					for (int i = 0; i < k.size(); i++) {
						cantExistenteTotalAlmacenes += k.get(i).getCantexistencia();
						stkmaximo = k.get(i).getTbProducto().getStkmaximo();
						stkminimo = k.get(i).getTbProducto().getStkminimoproducto();
					}
					log.info(" **  " + cantExistenteTotalAlmacenes + " " + stkminimo + " " + stkmaximo + " " + validado);
					 
					if (objKardexSie.getCantsalida() > 0) {
						log.info(" cantexist "+ cantExistenteTotalAlmacenes +" cant Sal "+objKardexSie.getCantsalida() +" stk min " +stkminimo+ " almacen 2 " + idAlmacen2);
						if (cantExistenteTotalAlmacenes - objKardexSie.getCantsalida() < stkminimo && idAlmacen2==0 ){
							mensaje = " No puede excederse del stock minimo, el cual es " + stkminimo;
							log.info(mensaje);
						}else if (cantExistenteTotalAlmacenes - objKardexSie.getCantsalida() < 0) {
							mensaje = " No puede exceder la cantidad de salida al stock actual del producto, el cual es " + cantExistenteTotalAlmacenes;
							log.info(mensaje);
						}else {
							log.info("en el for 1 ");
							for (int j = 0; j< k.size(); j++) {
								log.info("en el for 1 2");
							if (objKardexSie.getCantsalida() > 0) {
								if (k.get(j).getCantexistencia()- objKardexSie.getCantsalida() < 0) {
									mensaje = "La cantidad existente del producto es "
											+ k.get(j).getCantexistencia();
								}
								else if(idAlmacen2!=0 ){
									for (int i = 0; i < k.size(); i++) {
										//la cantidad existente en un almacen no puede resultar menor que 0
										if( k.get(i).getTbPuntoVenta().getIdpuntoventa() == idAlmacen ){
											
											if( k.get(i).getCantexistencia()- objKardexSie.getCantsalida()<0){
												mensaje = "La cantidad de salida de dicho producto no puede ser mayor al actual: " + k.get(i).getCantexistencia();
											}else{
												validado=true;
											}
										}
									}
								}else{
									validado=true;
								}
							}
						}
					}
						 
					} else if (objKardexSie.getCantentrada() > 0) {
						if (cantExistenteTotalAlmacenes + objKardexSie.getCantentrada() > stkmaximo && idAlmacen2==0 ) {
							mensaje = " No puede excederse del stock máximo, el cual es "+ stkmaximo;
						}
						else{
							for (int j = 0; j< k.size(); j++) {
								log.info("en el for de entrada "+k.get(j).getTbPuntoVenta().getIdpuntoventa()+ " * "+  idAlmacen);
								 if (k.get(j).getTbPuntoVenta().getIdpuntoventa() == idAlmacen) {
									 log.info("almacen   " +k.get(j).getTbPuntoVenta().getIdpuntoventa() +"  "+ idAlmacen );
									// es entrada pero no salida de otro almacen
									if (objKardexSie.getCantentrada() > 0 && idAlmacen2==0  ) {
										log.info(" si solo hay entrada " +objKardexSie.getCantentrada() +" "+ idAlmacen2);
										
										if (k.get(j).getCantexistencia() + objKardexSie.getCantentrada() > stkmaximo) {
											log.info("existe "+k.get(j).getCantexistencia()+" "+ objKardexSie.getCantsalida()+" max "+ stkmaximo ); 
											mensaje = "No debe excederse el stock máximo permitido";
										}else{
											validado=true;
										}
										
										// es entrada y salida de otro almacen 
										// se analiza la cantidad existente en dicho almacen
									}else if(objKardexSie.getCantentrada() > 0 && idAlmacen2!=0  ){
										log.info(" entrada y salida " +objKardexSie.getCantentrada() +" "+ idAlmacen2);
										
										for (int i = 0; i < k.size(); i++) {
											//la cantidad existente en un almacen no puede resultar menor que 0
											if( k.get(i).getTbPuntoVenta().getIdpuntoventa() == idAlmacen2 ){
												//getCantidadEntrada() vendria a ser la salida del almacen2
												if( k.get(i).getCantexistencia()- objKardexSie.getCantentrada()<0){
													mensaje = "La cantidad de salida de dicho producto no puede ser mayor al actual: " + k.get(i).getCantexistencia() ;
												}else{
													validado=true;
												}
											}
										}
										
									}else{
										validado=true;
									}
								}
							}
						}
						
					} else {
						log.info(" es true! :D");
						validado = true;
					}
					log.info(" *2*  " + cantExistenteTotalAlmacenes + " "
							+ stkminimo + " " + stkmaximo + " " + validado);
					log.info(mensaje);
				}
				//Falta verificar la cantidad que va ingresar con el stock maximo permitido para dicho producto
				//ya que es la primera vez que se ingresa algun movimiento para dicho producto
				else if(k.size()==0){
					
					if (objKardexSie.getCantsalida() > 0) {
						mensaje="Dicho producto no tiene un stock actual ";
					}else if(objKardexSie.getCantentrada() > 0){
						ProductoSie prod= 	objproductoService.findProducto(idproducto);
						if(objKardexSie.getCantentrada() >  prod.getStkmaximo() ){
							mensaje="El stock máximo permitido es " + prod.getStkmaximo() ;
						}
						else{
							validado = true;
						}
					}
				
					log.info(mensaje);
				}
				else {
					log.info(" * null *");
					k = new ArrayList<KardexSie>();
					validado = true;
					log.info(mensaje);
				}
				}
				if (validado == true) {
					log.info(mensaje);
					log.info(" *************** INSERTAR *********");
					objKardexService.insertMovimiento(
							objKardexSie.getCantsalida(),
							objKardexSie.getCantentrada(),
							objKardexSie.getDetallekardex(), idproducto,
							idtipokardexproducto, idAlmacen, idAlmacen2);

					mensaje = "Se registro";
					
				}
				if(mensaje !=null){
				log.info(" *valor *" + validado + " mensaje :" + mensaje);
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
		//return getViewList();
		return listar();
	}

	/**
	 * @return the idtipokardexproducto
	 */
	public int getIdtipokardexproducto() {
		return idtipokardexproducto;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #listar()
	 */
	public String listar() {
		log.info("listar movimientos diarios  'MovimientoSieAction' ");
		data = objKardexService.ConsultaKardexDiario();
		if (data == null) {
			data = new ArrayList<KardexSie>();
		}
		return getViewList();
	}

	/**
	 * @return the idAlmacen2
	 */
	public int getIdAlmacen2() {
		return idAlmacen2;
	}

	/**
	 * @param idAlmacen2
	 *            the idAlmacen2 to set
	 */
	public void setIdAlmacen2(int idAlmacen2) {
		this.idAlmacen2 = idAlmacen2;
	}

	/**
	 * @return the idempresa
	 */
	public int getIdempresa() {
		return idempresa;
	}

	/**
	 * @param idempresa the idempresa to set
	 */
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}

}
