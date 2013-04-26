package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.model.dao.DetPaqueteDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.DetallePaqueteService;

@Stateless
public class DetallePaqueteServiceImpl implements DetallePaqueteService {
	private Log log = LogFactory.getLog(DetallePaqueteServiceImpl.class);
	
	@EJB
	private  DetPaqueteDAO objDetPaqueteDao;
	@EJB
	private  EstadoGeneralDAO objEstadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetallePaqueteService#insertDetPaquete(com.edicsem.pe.sie.entity.DetPaqueteSie)
	 */
	public void insertDetPaquete(DetPaqueteSie p) {
		p.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(106));
		objDetPaqueteDao.insertDetPaquete(p);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetallePaqueteService#updateDetPaquete(com.edicsem.pe.sie.entity.DetPaqueteSie)
	 */
	public void updateDetPaquete(DetPaqueteSie p) {
		objDetPaqueteDao.updateDetPaquete(p);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetallePaqueteService#findDetPaquete(int)
	 */
	public DetPaqueteSie findDetPaquete(int id) {
		return objDetPaqueteDao.findDetPaquete(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetallePaqueteService#listarDetPaquetes(int)
	 */
	public List listarDetPaquetes(int paquete){
		return objDetPaqueteDao.listarDetPaquetes(paquete);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetallePaqueteService#verificarPaquetesicontieneProductos(int)
	 */
	public boolean verificarPaquetesicontieneProductos(int parametroObtenido) {
		log.info("en el servicio verificarPaquetesicontieneProductos");
		return objDetPaqueteDao.verificarPaquetesicontieneProductos(parametroObtenido);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetallePaqueteService#eliminarDetPaquete(int)
	 */
	public void eliminarDetPaquete(int id) {
		 objDetPaqueteDao.eliminarDetPaquete(id);	
	}
}
