package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CarroSie;
import com.edicsem.pe.sie.entity.DetTurnoEmplSie;
import com.edicsem.pe.sie.entity.TurnoSie;
import com.edicsem.pe.sie.model.dao.CarroDAO;
import com.edicsem.pe.sie.model.dao.DetTurnoEmplDAO;
import com.edicsem.pe.sie.model.dao.TurnoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetTurnoEmplDAOImpl implements DetTurnoEmplDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetTurnoEmplDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetTurnoEmplDAO#insertDetTurnoEmpl(com.edicsem.pe.sie.entity.DetTurnoEmplSie)
	 */
	public void insertDetTurnoEmpl(DetTurnoEmplSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetTurnoEmpl");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetTurnoEmplDAO#updateDetTurnoEmpl(com.edicsem.pe.sie.entity.DetTurnoEmplSie)
	 */
	public void updateDetTurnoEmpl(DetTurnoEmplSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetTurnoEmpl");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetTurnoEmplDAO#findDetTurnoEmpl(int)
	 */
	public DetTurnoEmplSie findDetTurnoEmpl(int id) {
		DetTurnoEmplSie t= new DetTurnoEmplSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetTurnoEmpl  "+ id);
			}
			t =	em.find(DetTurnoEmplSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetTurnoEmplDAO#listarDetTurnoEmpl()
	 */
	public List listarDetTurnoEmpl() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetTurnoEmplSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista DetTurnoEmplSie --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
