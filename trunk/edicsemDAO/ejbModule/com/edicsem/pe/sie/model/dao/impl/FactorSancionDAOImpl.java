package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.FactorSancionSie;
import com.edicsem.pe.sie.model.dao.FactorSancionDAO;

@Stateless
public class FactorSancionDAOImpl implements FactorSancionDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;

	private static Log log = LogFactory.getLog(FactorSancionDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FactorSancionDAO#insertFactorSancion(com.edicsem.pe.sie.entity.FactorSancionSie)
	 */
	public void insertFactorSancion(FactorSancionSie s) {
		log.info(" insertar  "+ s.getDescripcion()+
				" - ");
		try {
			em.persist(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FactorSancionDAO#updateFactorSancion(com.edicsem.pe.sie.entity.FactorSancionSie)
	 */
	public void updateFactorSancion(FactorSancionSie s) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Factor sancion");
			}
			log.info("bean" + s.getDescripcion() + " " + s.getIdfactor());
			em.merge(s);
			log.info("--- ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FactorSancionDAO#findFactorSancion(int)
	 */
	public FactorSancionSie findFactorSancion(int id) {
		FactorSancionSie s = new FactorSancionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar   " +id);
			}
			s = em.find(FactorSancionSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FactorSancionDAO#listarFactorSancion()
	 */
	public List listarFactorSancion() {
		List lista = null;
		try {
			Query q = em.createQuery("select c from FactorSancionSie c ");
			lista = q.getResultList();
			log.info("  tamaño  -->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
