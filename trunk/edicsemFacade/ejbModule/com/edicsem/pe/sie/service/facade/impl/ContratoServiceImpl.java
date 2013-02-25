package com.edicsem.pe.sie.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.EntregasPeruDTO;
import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetPaqueteDAO;
import com.edicsem.pe.sie.model.dao.DetProductoContratoDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.PaqueteDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.ContratoService;

@Stateless
public class ContratoServiceImpl implements ContratoService {
	
	

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
	private PaqueteDAO objPaqueteDAO;
	@EJB
	private AlmacenDAO objAlmacenDAO;
	@EJB
	private DetPaqueteDAO objDetPaqueteDAO;
	@EJB
	private DetContratoEmpleadoDAO objDetContratoEmpleadoDAO;
	@EJB
	private CargoEmpleadoDAO objCargoEmpleadoDAO;
	@EJB
	private ContratoEmpleadoDAO objContratoEmpleadoDAO;
	
	public static Log log = LogFactory.getLog(ContratoServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#insertContrato(int, int, int, int, com.edicsem.pe.sie.entity.ClienteSie, java.util.List, com.edicsem.pe.sie.entity.DomicilioPersonaSie, com.edicsem.pe.sie.entity.ContratoSie, java.util.List, java.util.List, java.util.List)
	 */
	public void insertContrato(int idtipodoc,int Tipocasa,int idUbigeo,int  idempresa, ClienteSie  cliente, List<TelefonoPersonaSie> telefonoList, DomicilioPersonaSie domicilio,  ContratoSie contrato,List<DetProductoContratoSie> detprodcont, List<CobranzaSie> cobranza, List<Integer> detidEmpleadosList) {
		log.info(" * en insertar el contrato  ");
		cliente.setTbTipoDocumentoIdentidad(objtipoDao.buscarTipoDocumento(idtipodoc));
		cliente.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(23));
		cliente.setTipocliente(1);
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
		log.info(" INSER CLIENTE" );
		objContratoDao.insertContrato(contrato);
		log.info("contrato insertado!  "+contrato.getIdcontrato());
		for (DetProductoContratoSie detprodcontrato : detprodcont) {
			detprodcontrato.setTbContrato(objContratoDao.findContrato(contrato.getIdcontrato()));
			objDetProductoContratoDao.insertDetProductoContrato(detprodcontrato);
		}
		log.info(" terminado tamaño cobranza " + cobranza.size());
		for (CobranzaSie objcobranza : cobranza) {
			objcobranza.setCantcuotas(""+ contrato.getNumcuotas());
			objcobranza.setIdcliente(cliente.getIdcliente());
			objcobranza.setIdcontrato(contrato.getIdcontrato());
			objcobranza.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(27));
			objcobranza.setTbContrato(objContratoDao.findContrato(contrato.getIdcontrato()));
			objcobranza.setTbCliente(cliente);
			objCobranzaDao.insertCobranza(objcobranza);
			log.info(" terminado cobranza " );
		}
		log.info(" terminado DET  :D " +detidEmpleadosList.size());
		
		DetContratoEmpleadoSie d;
		for (int i = 0; i < detidEmpleadosList.size() ; i++) {
			d = new DetContratoEmpleadoSie();
			d.setTbEmpleado(objEmpleadoDao.buscarEmpleado(detidEmpleadosList.get(i)));
			if(i==0){
				d.setIdCargoContrato(1);
			}
			else if(i==1){
				d.setIdCargoContrato(2);
			}
			else if(i>1){
				d.setIdCargoContrato(3);
			}
			log.info("contrato ************************** ---> !  "+contrato.getIdcontrato());
			d.setTbContrato(objContratoDao.findContrato(contrato.getIdcontrato()));
			d.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(31));
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
	
