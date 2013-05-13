package com.edicsem.pe.sie.client.action.mantenimiento;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.client.action.ComboAction;
import com.edicsem.pe.sie.client.action.SMTPConfig;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.HistoricoObservacionesSie;
import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.RefinanciarPagoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.DetProductoContratoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.HistoricoObservacionesService;
import com.edicsem.pe.sie.service.facade.IncidenciaService;
import com.edicsem.pe.sie.service.facade.ParametroService;
import com.edicsem.pe.sie.service.facade.RefinanciarPagoService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
import com.edicsem.pe.sie.service.facade.TipoLLamadaService;
import com.edicsem.pe.sie.service.facade.TipoRefinanciaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name="seguimientoCobranzaOpera")
@SessionScoped
public class MantenimientoCobranzaOperaSearchAction extends BaseMantenimientoAbstractAction {
    /*variables*/
	private CobranzaOperadoraSie objCobranzaOpera;
	private CobranzaSie objCobranza;
	private ClienteSie objcliente;
	private TelefonoPersonaSie objtelefono;
	private List<CobranzaOperadoraSie> cobranzaOperaList;
	private List<CobranzaOperadoraSie> filtrarCobranza;
	private List<CobranzaSie> detallePagos;
	private List<CobranzaSie> detallePagosRefinan;
	private List<TelefonoPersonaSie> listatelefono;
	private List<DetProductoContratoSie> productoContratoList;
	private Date fechoy;
	private int tipollamada;
	private int idRefi;
	private boolean programarLlamada, refinanciar;
	private Date fechaProgramada;
	private int idtiporefinan;
	private String mensaje;
	private boolean newRecord =false;
	private int idcontrato;
	private int idincidencia;
	private ContratoSie objContrato;
	private BigDecimal totalacumulado;
	private List<HistoricoObservacionesSie> lstHistorico;
	private RefinanciarPagoSie objRefinanPago;
	private int cantcuotasrest;
	private BigDecimal montoRestantes;
	private Date fechaProgramRest;
	private BigDecimal totalacumulafoRefi;
	
	//Consultar
	private String numDniCliente,codigoContrato,apePatCliente,apeMatCliente,nombreCliente;
	private int tamanoLista, radio,idtipodoc;
	private List<ContratoSie> contratoXClienteList;
	
	HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
	
	@EJB
	private ContratoService objContratoService;
	@EJB
	private CobranzaOperaService objCobranzaOperaService;
	@EJB
	private CobranzaService objCobranzaService;
	@EJB
	private ClienteService objClienteService;
	@EJB
	private TelefonoEmpleadoService objTelefonoService;
	@EJB
	private TipoLLamadaService objTipoLLamadaService;
	@EJB
	private DetProductoContratoService objProductoContratoService;
	@EJB
	private HistoricoObservacionesService objHistoricoService;
	@EJB
	private IncidenciaService objIncidenciaService;
	@EJB
	private EmpleadoSieService objEmpleadoService;
	@EJB
	private ParametroService objParametroService;
	@EJB
	private RefinanciarPagoService objRefinanciarPagoService;
	@EJB
	private EstadogeneralService objEstadoService;
	@EJB
	private TipoRefinanciaService objTipoRefinanService;
	
	@ManagedProperty(value = "#{comboAction}")
	private ComboAction comboManager;
	
	public static Log log = LogFactory.getLog(MantenimientoCobranzaOperaSearchAction.class);
	
