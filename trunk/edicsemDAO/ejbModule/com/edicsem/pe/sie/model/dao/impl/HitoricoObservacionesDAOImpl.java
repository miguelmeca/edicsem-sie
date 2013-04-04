package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HistoricoObservacionesSie;
import com.edicsem.pe.sie.model.dao.HistoricoObbservacionesDAO;

/**
 * @author karen
 *
 */
@Stateless
public class HitoricoObservacionesDAOImpl implements HistoricoObbservacionesDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(HitoricoObservacionesDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistoricoObbservacionesDAO#insertHistoricoObservaciones(com.edicsem.pe.sie.entity.HistoricoObservacionesSie)
	 */
	public void insertHistoricoObservaciones(HistoricoObservacionesSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar historico Observaciones");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistoricoObbservacionesDAO#updateHistoricoObservaciones(com.edicsem.pe.sie.entity.HistoricoObservacionesSie)
	 */
	public void updateHistoricoObservaciones(HistoricoObservacionesSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar historico observaciones");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistoricoObbservacionesDAO#findHistoricoObservaciones(int)
	 */
	public HistoricoObservacionesSie findHistoricoObservaciones(int id) {
		HistoricoObservacionesSie c= new HistoricoObservacionesSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar historico observaciones  "+ id);
			}
			c =	em.find(HistoricoObservacionesSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistoricoObbservacionesDAO#listarHistoricoObservaciones()
	 */
	public List listarHistoricoObservaciones() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HistoricoObservacionesSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista HistoricoObservaciones --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