	public void updateEntregasPeru(List<EntregasPeruDTO> credito) {

		ContratoSie con = new ContratoSie();
		DetProductoContratoSie detprodcon = new DetProductoContratoSie();
		DetContratoEmpleadoSie detconemp = new DetContratoEmpleadoSie();
		CargoEmpleadoSie cargoemp = new CargoEmpleadoSie();
		ProductoSie produc = new ProductoSie();	
		PaqueteSie paque = new PaqueteSie();
		PuntoVentaSie punto = new PuntoVentaSie();
		ContratoEmpleadoSie contraemple= new ContratoEmpleadoSie();
		
		log.info("actualizar() *");
		
		for (int i = 0; i < credito.size(); i++) {	
			EntregasPeruDTO s = credito.get(i);
			detprodcon = new DetProductoContratoSie();
		con = objContratoDao.buscarXcodigoContrato(s.getNumerodecontrato());
		
			log.info("Num-Contrato"+"--> "+con.getCodcontrato()+ "--ID DE CONTRATO"+ con.getIdcontrato());
			con.setLugarentrega(s.getLugardelaentrega());
			log.info("Lugarentrega"+"-->"+con.getLugarentrega());			
			con.setFechaentrega(s.getFecha());
			log.info("Fecha de Entrega"+ "-->"+con.getFechaentrega());			
			con.setTbEmpresa(objEmpresaDao.buscarIdEmpresa(s.getEmpresa().trim().toUpperCase()));
			log.info("Id-Empresa"+"-->"+con.getTbEmpresa().getIdempresa());
			con.setTbCliente(objClienteDao.buscarIdCliente(s.getDnidelcliente()));
			log.info("Id-Cliente"+"-->"+con.getTbCliente().getIdcliente().toString());
			
		punto = objAlmacenDAO.buscarIdpuntoVenta(s.getPuntodeventa());
		log.info("NOMBRE-PUNTO-VENTA"+"-->"+ punto.getDescripcion() + "ID-PUNTO-VENTA"+"-->"+punto.getIdpuntoventa());
		
		if (punto!=null) {
			con.setTbPuntoVenta(punto);
			log.info("Dentro del If!=null"+ "-->"+ con.getTbPuntoVenta().getIdpuntoventa());			
		}				
			if (s.getEventodeventa().trim().toUpperCase().equalsIgnoreCase("MASIVO")) {				
				String tventaMas = s.getEventodeventa();
				tventaMas = tventaMas.substring(0, 3);			
				con.setTipoventa(tventaMas);
				log.info("TIpo-Venta-MAS"+"-->"+con.getTipoventa());	
			}	else if (s.getEventodeventa().trim().toUpperCase().equalsIgnoreCase("PUNTO DE VENTA")) {				
				con.setTipoventa("PNT");				
				log.info("TIpo-Venta-PNT"+"-->"+con.getTipoventa());
			}			
			else {
				con.setTipoventaotros(s.getEventodeventa());				
			}			
			con.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(25));
			objContratoDao.updateContrato(con);
			
/***DET_PRODUCTO_CONTRATO***/
			
			detprodcon.setTbContrato(con);
			log.info("ID DE CONTRATO PARA DET_PRODUCTO_CONTRATO"+"-->"+ con.getIdcontrato());
			detprodcon.setCantidad(s.getCantidaddemercaderia());
			log.info("Cantidad de Mercaderia"+ "-->"+ detprodcon.getCantidad());
			detprodcon.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(74));
					
			ProductoSie obj1 = objProductoDAO.buscarXcodigoProducto(s.getCodigodemercaderia());
			if (obj1.getCodproducto()!=null) {			
				log.info("CODIGO_PRODUCTO"+"-->  "+obj1.getCodproducto() +" -- " + "ID_PRODUCTO-->"+ obj1.getIdproducto());
				detprodcon.setTbProducto(obj1);	
				objDetProductoContratoDao.insertDetProductoContrato(detprodcon);
			}
			else{
				List<DetPaqueteSie> lista = objDetPaqueteDAO.buscarXcodigoPaquete(s.getCodigodemercaderia());
				for (int j = 0; j <lista.size(); j++) {
					DetProductoContratoSie det= new DetProductoContratoSie();

					det.setTbContrato(con);
					log.info("ID DE CONTRATO PARA DETALLE"+"-->"+ con.getIdcontrato());
					det.setCantidad(s.getCantidaddemercaderia());
					log.info("Cantidad de Mercaderia"+ "-->"+ detprodcon.getCantidad());
					det.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(74));					
//					log.info("productos de la lista "+"-->  "+lista.get(j).getTbProducto().getIdproducto() );
					produc= objProductoDAO.findProducto(lista.get(j).getTbProducto().getIdproducto());	 
					det.setTbProducto(produc);	
					objDetProductoContratoDao.insertDetProductoContrato(det);
				}
		
			}
			
