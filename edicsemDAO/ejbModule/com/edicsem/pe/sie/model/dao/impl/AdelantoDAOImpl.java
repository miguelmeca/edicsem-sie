package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.AdelantoSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.model.dao.AdelantoDAO;
import com.edicsem.pe.sie.model.dao.ContratoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class AdelantoDAOImpl implements AdelantoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(AdelantoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AdelantoDAO#insertAdelanto(com.edicsem.pe.sie.entity.AdelantoSie)
	 */
	public void insertAdelanto(AdelantoSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar adelanto");
			} 
			em.persist(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AdelantoDAO#updateAdelanto(com.edicsem.pe.sie.entity.AdelantoSie)
	 */
	public void updateAdelanto(AdelantoSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar adelanto");
			} 
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AdelantoDAO#findAdelanto(int)
	 */
	public AdelantoSie findAdelanto(int id) {
		AdelantoSie a= new AdelantoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Adelanto");
			} 
		a=	em.find(AdelantoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AdelantoDAO#listarAdelantos()
	 */
	public List listarAdelantos() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from AdelantoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Adelanto --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
