package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CriterioComisionSie;
import com.edicsem.pe.sie.model.dao.CriterioComisionDAO;

/**
 * @author karen
 *
 */
@Stateless
public class CriterioComisionDAOImpl implements CriterioComisionDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CriterioComisionDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CriterioComisionDAO#insertCriterioComision(com.edicsem.pe.sie.entity.CriterioComisionSie)
	 */
	public void insertCriterioComision(CriterioComisionSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar criterio comision");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CriterioComisionDAO#updateCriterioComision(com.edicsem.pe.sie.entity.CriterioComisionSie)
	 */
	public void updateCriterioComision(CriterioComisionSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar criterio comision");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CriterioComisionDAO#findCriterioComision(int)
	 */
	public CriterioComisionSie findCriterioComision(int id) {
		CriterioComisionSie c= new CriterioComisionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar criterio comision"+ id);
			}
			c=	em.find(CriterioComisionSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CriterioComisionDAO#listarCriterioComision()
	 */
	public List listarCriterioComision() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CriterioComisionSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista criterio comision --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
}
