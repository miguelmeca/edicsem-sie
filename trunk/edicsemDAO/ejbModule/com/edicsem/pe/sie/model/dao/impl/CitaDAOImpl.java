package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CitaSie;
import com.edicsem.pe.sie.model.dao.CitaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class CitaDAOImpl implements CitaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CitaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CitaDAO#insertCita(com.edicsem.pe.sie.entity.CitaSie)
	 */
	public void insertCita(CitaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Cita");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CitaDAO#updateCita(com.edicsem.pe.sie.entity.CitaSie)
	 */
	public void updateCita(CitaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar cita");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CitaDAO#findCita(int)
	 */
	public CitaSie findCita(int id) {
		CitaSie c= new CitaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar cita "+ id);
			}
			c =	em.find(CitaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CitaDAO#listarCitas()
	 */
	public List listarCitas() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CitaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista citas --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
