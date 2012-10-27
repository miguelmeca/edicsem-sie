package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
		log.info(" cliente estado " + cliente.getTbEstadoGeneral().getCodestadogeneral() );
		objClienteDao.insertCliente(cliente);	
		log.info(" cliente estado " + cliente.getTbEstadoGeneral().getCodestadogeneral() );
		for (TelefonoPersonaSie telefonoPersonaSie : telefonoList) {
			telefonoPersonaSie.setIdcliente(cliente);
			telefonoPersonaSie.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(17));
			objTelefonoDao.insertarTelefonoEmpleado(telefonoPersonaSie);
		}	
		log.info(" domicilio  ");
		
		domicilio.setTbTipoCasa(objTipoCasaService.findTipoCasa(Tipocasa));
		domicilio.setTbUbigeo(objUbigeoDao.findUbigeo(idUbigeo));
		domicilio.setIdcliente(cliente);
		domicilio.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(15));
		log.info(" seteo domi"  );
		objDomicilioDao.insertarDomicilioEmpleado(domicilio);
		log.info(" inser domicilio "  );
		contrato.setTbCliente(cliente);
		contrato.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(25));
		contrato.setTbEmpresa(objEmpresaDao.findEmpresa(idempresa));
		log.info(" INSER CLIENTE" );
		objContratoDao.insertContrato(contrato);
		for (DetProductoContratoSie detprodcontrato : detprodcont) {
			detprodcontrato.setTbContrato(contrato);
			objDetProductoContratoDao.insertDetProductoContrato(detprodcontrato);
			log.info(" 1 contrato " );
		}
		log.info(" terminado tamaño cobranza " + cobranza.size());
		for (CobranzaSie objcobranza : cobranza) {
			log.info(" terminado cliente " +cliente.getIdcliente());
			log.info(" terminado contrato " +contrato.getIdcontrato());
			objcobranza.setCantcuotas(""+ contrato.getNumcuotas());
			objcobranza.setIdcliente(cliente.getIdcliente());
			objcobranza.setIdcontrato(contrato.getIdcontrato());
			log.info(" terminado estado " );
			objcobranza.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(27));
			log.info(" terminado estado 2" );
			objcobranza.setTbContrato(contrato);
			log.info(" terminado con " );
			objcobranza.setTbCliente(cliente);
			log.info(" terminado cli " );
			objCobranzaDao.insertCobranza(objcobranza);
			log.info(" terminado cobranza " );
		}
		log.info(" terminado DET " +detidEmpleadosList.size());
		/*for (int i = 0; i < detidEmpleadosList.size() ; i++) {
			log.info(" IDEMPL "+detidEmpleadosList.get(i));
			DetContratoEmpleadoSie dce = new DetContratoEmpleadoSie();
			dce.setIdempleado(detidEmpleadosList.get(i));
			dce.setIdcontrato(contrato.getIdcontrato());
			dce.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(31));
			dce.setTbContrato(contrato);
			dce.setTbEmpleado(objEmpleadoDao.buscarEmpleado(detidEmpleadosList.get(i)));
			objDetContratoEmpleadoDao.insertDetContratoEmpleado(dce);
			log.info(" terminado EMPL " );
		}*/
		
		log.info("fin  " );
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
}
