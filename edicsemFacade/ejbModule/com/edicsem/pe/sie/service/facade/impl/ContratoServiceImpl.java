package com.edicsem.pe.sie.service.facade.impl;

import java.math.BigDecimal;
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
		log.info(" terminado tama�o cobranza " + cobranza.size());
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
		
		ClienteSie cli = new ClienteSie();
		ContratoSie con = new ContratoSie();
		
		for (int i = 0; i < sistMig.size(); i++) {
			
			SistemaIntegradoDTO s = sistMig.get(i);
			if(sistMig.get(i-1).getCodContrato()!=s.getCodContrato()){
			
			cli = new ClienteSie();
			con = new ContratoSie();
			
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
			objClienteDao.insertCliente(cli);
			
			//insertar Domicilio
			DomicilioPersonaSie dom = new DomicilioPersonaSie();
			dom.setDomicilio(s.getDireccion());
//			dom.setTbTipoCasa(objTipoCasaService.findTipoCasa(s.get));
			//buscar ubigeo 
			
			//dom.setTbUbigeo(objUbigeoDao.findUbigeo(idUbigeo));
			dom.setIdcliente(cli);
			dom.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
			objDomicilioDao.insertarDomicilioEmpleado(dom);
			
			con.setCodcontrato(s.getCodContrato());
			con.setNumcuotas(s.getCantCuotas());
			con.setPagosubinicial(new  BigDecimal(s.getImporteInicial()));
			con.setTbCliente(cli);
			//insertar contrato
			objContratoDao.insertContrato(con);
			}
			
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
			objCobranzaDao.insertCobranza(cob);
		}
		
	}

	
}
