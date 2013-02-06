package com.edicsem.pe.sie.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.model.dao.ClienteDAO;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetProductoContratoDAO;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.service.facade.TipoCasaService;

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
	private TipoCasaService objTipoCasaService;
	@EJB
	private EmpresaDAO objEmpresaDao;
	@EJB
	private DetContratoEmpleadoDAO objDetContratoEmpleadoDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	
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
		domicilio.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasa));
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
	public void insertMigracion(List<SistemaIntegradoDTO> sistMig) {
		log.info("insertMigracion() *");
		ClienteSie cli = new ClienteSie();
		ContratoSie con = new ContratoSie();
		TelefonoPersonaSie tel = new TelefonoPersonaSie();
		List<TelefonoPersonaSie> telList= new ArrayList<TelefonoPersonaSie>() ;
		List<String> telefonoString= new ArrayList<String>();
		String codigoContr = "";
		boolean isadd=false;
		for (int i = 0; i < sistMig.size(); i++) {
			
			SistemaIntegradoDTO s = sistMig.get(i);
			
			if(!s.getCodContrato().equals(codigoContr)){
				 telList= new ArrayList<TelefonoPersonaSie>() ;
				log.info("nuevo contrato  --> "+s.getCodContrato()+"  "+codigoContr);
				cli = new ClienteSie();
				con = new ContratoSie();
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
				cli.setFecnacimiento(s.getFecnacimiento());
				cli.setTelftrabajo(s.getTelftrabajo());
				cli.setTitulartelefono(s.getTitulartelefono());
				cli.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(23)); 
				
				telefonoString = new ArrayList<String>();
				//objClienteDao.insertCliente(cli);
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
			
			//insertar Domicilio
			DomicilioPersonaSie dom = new DomicilioPersonaSie();
			dom.setDomicilio(s.getDireccion());
//			dom.setTbTipoCasa(objTipoCasaService.findTipoCasa(s.get));
			//buscar ubigeo por descripcion del ditrito
			// si hay mas de una opción? seria la de lima por defecto
			
			if(s.getDistrito().equalsIgnoreCase("S.J.L.")){
				s.setDistrito("SAN JUAN DE LURIGANCHO");
			}else if(s.getDistrito().equalsIgnoreCase("SURCO")){
				s.setDistrito("SANTIAGO DE SURCO");
			}
			else if(s.getDistrito().equalsIgnoreCase("V.E.S")){
				s.setDistrito("VILLA EL SALVADOR");
			}
			else if(s.getDistrito().equalsIgnoreCase("V.M.T")){
				s.setDistrito("VILLA MARIA DEL TRIUNFO");
			}
			
			dom.setTbUbigeo(objUbigeoDao.findUbigeoXDescripcion(s.getDistrito().toUpperCase()));
			dom.setIdcliente(cli);
			dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
			//objDomicilioDao.insertarDomicilioEmpleado(dom);
			
			con.setNumcuotas(s.getCantCuotas());
			con.setPagosubinicial(new  BigDecimal(s.getImporteInicial()));
			con.setTbCliente(cli);
			//insertar contrato
			//	objContratoDao.insertContrato(con);
			
			CobranzaSie cob = new CobranzaSie();
			cob.setCantcuotas(s.getCantCuotas().toString());
			cob.setFecpago(s.getFehaPago());
			cob.setNumletra(s.getNumLetra());
			cob.setFecvencimiento(s.getFecnacimiento());
			cob.setRegistroreniec(s.getRegistroReniec());
			cob.setImpcobrado(new BigDecimal(s.getImporteCobrado()));
			cob.setImportemasmora(new BigDecimal(s.getImportemasmora()));
			cob.setDiasretraso(s.getDiasRetraso());
			cob.setTbContrato(con);
			//insertar cobranza
			//	objCobranzaDao.insertCobranza(cob);
			
			log.info(" contr***** "+con.getCodcontrato()+" "+codigoContr);
			
			for (int j = 0; j < telList.size(); j++) {
				
				if(con.getCodcontrato()!=codigoContr){
					
					log.info("contrato: "+con.getCodcontrato()+" opera "+telList.get(j).getOperadorTelefonico()+" telefono  "+telList.get(j).getTelefono()+" desc "+telList.get(j).getDescTelefono()+" tipo "+telList.get(j).getTipotelefono());
					//insertando telefonos
					
					if(j+1 == telList.size()){
						codigoContr=  con.getCodcontrato();
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ContratoService#obtenerCodigo()
	 */
	public int obtenerCodigo() {
		return objContratoDao.obtenerCodigo();
	}
	
}
