package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.model.dao.DetProductoContratoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetProductoContratoDAOImpl implements DetProductoContratoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetProductoContratoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetProductoContratoDAO#insertDetProductoContrato(com.edicsem.pe.sie.entity.DetProductoContratoSie)
	 */
	public void insertDetProductoContrato(DetProductoContratoSie det) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetProductoContrato");
			} 
			em.persist(det);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetProductoContratoDAO#updateDetProductoContrato(com.edicsem.pe.sie.entity.DetProductoContratoSie)
	 */
	public void updateDetProductoContrato(DetProductoContratoSie det) {
		try {
			if (log.isInfoEnabled()) {
			log.info("modificar DetProductoContrato");
		}
		em.merge(det);
	} catch (Exception e) {
		e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetProductoContratoDAO#findDetProductoContrato(int)
	 */
	public DetProductoContratoSie findDetProductoContrato(int id) {
		DetProductoContratoSie det= new DetProductoContratoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetProductoContrato ");
			} 
		det=	em.find(DetProductoContratoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return det;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetProductoContratoDAO#listarDetProductoContrato()
	 */
	public List listarDetProductoContrato() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetProductoContratoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista DetProductoContrato --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
}
