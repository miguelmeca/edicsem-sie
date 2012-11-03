package com.edicsem.pe.sie.client.action.mantenimiento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "contratoForm")
@SessionScoped
public class MantenimientoContratoFormAction extends
		BaseMantenimientoAbstractAction {

	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoContratoFormAction.class);
	private int Tipocasa,idtipodoc,idUbigeo, idempresa,tipoVenta,tipopago, idpaquete, idProducto, idempleadoExpositor, idempleadoVendedor, idempleadoColaborador;
	private String idProvincia, idDepartamento,  ubigeoDefecto,selectTelef;
	private ProductoSie objProductoSie;
	private ClienteSie objClienteSie;
	private DomicilioPersonaSie objDomicilioSie;
	private ContratoSie objContratoSie;
	private ProductoSie selectedProducto;
	private CobranzaSie objCobranzaSie;
	private List<TelefonoPersonaSie> telefonoList;
	private List<CobranzaSie> cobranzaList;
	private List<DetPaqueteSie> detPaqueteList;
	private TelefonoPersonaSie nuevoTelef;
	private int TipoTelef, operadorTelefonico, idCobranza;
	private double precioProducto;
	private boolean defectoUbigeo,defectopaquete;
	private boolean newRecord = false;
	private PaqueteSie objPaquete;
	private DetPaqueteSie objDetPaquete;
	
	@EJB
	private ProductoService objProductoService;
	@EJB
	private DetallePaqueteService objDetPaqueteService;
	@EJB
	private PaqueteService objPaqueteService;

	@EJB
	private ContratoService objContratoService;
	
	public MantenimientoContratoFormAction() {
		log.info("inicializando constructor MantenimientoContrato");
		init();
	}
	
	public void init() {
		log.info("init()");
		objProductoSie = new ProductoSie();
		objDomicilioSie = new DomicilioPersonaSie();
		objClienteSie = new ClienteSie();
		objContratoSie = new ContratoSie();
		objCobranzaSie = new CobranzaSie();
		objPaquete = new PaqueteSie();
		objDetPaquete = new DetPaqueteSie();
		defectoUbigeo = true;
		defectopaquete= true;
		ubigeoDefecto = "";
		nuevoTelef = new TelefonoPersonaSie();
		nuevoTelef.setTipoTelef("");
		 selectTelef="";
		telefonoList = new ArrayList<TelefonoPersonaSie>();
		operadorTelefonico=1;
		idCobranza=0;
		TipoTelef=1;
		tipoVenta=1;
		idempleadoColaborador=0;
		idempleadoExpositor=0;
		idempleadoVendedor=0;
		cobranzaList= new ArrayList<CobranzaSie>() ;
		detPaqueteList = new ArrayList<DetPaqueteSie>() ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		selectTelef= "";
		log.info("agregar() 15");
		limpiarCampos();
		objProductoSie = new ProductoSie();
		setNewRecord(true);
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		comboManager.setIdCargo(2);
		log.info("agregar    :D  --- ");
		return getViewMant();
	}

	public void cambioUbigeoDefecto() {
		log.info(" defecto  " + defectoUbigeo);
		comboManager.setUbigeoDeparItems(null);
		comboManager.setUbigeoProvinItems(null);
		comboManager.setUbigeoDistriItems(null);
		if (defectoUbigeo) {
			comboManager.setIdDepartamento("15");
			comboManager.setIdProvincia("01");
			log.info(" defecto lima true 1");
			ubigeoDefecto = "";
		} else {
			comboManager.setIdDepartamento(null);
			comboManager.setIdProvincia(null);
			log.info(" cambio ubigeo   false  otro");
		}
	}

	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = 0;
		log.info("cambiar   :D  --- ");
	}

	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- ");
	}

	public void cambioUbica() {
		log.info("Ubigeo -------->" + idUbigeo + " " + ubigeoDefecto);
		String dist = "";
		Iterator it = comboManager.getUbigeoDistriItems().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo+"")) {
				log.info("xxx -" + e.getKey() + "- value -" + idUbigeo+"-");
				dist = (String) e.getKey();
				log.info("dist " + dist);
				break;
			}
		}
		ubigeoDefecto = "LIMA-LIMA-" + dist;
	}

	public String ingresarUbigeo() {
		// enviamos el nombre completo del depa- provincia-distrito

		log.info("ingresarUbigeo :D a --- " + idUbigeo);

		Iterator it = comboManager.getUbigeoDeparItems().entrySet().iterator();
		Iterator it2 = comboManager.getUbigeoProvinItems().entrySet().iterator();
		Iterator it3 = comboManager.getUbigeoDistriItems().entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idDepartamento)) {
				ubigeoDefecto = (String) e.getKey();
				log.info("ubigeo depa " + ubigeoDefecto);
				break;
			}
		}
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idProvincia)) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo prov " + ubigeoDefecto);
				break;
			}
		}
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo+"")) {
				ubigeoDefecto += "-" + (String) e.getKey();
				log.info("ubigeo distrito " + ubigeoDefecto);
				break;
			}
		}
		log.info("ubigeo ------> :D   " + ubigeoDefecto);
		
		return getViewMant();
	}
	
	/**
	 * Agregar Teléfono a la lista*/
	public void  telefonoAgregar(ActionEvent f){
		log.info("telefono agregar " );
		
		if( nuevoTelef.getTelefono()==null){
			mensaje= "Debe ingresar un número telefónico";
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
		log.info("telefono agregar " + nuevoTelef.getTelefono());
		boolean verifica= false;
		mensaje=null;
		if(TipoTelef==1)nuevoTelef.setTipotelefono("F");
		else
			nuevoTelef.setTipotelefono("C");
		//claro
		if(operadorTelefonico==1)
			nuevoTelef.setOperadorTelefonico("Claro");
		else if(operadorTelefonico==2)
			nuevoTelef.setOperadorTelefonico("Movistar");
		else if(operadorTelefonico==3)
			nuevoTelef.setOperadorTelefonico("Nextel");
		
		for (int i = 0; i < telefonoList.size(); i++) {
			log.info("  "+telefonoList.get(i).getTelefono() +" "+ nuevoTelef.getTelefono());
			if(telefonoList.get(i).getTelefono().equals(nuevoTelef.getTelefono())){
				 verifica= false;
				 mensaje = "el telefono ya se encuentra registrado en la lista de referencias";
				 break;
			}else{
				 verifica= true;
				
			}
		}if( telefonoList.size()==0){
			 verifica= true;
		}
		if( verifica){
			telefonoList.add(nuevoTelef);
			log.info("se agrego " + nuevoTelef.getTelefono());
		}
		if( mensaje!=null){
		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		nuevoTelef = new TelefonoPersonaSie();
		}
	}
	/**
	 * Eliminar Teléfono de la lista*/
	public void  telefonoElimina(){
		log.info("telefono telefonoElimina " + selectTelef);
		for (int i = 0; i < telefonoList.size(); i++) {
			log.info("t"+ telefonoList.get(i)+"-"+selectTelef);
			log.info("t"+ telefonoList.get(i).getTelefono()+"-"+selectTelef);
			if(telefonoList.get(i).getTelefono().equals(selectTelef)){
				telefonoList.remove(i);
				log.info("se elimino ");
			}
		}
	}
	
	public void cambiopaqueteDefecto(){
		log.info("en el metodod cambiopaqueteDefecto() " + defectopaquete );
	}
	
	public void cambioPaquete(){
		log.info("en el metodod cambioPaquete() ");
		detPaqueteList=new ArrayList<DetPaqueteSie>();
		
		List<DetPaqueteSie> detalle = objDetPaqueteService.listarDetPaquetes(getIdpaquete());
		if(detalle.size()>0){
			for (int i = 0; i < detalle.size(); i++) {
			log.info("tamaño li 22 ...."+ detalle.get(i).getCantidad());
			DetPaqueteSie detq =detalle.get(i);
			log.info("tamaño li 33 "+ detalle.get(i).getTbProducto().getDescripcionproducto());
			detq.setItem(i+1);
			detPaqueteList.add(detq);
			log.info("tamaño lista de paq "+ detPaqueteList.size());
		}
		log.info(" karenx d");
		setPrecioProducto(objPaqueteService.findPaquete(getIdpaquete()).getPrecioventa().doubleValue());
		log.info(" karenx precio "+objProductoSie.getPrecioventa());
		}
	}

	public void agregarProducto(ActionEvent f){
		mensaje=null;
		log.info("agregarProducto ");
		int cantidad= detPaqueteList.size();
		DetPaqueteSie det = new DetPaqueteSie();
		det.setTbProducto(objProductoService.findProducto(objProductoSie.getIdproducto()));
		det.setCantidad(objProductoSie.getCantidadContrato());
		det.setObservacion(objProductoSie.getObservacionContrato());
		if(cantidad==0){
			det.setItem(1);
			detPaqueteList.add(det);
			log.info("tamaño lista de paq "+ detPaqueteList.size());
		}else{
			for (int i = 0; i < detPaqueteList.size(); i++) {
				if(detPaqueteList.get(i).getTbProducto().getIdproducto()==det.getTbProducto().getIdproducto()){
					mensaje="Dicho producto ya se encuentra registrado en la lista ";
				}
			det.setItem(cantidad+1);
			}
			if(mensaje==null){
				detPaqueteList.add(det);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			log.info("tamaño lista de paq "+ detPaqueteList.size());
		}
	}
	  
    public void eliminarProducto(){
    	log.info("en eliminarProducto()");
		for (int i = 0; i < detPaqueteList.size(); i++) {
			if(detPaqueteList.get(i).getItem()==(idCobranza)){
				detPaqueteList.remove(i);
				for (int j = i; j < detPaqueteList.size(); j++) {
					log.info(" i " +i+"  j "+ j);
					i=i+1;
					detPaqueteList.get(j).setItem(i);
					detPaqueteList.set(j, detPaqueteList.get(j));
				}
			}
		}
		idCobranza=0;
    }
    
	public void limpiarDatosTelefono(){
		nuevoTelef = new TelefonoPersonaSie();
	}
	
	public void insertarCobranza(ActionEvent f) throws Exception{
		log.info(" insertarCobranza  :d "+objContratoSie.getNumcuotas()+" precio "+ getPrecioProducto()+"  fec ven  "+ objContratoSie.getFechacuotainicial());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		cobranzaList= new ArrayList<CobranzaSie>() ;
		BigDecimal d = new BigDecimal(getPrecioProducto() /objContratoSie.getNumcuotas().doubleValue());
		d=d.setScale(2, RoundingMode.HALF_UP);
		objContratoSie.setPagomensual(d);
		log.info(" d "+ d+"  Pagomensual "+objContratoSie.getPagomensual());
		
		if(objContratoSie.getPagosubinicial()!=new BigDecimal(0.0)){
			BigDecimal d2= new BigDecimal(d.doubleValue()-objContratoSie.getPagosubinicial().doubleValue());
			d2=d2.setScale(2, RoundingMode.HALF_UP);
			objContratoSie.setCuotainicial(d2);
		}
		Date fechaVencimiento = null ;
		sdf.format(objContratoSie.getFechacuotainicial());
		String fecha3 =sdf.format(objContratoSie.getFechacuotainicial());
		fechaVencimiento =sdf.parse(fecha3);
		for (int i = 1; i <= objContratoSie.getNumcuotas(); i++) {
			objCobranzaSie.setNumletra(i+"");
			if(i==1 && objContratoSie.getCuotainicial()!=new BigDecimal(0.0)){
				objCobranzaSie.setImpinicial(objContratoSie.getCuotainicial());
				fechaVencimiento=objContratoSie.getFechacuotainicial();
			}else{
				
			objCobranzaSie.setImpinicial(objContratoSie.getPagomensual());
			
			if(i==1){
				fechaVencimiento = DateUtil.addToDate(objContratoSie.getFechacuotainicial(), Calendar.MONTH, 1);
			}else{
				fechaVencimiento = DateUtil.addToDate(objContratoSie.getFechacuotainicial(), Calendar.MONTH, Integer.parseInt(objCobranzaSie.getNumletra())-1);
			}
			}
			log.info("fec venc  "+i +" "+ fechaVencimiento +" conv "+ DateUtil.formatoString(fechaVencimiento, "dd/MM/yyyy") +" Numcuotas "+objContratoSie.getNumcuotas()+" mensualito "+ objContratoSie.getPagomensual()
					+"  pagar  "+objCobranzaSie.getImpinicial() );
			objCobranzaSie.setFecvencimiento(fechaVencimiento);
			
			cobranzaList.add(objCobranzaSie);
			objCobranzaSie= new CobranzaSie();
		}
	}
	
	public void onEdit(RowEditEvent event) {
		log.info("en onedit()");
		for (int i = 0; i < cobranzaList.size(); i++) {
			log.info("en onedit() ------- "+ cobranzaList.get(i).getFecvencimiento());
		}
    }
      
    public void onCancel(RowEditEvent event) {
    	
    }
    
    public void onEditDetPaquete(RowEditEvent event) {
		log.info("en onEditDetPaquete() ");

		log.info("en onEditDetPaquete()sss " + idProducto );
		log.info("en onEditDetPaquete()sss2 "  );
		for (int i = 0; i < detPaqueteList.size(); i++) {
			log.info("en onEditDetPaquete() 1 ------- "+i+"   "+ detPaqueteList.get(i).getCantidad()+" descr "+ detPaqueteList.get(i).getTbProducto().getDescripcionproducto());
		}
    }
    
    public void onCancelDetPaquete(RowEditEvent event) {
    	
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #update()
	 */
	public String update() throws Exception {
		log.info("update()");
		setNewRecord(false);
		return getViewList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #insertar()
	 */
	public String insertar() {
		List<DetProductoContratoSie> detProductoContratoList = new ArrayList<DetProductoContratoSie>();
		List<Integer> detidEmpleadosList = new ArrayList<Integer>();
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' ");
			}
			if (isNewRecord()) {
				log.info("a insertar ");
				
				for (int i = 0; i < detPaqueteList.size(); i++) {
					DetProductoContratoSie det=new DetProductoContratoSie();
					log.info(" q "+detPaqueteList.get(i).getTbProducto().getIdproducto());
					det.setTbProducto(detPaqueteList.get(i).getTbProducto());
					det.setCantidad(detPaqueteList.get(i).getCantidad());
					det.setObservacion(detPaqueteList.get(i).getObservacion());
					detProductoContratoList.add(det);
				}
				if(tipoVenta==1)
				objContratoSie.setTipoventa("MAS");
				else if(tipoVenta==2)
					objContratoSie.setTipoventa("PNT");
				
				if(getIdempleadoExpositor()!=0)
					detidEmpleadosList.add(getIdempleadoExpositor());
				if(getIdempleadoVendedor()!=0)
					detidEmpleadosList.add(getIdempleadoVendedor());
				if(getIdempleadoColaborador()!=0)
					detidEmpleadosList.add(getIdempleadoColaborador());
				
				log.info("a insertar contratito");
				objContratoService.insertContrato(idtipodoc,Tipocasa,idUbigeo, idempresa, objClienteSie, telefonoList, objDomicilioSie,  objContratoSie,detProductoContratoList, cobranzaList, detidEmpleadosList);
				log.info(" despues de  insertar ");
				mensaje = Constants.MESSAGE_INFO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				objProductoSie = new ProductoSie();
			} else {
				log.info("a actualizar  ");
				limpiarCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return getViewList();
	}
 
	public void limpiarCampos() {

	}

	public ProductoSie getSelectedProducto() {
		return selectedProducto;
	}

	public void setSelectedProducto(ProductoSie selectedProducto) {
		this.selectedProducto = selectedProducto;
	}

	/**
	 * @return the objProductoSie
	 */
	public ProductoSie getObjProductoSie() {
		return objProductoSie;
	}

	/**
	 * @param objProductoSie
	 *            the objProductoSie to set
	 */
	public void setObjProductoSie(ProductoSie objProductoSie) {
		this.objProductoSie = objProductoSie;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord
	 *            the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the comboManager
	 */
	public ComboAction getComboManager() {
		return comboManager;
	}

	/**
	 * @param comboManager
	 *            the comboManager to set
	 */
	public void setComboManager(ComboAction comboManager) {
		this.comboManager = comboManager;
	}

	/**
	 * @return the Tipocasa
	 */
	public int getTipocasa() {
		return Tipocasa;
	}

	/**
	 * @param tipocasa
	 */
	public void setTipocasa(int tipocasa) {
		Tipocasa = tipocasa;
	}

	/**
	 * @return the idempresa
	 */
	public int getIdempresa() {
		return idempresa;
	}

	/**
	 * @param idempresa
	 */
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia
	 *            the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the idDepartamento
	 */
	public String getIdDepartamento() {
		return idDepartamento;
	}

	/**
	 * @param idDepartamento
	 *            the idDepartamento to set
	 */
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	/**
	 * @return the objContratoSie
	 */
	public ContratoSie getObjContratoSie() {
		return objContratoSie;
	}

	/**
	 * @param objContratoSie
	 *            the objContratoSie to set
	 */
	public void setObjContratoSie(ContratoSie objContratoSie) {
		this.objContratoSie = objContratoSie;
	}

	/**
	 * @return the objClienteSie
	 */
	public ClienteSie getObjClienteSie() {
		return objClienteSie;
	}

	/**
	 * @param objClienteSie
	 *            the objClienteSie to set
	 */
	public void setObjClienteSie(ClienteSie objClienteSie) {
		this.objClienteSie = objClienteSie;
	}

	/**
	 * @return the objDomicilioSie
	 */
	public DomicilioPersonaSie getObjDomicilioSie() {
		return objDomicilioSie;
	}

	/**
	 * @param objDomicilioSie
	 *            the objDomicilioSie to set
	 */
	public void setObjDomicilioSie(DomicilioPersonaSie objDomicilioSie) {
		this.objDomicilioSie = objDomicilioSie;
	}

	/**
	 * @return the objCobranzaSie
	 */
	public CobranzaSie getObjCobranzaSie() {
		return objCobranzaSie;
	}

	/**
	 * @param objCobranzaSie
	 *            the objCobranzaSie to set
	 */
	public void setObjCobranzaSie(CobranzaSie objCobranzaSie) {
		this.objCobranzaSie = objCobranzaSie;
	}

	/**
	 * @return the defectoUbigeo
	 */
	public boolean isDefectoUbigeo() {
		return defectoUbigeo;
	}

	/**
	 * @param defectoUbigeo
	 *            the defectoUbigeo to set
	 */
	public void setDefectoUbigeo(boolean defectoUbigeo) {
		this.defectoUbigeo = defectoUbigeo;
	}

	/**
	 * @return the ubigeoDefecto
	 */
	public String getUbigeoDefecto() {
		return ubigeoDefecto;
	}

	/**
	 * @param ubigeoDefecto
	 *            the ubigeoDefecto to set
	 */
	public void setUbigeoDefecto(String ubigeoDefecto) {
		this.ubigeoDefecto = ubigeoDefecto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #getViewMant()
	 */
	public String getViewMant() {
		return Constants.CONTRATO_FORM_PAGE;
	}

	/**
	 * @return the telefonoList
	 */
	public List<TelefonoPersonaSie> getTelefonoList() {
		return telefonoList;
	}

	/**
	 * @param telefonoList the telefonoList to set
	 */
	public void setTelefonoList(List<TelefonoPersonaSie> telefonoList) {
		this.telefonoList = telefonoList;
	}
	
	/**
	 * @return the nuevoTelef
	 */
	public TelefonoPersonaSie getNuevoTelef() {
		return nuevoTelef;
	}

	/**
	 * @param nuevoTelef the nuevoTelef to set
	 */
	public void setNuevoTelef(TelefonoPersonaSie nuevoTelef) {
		this.nuevoTelef = nuevoTelef;
	}

	/**
	 * @return the tipoTelef
	 */
	public int getTipoTelef() {
		return TipoTelef;
	}

	/**
	 * @param tipoTelef the tipoTelef to set
	 */
	public void setTipoTelef(int tipoTelef) {
		TipoTelef = tipoTelef;
	}

	/**
	 * @return the operadorTelefonico
	 */
	public int getOperadorTelefonico() {
		return operadorTelefonico;
	}

	/**
	 * @param operadorTelefonico the operadorTelefonico to set
	 */
	public void setOperadorTelefonico(int operadorTelefonico) {
		this.operadorTelefonico = operadorTelefonico;
	}

	/**
	 * @return the selectTelef
	 */
	public String getSelectTelef() {
		return selectTelef;
	}

	/**
	 * @param selectTelef the selectTelef to set
	 */
	public void setSelectTelef(String selectTelef) {
		this.selectTelef = selectTelef;
	}

	/**
	 * @return the tipoVenta
	 */
	public int getTipoVenta() {
		return tipoVenta;
	}

	/**
	 * @param tipoVenta the tipoVenta to set
	 */
	public void setTipoVenta(int tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	/**
	 * @return the tipopago
	 */
	public int getTipopago() {
		return tipopago;
	}

	/**
	 * @param tipopago the tipopago to set
	 */
	public void setTipopago(int tipopago) {
		this.tipopago = tipopago;
	}

	/**
	 * @return the precioProducto
	 */
	public double getPrecioProducto() {
		return precioProducto;
	}

	/**
	 * @param precioProducto the precioProducto to set
	 */
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	/**
	 * @return the cobranzaList
	 */
	public List<CobranzaSie> getCobranzaList() {
		return cobranzaList;
	}

	/**
	 * @param cobranzaList the cobranzaList to set
	 */
	public void setCobranzaList(List<CobranzaSie> cobranzaList) {
		this.cobranzaList = cobranzaList;
	}

	/**
	 * @return the objPaquete
	 */
	public PaqueteSie getObjPaquete() {
		return objPaquete;
	}

	/**
	 * @param objPaquete the objPaquete to set
	 */
	public void setObjPaquete(PaqueteSie objPaquete) {
		this.objPaquete = objPaquete;
	}

	/**
	 * @return the objDetPaquete
	 */
	public DetPaqueteSie getObjDetPaquete() {
		return objDetPaquete;
	}

	/**
	 * @param objDetPaquete the objDetPaquete to set
	 */
	public void setObjDetPaquete(DetPaqueteSie objDetPaquete) {
		this.objDetPaquete = objDetPaquete;
	}

	/**
	 * @return the defectopaquete
	 */
	public boolean isDefectopaquete() {
		return defectopaquete;
	}

	/**
	 * @param defectopaquete the defectopaquete to set
	 */
	public void setDefectopaquete(boolean defectopaquete) {
		this.defectopaquete = defectopaquete;
	}

	/**
	 * @return the detPaqueteList
	 */
	public List<DetPaqueteSie> getDetPaqueteList() {
		return detPaqueteList;
	}

	/**
	 * @param detPaqueteList the detPaqueteList to set
	 */
	public void setDetPaqueteList(List<DetPaqueteSie> detPaqueteList) {
		this.detPaqueteList = detPaqueteList;
	}

	/**
	 * @return the idpaquete
	 */
	public int getIdpaquete() {
		return idpaquete;
	}

	/**
	 * @param idpaquete the idpaquete to set
	 */
	public void setIdpaquete(int idpaquete) {
		this.idpaquete = idpaquete;
	}

	/**
	 * @return the idProducto
	 */
	public int getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the idCobranza
	 */
	public int getIdCobranza() {
		return idCobranza;
	}

	/**
	 * @param idCobranza the idCobranza to set
	 */
	public void setIdCobranza(int idCobranza) {
		this.idCobranza = idCobranza;
	}

	/**
	 * @return the idempleadoExpositor
	 */
	public int getIdempleadoExpositor() {
		return idempleadoExpositor;
	}

	/**
	 * @param idempleadoExpositor the idempleadoExpositor to set
	 */
	public void setIdempleadoExpositor(int idempleadoExpositor) {
		this.idempleadoExpositor = idempleadoExpositor;
	}

	/**
	 * @return the idempleadoVendedor
	 */
	public int getIdempleadoVendedor() {
		return idempleadoVendedor;
	}

	/**
	 * @param idempleadoVendedor the idempleadoVendedor to set
	 */
	public void setIdempleadoVendedor(int idempleadoVendedor) {
		this.idempleadoVendedor = idempleadoVendedor;
	}

	/**
	 * @return the idempleadoColaborador
	 */
	public int getIdempleadoColaborador() {
		return idempleadoColaborador;
	}

	/**
	 * @param idempleadoColaborador the idempleadoColaborador to set
	 */
	public void setIdempleadoColaborador(int idempleadoColaborador) {
		this.idempleadoColaborador = idempleadoColaborador;
	}

	/**
	 * @return the idtipodoc
	 */
	public int getIdtipodoc() {
		return idtipodoc;
	}

	/**
	 * @param idtipodoc the idtipodoc to set
	 */
	public void setIdtipodoc(int idtipodoc) {
		this.idtipodoc = idtipodoc;
	}

	/**
	 * @return the idUbigeo
	 */
	public int getIdUbigeo() {
		return idUbigeo;
	}

	/**
	 * @param idUbigeo the idUbigeo to set
	 */
	public void setIdUbigeo(int idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	
}
