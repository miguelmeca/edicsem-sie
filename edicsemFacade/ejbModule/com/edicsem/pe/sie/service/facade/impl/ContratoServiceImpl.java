package com.edicsem.pe.sie.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.EmpleadoDTO;
import com.edicsem.pe.sie.beans.EntregasPeruDTO;
import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.HistoricoObservacionesSie;
import com.edicsem.pe.sie.entity.LugarVentaSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetPaqueteDAO;
import com.edicsem.pe.sie.model.dao.DetProductoContratoDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.LugarVentaDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.HistoricoObservacionesService;
import com.edicsem.pe.sie.service.facade.TipoClienteService;

@Stateless
public class ContratoServiceImpl implements ContratoService {
	@EJB
	private CargoEmpleadoDAO objCargoDao;
	@EJB
	private ClienteDAO objClienteDao;
	@EJB
	private TelefonoEmpleadoDAO objTelefonoDao;
	@EJB
	private DomicilioEmpleadoDAO objDomicilioDao;
	@EJB
	private ContratoDAO objContratoDao;
	@EJB
	private CobranzaDAO objCobranzaDao;
	@EJB
	private DetProductoContratoDAO objDetProductoContratoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private TipoDocumentoDAO objtipoDao;
	@EJB
	private UbigeoDAO objUbigeoDao;
	@EJB
	private TipoCasaDAO objTipoCasaDao;
	@EJB
	private EmpresaDAO objEmpresaDao;
	@EJB
	private DetContratoEmpleadoDAO objDetContratoEmpleadoDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private ProductoDAO objProductoDAO;
	@EJB
	private AlmacenDAO objAlmacenDAO;
	@EJB
	private DetPaqueteDAO objDetPaqueteDAO;
	@EJB
	private TipoEventoVentaDAO objTipoEventoVentaDAO;
	@EJB
	private LugarVentaDAO objLugarVentaDAO;
	@EJB
	private HistoricoObservacionesService objHistorialDao;
	@EJB
	private TipoClienteService objTipoClienteDao;
	
