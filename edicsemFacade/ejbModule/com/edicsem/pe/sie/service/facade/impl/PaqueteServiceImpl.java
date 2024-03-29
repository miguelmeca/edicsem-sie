package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.PaqueteDAO;
import com.edicsem.pe.sie.service.facade.PaqueteService;

@Stateless
public class PaqueteServiceImpl implements PaqueteService {
	
	public static Log log = LogFactory.getLog(CargoEmpleadoServiceImpl.class);
	
	@EJB
	private  PaqueteDAO objDetPaqueteDao;
	@EJB
	private  EstadoGeneralDAO objEstadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PaqueteService#insertPaquete(com.edicsem.pe.sie.entity.PaqueteSie)
	 */
	public void insertPaquete(PaqueteSie p) {
		p.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(60));
		objDetPaqueteDao.insertPaquete(p);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PaqueteService#updatePaquete(com.edicsem.pe.sie.entity.PaqueteSie)
	 */
	public void updatePaquete(PaqueteSie p) {
		objDetPaqueteDao.updatePaquete(p);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PaqueteService#findPaquete(int)
	 */
	public PaqueteSie findPaquete(int id) {
		return objDetPaqueteDao.findPaquete(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.PaqueteService#listarPaquetes()
	 */
	public List listarPaquetes() {
		return objDetPaqueteDao.listarPaquetes();
	}


	public PaqueteSie buscarXcodigoPaquete(String codPaquete) {
		log.info("en el SERVICIO BUSCANDO COD-PAQUETE"+"-->  "+codPaquete);
		return  objDetPaqueteDao.buscarXcodigoPaquete(codPaquete);
	}
}
