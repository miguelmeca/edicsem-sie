package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TurnoSie;
import com.edicsem.pe.sie.model.dao.TurnoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TurnoDAOImpl implements TurnoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TurnoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#insertTurno(com.edicsem.pe.sie.entity.TurnoSie)
	 */
	public void insertTurno(TurnoSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar turno");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#updateTurno(com.edicsem.pe.sie.entity.TurnoSie)
	 */
	public void updateTurno(TurnoSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar turno");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#findTurno(int)
	 */
	public TurnoSie findTurno(int id) {
		TurnoSie t= new TurnoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar turno "+ id);
			}
			t =	em.find(TurnoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TurnoDAO#listarTurno()
	 */
	public List listarTurno() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TurnoSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista turno --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
