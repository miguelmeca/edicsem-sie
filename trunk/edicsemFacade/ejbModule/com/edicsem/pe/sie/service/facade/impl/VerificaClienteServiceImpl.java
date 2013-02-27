package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.VerificaClienteSie;
import com.edicsem.pe.sie.entity.VerificaProductoSie;
import com.edicsem.pe.sie.entity.VerificaTelefonoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.VerificaClienteDAO;
import com.edicsem.pe.sie.model.dao.VerificaProductoDAO;
import com.edicsem.pe.sie.model.dao.VerificaTelefonoDAO;
import com.edicsem.pe.sie.service.facade.VerificaClienteService;

@Stateless
public class VerificaClienteServiceImpl implements VerificaClienteService {

	@EJB
	private  VerificaClienteDAO objverificaclienteDao;
	@EJB
	private  VerificaProductoDAO objverificaproductoDao;
	@EJB
	private  VerificaTelefonoDAO objverificatelefonoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AlmacenService#insertAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */
	public static Log log = LogFactory.getLog(ContratoServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaClienteService#insertVerificaCliente(com.edicsem.pe.sie.entity.VerificaClienteSie)
	 */
	public void insertVerificaCliente(VerificaClienteSie v,List<VerificaProductoSie> lstProducto, List<VerificaTelefonoSie> lstTelefono) {
		v.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(76));
		objverificaclienteDao.insertVerificaCliente(v);
		
		for (int i = 0; i < lstProducto.size(); i++) {
			VerificaProductoSie vp = lstProducto.get(i);
			vp.setTbVerificaCliente(v);
			objverificaproductoDao.insertVerificaProducto(vp);
		}
		for (int i = 0; i < lstTelefono.size(); i++) {
			VerificaTelefonoSie vt = lstTelefono.get(i);
			vt.setTbVerificaCliente(v);
			objverificatelefonoDao.insertVerificaTelefono(vt);
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaClienteService#updateVerificaCliente(com.edicsem.pe.sie.entity.VerificaClienteSie)
	 */
	public void updateVerificaCliente(VerificaClienteSie v) {
		objverificaclienteDao.updateVerificaCliente(v);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaClienteService#findVerificaCliente(int)
	 */
	public VerificaClienteSie findVerificaCliente(int id) {
		return objverificaclienteDao.findVerificaCliente(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaClienteService#listarVerificaCliente()
	 */
	public List listarVerificaCliente() {
		return objverificaclienteDao.listarVerificaCliente();
	}
	
}