	public MantenimientoCobranzaOperaSearchAction() {
		log.info("inicializando 'MantenimientoCobranzaOperaSearchAction'");
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#init()
	 */
	public void init() {
		log.info("init()");
		tamanoLista=0;
		objCobranza = new CobranzaSie();
		objCobranzaOpera = new CobranzaOperadoraSie();
		objcliente = new ClienteSie();
		objtelefono = new TelefonoPersonaSie();
		lstHistorico = new ArrayList<HistoricoObservacionesSie>();
		idcontrato=0;
		detallePagos= new ArrayList<CobranzaSie>();
		objContrato= new ContratoSie();
		detallePagosRefinan = new ArrayList<CobranzaSie>();
		productoContratoList = new ArrayList<DetProductoContratoSie>();
		listatelefono = new ArrayList<TelefonoPersonaSie>();
		contratoXClienteList = new ArrayList<ContratoSie>();
		totalacumulado =new BigDecimal(0);
		fechaProgramada= new Date();
		idcontrato=0;
		radio=1;
		idincidencia=0;
		objRefinanPago = new RefinanciarPagoSie();
		refinanciar=false;
		idtiporefinan=0;
		idRefi=1;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#listar()
	 */
	public String listar() {
		log.info("listar() 'MantenimientoCobranzaOperadoraSieSearchAction' ");
		objCobranza = new CobranzaSie();
		objCobranzaOpera = new CobranzaOperadoraSie();
		objcliente = new ClienteSie();
		objtelefono = new TelefonoPersonaSie();
		lstHistorico = new ArrayList<HistoricoObservacionesSie>();
		detallePagos= new ArrayList<CobranzaSie>();
		objContrato= new ContratoSie();
		detallePagosRefinan = new ArrayList<CobranzaSie>();
		productoContratoList = new ArrayList<DetProductoContratoSie>();
		listatelefono = new ArrayList<TelefonoPersonaSie>();
		totalacumulado =new BigDecimal(0);
		fechaProgramada= new Date();
		idcontrato=0;
		idincidencia=0;
		objRefinanPago = new RefinanciarPagoSie();
		refinanciar=false;
		idtiporefinan=0;
		idRefi=1;
		cobranzaOperaList = objCobranzaOperaService.listarCobranzasOpera(sessionUsuario.getUsuario());
		if (cobranzaOperaList == null) {
			cobranzaOperaList = new ArrayList<CobranzaOperadoraSie>();
		}
		return getViewList();
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String mostrar() throws Exception {
		log.info("mostrar()");
		if(idcontrato==0){
			idcontrato = objCobranzaOpera.getTbCobranza().getTbContrato().getIdcontrato();
		}
		// comenzamos a mostrar el detalles
		log.info("listarDetalleDePagos 'MantenimientoCobranzasPorCuotasSearchAction' ");
		detallePagos = objCobranzaService.listarCobranzasXidcontrato(idcontrato);
			if (detallePagos == null) {
				detallePagos = new ArrayList<CobranzaSie>();
			}else{
				totalacumulado= BigDecimal.ZERO;
				for (int i = 0; i < detallePagos.size(); i++) {
					//seteamos  el precio a pagar
					log.info("fec "+detallePagos.get(i).getFecpago());
					if(detallePagos.get(i).getFecpago()==null){
						log.info("fec "+detallePagos.get(i).getImportemasmora());
						totalacumulado = totalacumulado.add(detallePagos.get(i).getImportemasmora());
					}
				}
			}
		idRefi=1;
		fechaProgramada= new Date();
		fechaProgramRest = new Date();
		objContrato = objContratoService.findContrato(idcontrato);
	    // mostramos los datos del cliente
		objcliente = objClienteService.findCliente(objContrato.getTbCliente().getIdcliente());
		comboManager.setIdtipocliente(objcliente.getTbTipoCliente().getIdtipocliente());
		log.info("listartelefonos x idcliente "+totalacumulado);
		listatelefono = objTelefonoService.listarTelefonoEmpleadosXidcliente(objcliente.getIdcliente());
		if (listatelefono == null) {
			listatelefono = new ArrayList<TelefonoPersonaSie>();
		}
		idtiporefinan=0;
		//productos del contrato
		productoContratoList =objProductoContratoService.listarDetProductoContratoXContrato(idcontrato);
		//Mostramos el historial
		lstHistorico = objHistoricoService.listarHistorial(idcontrato);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#consultar()
	 */
	public String consultar() throws Exception {
		log.info("consultar() ");
		contratoXClienteList = objContratoService.listarClientePorParametro(numDniCliente, codigoContrato, nombreCliente, apePatCliente, apeMatCliente);
		objcliente = new ClienteSie();
		detallePagos = new ArrayList<CobranzaSie>();
		objContrato = new ContratoSie();
		productoContratoList = new ArrayList<DetProductoContratoSie>();
		totalacumulado= new BigDecimal(0);
		lstHistorico = new ArrayList<HistoricoObservacionesSie>();
		objCobranzaOpera = new CobranzaOperadoraSie();
		if(contratoXClienteList.size()==1){
			log.info("consultar() == 1 ");
			objcliente = objClienteService.findCliente(contratoXClienteList.get(0).getTbCliente().getIdcliente());
			log.info("  " + objcliente.getIdcliente()+"  "+objcliente.getNombresCompletos());
			listatelefono = objTelefonoService.listarTelefonoEmpleadosXidcliente(objcliente.getIdcliente());
			productoContratoList =objProductoContratoService.listarDetProductoContratoXContrato(contratoXClienteList.get(0).getIdcontrato());
			objContrato = objContratoService.findContrato(contratoXClienteList.get(0).getIdcontrato());
			detallePagos = objCobranzaService.listarCobranzasXidcontrato(contratoXClienteList.get(0).getIdcontrato());
			for (int i = 0; i < detallePagos.size(); i++) {
				if(detallePagos.get(i).getFecpago()==null){
				totalacumulado = totalacumulado.add(detallePagos.get(i).getImportemasmora());
				}
			}
			lstHistorico = objHistoricoService.listarHistorial(contratoXClienteList.get(0).getIdcontrato());
			//Mostrar el registro de la última llamada
			
			mensaje = "Consulto realizada";
		}
		else if(contratoXClienteList.size()==0){
			mensaje = "Consulto realizada, no se encontraron datos";
		}
		log.info(" lista x consultaaa "+contratoXClienteList.size()+" MENSAJE "+ mensaje);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Constants.MESSAGE_INFO_TITULO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
	
	/**
	 * Registrar la refinanciacion
	 */
	public void registrarRefinanciacion(){
		log.info("registrarRefinanciacion "+ idtiporefinan);
		List<CobranzaSie> listFaltaPagar= new ArrayList<CobranzaSie>();
		detallePagosRefinan= new ArrayList<CobranzaSie>();
		TipoRefinanciaSie objTipoRefina = objTipoRefinanService.findTipoRefinancia(idtiporefinan);
		if(idtiporefinan !=0){
			//seleccionar solo las que se adeuden
			for (int i = 0; i < detallePagos.size() ; i++) {
				if(detallePagos.get(i).getFecpago()==null){
					listFaltaPagar.add(detallePagos.get(i));
				}
			}
				//primeras cuotas con mora
			Calendar c = new GregorianCalendar();
			log.info("pagar cuotas con mora "+objTipoRefina.getCuotaconmora());
				for (int i = 0; i < objTipoRefina.getCuotaconmora(); i++) {
					CobranzaSie objCob = new CobranzaSie();
					objCob.setNumletra(listFaltaPagar.get(i).getNumletra());
					objCob.setImpinicial(listFaltaPagar.get(i).getImportemasmora());
					objCob.setFecvencimiento(c.getTime());
					detallePagosRefinan.add(objCob);
				}
				//cuotas Restantes
				log.info("pagar cuotas con mora "+listFaltaPagar.size());
				log.info("Fecha prog rest "+fechaProgramRest);
				for (int i = objTipoRefina.getCuotaconmora(); i < listFaltaPagar.size() ; i++) {
					log.info("Calendar  gettime "+c.getTime());
					CobranzaSie objCob = new CobranzaSie();
					objCob.setNumletra(listFaltaPagar.get(i).getNumletra());
					objCob.setImpinicial(listFaltaPagar.get(i).getImpinicial().add(objTipoRefina.getAumentocuotarest()));
					c.setTime(fechaProgramRest);
					c.add(Calendar.MONTH, i);
					log.info("Fecha gettime "+c.getTime());
					objCob.setFecvencimiento(c.getTime());
					detallePagosRefinan.add(objCob);
				}
			}
		else{
		/*Primera cuota*/
		CobranzaSie cob = new CobranzaSie();
		cob.setImpinicial(objRefinanPago.getImpapagar());
		cob.setFecvencimiento(objRefinanPago.getFechaprogramada());
		cob.setNumletra("0");
		detallePagosRefinan.add(cob);
		/*Cuotas restantes*/
		for (int i = 0; i < cantcuotasrest; i++) {
			cob = new CobranzaSie();
			cob.setImpinicial(montoRestantes);
			Calendar c = new GregorianCalendar();
			c.setTime(fechaProgramRest);
			c.add(Calendar.MONTH, i);
			cob.setNumletra(i+"");
			cob.setFecvencimiento(c.getTime());
			detallePagosRefinan.add(cob);
		}
		
		String antiguoPago="", refinanPago="";
		for (int i = 0; i < detallePagos.size(); i++) {
			antiguoPago+=detallePagos.get(i).getNumletra();
			antiguoPago+=","+detallePagos.get(i).getImpinicial();
			antiguoPago+=","+detallePagos.get(i).getFechaVencimientoString()+";";
		}
		for (int i = 0; i < detallePagosRefinan.size(); i++) {
			refinanPago+=detallePagos.get(i).getNumletra();
			refinanPago+=","+detallePagos.get(i).getImpinicial();
			refinanPago+=","+detallePagos.get(i).getFechaVencimientoString()+";";
		}
		
		objRefinanPago.setAntiguoPago(antiguoPago);
		objRefinanPago.setRefinanciadoPago(refinanPago);
		objRefinanPago.setTbContrato(objContrato);
		}
		totalacumulafoRefi= new BigDecimal(0.0);
		for (int i = 0; i < detallePagosRefinan.size(); i++) {
			totalacumulafoRefi =totalacumulafoRefi.add(detallePagosRefinan.get(i).getImpinicial());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar()");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie) session.getAttribute(Constants.USER_KEY);
		
		try {
			if(idcontrato!=0){
				
				objCobranzaOpera.setTbTipoLlamada(objTipoLLamadaService.findTipoLLamada(tipollamada));
				
				if(idincidencia!=0){
				IncidenciaSie in = objIncidenciaService.findIncidencia(idincidencia);
				
					//Enviar mensaje dependiendo del incidente encontrado
					if(in.getUsuariosenviomsj()!=null){
						String[] usuario = in.getUsuariosenviomsj().split(",");
						for (int j = 0; j < usuario.length; j++) {
							EmpleadoSie emp = objEmpleadoService.buscarEmpleadosPorUsuario(usuario[j]);
							
							if(SMTPConfig.sendMail(in.getDescripcion(),objCobranzaOpera.getObservaciones(),emp.getCorreo())){
								  log.info("envío Correcto");
							}else {
								log.info("envío Fallido, será enviado al administrador del sistema ");
							       objParametroService.buscarPorDescripcion(Constants.PARAM_ADMINISTRADOR_SISTEMA);
							       mensaje ="El siguiente correo no pudo ser enviado satisfactoriamente, por favor corregir dicho error: \n";
							       mensaje +="Para: "+emp.getCorreo();
							       mensaje +="De: "+sessionUsuario.getCorreo();
							       mensaje +="Mensaje : "+ objCobranzaOpera.getObservaciones();
							       
							       if(SMTPConfig.sendMail(in.getDescripcion(),mensaje,emp.getCorreo())){
							    	   mensaje=null;
							       }
							}
						}
					}
				}
				if(objCobranzaOpera.getFechapromesapago()!=null){
					objCobranzaOpera.setTbEstadoGeneral(objEstadoService.findEstadogeneral(110));
				}
				//Volver a llamar
				else if(fechaProgramada!=null){
					objCobranzaOpera.setFechaprogramada(new Timestamp(fechaProgramada.getTime()));
					objCobranzaOpera.setTbEstadoGeneral(objEstadoService.findEstadogeneral(109));
				}else{
					//No mostrar (Llamada finalizada)
					objCobranzaOpera.setTbEstadoGeneral(objEstadoService.findEstadogeneral(110));
				}
				//Actualizar la Cobranza de la operadora
				objCobranzaOpera.setUsuariomodifica(sessionUsuario.getUsuario());
				Calendar cal = new GregorianCalendar();
				objCobranzaOpera.setFechamodifica(new Timestamp(cal.getTimeInMillis()));
				objCobranzaOperaService.updateCobranzaOpera(objCobranzaOpera);
				mensaje =Constants.MESSAGE_REGISTRO_TITULO;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_REGISTRO_TITULO, mensaje);	
				//Insertando la refinanciacion acordada
				if(refinanciar){
					objRefinanciarPagoService.insertRefinanPago(objRefinanPago);
				}
			}else{
				//No selecciono ningun contrato
				mensaje = Constants.MESSAGE_ERROR_ID_COBRANZA;
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_REGISTRO_TITULO, mensaje);
			}
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = e.getMessage();
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,Constants.MESSAGE_ERROR_FATAL_TITULO, mensaje);
			log.error(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return listar();
	}
	
	/*GETs Y SETs*/
	public String getViewList() {
		return "manteCobranzaOperaList";
	}

	/**
	 * @return the objCobranzaOpera
	 */
	public CobranzaOperadoraSie getObjCobranzaOpera() {
		return objCobranzaOpera;
	}

	/**
	 * @param objCobranzaOpera the objCobranzaOpera to set
	 */
	public void setObjCobranzaOpera(CobranzaOperadoraSie objCobranzaOpera) {
		this.objCobranzaOpera = objCobranzaOpera;
	}

	/**
	 * @return the cobranzaOperaList
	 */
	public List<CobranzaOperadoraSie> getCobranzaOperaList() {
		return cobranzaOperaList;
	}

	/**
	 * @param cobranzaOperaList the cobranzaOperaList to set
	 */
	public void setCobranzaOperaList(List<CobranzaOperadoraSie> cobranzaOperaList) {
		this.cobranzaOperaList = cobranzaOperaList;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the detallePagos
	 */
	public List<CobranzaSie> getDetallePagos() {
		return detallePagos;
	}

	/**
	 * @param detallePagos the detallePagos to set
	 */
	public void setDetallePagos(List<CobranzaSie> detallePagos) {
		this.detallePagos = detallePagos;
	}

	/**
	 * @return the objCobranza
	 */
	public CobranzaSie getObjCobranza() {
		return objCobranza;
	}

	/**
	 * @param objCobranza the objCobranza to set
	 */
	public void setObjCobranza(CobranzaSie objCobranza) {
		this.objCobranza = objCobranza;
	}

	/**
	 * @return the objcliente
	 */
	public ClienteSie getObjcliente() {
		return objcliente;
	}

	/**
	 * @param objcliente the objcliente to set
	 */
	public void setObjcliente(ClienteSie objcliente) {
		this.objcliente = objcliente;
	}

	/**
	 * @return the objtelefono
	 */
	public TelefonoPersonaSie getObjtelefono() {
		return objtelefono;
	}

	/**
	 * @param objtelefono the objtelefono to set
	 */
	public void setObjtelefono(TelefonoPersonaSie objtelefono) {
		this.objtelefono = objtelefono;
	}

	/**
	 * @return the listatelefono
	 */
	public List<TelefonoPersonaSie> getListatelefono() {
		return listatelefono;
	}

	/**
	 * @param listatelefono the listatelefono to set
	 */
	public void setListatelefono(List<TelefonoPersonaSie> listatelefono) {
		this.listatelefono = listatelefono;
	}


	/**
	 * @return the tipollamada
	 */
	public int getTipollamada() {
		return tipollamada;
	}

	/**
	 * @param tipollamada the tipollamada to set
	 */
	public void setTipollamada(int tipollamada) {
		this.tipollamada = tipollamada;
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
	 * @return the filtrarCobranza
	 */
	public List<CobranzaOperadoraSie> getFiltrarCobranza() {
		return filtrarCobranza;
	}

	/**
	 * @param filtrarCobranza the filtrarCobranza to set
	 */
	public void setFiltrarCobranza(List<CobranzaOperadoraSie> filtrarCobranza) {
		this.filtrarCobranza = filtrarCobranza;
	}

	/**
	 * @return the objContrato
	 */
	public ContratoSie getObjContrato() {
		return objContrato;
	}

	/**
	 * @param objContrato the objContrato to set
	 */
	public void setObjContrato(ContratoSie objContrato) {
		this.objContrato = objContrato;
	}

	/**
	 * @return the fechoy
	 */
	public Date getFechoy() {
		try {
			fechoy = DateUtil.getToday().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechoy;
	}

	/**
	 * @param fechoy the fechoy to set
	 */
	public void setFechoy(Date fechoy) {
		this.fechoy = fechoy;
	}

	/**
	 * @return the productoContratoList
	 */
	public List<DetProductoContratoSie> getProductoContratoList() {
		return productoContratoList;
	}

	/**
	 * @param productoContratoList the productoContratoList to set
	 */
	public void setProductoContratoList(
			List<DetProductoContratoSie> productoContratoList) {
		this.productoContratoList = productoContratoList;
	}

	/**
	 * @return the lstHistorico
	 */
	public List<HistoricoObservacionesSie> getLstHistorico() {
		return lstHistorico;
	}

	/**
	 * @param lstHistorico the lstHistorico to set
	 */
	public void setLstHistorico(List<HistoricoObservacionesSie> lstHistorico) {
		this.lstHistorico = lstHistorico;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the programarLlamada
	 */
	public boolean isProgramarLlamada() {
		return programarLlamada;
	}

	/**
	 * @param programarLlamada the programarLlamada to set
	 */
	public void setProgramarLlamada(boolean programarLlamada) {
		this.programarLlamada = programarLlamada;
	}

	/**
	 * @return the fechaProgramada
	 */
	public Date getFechaProgramada() {
		return fechaProgramada;
	}

	/**
	 * @param fechaProgramada the fechaProgramada to set
	 */
	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	/**
	 * @return the idincidencia
	 */
	public int getIdincidencia() {
		return idincidencia;
	}

	/**
	 * @param idincidencia the idincidencia to set
	 */
	public void setIdincidencia(int idincidencia) {
		this.idincidencia = idincidencia;
	}

	/**
	 * @return the detallePagosRefinan
	 */
	public List<CobranzaSie> getDetallePagosRefinan() {
		return detallePagosRefinan;
	}

	/**
	 * @param detallePagosRefinan the detallePagosRefinan to set
	 */
	public void setDetallePagosRefinan(List<CobranzaSie> detallePagosRefinan) {
		this.detallePagosRefinan = detallePagosRefinan;
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
	 * @return the objRefinanPago
	 */
	public RefinanciarPagoSie getObjRefinanPago() {
		return objRefinanPago;
	}

	/**
	 * @param objRefinanPago the objRefinanPago to set
	 */
	public void setObjRefinanPago(RefinanciarPagoSie objRefinanPago) {
		this.objRefinanPago = objRefinanPago;
	}

	/**
	 * @return the cantcuotasrest
	 */
	public int getCantcuotasrest() {
		return cantcuotasrest;
	}

	/**
	 * @param cantcuotasrest the cantcuotasrest to set
	 */
	public void setCantcuotasrest(int cantcuotasrest) {
		this.cantcuotasrest = cantcuotasrest;
	}

	/**
	 * @return the montoRestantes
	 */
	public BigDecimal getMontoRestantes() {
		return montoRestantes;
	}

	/**
	 * @param montoRestantes the montoRestantes to set
	 */
	public void setMontoRestantes(BigDecimal montoRestantes) {
		this.montoRestantes = montoRestantes;
	}

	/**
	 * @return the fechaProgramRest
	 */
	public Date getFechaProgramRest() {
		return fechaProgramRest;
	}

	/**
	 * @param fechaProgramRest the fechaProgramRest to set
	 */
	public void setFechaProgramRest(Date fechaProgramRest) {
		this.fechaProgramRest = fechaProgramRest;
	}

	public boolean isRefinanciar() {
		return refinanciar;
	}

	public void setRefinanciar(boolean refinanciar) {
		this.refinanciar = refinanciar;
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
	 * @return the idtiporefinan
	 */
	public int getIdtiporefinan() {
		return idtiporefinan;
	}

	/**
	 * @param idtiporefinan the idtiporefinan to set
	 */
	public void setIdtiporefinan(int idtiporefinan) {
		this.idtiporefinan = idtiporefinan;
	}

	/**
	 * @return the idRefi
	 */
	public int getIdRefi() {
		return idRefi;
	}

	/**
	 * @param idRefi the idRefi to set
	 */
	public void setIdRefi(int idRefi) {
		this.idRefi = idRefi;
	}

	/**
	 * @return the totalacumulafoRefi
	 */
	public BigDecimal getTotalacumulafoRefi() {
		return totalacumulafoRefi;
	}

	/**
	 * @param totalacumulafoRefi the totalacumulafoRefi to set
	 */
	public void setTotalacumulafoRefi(BigDecimal totalacumulafoRefi) {
		this.totalacumulafoRefi = totalacumulafoRefi;
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
	 * @return the radio
	 */
	public int getRadio() {
		return radio;
	}

	/**
	 * @param radio the radio to set
	 */
	public void setRadio(int radio) {
		this.radio = radio;
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
}
