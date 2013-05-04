package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CalificacionEquifaxSie;
import com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO;

/**
 * @author karen
 *
 */
@Stateless
public class CalificacionEquifaxDAOImpl implements CalificacionEquifaxDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CalificacionEquifaxDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO#insertCalificacion(com.edicsem.pe.sie.entity.CalificacionEquifaxSie)
	 */
	public void insertCalificacion(CalificacionEquifaxSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar calificacion");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO#updateCalificacion(com.edicsem.pe.sie.entity.CalificacionEquifaxSie)
	 */
	public void updateCalificacion(CalificacionEquifaxSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar calificacion");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO#findCalificacion(int)
	 */
	public CalificacionEquifaxSie findCalificacion(int id) {
		CalificacionEquifaxSie c= new CalificacionEquifaxSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar calificacion "+ id);
			}
			c = em.find(CalificacionEquifaxSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CalificacionEquifaxDAO#listarCalificacionEquifax()
	 */
	public List listarCalificacionEquifax() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CalificacionEquifaxSie p where p.tbEstadoGeneral.idestadogeneral = "+ 13);
			lista =  q.getResultList(); 
		   log.info("tamaño lista calificacion --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
