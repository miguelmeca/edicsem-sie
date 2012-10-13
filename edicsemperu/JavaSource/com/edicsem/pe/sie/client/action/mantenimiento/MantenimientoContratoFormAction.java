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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
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
	private int Tipocasa, idempresa,tipoVenta,tipopago;
	private String idProvincia, idDepartamento, idUbigeo, ubigeoDefecto,selectTelef;
	private ProductoSie objProductoSie;
	private ClienteSie objClienteSie;
	private DomicilioPersonaSie objDomicilioSie;
	private ContratoSie objContratoSie;
	private ProductoSie selectedProducto;
	private CobranzaSie objCobranzaSie;
	private List<TelefonoPersonaSie> telefonoList;
	private List<CobranzaSie> cobranzaList;
	private TelefonoPersonaSie nuevoTelef;
	private int TipoTelef, operadorTelefonico;
	private double precioProducto;
	private boolean defectoUbigeo;
	private boolean newRecord = false;
	
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
		defectoUbigeo = true;
		ubigeoDefecto = "";
		nuevoTelef = new TelefonoPersonaSie();
		nuevoTelef.setTipoTelef("");
		 selectTelef="";
		telefonoList = new ArrayList<TelefonoPersonaSie>();
		operadorTelefonico=1;
		TipoTelef=1;
		tipoVenta=1;
		cobranzaList= new ArrayList<CobranzaSie>() ;
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
		log.info("agregar    :D  --- ");
		return getViewMant();
	}

	public void cambioUbigeoDefecto() {
		log.info(" defecto  " + defectoUbigeo);
		comboManager.setUbigeoDeparItems(null);
		comboManager.setUbigeoProvinItems(null);
		comboManager.setUbigeoDistriItems(null);
		if (defectoUbigeo) {
			log.info(" defecto lima true 1");
			ubigeoDefecto = "";
		} else {
			log.info(" cambio ubigeo   false  otro");
		}
	}

	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = null;
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
			System.out.println("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo)) {
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
		ubigeoDefecto = "otro ubigeo";

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
			if (e.getValue().toString().equals(idUbigeo)) {
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
	public void  telefonoAgregar(){
		log.info("telefono agregar " + nuevoTelef.getTelefono());
		boolean verifica= true;
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
			if(telefonoList.get(i).getTelefono().equals(nuevoTelef.getTelefono())){
				 verifica= true;
			}
		}
		if( verifica){
			telefonoList.add(nuevoTelef);
			log.info("se agrego " + nuevoTelef.getTelefono());
		}
		nuevoTelef = new TelefonoPersonaSie();
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
	
	public void limpiarDatosTelefono(){
		nuevoTelef = new TelefonoPersonaSie();
	}
	
	public void insertarCobranza() throws Exception{
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
		for (int i = 0; i < objContratoSie.getNumcuotas(); i++) {
			objCobranzaSie.setNumletra(i+"");
			if(i==0 && objContratoSie.getCuotainicial()!=new BigDecimal(0.0)){
				objCobranzaSie.setImpinicial(objContratoSie.getCuotainicial());
				fechaVencimiento=objContratoSie.getFechacuotainicial();
			}else{
				
			objCobranzaSie.setImpinicial(objContratoSie.getPagomensual());
			//una posible anternativa
			if(i==1){
				fechaVencimiento = DateUtil.addToDate(objContratoSie.getFechacuotainicial(), Calendar.MONTH, 1);
			}else{
				fechaVencimiento = DateUtil.addToDate(fechaVencimiento, Calendar.MONTH, 1);
			}
			}
			objCobranzaSie.setFechaVencimientoString(DateUtil.formatoString(fechaVencimiento, "dd/MM/yyyy"));
			log.info("fec venc  "+i +" "+ fechaVencimiento+" fecha formt " +fechaVencimiento +" conv "+ DateUtil.formatoString(fechaVencimiento, "dd/MM/yyyy") +" Numcuotas "+objContratoSie.getNumcuotas()+" "+ objContratoSie.getPagomensual()
					+"  pagar  "+objCobranzaSie.getImpinicial() );
			objCobranzaSie.setFecvencimiento(fechaVencimiento);
			
			cobranzaList.add(objCobranzaSie);
			objCobranzaSie= new CobranzaSie();
		}
 
		objContratoSie = new ContratoSie();
	}
	
	public void onEdit(RowEditEvent event) {
		log.info("en onedit()");
		for (int i = 0; i < cobranzaList.size(); i++) {
			objCobranzaSie.setFechaVencimientoString(DateUtil.formatoString(cobranzaList.get(i).getFecvencimiento(), "dd/MM/yyyy"));
			log.info("en onedit() ------- "+ cobranzaList.get(i).getFecvencimiento());
		}
    }  
      
    public void onCancel(RowEditEvent event) {
    	
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
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' ");
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
	 * @return the idUbigeo
	 */
	public String getIdUbigeo() {
		return idUbigeo;
	}

	/**
	 * @param idUbigeo
	 *            the idUbigeo to set
	 */
	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
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
		return Constants.MANT_CONTRATO_FORM_PAGE;
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

	
}
