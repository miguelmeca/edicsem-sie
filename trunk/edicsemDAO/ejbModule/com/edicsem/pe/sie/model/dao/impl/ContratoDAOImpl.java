package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.model.dao.ContratoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ContratoDAOImpl implements ContratoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ContratoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#insertContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	
	public void insertContrato(ContratoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar contrato");
			} 
			em.persist(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#updateContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	
	public void updateContrato(ContratoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar contrato");
			} 
			em.merge(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#findContrato(int)
	 */
	
	public ContratoSie findContrato(int id) {
		ContratoSie contrato= new ContratoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Contrato");
			} 
		contrato=	em.find(ContratoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contrato;
	}

	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#listarContratos()
	 */
	public List listarContratos() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ContratoSie p ");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista Contrato --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
