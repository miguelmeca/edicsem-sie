package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.VerificaClienteSie;
import com.edicsem.pe.sie.model.dao.VerificaClienteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class VerificaClienteDAOImpl implements VerificaClienteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(VerificaClienteDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaClienteDAO#findVerificaCliente(int)
	 */
	public VerificaClienteSie findVerificaCliente(int id) {
		VerificaClienteSie v= new VerificaClienteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Verifica Cliente "+ id);
			} 
		v =	em.find(VerificaClienteSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaClienteDAO#listarVerificaCliente()
	 */
	public List listarVerificaCliente() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from VerificaClienteSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista Verifica Cliente --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaClienteDAO#insertVerificaCliente(com.edicsem.pe.sie.entity.VerificaClienteSie)
	 */
	public void insertVerificaCliente(VerificaClienteSie v) {
		log.info(" insertar  ");
		try {
			em.persist(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaClienteDAO#updateVerificaCliente(com.edicsem.pe.sie.entity.VerificaClienteSie)
	 */
	public void updateVerificaCliente(VerificaClienteSie v) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar verifica cliente");
			}
			em.merge(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