	public static Log log = LogFactory.getLog(ContratoServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#insertContrato(int, int, int, int, com.edicsem.pe.sie.entity.ClienteSie, java.util.List, com.edicsem.pe.sie.entity.DomicilioPersonaSie, com.edicsem.pe.sie.entity.ContratoSie, java.util.List, java.util.List, java.util.List, int)
	 */
	public void insertContrato(int idtipodoc,int Tipocasa,int idUbigeo,int  idempresa, ClienteSie  cliente, List<TelefonoPersonaSie> telefonoList, DomicilioPersonaSie domicilio,  ContratoSie contrato,List<DetProductoContratoSie> detprodcont, List<CobranzaSie> cobranza,  List<EmpleadoDTO>  detidEmpleadosList,int tipoVenta, int idpuntoventa) {
		cliente.setTbTipoDocumentoIdentidad(objtipoDao.buscarTipoDocumento(idtipodoc));
		cliente.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(23));
		cliente.setTbTipoCliente(objTipoClienteDao.findTipoCliente(1));
		objClienteDao.insertCliente(cliente);
		for (TelefonoPersonaSie telefonoPersonaSie : telefonoList) {
			telefonoPersonaSie.setIdcliente(cliente);
			telefonoPersonaSie.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
			objTelefonoDao.insertarTelefonoEmpleado(telefonoPersonaSie);
		}
		domicilio.setTbTipoCasa(objTipoCasaDao.findTipoCasa(Tipocasa));
		domicilio.setTbUbigeo(objUbigeoDao.findUbigeo(idUbigeo));
		domicilio.setIdcliente(cliente);
		domicilio.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
		objDomicilioDao.insertarDomicilioEmpleado(domicilio);
		log.info(" inser domicilio "  );
		contrato.setTbCliente(cliente);
		contrato.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(25));
		contrato.setTbEmpresa(objEmpresaDao.findEmpresa(idempresa));
		contrato.setTbTipoEvento(objTipoEventoVentaDAO.findTipoEventoVenta(tipoVenta));
		contrato.setTbPuntoVenta(objAlmacenDAO.findAlmacen(idpuntoventa));
		log.info(" INSER CLIENTE" );
		objContratoDao.insertContrato(contrato);
		log.info("contrato insertado!  "+contrato.getIdcontrato());
		for (DetProductoContratoSie detprodcontrato : detprodcont) {
			detprodcontrato.setTbContrato(objContratoDao.findContrato(contrato.getIdcontrato()));
			objDetProductoContratoDao.insertDetProductoContrato(detprodcontrato);
		}
		log.info(" terminado tama�o cobranza " + cobranza.size());
		for (CobranzaSie objcobranza : cobranza) {
			objcobranza.setCantcuotas(""+ contrato.getNumcuotas());
			objcobranza.setIdcliente(cliente.getIdcliente());
			objcobranza.setIdcontrato(contrato.getIdcontrato());
			objcobranza.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(28));
			objcobranza.setTbContrato(objContratoDao.findContrato(contrato.getIdcontrato()));
			objcobranza.setTbCliente(cliente);
			objCobranzaDao.insertCobranza(objcobranza);
			log.info(" terminado cobranza " );
		}
		log.info(" terminado DET  :D " +detidEmpleadosList.size());
		
		DetContratoEmpleadoSie d;
		for (int i = 0; i < detidEmpleadosList.size() ; i++) {
			d = new DetContratoEmpleadoSie();
			d.setTbEmpleado(objEmpleadoDao.buscarEmpleado(detidEmpleadosList.get(i).getIdempleado()));
			d.setTbCargoempleado(objCargoDao.buscarCargoEmpleado(detidEmpleadosList.get(i).getCargo()));
			log.info("contrato ************************** ---> !  "+contrato.getIdcontrato());
			d.setTbContrato(objContratoDao.findContrato(contrato.getIdcontrato()));
			d.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(32));
			objDetContratoEmpleadoDao.insertDetContratoEmpleado(d);
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#updateContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	public void updateContrato(ContratoSie contrato) {
		objContratoDao.updateContrato(contrato);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#findContrato(int)
	 */
	
	public ContratoSie findContrato(int id) {
		return objContratoDao.findContrato(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#listarContratos()
	 */
	public List listarContratos() {
		return objContratoDao.listarContratos();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#listarClientePorParametro(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List listarClientePorParametro(String numDocumento, String codigoContrato, String nombreCliente, String apePat, String apeMat) {
		return objContratoDao.listarClientePorParametro(numDocumento, codigoContrato, nombreCliente, apePat, apeMat);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#insertMigracion(java.util.List)
	 */
	public String updateEntregasPeru(List<EntregasPeruDTO> credito) {
		String mensaje=null;
		ContratoSie con = new ContratoSie();
		DetProductoContratoSie detprodcon = new DetProductoContratoSie();
		DetContratoEmpleadoSie detconemp = new DetContratoEmpleadoSie();
		CargoEmpleadoSie cargoemp = new CargoEmpleadoSie();
		ProductoSie produc = new ProductoSie();	
		PuntoVentaSie punto = new PuntoVentaSie();
		TipoEventoVentaSie tipoEvento= null;
		List<DetPaqueteSie> lista =null;
		LugarVentaSie lugar =null;
		boolean isaddPaquete=false;
		log.info("actualizar() *");
		
		for (int i = 0; i < credito.size(); i++) {
		EntregasPeruDTO s = credito.get(i);
		detprodcon = new DetProductoContratoSie();
		con = objContratoDao.buscarXcodigoContrato(s.getNumerodecontrato());
		isaddPaquete=false;
		log.info("Num-Contrato"+"--> "+con.getCodcontrato()+ "--ID DE CONTRATO"+ con.getIdcontrato());
		if(s.getLugardelaentrega().equalsIgnoreCase("Punto de Venta")){
			punto = objAlmacenDAO.buscarIdpuntoVenta(s.getPuntodeventa());
			if (punto!=null) {
				log.info("NOMBRE-PUNTO-VENTA"+"-->"+ punto.getDescripcion() + "ID-PUNTO-VENTA"+"-->"+punto.getIdpuntoventa());
				con.setTbPuntoVenta(punto);
			}else{
				mensaje="No se encontr� el punto de Venta '"+s.getPuntodeventa()+"', por favor insertarlo";
				break;
			}
		}else{
			lugar = objLugarVentaDAO.findLugarVenta(s.getLugardelaentrega());
			if(lugar!=null){
				con.setTbLugarVenta(lugar);
				con.setLugarentrega(s.getPuntodeventa());
			}else{
				//insertamos el lugar de entrega
				LugarVentaSie lu = new LugarVentaSie();
				objLugarVentaDAO.insertLugarVenta(lu);
				con.setTbLugarVenta(lu);
				con.setLugarentrega(s.getPuntodeventa());
			}
		}
		con.setFechaentrega(s.getFecha());
		log.info("Fecha de Entrega"+ "-->"+con.getFechaentrega());			
		con.setTbEmpresa(objEmpresaDao.buscarIdEmpresa(s.getEmpresa().trim().toUpperCase()));
		log.info("Id-Empresa"+"-->"+con.getTbEmpresa().getIdempresa());
		con.setTbCliente(objClienteDao.buscarIdCliente(s.getDnidelcliente()));
		log.info("Id-Cliente"+"-->"+con.getTbCliente().getIdcliente().toString());
		
		tipoEvento = objTipoEventoVentaDAO.findTipoEventoVenta(s.getEventodeventa().trim().toUpperCase());
		if(tipoEvento!=null){
			con.setTbTipoEvento(tipoEvento);
		}else{
			mensaje="Debe registrar el tipo de evento "+s.getEventodeventa().trim().toUpperCase();
			break;
		}
		con.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(25));
		objContratoDao.updateContrato(con);
		
		/***DET_PRODUCTO_CONTRATO***/
		
		detprodcon.setTbContrato(con);
		ProductoSie obj1 = null;
		log.info("ID DE CONTRATO PARA DET_PRODUCTO_CONTRATO"+"-->"+ con.getIdcontrato());
		detprodcon.setCantidad(s.getCantidaddemercaderia());
		log.info("Cantidad de Mercaderia"+ "-->"+ detprodcon.getCantidad());
		detprodcon.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(74));
		obj1 = objProductoDAO.buscarXcodigoProducto(s.getCodigodemercaderia());
		if (obj1.getCodproducto()!=null) {
			log.info("CODIGO_PRODUCTO"+"-->  "+obj1.getCodproducto() +" -- " + "ID_PRODUCTO-->"+ obj1.getIdproducto());
			detprodcon.setTbProducto(obj1);
			objDetProductoContratoDao.insertDetProductoContrato(detprodcon);
			isaddPaquete=true;
		}
		else{
			lista = objDetPaqueteDAO.buscarXcodigoPaquete(s.getCodigodemercaderia());
				for (int j = 0; j <lista.size(); j++) {
					DetProductoContratoSie det= new DetProductoContratoSie();
					det.setTbContrato(con);
					log.info("ID DE CONTRATO PARA DETALLE"+"-->"+ con.getIdcontrato());
					det.setCantidad(s.getCantidaddemercaderia());
					log.info("Cantidad de Mercaderia"+ "-->"+ detprodcon.getCantidad());
					det.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(74));
					produc= objProductoDAO.findProducto(lista.get(j).getTbProducto().getIdproducto());	 
					det.setTbProducto(produc);	
					objDetProductoContratoDao.insertDetProductoContrato(det);
					isaddPaquete=true;
				}
		}
		if(isaddPaquete!=true){
			mensaje="No se encontr� el producto o paquete on el c�digo '"+s.getCodigodemercaderia()+"', por favor insertarlo";
			break;
		}
		/****DET_CONTRATO_EMPLEADO****/
		
		if (s.getNombredelvendedor()!=null) {
			detconemp = new DetContratoEmpleadoSie();
			detconemp.setTbEmpleado(objEmpleadoDao.buscarEmpleadoPorNombreCompleto(s.getNombredelvendedor()));
			if(detconemp.getTbEmpleado()==null){
				mensaje="No se registr� el empleado "+s.getNombredelexpositor()+" como EXPOSITOR";
				break;
			}else{
				log.info("ID-VENDEDOR igual ah ID-EMPLEADO"+"--> "+ detconemp.getTbEmpleado().getIdempleado());
				detconemp.setTbCargoempleado(objCargoDao.buscarCargoEmpleado("VENDEDOR"));
				detconemp.setTbContrato(con);
				log.info("ID DE CONTRATO-TB_DET_CONTRATO_EMPLEADO"+"-->"+ con.getIdcontrato()+" -- "+ con.getCodcontrato());
				detconemp.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(58));
				objDetContratoEmpleadoDao.insertDetContratoEmpleado(detconemp);
			}
		}
		
		if (s.getNombredelexpositor()!=null) {
			detconemp = new DetContratoEmpleadoSie();
			detconemp.setTbEmpleado(objEmpleadoDao.buscarEmpleadoPorNombreCompleto(s.getNombredelexpositor()));
			if(detconemp.getTbEmpleado()==null){
				mensaje="No se registro el empleado "+s.getNombredelexpositor()+" como EXPOSITOR";
				break;
			}else{
				log.info("ID-EXPOSITOR igual ah ID-EMPLEADO"+"--> "+ detconemp.getTbEmpleado().getIdempleado());
				detconemp.setTbCargoempleado(objCargoDao.buscarCargoEmpleado("EXPOSITOR"));
				detconemp.setTbContrato(con);
				log.info("ID DE CONTRATO-TB_DET_CONTRATO_EMPLEADO"+"-->"+ con.getIdcontrato()+" -- "+ con.getCodcontrato());
				detconemp.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(58));
				objDetContratoEmpleadoDao.insertDetContratoEmpleado(detconemp);
			}
		}
		
		if (s.getNombredelsupervisor()!=null) {
			detconemp = new DetContratoEmpleadoSie();
			detconemp.setTbEmpleado(objEmpleadoDao.buscarEmpleadoPorNombreCompleto(s.getNombredelsupervisor().toUpperCase()));
			if(detconemp.getTbEmpleado()==null){
				mensaje="No se registro el empleado "+s.getNombredelexpositor()+" como EXPOSITOR";
				break;
			}else{
			log.info("ID-SUPERVISOR igual ah ID-EMPLEADO"+"--> "+ detconemp.getTbEmpleado().getIdempleado());
			detconemp.setTbCargoempleado(objCargoDao.buscarCargoEmpleado("SUPERVISOR"));
			detconemp.setTbContrato(con);
			log.info("ID DE CONTRATO-TB_DET_CONTRATO_EMPLEADO"+"-->"+ con.getIdcontrato()+" -- "+ con.getCodcontrato());
			detconemp.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(58));
			objDetContratoEmpleadoDao.insertDetContratoEmpleado(detconemp);
			}
		}
		}
		return mensaje;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#insertMigracion(java.util.List, java.util.List, java.lang.String)
	 */
	public String insertMigracion(List<SistemaIntegradoDTO> sistMig, List<SistemaIntegradoDTO> sisMigUpdate, String usuariocreacion) {
		log.info("insertMigracion()");
		String mensaje=null;
		ClienteSie cli = new ClienteSie();
		ContratoSie con = new ContratoSie();
		TelefonoPersonaSie tel = new TelefonoPersonaSie();
		DomicilioPersonaSie dom = new DomicilioPersonaSie();
		List<DomicilioPersonaSie> domList= new ArrayList<DomicilioPersonaSie>() ;
		List<TelefonoPersonaSie> telList= new ArrayList<TelefonoPersonaSie>() ;
		List<String> telefonoString= new ArrayList<String>();
		List<String> domicilioString= new ArrayList<String>();
		String codigoContr = "";
		boolean isadd = false;
		List<UbigeoSie> ubi = null;
		try {
		
		for (int i = 0; i < sistMig.size(); i++) {
			
			SistemaIntegradoDTO s = sistMig.get(i);
			if(s.getDistrito().trim().equalsIgnoreCase("S.J.L.")){
				s.setDistrito("SAN JUAN DE LURIGANCHO");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("SURCO")){
				s.setDistrito("SANTIAGO DE SURCO");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("V.E.S.")){
				s.setDistrito("VILLA EL SALVADOR");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("V.E.S")){
				s.setDistrito("VILLA EL SALVADOR");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("V.M.T.")){
				s.setDistrito("VILLA MARIA DEL TRIUNFO");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("S.J.M.")){
				s.setDistrito("SAN JUAN DE MIRAFLORES");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("S.M.P.")){
				s.setDistrito("SAN MARTIN DE PORRES");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("Ate")){
				s.setDistrito("ATE");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("Lurigancho")){
				s.setDistrito("SAN JUAN DE LURIGANCHO");
			}
			if(!s.getCodContrato().equals(codigoContr)){
				telList= new ArrayList<TelefonoPersonaSie>();
				domList= new ArrayList<DomicilioPersonaSie>();
				log.info("nuevo contrato  --> "+s.getCodContrato()+"  "+codigoContr);
				cli = new ClienteSie();
				con = new ContratoSie();
				telefonoString = new ArrayList<String>();
				domicilioString = new ArrayList<String>();
				con.setCodcontrato(s.getCodContrato());
				//insertar clliente
				cli.setApematcliente(s.getApematcliente());
				cli.setApepatcliente(s.getApepatcliente());
				cli.setNombrecliente(s.getNombrecliente());
				cli.setNumdocumento(s.getNumdocumento());
				cli.setCorreo(s.getCorreo());
				cli.setEmpresatrabajo(s.getEmpresatrabajo());
				cli.setCargotrabajo(s.getCargotrabajo());
				cli.setDirectrabajo(s.getDirectrabajo());
				cli.setPlanoTrabajo(s.getPlanoTrabajo());
				cli.setLetraTrabajo(s.getLetraSectorTrabajo());
				cli.setSectorTrabajo(s.getNumSectorTrabajo());
				cli.setFecnacimiento(s.getFecnacimiento());
				
				String [ ] telTraba = s.getTelftrabajo().trim().split("([\\s-+(]+)");
				
				if(telTraba.length==1){
				cli.setTelftrabajo(telTraba[0]);
				}
				
				if(telTraba.length>1){
					for (int j = 0; j < telTraba.length; j++) {
						
						if(telTraba[j].trim().matches("([0-9]+)")){
								tel.setTelefono(telTraba[j].toString().trim());
								if(telTraba.length>j+1){
									if(telTraba[j+1].toString().trim().matches("([a-zA-Z(]//s)+") ){
									tel.setDescTelefono(telTraba[j+1].toString().trim());
								}
								}
						}
						if(telTraba[j].toString().trim().matches("([a-zA-Z(]//s)+") ){
							tel.setDescTelefono(telTraba[j].toString().trim());
							if(telTraba.length>j+1){
							if(telTraba[j+1].trim().matches("([0-9]+)")){
								tel.setTelefono(telTraba[j+1].toString().trim());
							}
							}
						}
						if(tel.getTelefono()!=null ){
							if(tel.getTelefono().matches("\\d{9}")){
								log.info("telefono ");
								tel.setTipotelefono("C");
							}else {
								tel.setTipotelefono("F");
							}
						tel.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
						tel.setIdcliente(cli);
						if(tel.getDescTelefono()==null){
							tel.setDescTelefono("Trabajo");
						}
						telList.add(tel);
						tel= new TelefonoPersonaSie();
						}
					}
				}
				
				cli.setTitulartelefono(s.getTitulartelefono());
				cli.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(23)); 
				cli.setTbTipoDocumentoIdentidad(objtipoDao.buscarTipoDocumento(1));
				cli.setTbTipoCliente(objTipoClienteDao.findTipoCliente(1));
				
				log.info(" insertando cliente:  "+ cli.getNombrecliente()+" ape. pat "+cli.getApepatcliente() );
				cli.setUsuariocreacion(usuariocreacion);
				objClienteDao.insertCliente(cli);
				con.setFechaentrega(s.getFechaEntrega());
				con.setNumcuotas(s.getCantCuotas());
				con.setPagosubinicial(new  BigDecimal(s.getImporteInicial()));
				EmpresaSie empr = objEmpresaDao.findEmpresaXdescripcion(s.getEmpresa());
				if(empr!=null){
					con.setTbEmpresa(empr);
				}else{
					mensaje="No se encontr� la empresa '"+s.getEmpresa()+"' en la base de datos, por favor consultar al administrador";
					break;
				}
				
				con.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(25));
				con.setTbCliente(cli);
				//insertar contrato
				con.setUsuariocreacion(usuariocreacion);
				objContratoDao.insertContrato(con);
				log.info("  insertando contrato * "+con.getCodcontrato());
				
				//insertar el historial
				if(s.getHistoria()!=null||s.getHistoria().trim().equals("")){
				HistoricoObservacionesSie h = new HistoricoObservacionesSie();
				h.setObservacion(s.getHistoria());
				h.setTbContrato(con);
				h.setUsuariocreacion(usuariocreacion);
				h.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(98));
				objHistorialDao.insertHistoricoObservaciones(h);
				}
				dom = new DomicilioPersonaSie();
				dom.setDomicilio(s.getDireccion());
				dom.setTbTipoCasa(objTipoCasaDao.findTipoCasa(1));
				ubi = objUbigeoDao.findUbigeoXDescripcion(s.getDistrito().toUpperCase());
				log.info(" ubi "+ubi.size());
				if(ubi.size()==0){
					dom.setUbicacion(s.getDistrito().toUpperCase());
				}else{
					dom.setTbUbigeo(ubi.get(0));
				}
				dom.setIdcliente(cli);
				dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
				dom.setPlanoDomicilio(s.getPlanoDistrito());
				dom.setSectorDomicilio(s.getNumSector());
				dom.setLetraDomicilio(s.getLetraSector());
				domList.add(dom);
				if(domList.size()>0){
				log.info("domicilio :D "+domList.get(0).getDomicilio() );
				}
			}
			
			String [ ] telefono = s.getNumTelefono().trim().split("([\\s(-])+");
	 
			for (int j = 0; j < telefono.length; j++) {
				telefono[j]= telefono[j].toString().replaceAll("([(-)])", " "); 
			}
			
			log.info("tamano**  "+telefono.length);
			for (int j = 0; j < telefono.length; j++) { 
				 tel = new TelefonoPersonaSie();
				 
				 if(telefono[j].toString().trim().equalsIgnoreCase("C")||telefono[j].toString().trim().equalsIgnoreCase("(CLARO)")||telefono[j].toString().trim().equalsIgnoreCase("claro")){
						tel.setOperadorTelefonico("Claro");
					}
					else if(telefono[j].toString().trim().equalsIgnoreCase("M")){
						tel.setOperadorTelefonico("Movistar");
					}
					else if(telefono[j].toString().trim().equalsIgnoreCase("N")){
						tel.setOperadorTelefonico("Nextel");
					}
					else if(telefono[j].matches("[a-zA-Z(]+")){
						tel.setDescTelefono(telefono[j].toString().trim());
						if(telefono.length>j+1){
							if(telefono[j+1].toString().trim().matches("[a-zA-Z(]+") ){
								tel.setDescTelefono(tel.getDescTelefono()+" "+telefono[j+1].toString().trim());
								if(telefono.length>j+2){
									if(telefono[j+2].trim().matches("([0-9]+)")&&tel.getTelefono()==null){
										tel.setTelefono(telefono[j+2].toString().trim());
										isadd=true;
									}
								}
							}
						}else{
							isadd=true;
						}
					}

					else if(telefono[j].trim().matches("([0-9]+)")){
						tel.setTelefono(telefono[j].toString().trim());
						if(tel.getTelefono().length()< 4 && telefono.length>j+1){
							tel.setTelefono(tel.getTelefono()+" "+telefono[j+1].toString().trim());
							j=j+1;
						}
					}
				 
					if(telefono.length>j+1 && isadd==false){
						if(telefono[j+1]!=null){
							//log.info("  telef 2:  "+telefono[j+1].toString());
							if(telefono[j+1].toString().trim().equalsIgnoreCase("C")||telefono[j+1].toString().trim().equalsIgnoreCase("(CLARO)")||telefono[j+1].toString().trim().equalsIgnoreCase("claro")){
								tel.setOperadorTelefonico("Claro");
							}
							else if(telefono[j+1].toString().trim().equalsIgnoreCase("M")){
								tel.setOperadorTelefonico("Movistar");
							}
							else if(telefono[j+1].toString().trim().equalsIgnoreCase("N")){
								tel.setOperadorTelefonico("Nextel");
							}
							else if(telefono[j+1].toString().trim().matches("[a-zA-Z(]+") && tel.getDescTelefono()==null){
								tel.setDescTelefono(telefono[j+1].toString().trim());
								//nombre + apellido analizamos si el nombre es compuesto en la descripcion
								if(telefono.length>j+2){
									if(telefono[j+2].toString().trim().matches("[a-zA-Z(]+") ){
										tel.setDescTelefono(tel.getDescTelefono()+" "+telefono[j+2].toString().trim());
									}
								}
							}
							else if(telefono[j+1].trim().matches("([0-9]+)")&&tel.getTelefono()==null){
								tel.setTelefono(telefono[j+1].toString().trim());
							}
						}
					}
					
				if(tel.getTelefono()!=null && !telefonoString.contains(tel.getTelefono())){
					//definir tipo telefono
					if(tel.getTelefono()!=null ){
						if(tel.getTelefono().matches("\\d{9}")){
							log.info("telefono ");
							tel.setTipotelefono("C");
						}else {
							tel.setTipotelefono("F");
						}
					}
					telefonoString.add(tel.getTelefono());
					log.info("telefono: "+tel.getTelefono());
					tel.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
					tel.setIdcliente(cli);
					telList.add(tel);
					isadd=false;
				}
			}
			
			//6614412 (CLARO) 4852734 (CASA)
			//7969418 991289883 C
			//2475338 2478901 UBALDO
			//5651567 945379356 clienta 985374910
			//5651567 945379356 clienta 985374910
			//944470605 M     //998116218 N
			//961075202 961718820 ESPOSO
			//041-476090 992915331 C HIJA 943694723 M
			//6572969 990883811 C 606*1805 N 6377138
			//5253720 986228812 C 6572969 REF
			//2485326 994332725 C 971146127 HIJA
			//992528025 EDITH
			//56 763128
			//997830414(ESPOSO) 993701254 titular
			//4320246(HNA. ANA) 989994543
			//074 201722 979984307
			//ELSA CORRALES 980111438
			//2515079 - 2510803
			//2510803
			//074-202229
			//074300521 979838615 M
			//995034944 4691909(TRABAJO)
			//5594049 824*1972
			//971046238 M 985179605 ROMERO 986644735 LUIS ENRIQUE
			//062 525247 062 791857 962668706
			//5737224 claro 980629950
			//AMIGA CAROL 992233488 987113743
			//3588049 - 3265417 983050242
			//claro 989831101 997116377
			//5784080 ANGELA ABUELA 949262841
			//movistar 996900021 064 545458
			//no ex 5335104 995669453 M
			//ANONIMO
			//999935626 4582558 (FAMILIAR)
						
			for (int j = 0; j < domList.size(); j++) {
				domicilioString.add(domList.get(j).getDomicilio());
			}
			
			//insertar Domicilio
			
			if(!domicilioString.contains(s.getDireccion())){
				log.info("nuevo domi  " + s.getDireccion());
				dom = new DomicilioPersonaSie();
				dom.setDomicilio(s.getDireccion());
				dom.setTbTipoCasa(objTipoCasaDao.findTipoCasa(1));
				ubi = objUbigeoDao.findUbigeoXDescripcion(s.getDistrito().toUpperCase());
				if( ubi.size()==0){
					dom.setUbicacion(s.getDistrito().toUpperCase());
				}else{
					dom.setTbUbigeo(ubi.get(0));
				}
				dom.setIdcliente(cli);
				dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
				dom.setPlanoDomicilio(s.getPlanoDistrito());
				dom.setSectorDomicilio(s.getNumSector());
				dom.setLetraDomicilio(s.getLetraSector());
				domList.add(dom);
				log.info("domicilio: "+dom.getDomicilio() );
				objDomicilioDao.insertarDomicilioEmpleado(dom);
				log.info("tama�o domic  "+domList.size());
			}
			
			CobranzaSie cob = new CobranzaSie();
			cob.setCantcuotas(s.getCantCuotas().toString());
			cob.setFecpago(s.getFehaPago());
			cob.setNumletra(s.getNumLetra());
			log.info(" --> " +s.getFechaVencimiento());
			cob.setFecvencimiento(s.getFechaVencimiento());
			cob.setRegistroreniec(s.getRegistroReniec());
			cob.setImpinicial(new BigDecimal(s.getImporteInicial()));
			cob.setImpcobrado(new BigDecimal(s.getImporteCobrado()));
			cob.setImportemasmora(new BigDecimal(s.getImportemasmora()));
			cob.setDiasretraso(s.getDiasRetraso());
			cob.setIdcliente(cli.getIdcliente());
			cob.setIdcontrato(con.getIdcontrato());
			cob.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(27));
			cob.setTbContrato(objContratoDao.findContrato(con.getIdcontrato()));
			cob.setTbCliente(cli);
			
			//insertar cobranza
			log.info("  insertando cobranza * "+cob.getTbContrato().getCodcontrato()+" "+cob.getCantcuotas()+" "+cob.getFecpago());
			cob.setUsuariocreacion(usuariocreacion);
			objCobranzaDao.insertCobranza(cob);
			
			log.info(" contr***** "+con.getCodcontrato()+" "+codigoContr);
			
			if(con.getCodcontrato()!=codigoContr){
				for (int j2 = 0; j2 < telList.size(); j2++) {
					objTelefonoDao.insertarTelefonoEmpleado(telList.get(j2));
					log.info("contrato: "+con.getCodcontrato()+" opera "+telList.get(j2).getOperadorTelefonico()+" telefono  "+telList.get(j2).getTelefono()+" desc "+telList.get(j2).getDescTelefono()+" tipo "+telList.get(j2).getTipotelefono());
				}
				log.info("tama�o domic  "+domList.size());
				for (int g = 0; g< domList.size(); g++) {
					log.info("domicilio: "+domList.get(g).getDomicilio() );
					objDomicilioDao.insertarDomicilioEmpleado(domList.get(g));
				}
				codigoContr=  con.getCodcontrato();
			}
		}
		
		//  *************** Contratos a actualizar  ***********************************
		int contCob=0;
		for (int i = 0; i < sisMigUpdate.size(); i++) {
			con = objContratoDao.buscarXcodigoContrato(sisMigUpdate.get(i).getCodContrato());
			SistemaIntegradoDTO s = sistMig.get(i);
			if(s.getDistrito().trim().equalsIgnoreCase("S.J.L.")){
				s.setDistrito("SAN JUAN DE LURIGANCHO");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("SURCO")){
				s.setDistrito("SANTIAGO DE SURCO");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("V.E.S.")||
					s.getDistrito().trim().equalsIgnoreCase("V.E.S")){
				s.setDistrito("VILLA EL SALVADOR");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("V.M.T.")){
				s.setDistrito("VILLA MARIA DEL TRIUNFO");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("S.J.M.")){
				s.setDistrito("SAN JUAN DE MIRAFLORES");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("S.M.P.")){
				s.setDistrito("SAN MARTIN DE PORRES");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("Ate")){
				s.setDistrito("ATE");
			}
			else if(s.getDistrito().trim().equalsIgnoreCase("Lurigancho")){
				s.setDistrito("SAN JUAN DE LURIGANCHO");
			}
			telList= new ArrayList<TelefonoPersonaSie>();
			domList= new ArrayList<DomicilioPersonaSie>();
			//buscar cliente de dicho contrato
			cli = objClienteDao.findCliente(con.getTbCliente().getIdcliente());
			//buscar telefonos
			telList = objTelefonoDao.listarTelefonoEmpleadosXidcliente(con.getTbCliente().getIdcliente());
			//buscar domicilios
			domList =objDomicilioDao.listarDomicilioCliente(con.getTbCliente().getIdcliente());
			
			if(!s.getCodContrato().equals(codigoContr)){
				telefonoString = new ArrayList<String>();
				domicilioString = new ArrayList<String>();
				//actualizar cliente
				cli.setApematcliente(s.getApematcliente());
				cli.setApepatcliente(s.getApepatcliente());
				cli.setNombrecliente(s.getNombrecliente());
				cli.setNumdocumento(s.getNumdocumento());
				cli.setCorreo(s.getCorreo());
				cli.setEmpresatrabajo(s.getEmpresatrabajo());
				cli.setCargotrabajo(s.getCargotrabajo());
				cli.setDirectrabajo(s.getDirectrabajo());
				cli.setPlanoTrabajo(s.getPlanoTrabajo());
				cli.setLetraTrabajo(s.getLetraSectorTrabajo());
				cli.setSectorTrabajo(s.getNumSectorTrabajo());
				cli.setFecnacimiento(s.getFecnacimiento());
				
				String [ ] telTraba = s.getTelftrabajo().trim().split("([\\s-+(]+)");
				if(telTraba.length==1){
					cli.setTelftrabajo(telTraba[0]);
				}
				if(telTraba.length>1){
					for (int j = 0; j < telTraba.length; j++) {
						
						if(telTraba[j].trim().matches("([0-9]+)")){
							tel.setTelefono(telTraba[j].toString().trim());
							if(telTraba.length>j+1){
								if(telTraba[j+1].toString().trim().matches("([a-zA-Z(]//s)+") ){
									tel.setDescTelefono(telTraba[j+1].toString().trim());
								}
							}
						}
						if(telTraba[j].toString().trim().matches("([a-zA-Z(]//s)+") ){
							tel.setDescTelefono(telTraba[j].toString().trim());
							if(telTraba.length>j+1){
								if(telTraba[j+1].trim().matches("([0-9]+)")){
									tel.setTelefono(telTraba[j+1].toString().trim());
								}
							}
						}
						if(tel.getTelefono()!=null ){
							if(tel.getTelefono().matches("\\d{9}")){
								log.info("telefono ");
								tel.setTipotelefono("C");
							}else {
								tel.setTipotelefono("F");
							}
						tel.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
						tel.setIdcliente(cli);
						if(tel.getDescTelefono()==null){
							tel.setDescTelefono("Trabajo");
						}
						telList.add(tel);
						tel= new TelefonoPersonaSie();
						}
					}
				}
				cli.setTitulartelefono(s.getTitulartelefono());
				cli.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(23)); 
				cli.setTbTipoDocumentoIdentidad(objtipoDao.buscarTipoDocumento(1));
				cli.setTbTipoCliente(objTipoClienteDao.findTipoCliente(1));
				cli.setUsuariomodifica(usuariocreacion);
				objClienteDao.updateCliente(cli);
				con.setFechaentrega(s.getFechaEntrega());
				con.setNumcuotas(s.getCantCuotas());
				con.setPagosubinicial(new  BigDecimal(s.getImporteInicial()));
				EmpresaSie empr = objEmpresaDao.findEmpresaXdescripcion(s.getEmpresa());
				if(empr!=null){
					con.setTbEmpresa(empr);
				}else{
					mensaje="No se encontr� la empresa '"+s.getEmpresa()+"' en la base de datos, por favor consultar al administrador";
					break;
				}
				//actualizar contrato
				con.setUsuariomodifica(usuariocreacion);
				objContratoDao.updateContrato(con);
				//actualizar el historial
				HistoricoObservacionesSie h = null;
				List<HistoricoObservacionesSie> lstHistoObs = null;
				lstHistoObs = objHistorialDao.listarHistorial(con.getIdcontrato());
				if(lstHistoObs.size()>0){
					h= lstHistoObs.get(lstHistoObs.size()-1);
					if(s.getHistoria().contains(h.getObservacion())){
						String[] corta = s.getHistoria().split(h.getObservacion());
						if(corta.length>1){
							h = new HistoricoObservacionesSie();
							h.setObservacion(corta[2]);
							h.setTbContrato(con);
							h.setUsuariocreacion(usuariocreacion);
							h.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(98));
							objHistorialDao.insertHistoricoObservaciones(h);
						}
					}
				}else if(s.getHistoria()!=null||s.getHistoria().trim().equals("")){
					h = new HistoricoObservacionesSie();
					h.setObservacion(s.getHistoria());
					h.setTbContrato(con);
					h.setUsuariocreacion(usuariocreacion);
					h.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(98));
					objHistorialDao.insertHistoricoObservaciones(h);
				}
				//buscando domicilio
				dom = new DomicilioPersonaSie();
				dom.setDomicilio(s.getDireccion());
				dom.setTbTipoCasa(objTipoCasaDao.findTipoCasa(1));
				ubi = objUbigeoDao.findUbigeoXDescripcion(s.getDistrito().toUpperCase());
				if(ubi.size()==0){
					dom.setUbicacion(s.getDistrito().toUpperCase());
				}else{
					dom.setTbUbigeo(ubi.get(0));
				}
				dom.setIdcliente(cli);
				dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
				dom.setPlanoDomicilio(s.getPlanoDistrito());
				dom.setSectorDomicilio(s.getNumSector());
				dom.setLetraDomicilio(s.getLetraSector());
				domList.add(dom);
			}
			String [ ] telefono = s.getNumTelefono().trim().split("([\\s(-])+");
	 
			for (int j = 0; j < telefono.length; j++) {
				telefono[j]= telefono[j].toString().replaceAll("([(-)])", " "); 
			}
			
			for (int j = 0; j < telefono.length; j++) { 
				 tel = new TelefonoPersonaSie();
				 
				 if(telefono[j].toString().trim().equalsIgnoreCase("C")||telefono[j].toString().trim().equalsIgnoreCase("(CLARO)")||telefono[j].toString().trim().equalsIgnoreCase("claro")){
						tel.setOperadorTelefonico("Claro");
					}
					else if(telefono[j].toString().trim().equalsIgnoreCase("M")){
						tel.setOperadorTelefonico("Movistar");
					}
					else if(telefono[j].toString().trim().equalsIgnoreCase("N")){
						tel.setOperadorTelefonico("Nextel");
					}
					else if(telefono[j].matches("[a-zA-Z(]+")){
						tel.setDescTelefono(telefono[j].toString().trim());
						if(telefono.length>j+1){
							if(telefono[j+1].toString().trim().matches("[a-zA-Z(]+") ){
								tel.setDescTelefono(tel.getDescTelefono()+" "+telefono[j+1].toString().trim());
								if(telefono.length>j+2){
									if(telefono[j+2].trim().matches("([0-9]+)")&&tel.getTelefono()==null){
										tel.setTelefono(telefono[j+2].toString().trim());
										isadd=true;
									}
								}
							}
						}else{
							isadd=true;
						}
					}
					else if(telefono[j].trim().matches("([0-9]+)")){
						tel.setTelefono(telefono[j].toString().trim());
						if(tel.getTelefono().length()< 4 && telefono.length>j+1){
							tel.setTelefono(tel.getTelefono()+" "+telefono[j+1].toString().trim());
							j=j+1;
						}
					}
					if(telefono.length>j+1 && isadd==false){
						if(telefono[j+1]!=null){
							if(telefono[j+1].toString().trim().equalsIgnoreCase("C")||telefono[j+1].toString().trim().equalsIgnoreCase("(CLARO)")||telefono[j+1].toString().trim().equalsIgnoreCase("claro")){
								tel.setOperadorTelefonico("Claro");
							}
							else if(telefono[j+1].toString().trim().equalsIgnoreCase("M")){
								tel.setOperadorTelefonico("Movistar");
							}
							else if(telefono[j+1].toString().trim().equalsIgnoreCase("N")){
								tel.setOperadorTelefonico("Nextel");
							}
							else if(telefono[j+1].toString().trim().matches("[a-zA-Z(]+") && tel.getDescTelefono()==null){
								tel.setDescTelefono(telefono[j+1].toString().trim());
								//nombre + apellido analizamos si el nombre es compuesto en la descripcion
								if(telefono.length>j+2){
									if(telefono[j+2].toString().trim().matches("[a-zA-Z(]+") ){
										tel.setDescTelefono(tel.getDescTelefono()+" "+telefono[j+2].toString().trim());
									}
								}
							}
							else if(telefono[j+1].trim().matches("([0-9]+)")&&tel.getTelefono()==null){
								tel.setTelefono(telefono[j+1].toString().trim());
							}
						}
					}
					
				if(tel.getTelefono()!=null && !telefonoString.contains(tel.getTelefono())){
					//definir tipo telefono
					if(tel.getTelefono()!=null ){
						if(tel.getTelefono().matches("\\d{9}")){
							log.info("telefono ");
							tel.setTipotelefono("C");
						}else {
							tel.setTipotelefono("F");
						}
					}
					telefonoString.add(tel.getTelefono());
					log.info("telefono: "+tel.getTelefono());
					tel.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
					tel.setIdcliente(cli);
					telList.add(tel);
					isadd=false;
				}
			}
			for (int j = 0; j < domList.size(); j++) {
				domicilioString.add(domList.get(j).getDomicilio());
			}
			//Actualizar Domicilio
			if(!domicilioString.contains(s.getDireccion())){
				log.info("nuevo domi  " + s.getDireccion());
				dom = new DomicilioPersonaSie();
				dom.setDomicilio(s.getDireccion());
				dom.setTbTipoCasa(objTipoCasaDao.findTipoCasa(1));
				ubi = objUbigeoDao.findUbigeoXDescripcion(s.getDistrito().toUpperCase());
				if( ubi.size()==0){
					dom.setUbicacion(s.getDistrito().toUpperCase());
				}else{
					dom.setTbUbigeo(ubi.get(0));
				}
				dom.setIdcliente(cli);
				dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
				dom.setPlanoDomicilio(s.getPlanoDistrito());
				dom.setSectorDomicilio(s.getNumSector());
				dom.setLetraDomicilio(s.getLetraSector());
				domList.add(dom);
				objDomicilioDao.insertarDomicilioEmpleado(dom);
			}
			//buscar cobranza
			List<CobranzaSie> cobAntlst = objCobranzaDao.buscarCobranzaXcodigoContrato(con.getIdcontrato());
			
				CobranzaSie cob = cobAntlst.get(contCob);
				cob.setCantcuotas(s.getCantCuotas().toString());
				cob.setFecpago(s.getFehaPago());
				cob.setNumletra(s.getNumLetra());
				cob.setFecvencimiento(s.getFechaVencimiento());
				cob.setRegistroreniec(s.getRegistroReniec());
				cob.setImpinicial(new BigDecimal(s.getImporteInicial()));
				cob.setImpcobrado(new BigDecimal(s.getImporteCobrado()));
				cob.setImportemasmora(new BigDecimal(s.getImportemasmora()));
				cob.setDiasretraso(s.getDiasRetraso());
				cob.setIdcliente(cli.getIdcliente());
				cob.setIdcontrato(con.getIdcontrato());
				cob.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(27));
				cob.setTbContrato(con);
				cob.setTbCliente(cli);
				//actualizar cobranza
				cob.setUsuariomodifica(usuariocreacion);
				objCobranzaDao.updateCobranza(cob);
				
			//Las cobranzas que no estan dentro del detalle de pagos e deshabilitaran de la base de datos
			if(contCob<cobAntlst.size()){
				for (int j = contCob; j < cobAntlst.size(); j++) {
					CobranzaSie cob2 = cobAntlst.get(j);
					cob2.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(28));
				}
			}
				
			if(con.getCodcontrato()!=codigoContr){
				for (int j2 = 0; j2 < telList.size(); j2++) {
					objTelefonoDao.insertarTelefonoEmpleado(telList.get(j2));
				}
				for (int g = 0; g< domList.size(); g++) {
					objDomicilioDao.insertarDomicilioEmpleado(domList.get(g));
				}
				codigoContr=  con.getCodcontrato();
				contCob=0;
			}else{
				contCob+=1;
			}
		}
		
		} catch (Exception e) {
			mensaje ="Mensaje: "+e.getMessage()+" Error en el contrato "+con.getCodcontrato()+", Causa: "+e.getMessage();
		}
		
		return mensaje;
	}
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#obtenerCodigo()
	 */
	public int obtenerCodigo() {
		return objContratoDao.obtenerCodigo();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#buscarXcodigoContrato(java.lang.String)
	 */
	public ContratoSie buscarXcodigoContrato(String codContrato) {
		return objContratoDao.buscarXcodigoContrato(codContrato);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#listarContratoEntregaLetraObsequio(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List listarContratoEntregaLetraObsequio(String numDocumento,String codigoContrato, String nombreCliente, String apePat,String apeMat) {
		return objContratoDao.listarContratoEntregaLetraObsequio(numDocumento, codigoContrato, nombreCliente, apePat, apeMat);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#findcantContratoFacturado(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public Integer findcantContratoFacturado(Integer idEmpleado, Integer cargo, String fechaInicio, String fechaFin) {
		return objContratoDao.findcantContratoFacturado(idEmpleado, cargo,fechaInicio, fechaFin);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#findcantContratoEntregado(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public Integer findcantContratoEntregado(Integer idEmpleado, Integer cargo, String fechaInicio, String fechaFin) {
		return objContratoDao.findcantContratoEntregado(idEmpleado, cargo, fechaInicio, fechaFin);
	}
	
}
