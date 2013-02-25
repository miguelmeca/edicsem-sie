package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.SeguimientoContratoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.DetGrupoEmpleadoService;
import com.edicsem.pe.sie.service.facade.DetProductoContratoService;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.PaqueteService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.SeguimientoContratoService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.UbigeoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "contratoForm")
@SessionScoped
public class MantenimientoContratoFormAction extends
		BaseMantenimientoAbstractAction {

	private String mensaje;
	public static Log log = LogFactory.getLog(MantenimientoContratoFormAction.class);
	private int Tipocasa,idtipodoc,idUbigeo, idempresa,tipoVenta,tipopago, idpaquete, idProducto, idempleadoExpositor, idempleadoVendedor, idempleadoColaborador, idTelefono;
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
	private List<DetProductoContratoSie> detProductoContrato;
	private TelefonoPersonaSie nuevoTelef;
	private int TipoTelef, operadorTelefonico, idCobranza,idestadoProducto;
	private double precioProducto,precioMensual;
	private boolean defectoUbigeo,defectopaquete;
	private boolean newRecord = false;
	private boolean skip;
	private PaqueteSie objPaquete;
	private DetPaqueteSie objDetPaquete;
	private List<ContratoSie> contratoXClienteList;
	private Date dhoy, dValidoFecNac, fechaMensual;
	private BigDecimal totalacumulado;
	private int idGrupo;
	private List<DetGrupoEmpleadoSie> detgrupoList;
	private int estadoRefinan;
	
	//Consultar
	private String numDniCliente,codigoContrato,apePatCliente,apeMatCliente,nombreCliente;
	private int tamanoLista,idcontrato,idcliente, radio;
	private List<DomicilioPersonaSie> domicilioList;
	
	//Gestionar
	private SeguimientoContratoSie objSeguimiento;
	private int cuotasNuevas,idMotivo;
	
	@EJB
	private ProductoService objProductoService;
	@EJB
	private DetallePaqueteService objDetPaqueteService;
	@EJB
	private PaqueteService objPaqueteService;
	@EJB
	private ContratoService objContratoService;
	@EJB
	private ClienteService objClienteService;
	@EJB
	private DomicilioEmpleadoService objDomicilioService;
	@EJB
	private TelefonoEmpleadoService objTelefonoService;
	@EJB
	private DetProductoContratoService objDetProductoService;
	@EJB
	private CobranzaService objCobranzaService;
	@EJB
	private EstadogeneralService objEstadoService;
	@EJB
	private UbigeoService objubigeoService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	@EJB
	private DetGrupoEmpleadoService objDetGrupoService;
	@EJB
	private SeguimientoContratoService objSeguimientoContratoService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;

	public MantenimientoContratoFormAction() {
		log.info("inicializando constructor MantenimientoContrato");
		init();
	}
	
	public void init() {
		log.info("init()");
		tamanoLista=0;
		limpiarCampos();
		radio=1;
		operadorTelefonico=1;
		idCobranza=0;
		TipoTelef=1;
		tipoVenta=1;
		skip = false;
		idUbigeo=0;
		idtipodoc=1;
		tipopago=1;
		defectoUbigeo = true;
		defectopaquete= true;
		selectTelef="";
		idempleadoExpositor=0;
		idempleadoVendedor=0;
		idempleadoColaborador=0;
		Tipocasa=0;idUbigeo=0;
		idempresa = 0;
		ubigeoDefecto = "";
		totalacumulado= new BigDecimal(0);
		precioMensual=0.0;
		cuotasNuevas=1;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction
	 * #agregar()
	 */
	public String agregar() {
		log.info("agregar()))");
		ubigeoDefecto = "";
		detProductoContrato = new ArrayList<DetProductoContratoSie>();
		limpiarCampos();
		objContratoSie = new ContratoSie();
		//buscar codigo nuevo por el número de secuencial
		log.info("codigo  "+objContratoService.obtenerCodigo());
		objContratoSie.setCodcontrato(""+objContratoService.obtenerCodigo());
		setNewRecord(true);
		comboManager.setUbigeoDeparItems(null);
		comboManager.setUbigeoProvinItems(null);
		comboManager.setUbigeoDistriItems(null);
		comboManager.setIdDepartamento("15");
		comboManager.setIdProvincia("01");
		idDepartamento="15";
		idProvincia="01";
		idUbigeo=0;
		comboManager.setCargoEmpleado(2);
		comboManager.setCodigoEstado(Constants.COD_ESTADO_TB_DET_CONTRATO_PRODUCTO);
		objProductoSie.setCantidadContrato(1);
		nuevoTelef.setTipoTelef("");
		selectTelef= "";
		idpaquete=0;
		Tipocasa=0;
		tamanoLista=0;
		totalacumulado= new BigDecimal(0);
		return getViewMant();
	}
	
	public String agregar2() {
		log.info("agregar2()))");
		ubigeoDefecto="";
		objClienteSie=null;
		telefonoList=null;
		domicilioList=new ArrayList<DomicilioPersonaSie>();
		contratoXClienteList=new ArrayList<ContratoSie>();
		objDomicilioSie=null;
		detProductoContrato=null;
		cobranzaList=null;
		numDniCliente=null;
		codigoContrato=null;
		apePatCliente=null;
		apeMatCliente=null;
		nombreCliente=null;
		return Constants.CONSULTA_CONTRATO_FORM_PAGE;
	}
	
	public String agregarRefinanciar() {
		log.info("agregarRefinanciar()");
		objSeguimiento= new SeguimientoContratoSie();
		ubigeoDefecto="";
		objClienteSie=null;
		telefonoList=null;
		domicilioList=new ArrayList<DomicilioPersonaSie>();
		contratoXClienteList=new ArrayList<ContratoSie>();
		objContratoSie = new ContratoSie();
		objDomicilioSie=null;
		detProductoContrato=null;
		comboManager.setCodigoEstado(Constants.COD_ESTADO_TB_CONTRATO);
		cobranzaList=new ArrayList<CobranzaSie>();
		numDniCliente=null;
		codigoContrato=null;
		apePatCliente=null;
		apeMatCliente=null;
		nombreCliente=null;
		cuotasNuevas=1;
		totalacumulado= new BigDecimal(0);
		return Constants.GESTIONAR_CONTRATO_FORM_PAGE;
	}
	
	public void cambiar() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(null);
		idProvincia = null;
		idUbigeo = 0;
		log.info("cambiar   :D  --- ");
	}
	
	public void listarempleados(){
		comboManager.setIdEmpresa(idempresa);
	}
	
	public void cambiar2() {
		comboManager.setIdDepartamento(getIdDepartamento());
		comboManager.setIdProvincia(getIdProvincia());
		log.info("cambiar 2  :D  --- ");
	}

	public String ingresarUbigeo() {
		// enviamos el nombre completo del depa- provincia-distrito

		log.info("ingresarUbigeo :D a --- " + idUbigeo);

		Iterator it = comboManager.getUbigeoDeparItems().entrySet().iterator();
		Iterator it2 = comboManager.getUbigeoProvinItems().entrySet().iterator();
		Iterator it3 = comboManager.getUbigeoDistriItems().entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idDepartamento)) {
				ubigeoDefecto = (String) e.getKey();
				log.info("ubigeo depa " + ubigeoDefecto);
				break;
			}
		}
		while (it2.hasNext()) {
			Map.Entry e = (Map.Entry) it2.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idProvincia)) {
				ubigeoDefecto += "- " + (String) e.getKey();
				log.info("ubigeo prov " + ubigeoDefecto);
				break;
			}
		}
		while (it3.hasNext()) {
			Map.Entry e = (Map.Entry) it3.next();
			log.info("key " + e.getKey() + " value " + e.getValue());
			if (e.getValue().toString().equals(idUbigeo+"")) {
				ubigeoDefecto += "- " + (String) e.getKey();
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
		log.info("telefono agregar " );
		
		if( nuevoTelef.getTelefono()==null||nuevoTelef.getTelefono().equals("")){
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
				 mensaje = "El telefono ya se encuentra registrado en la lista de referencias";
				 break;
			}else{
				 verifica= true;
				
			}
		}if( telefonoList.size()==0){
			 verifica= true;
		}
		if( verifica){
			telefonoList.add(nuevoTelef);
			mensaje="Se agregó telefono";
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Constants.MESSAGE_INFO_TITULO, mensaje);
			log.info("se agrego " + nuevoTelef.getTelefono());
		}
		if( mensaje!=null && verifica==false){
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		nuevoTelef = new TelefonoPersonaSie();
		}
	}
	
	/**
	 * Eliminar Teléfono de la lista*/
	public void  eliminaTelefono(){
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
		}
	}

	public void agregarProducto(){
		mensaje=null;
		log.info("agregarProducto "+ detPaqueteList.size()+"   "+objProductoSie.getIdproducto());
		
		if(objProductoSie.getIdproducto()==null||objProductoSie.getIdproducto()==0){
			mensaje="Debe seleccionar un producto para agregarlo a la lista";
		}else{
			int cantidad= detPaqueteList.size();
			DetPaqueteSie det = new DetPaqueteSie();
			det.setTbProducto(objProductoService.findProducto(objProductoSie.getIdproducto()));
			det.setCantidad(objProductoSie.getCantidadContrato());
			det.setObservacion(objProductoSie.getObservacionContrato());
			if(objProductoSie.getCantidadContrato()<1){
				mensaje="Cantidad debe ser mayor que 0 ";
			}
			if(cantidad==0){
				det.setItem(1);
				det.setTbEstadoGeneral(objEstadoService.findEstadogeneral(idestadoProducto));
				detPaqueteList.add(det);
				log.info("tamaño lista de paqu "+ detPaqueteList.size());
			}else{
				for (int i = 0; i < detPaqueteList.size(); i++) {
					if(detPaqueteList.get(i).getTbProducto().getIdproducto()==det.getTbProducto().getIdproducto()){
						mensaje="Dicho producto ya se encuentra registrado en la lista, usted puede editarlo ";
					}
				det.setItem(cantidad+1);
				}
				if(mensaje==null){
					log.info(" se agrega cuando lista esta llena");
					det.setTbEstadoGeneral(objEstadoService.findEstadogeneral(idestadoProducto));
					detPaqueteList.add(det);
					mensaje="Se agregó correctamente ";
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							Constants.MESSAGE_INFO_TITULO, mensaje);
					mensaje=null;
				}
				objProductoSie= new ProductoSie();
				log.info("tamaño lista de paq "+ detPaqueteList.size()+" "+mensaje);
				
			}
		}
		if(mensaje!=null){
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
	
	public void insertarCobranza() throws Exception{
		log.info(" insertarCobranza  :d "+objContratoSie.getNumcuotas()+" precio "+ precioProducto +"  fec ven  "+ objContratoSie.getFechacuotainicial());
		BigDecimal d;
		totalacumulado= new BigDecimal(0);
		boolean isadd=true;
		BigDecimal d2 = new BigDecimal(0);
		if(tipopago==2){
			//contado
			cobranzaList= new ArrayList<CobranzaSie>();
			d= new BigDecimal(getPrecioProducto());
			d=d.setScale(2, RoundingMode.HALF_UP);
			objCobranzaSie.setCantcuotas("1");
			objCobranzaSie.setNumletra("0");
			objCobranzaSie.setFecvencimiento(objContratoSie.getFechacuotainicial());
			objCobranzaSie.setImpinicial(new BigDecimal(precioProducto));
			objCobranzaSie.setImportemasmora(objCobranzaSie.getImpinicial());
			cobranzaList.add(objCobranzaSie);
			objCobranzaSie= new CobranzaSie();
			
		}else{
			//crédito
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			log.info("  Pago Subinicial "+objContratoSie.getPagosubinicial());
			cobranzaList= new ArrayList<CobranzaSie>() ;
			 d = new BigDecimal(getPrecioProducto() /objContratoSie.getNumcuotas().doubleValue());
			d=d.setScale(2, RoundingMode.HALF_UP);
			objContratoSie.setPagomensual(d);
			log.info(" d "+ d+"  Pagomensual "+objContratoSie.getPagomensual());
		
			if(objContratoSie.getPagosubinicial()!=new BigDecimal(0)){
				d2 = new BigDecimal(d.doubleValue()-objContratoSie.getPagosubinicial().doubleValue());
				d2=d2.setScale(2, RoundingMode.HALF_UP);
				if(d2.doubleValue() <0){
					log.info(" menor q 0 "+d2);
					objContratoSie.setCuotainicial(new BigDecimal(0));
				}else{
					objContratoSie.setCuotainicial(d2);
				}
			}
			
			Date fechaVencimiento = null ;
			sdf.format(objContratoSie.getFechacuotainicial());
			String fecha3 =sdf.format(objContratoSie.getFechacuotainicial());
			fechaVencimiento =sdf.parse(fecha3);
			for (int i = 0; i < objContratoSie.getNumcuotas(); i++) {
				objCobranzaSie.setNumletra(i+"");
				isadd=true;
				log.info("  Pago Subinicial "+objContratoSie.getPagosubinicial()+"  "+new BigDecimal(0));
				//si el cliente entrega una parte de la cuota inicial , letra 0
				if(i==0 && objContratoSie.getPagosubinicial().doubleValue()>0){
					log.info(" cuota inicial ");
					objCobranzaSie.setImpinicial(objContratoSie.getPagosubinicial());
					objCobranzaSie.setFecvencimiento(objContratoSie.getFechaentrega());
					objCobranzaSie.setFecpago(objContratoSie.getFechaentrega());
					totalacumulado = totalacumulado.add(objCobranzaSie.getImpinicial());
					objCobranzaSie.setImportemasmora(objCobranzaSie.getImpinicial());
					cobranzaList.add(objCobranzaSie);
					objCobranzaSie= new CobranzaSie();
					//lo que queda por pagar de la cuota inicial , letra -1
					if(d2!=null){
					objCobranzaSie.setNumletra("-1");
					objCobranzaSie.setImpinicial(objContratoSie.getCuotainicial());
					objCobranzaSie.setFecvencimiento(objContratoSie.getFechacuotainicial());
					totalacumulado=totalacumulado.add(objCobranzaSie.getImpinicial());
					objCobranzaSie.setImportemasmora(objCobranzaSie.getImpinicial());
					cobranzaList.add(objCobranzaSie);
					objCobranzaSie= new CobranzaSie();
					isadd=false;
					log.info(DateUtil.formatoString(objCobranzaSie.getFecvencimiento(), "dd/MM/yyyy") +" Numcuotas "+objContratoSie.getNumcuotas()+" mensualito "+ objContratoSie.getPagomensual()
							+"  pagar  "+objCobranzaSie.getImpinicial() +"num let "+objCobranzaSie.getNumletra());
					}
				//si el cliente no entrega cuota inicial
				}else if(i==0 && objContratoSie.getPagosubinicial().doubleValue()<=0){
					log.info(" no entrega cuota inicial ");
					objCobranzaSie.setImpinicial(objContratoSie.getPagomensual());
					fechaVencimiento = objContratoSie.getFechacuotainicial();
				}
				else{
					log.info(" else  ");
					objCobranzaSie.setImpinicial(objContratoSie.getPagomensual());
					
					if(i==0){
						log.info(" i==0  ");
						fechaVencimiento = DateUtil.addToDate(objContratoSie.getFechacuotainicial(), Calendar.MONTH, 1);
					}else{
						log.info(" i!=0  ");
						log.info("d2    : :  "+d2);
						if(d2.compareTo(new BigDecimal(0))==-1){
							log.info("d2    : :  "+d2);
							// returns (-1 if a < b), (0 if a == b), (1 if a > b)
							d2= d2.multiply(new BigDecimal(-1));
							d2 = objContratoSie.getPagomensual().subtract(d2);
							log.info("d2    : :  "+d2);
							if(d2.compareTo(new BigDecimal(0))==-1){
								log.info("iffff :  "+0);
								objCobranzaSie.setImpinicial(new BigDecimal(0));
							}else{
								log.info("elseeee :  "+d2);
								objCobranzaSie.setImpinicial(d2);
							}
						}
						
						fechaVencimiento = DateUtil.addToDate(objContratoSie.getFechacuotainicial(), Calendar.MONTH, Integer.parseInt(objCobranzaSie.getNumletra()));
					}
				}
				log.info("fec venc  "+i +" "+ fechaVencimiento +" conv "+ DateUtil.formatoString(fechaVencimiento, "dd/MM/yyyy") +" Numcuotas "+objContratoSie.getNumcuotas()+" mensualito "+ objContratoSie.getPagomensual()
						+"  pagar  "+objCobranzaSie.getImpinicial() );
				if(isadd ){
					log.info(" agregar ");
				objCobranzaSie.setFecvencimiento(fechaVencimiento);
				totalacumulado =totalacumulado.add(objCobranzaSie.getImpinicial());
				log.info("totalacumulado:  "+totalacumulado);
				objCobranzaSie.setImportemasmora(objCobranzaSie.getImpinicial());
				cobranzaList.add(objCobranzaSie);
				objCobranzaSie= new CobranzaSie();
				}
			}
		}
	}
	
	public void onEdit(RowEditEvent event) {
		log.info("en onedit()");
		totalacumulado = new BigDecimal(0);
		for (int i = 0; i < cobranzaList.size(); i++) {
			totalacumulado = totalacumulado.add(cobranzaList.get(i).getImportemasmora());
			log.info("suma bigdecimal "+	totalacumulado);
		}
    }
    
    public void onCancel(RowEditEvent event) {
    	
    }
    
    public void onEditDetPaquete(RowEditEvent event) {
		log.info("en onEditDetPaquete() ");
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
		mensaje=null;
		String paginaRetorno = null;
		List<Integer> detidEmpleadosList = new ArrayList<Integer>();
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		try {
			if (log.isInfoEnabled()) {
				log.info("Entering my method 'insertar()' ");
			}
			List<String> dniList =  objEmpleadoService.listarDni();
			if(totalacumulado.doubleValue()!=precioProducto){
				mensaje="La suma del cronograma de pagos no es el mismo que el precio total ";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else if(dniList.contains(objClienteSie.getNumdocumento())){
				mensaje="Dicho número de documento le pertenece a otro empleado ";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else if( telefonoList.size()==0){
				mensaje= "Debe ingresar telefonos de referencia";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else if( detPaqueteList.size()==0){
				mensaje= "Debe ingresar un producto como mínimo ";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			else if( cobranzaList.size()==0){
				mensaje= "Debe ingresar el detalle de cobranza";
				msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			}
			if(mensaje ==null){
			if (isNewRecord()) {
				
				for (int i = 0; i < detPaqueteList.size(); i++) {
					DetProductoContratoSie det=new DetProductoContratoSie();
					log.info(" q "+detPaqueteList.get(i).getTbProducto().getIdproducto());
					det.setTbProducto(detPaqueteList.get(i).getTbProducto());
					det.setCantidad(detPaqueteList.get(i).getCantidad());
					det.setObservacion(detPaqueteList.get(i).getObservacion());
					det.setTbEstadoGeneral(detPaqueteList.get(i).getTbEstadoGeneral());
					detProductoContrato.add(det);
				}
				if(tipoVenta==1)
				objContratoSie.setTipoventa("MAS");
				else if(tipoVenta==2)
					objContratoSie.setTipoventa("PNT");
				
				if(getIdempleadoExpositor()!=0)
					detidEmpleadosList.add(getIdempleadoExpositor());
				if(getIdempleadoVendedor()!=0)
					detidEmpleadosList.add(getIdempleadoVendedor());
				if(getIdempleadoColaborador()!=0){
					detidEmpleadosList.add(getIdempleadoColaborador());
				}
				log.info("a insertar contratito");
				if(tipopago==1){
				objContratoSie.setNumcuotas(objContratoSie.getNumcuotas()-1);
				}
				objContratoSie.setUsuariocreacion(sessionUsuario.getUsuario());
				objCobranzaSie.setUsuariocreacion(sessionUsuario.getUsuario());
				objContratoService.insertContrato(idtipodoc,Tipocasa,idUbigeo, idempresa, objClienteSie, telefonoList, objDomicilioSie,  objContratoSie,detProductoContrato, cobranzaList, detidEmpleadosList);
				log.info(" despues de  insertar ");
				mensaje = "Se inserto correctamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_INFO_TITULO, mensaje);
				
			}paginaRetorno =Constants.CONSULTA_CONTRATO_FORM_PAGE;
			}else{
				paginaRetorno = getViewMant();
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return paginaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("consultar() ");
		limpiarCampos();
		contratoXClienteList = objContratoService.listarClientePorParametro(numDniCliente, codigoContrato, nombreCliente, apePatCliente, apeMatCliente);
		
		if(contratoXClienteList.size()==1){
			log.info("consultar() == 1 ");
			objClienteSie = objClienteService.findCliente(contratoXClienteList.get(0).getTbCliente().getIdcliente());
			log.info("  " + objClienteSie.getIdcliente()+"  "+objClienteSie.getNombresCompletos());
			domicilioList = objDomicilioService.listarDomicilioCliente(objClienteSie.getIdcliente());
			if(domicilioList.size()==1){
				objDomicilioSie = domicilioList.get(domicilioList.size()-1);
			}
			log.info("  ubigeo  " +objDomicilioSie.getTbUbigeo().getIdubigeo());
			UbigeoSie ubi =  objubigeoService.findUbigeo(objDomicilioSie.getTbUbigeo().getIdubigeo());
			String depaProv = objubigeoService.findDepaProv(ubi.getCoddepartamento(),ubi.getCodprovincia());
			ubigeoDefecto =depaProv+" - "+ubi.getNombre();
			telefonoList = objTelefonoService.listarTelefonoEmpleadosXidcliente(objClienteSie.getIdcliente());
			detProductoContrato = objDetProductoService.listarDetProductoContratoXContrato(contratoXClienteList.get(0).getIdcontrato());
			objContratoSie = objContratoService.findContrato(contratoXClienteList.get(0).getIdcontrato());
			cobranzaList =objCobranzaService.listarCobranzasXidcontrato(contratoXClienteList.get(0).getIdcontrato());
			totalacumulado= new BigDecimal(0);
			estadoRefinan= objContratoSie.getTbEstadoGeneral().getIdestadogeneral();
			for (int i = 0; i < cobranzaList.size(); i++) {
				totalacumulado = totalacumulado.add(cobranzaList.get(i).getImportemasmora());
			}
			fechaMensual = DateUtil.addToDate(cobranzaList.get(cobranzaList.size()-1).getFecvencimiento(), Calendar.MONTH,1);
			mensaje = "Consulto realizada";
		}
		else if(contratoXClienteList.size()==0){
			mensaje = "Consulto realizada, no se encontraron datos";
			objClienteSie = new ClienteSie();
			objDomicilioSie = new DomicilioPersonaSie();
			cobranzaList = new ArrayList<CobranzaSie>();
		}
		log.info(" lista x consultaaa "+contratoXClienteList.size()+" MENSAJE "+ mensaje);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	public String cargar() throws Exception{
		log.info(" cargar() ");
		
		objClienteSie = objClienteService.findCliente(idcliente);
		log.info("  " + objClienteSie.getIdcliente()+"  "+objClienteSie.getNombresCompletos());
		domicilioList = objDomicilioService.listarDomicilioCliente(idcliente);
		if(domicilioList.size()==1){
			objDomicilioSie = domicilioList.get(domicilioList.size()-1);
		}
		UbigeoSie ubi =  objubigeoService.findUbigeo(objDomicilioSie.getTbUbigeo().getIdubigeo());
		
		ubigeoDefecto = ubi.getNombre();
		telefonoList = objTelefonoService.listarTelefonoEmpleadosXidcliente(objClienteSie.getIdcliente());
		detProductoContrato = objDetProductoService.listarDetProductoContratoXContrato(idcontrato);
		objContratoSie = objContratoService.findContrato(idcontrato);
		cobranzaList =objCobranzaService.listarCobranzasXidcontrato(idcontrato);
		totalacumulado= new BigDecimal(0);
		estadoRefinan= objContratoSie.getTbEstadoGeneral().getIdestadogeneral();
		for (int i = 0; i < cobranzaList.size(); i++) {
			totalacumulado = totalacumulado.add(cobranzaList.get(i).getImportemasmora());
		}
		fechaMensual = DateUtil.addToDate(cobranzaList.get(cobranzaList.size()-1).getFecvencimiento(), Calendar.MONTH,1);
		return Constants.CONSULTA_CONTRATO_FORM_PAGE;
	}
	
	public void limpiarCampos(){
		objProductoSie = new ProductoSie();
		objClienteSie = new ClienteSie() ;
		telefonoList = new ArrayList<TelefonoPersonaSie>();
		objDomicilioSie= new DomicilioPersonaSie();
		objContratoSie= new ContratoSie();
		cobranzaList= new ArrayList<CobranzaSie>();
		detPaqueteList = new ArrayList<DetPaqueteSie>();
		detProductoContrato = new ArrayList<DetProductoContratoSie>();
		objCobranzaSie = new CobranzaSie();
		objPaquete = new PaqueteSie();
		objDetPaquete = new DetPaqueteSie();
		nuevoTelef = new TelefonoPersonaSie();
		contratoXClienteList= new ArrayList<ContratoSie>();
		domicilioList = new ArrayList<DomicilioPersonaSie>();
		objSeguimiento= new SeguimientoContratoSie();
		precioMensual=0.0;
		cuotasNuevas=1;
	}
	
	public void addCuota() throws Exception{
		log.info("addCuota()");
		if(precioMensual<1){
			mensaje="Debe ingresar un precio válido para agregar una nueva letra";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
		}if(cobranzaList.size()==0){
			mensaje="Debe buscar un contrato en la parte superior";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		else{
			for (int i = 0; i < cuotasNuevas; i++) {
				CobranzaSie cob = new CobranzaSie();
				cob.setImpinicial(new BigDecimal(precioMensual));
				cob.setImportemasmora(new BigDecimal(precioMensual));
				fechaMensual = DateUtil.addToDate(fechaMensual, Calendar.MONTH,i);
				int numletra= Integer.parseInt(cobranzaList.get(cobranzaList.size()-1).getNumletra());
				numletra=numletra+i+1;
				cob.setNumletra(numletra+"");
				cob.setFecvencimiento(fechaMensual);
				totalacumulado= totalacumulado.add(new BigDecimal(precioMensual));
				cobranzaList.add(cob);
				mensaje="Se agregó la nueva cuota";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String registrarGestion(){
		log.info("registrarGestion()");
		mensaje=null;
		if(objContratoSie.getIdcontrato()==null){
			mensaje ="Debe buscar un contrato";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		if(objSeguimiento.getTbMotivo()==null){
			mensaje ="Debe seleccionar un motivo";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO, mensaje);
		}else{
			objSeguimientoContratoService.insertSeguimientoContrato(objSeguimiento);
			mensaje = Constants.MESSAGE_REGISTRO_TITULO;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO, mensaje);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	public String cargarArchivoSustentatorio(FileUploadEvent event) {
		log.info("cargarImagenInsertar** " + event.getFile().getFileName());
		String path = Constants.RUTA_DOC_SUSTENTARIO;
		UploadedFile file = event.getFile();
		String tipo = event.getFile().getFileName();
		String type = tipo.substring(tipo.length()-3, tipo.length());
		log.info(" type "+type);
		try {
			 if(objContratoSie.getCodcontrato()==null){
				 mensaje="Debe buscar un contrato en la parte superior";
				 msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							Constants.MESSAGE_INFO_TITULO, mensaje);
			 }else{
			File directory = new File(path);
				if (!directory.getParentFile().exists()) {
					directory.mkdirs();
				}
			log.info("path  "+path);
			File f = new File(path +File.separator + "DocSust_"+ objContratoSie.getCodcontrato()+"."+type);
			log.info("Ruta del archivo "+f.getAbsolutePath());
			OutputStream salida = new FileOutputStream(f);
			objSeguimiento.setRutaarchivo(f.getPath());
			salida.write(file.getContents(), 0, file.getContents().length);
			salida.close();
			file.getInputstream().close();
			 mensaje="Se Adjuntó correctamente el archivo";
			 msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_INFO_TITULO, mensaje);
			 }
			 FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getViewMant();
	}
	
	public String onFlowProcess(FlowEvent event) {
	    log.info("Current wizard step:" + event.getOldStep());  
	    log.info("Next step:" + event.getNewStep());  
	    log.info("skip :"+skip);	
	     if(skip) {  
	            skip = true;   //reset in case user goes back  
	            return "confirm";  
	        }  
	     else {  
	            return event.getNewStep();  
	      }
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
		comboManager.setIdEmpresa(idempresa);
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
		comboManager.setTipoVenta(tipoVenta);
		comboManager.setTipoAlmacen(tipoVenta);
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

	/**
	 * @return the idTelefono
	 */
	public int getIdTelefono() {
		return idTelefono;
	}

	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}

	/**
	 * @return the numDniCliente
	 */
	public String getNumDniCliente() {
		return numDniCliente;
	}

	/**
	 * @param numDniCliente the numDniCliente to set
	 */
	public void setNumDniCliente(String numDniCliente) {
		this.numDniCliente = numDniCliente;
	}

	/**
	 * @return the codigoContrato
	 */
	public String getCodigoContrato() {
		return codigoContrato;
	}

	/**
	 * @param codigoContrato the codigoContrato to set
	 */
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the apePatCliente
	 */
	public String getApePatCliente() {
		return apePatCliente;
	}

	/**
	 * @param apePatCliente the apePatCliente to set
	 */
	public void setApePatCliente(String apePatCliente) {
		this.apePatCliente = apePatCliente;
	}

	/**
	 * @return the apeMatCliente
	 */
	public String getApeMatCliente() {
		return apeMatCliente;
	}

	/**
	 * @param apeMatCliente the apeMatCliente to set
	 */
	public void setApeMatCliente(String apeMatCliente) {
		this.apeMatCliente = apeMatCliente;
	}

	/**
	 * @return the contratoXClienteList
	 */
	public List<ContratoSie> getContratoXClienteList() {
		return contratoXClienteList;
	}

	/**
	 * @param contratoXClienteList the contratoXClienteList to set
	 */
	public void setContratoXClienteList(List<ContratoSie> contratoXClienteList) {
		this.contratoXClienteList = contratoXClienteList;
	}

	/**
	 * @return the tamanoLista
	 */
	public int getTamanoLista() {
		tamanoLista = contratoXClienteList.size();
		return tamanoLista;
	}

	/**
	 * @param tamanoLista the tamanoLista to set
	 */
	public void setTamanoLista(int tamanoLista) {
		this.tamanoLista = tamanoLista;
	}

	/**
	 * @return the domicilioList
	 */
	public List<DomicilioPersonaSie> getDomicilioList() {
		return domicilioList;
	}

	/**
	 * @param domicilioList the domicilioList to set
	 */
	public void setDomicilioList(List<DomicilioPersonaSie> domicilioList) {
		this.domicilioList = domicilioList;
	}

	/**
	 * @return the idcontrato
	 */
	public int getIdcontrato() {
		return idcontrato;
	}

	/**
	 * @param idcontrato the idcontrato to set
	 */
	public void setIdcontrato(int idcontrato) {
		this.idcontrato = idcontrato;
	}

	/**
	 * @return the idcliente
	 */
	public int getIdcliente() {
		return idcliente;
	}

	/**
	 * @param idcliente the idcliente to set
	 */
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	/**
	 * @return the radio
	 */
	public int getRadio() {
		if(radio==1){
			codigoContrato=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else if (radio ==2){
			numDniCliente=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else{
			numDniCliente=null;
			codigoContrato=null;
		}
		return radio;
	}

	/**
	 * @param radio the radio to set
	 */
	public void setRadio(int radio) {
		
		if(radio==1){
			codigoContrato=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else if (radio ==2){
			numDniCliente=null;
			nombreCliente=null;
			apePatCliente=null;
			apeMatCliente = null;
		}else{
			numDniCliente=null;
			codigoContrato=null;
		}
		
		this.radio = radio;
	}

	/**
	 * @return the objCobranzaService
	 */
	public CobranzaService getObjCobranzaService() {
		return objCobranzaService;
	}

	/**
	 * @param objCobranzaService the objCobranzaService to set
	 */
	public void setObjCobranzaService(CobranzaService objCobranzaService) {
		this.objCobranzaService = objCobranzaService;
	}

	/**
	 * @return the idestadoProducto
	 */
	public int getIdestadoProducto() {
		return idestadoProducto;
	}

	/**
	 * @param idestadoProducto the idestadoProducto to set
	 */
	public void setIdestadoProducto(int idestadoProducto) {
		this.idestadoProducto = idestadoProducto;
	}

	/**
	 * @return the detProductoContrato
	 */
	public List<DetProductoContratoSie> getDetProductoContrato() {
		return detProductoContrato;
	}

	/**
	 * @param detProductoContrato the detProductoContrato to set
	 */
	public void setDetProductoContrato(
			List<DetProductoContratoSie> detProductoContrato) {
		this.detProductoContrato = detProductoContrato;
	}

	/**
	 * @return the dhoy
	 */
	public Date getDhoy() {
		try {
			dhoy = DateUtil.getToday().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dhoy;
	}

	/**
	 * @param dhoy the dhoy to set
	 */
	public void setDhoy(Date dhoy) {
		this.dhoy = dhoy;
	}

	/**
	 * @return the dValidoFecNac
	 * @throws Exception 
	 */
	public Date getdValidoFecNac() throws Exception {
		dhoy = DateUtil.getToday().getTime();
		dValidoFecNac= DateUtil.addToDate(dhoy, Calendar.YEAR, -18);
		return dValidoFecNac;
	}

	/**
	 * @param dValidoFecNac the dValidoFecNac to set
	 */
	public void setdValidoFecNac(Date dValidoFecNac) {
		this.dValidoFecNac = dValidoFecNac;
	}

	/**
	 * @return the totalacumulado
	 */
	public BigDecimal getTotalacumulado() {
		return totalacumulado;
	}

	/**
	 * @param totalacumulado the totalacumulado to set
	 */
	public void setTotalacumulado(BigDecimal totalacumulado) {
		this.totalacumulado = totalacumulado;
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
	 * @return the detgrupoList
	 */
	public List<DetGrupoEmpleadoSie> getDetgrupoList() {
		return detgrupoList;
	}

	/**
	 * @param detgrupoList the detgrupoList to set
	 */
	public void setDetgrupoList(List<DetGrupoEmpleadoSie> detgrupoList) {
		this.detgrupoList = detgrupoList;
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
		detgrupoList= new ArrayList<DetGrupoEmpleadoSie>();
		comboManager.setCargoEmpleado(2);
		comboManager.setIdGrupo(idGrupo);
		detgrupoList = objDetGrupoService.listarEmpleadosXGrupo(idGrupo);
		this.idGrupo = idGrupo;
	}

	/**
	 * @return the estadoRefinan
	 */
	public int getEstadoRefinan() {
		return estadoRefinan;
	}

	/**
	 * @param estadoRefinan the estadoRefinan to set
	 */
	public void setEstadoRefinan(int estadoRefinan) {
		this.estadoRefinan = estadoRefinan;
	}

	public SeguimientoContratoSie getObjSeguimiento() {
		return objSeguimiento;
	}

	public void setObjSeguimiento(SeguimientoContratoSie objSeguimiento) {
		this.objSeguimiento = objSeguimiento;
	}

	/**
	 * @return the precioMensual
	 */
	public double getPrecioMensual() {
		return precioMensual;
	}

	/**
	 * @param precioMensual the precioMensual to set
	 */
	public void setPrecioMensual(double precioMensual) {
		this.precioMensual = precioMensual;
	}

	/**
	 * @return the cuotasNuevas
	 */
	public int getCuotasNuevas() {
		return cuotasNuevas;
	}

	/**
	 * @param cuotasNuevas the cuotasNuevas to set
	 */
	public void setCuotasNuevas(int cuotasNuevas) {
		this.cuotasNuevas = cuotasNuevas;
	}

	/**
	 * @return the fechaMensual
	 */
	public Date getFechaMensual() {
		return fechaMensual;
	}

	/**
	 * @param fechaMensual the fechaMensual to set
	 */
	public void setFechaMensual(Date fechaMensual) {
		this.fechaMensual = fechaMensual;
	}

	/**
	 * @return the idMotivo
	 */
	public int getIdMotivo() {
		return idMotivo;
	}

	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}
	
}
