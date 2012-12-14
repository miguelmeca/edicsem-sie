package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetpagoSie;
import com.edicsem.pe.sie.model.dao.DetPagoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetPagoDAOImpl implements DetPagoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetPagoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPagoDAO#insertDetpago(com.edicsem.pe.sie.entity.DetpagoSie)
	 */
	public void insertDetpago(DetpagoSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Detpago");
			} 
			em.persist(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPagoDAO#updateDetpago(com.edicsem.pe.sie.entity.DetpagoSie)
	 */
	public void updateDetpago(DetpagoSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Detpago");
			} 
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPagoDAO#findDetpago(int)
	 */
	public DetpagoSie findDetpago(int id) {
		DetpagoSie a= new DetpagoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Detpago");
			} 
		a=	em.find(DetpagoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPagoDAO#listarDetpago()
	 */
	public List listarDetpago() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetpagoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Detpago --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