/****DET_CONTRATO_EMPLEADO****/		
			
		detconemp.setTbContrato(con);
		log.info("ID DE CONTRATO-TB_DET_CONTRATO_EMPLEADO"+"-->"+ con.getIdcontrato()+" -- "+ con.getCodcontrato());
		
		detconemp.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(58));
		
		
		detconemp.setTbEmpleado(objEmpleadoDao.buscarEmpleadoVendedor(s.getNombredelvendedor()));
		log.info("ID-VENDEDOR igual ah ID-EMPLEADO"+"--> "+ detconemp.getTbEmpleado().getIdempleado());
		if (s.getNombredelvendedor()!=null) {
		detconemp.setIdCargoContrato(1);
		log.info("CARGO-CONTRATO-VENDEDOR-->"+"  "+ detconemp.getIdCargoContrato());
		}
		
		detconemp.setTbEmpleado(objEmpleadoDao.buscarEmpleadoVendedor(s.getNombredelexpositor()));
		log.info("ID-EXPOSITOR igual ah ID-EMPLEADO"+"--> "+ detconemp.getTbEmpleado().getIdempleado());
		if (s.getNombredelexpositor()!=null) {
			detconemp.setIdCargoContrato(2);
			log.info("CARGO-CONTRATO-EXPOSITOR-->"+"  "+ detconemp.getIdCargoContrato());
			}
		
		detconemp.setTbEmpleado(objEmpleadoDao.buscarEmpleadoVendedor(s.getNombredelsupervisor()));
		log.info("ID-SUPERVISOR igual ah ID-EMPLEADO"+"--> "+ detconemp.getTbEmpleado().getIdempleado());
		if (s.getNombredelsupervisor()!=null) {
			detconemp.setIdCargoContrato(3);
			log.info("CARGO-CONTRATO-SUPERVISOr-->"+"  "+ detconemp.getIdCargoContrato());
			}
		
		
		
		objDetContratoEmpleadoDao.insertDetContratoEmpleado(detconemp);
		
		
			
}			
	}
	
	public String insertMigracion(List<SistemaIntegradoDTO> sistMig) {
		log.info("insertMigracion() *");
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
		boolean isadd=false;
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
				for (int j = 0; j < telTraba.length; j++) {
				log.info(" tlf " + telTraba[j] );
				}
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
						telList.add(tel);
						tel= new TelefonoPersonaSie();
						}
					}
				}
				
				cli.setTitulartelefono(s.getTitulartelefono());
				cli.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(23)); 
				cli.setTbTipoDocumentoIdentidad(objtipoDao.buscarTipoDocumento(1));
				cli.setTipocliente(1);
				
				log.info(" insertando cliente:  "+ cli.getNombrecliente()+" ape. pat "+cli.getApepatcliente() );
				objClienteDao.insertCliente(cli);
				con.setNumcuotas(s.getCantCuotas());
				con.setPagosubinicial(new  BigDecimal(s.getImporteInicial()));
				con.setTbEmpresa(objEmpresaDao.findEmpresaXdescripcion(s.getEmpresa()));
				con.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(25));
				con.setTbCliente(cli);
				//insertar contrato
				objContratoDao.insertContrato(con);
				log.info("  insertando contrato * "+con.getCodcontrato());
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
			//si se encuentra un ( - ) se reemplaza por un espacio

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
					log.info("agregadooo ");
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
			
			//buscar ubigeo por descripcion del ditrito
			// si hay mas de una opción? seria la de lima por defecto
			
		for (int j = 0; j < domList.size(); j++) {
			domicilioString.add(domList.get(j).getDomicilio());
		}
			
			//insertar Domicilio
		
		
			
			if(!domicilioString.contains(s.getDireccion())){
				//domList = new ArrayList<DomicilioPersonaSie>();
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
				log.info("tamaño domic  "+domList.size());
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
			objCobranzaDao.insertCobranza(cob);
			
			log.info(" contr***** "+con.getCodcontrato()+" "+codigoContr);
			
			
			if(con.getCodcontrato()!=codigoContr){
				for (int j2 = 0; j2 < telList.size(); j2++) {
						objTelefonoDao.insertarTelefonoEmpleado(telList.get(j2));
						log.info("contrato: "+con.getCodcontrato()+" opera "+telList.get(j2).getOperadorTelefonico()+" telefono  "+telList.get(j2).getTelefono()+" desc "+telList.get(j2).getDescTelefono()+" tipo "+telList.get(j2).getTipotelefono());
				
				}
				log.info("tamaño domic  "+domList.size());
				for (int g = 0; g< domList.size(); g++) {
					log.info("domicilio: "+domList.get(g).getDomicilio() );
					objDomicilioDao.insertarDomicilioEmpleado(domList.get(g));
				}
				codigoContr=  con.getCodcontrato();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#obtenerCodigo()
	 */
	public int obtenerCodigo() {
		return objContratoDao.obtenerCodigo();
	}
	



public ContratoSie buscarXcodigoContrato(String codContrato) {
	log.info("en el SERVICIO BUSCANDO N DE CONTRATO"+ codContrato);
	return objContratoDao.buscarXcodigoContrato(codContrato);
}





}
