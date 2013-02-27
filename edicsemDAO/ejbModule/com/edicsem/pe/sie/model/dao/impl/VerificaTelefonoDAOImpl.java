package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.VerificaTelefonoSie;
import com.edicsem.pe.sie.model.dao.VerificaTelefonoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class VerificaTelefonoDAOImpl implements VerificaTelefonoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(VerificaTelefonoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaTelefonoDAO#insertVerificaTelefono(com.edicsem.pe.sie.entity.VerificaTelefonoSie)
	 */
	public void insertVerificaTelefono(VerificaTelefonoSie v) {
		try {
			em.persist(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaTelefonoDAO#updateVerificaTelefono(com.edicsem.pe.sie.entity.VerificaTelefonoSie)
	 */
	public void updateVerificaTelefono(VerificaTelefonoSie v) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar verifica telefono");
			}
			em.merge(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaTelefonoDAO#findVerificaTelefono(int)
	 */
	public VerificaTelefonoSie findVerificaTelefono(int id) {
		VerificaTelefonoSie v= new VerificaTelefonoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Verifica Cliente "+ id);
			} 
		v =	em.find(VerificaTelefonoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaTelefonoDAO#listarVerificaTelefono()
	 */
	public List listarVerificaTelefono() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from VerificaTelefonoSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista Verifica Telefono --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
