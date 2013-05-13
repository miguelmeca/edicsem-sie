package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CajaSie;
import com.edicsem.pe.sie.model.dao.CajaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class CajaDAOImpl implements CajaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CajaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CajaDAO#insertCaja(com.edicsem.pe.sie.entity.CajaSie)
	 */
	public void insertCaja(CajaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Caja");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CajaDAO#updateCaja(com.edicsem.pe.sie.entity.CajaSie)
	 */
	public void updateCaja(CajaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar caja");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CajaDAO#findCaja(int)
	 */
	public CajaSie findCaja(int id) {
		CajaSie c= new CajaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar caja "+ id);
			}
			c =	em.find(CajaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CajaDAO#listarCaja()
	 */
	public List listarCaja() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CajaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista caja --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
