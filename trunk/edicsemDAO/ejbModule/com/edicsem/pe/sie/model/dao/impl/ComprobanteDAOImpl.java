package com.edicsem.pe.sie.model.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComprobanteSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.model.dao.ComprobanteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ComprobanteDAOImpl implements ComprobanteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ComprobanteDAOImpl.class);
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#insertComprobante(com.edicsem.pe.sie.entity.ComprobanteSie)
	 */
	
	public void insertComprobante(ComprobanteSie comp) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Comprobante");
			} 
			em.persist(comp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#findComprobante(int)
	 */
	
	public ComprobanteSie findComprobante(int id) {
		ComprobanteSie com = new ComprobanteSie();
		try {
			if (log.isInfoEnabled()) {
			log.info("buscar comprobante");
			} 
			com = em.find(ComprobanteSie.class, id);
			log.info(" comprobante " +id);
			} catch (Exception e) {
			e.printStackTrace();
			}
		return com;
	}
	
}
