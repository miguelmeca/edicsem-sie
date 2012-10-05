package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class CobranzaDAOImpl implements CobranzaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CobranzaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranza(CobranzaSie Cobranza) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Cobranza");
			} 
			em.persist(Cobranza);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranza(CobranzaSie Cobranza) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Cobranza");
			} 
			em.merge(Cobranza);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#findCobranza(int)
	 */
	public CobranzaSie findCobranza(int id) {
		CobranzaSie obj= new CobranzaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Contrato");
			} 
		obj=	em.find(CobranzaSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#listarCobranzas()
	 */
	public List listarCobranzas() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CobranzaSie p ");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista Cobranza --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
}
