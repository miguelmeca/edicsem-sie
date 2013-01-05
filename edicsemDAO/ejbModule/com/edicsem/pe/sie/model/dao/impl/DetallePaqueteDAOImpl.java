package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.model.dao.DetPaqueteDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class DetallePaqueteDAOImpl implements DetPaqueteDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetallePaqueteDAOImpl.class);
	 
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPaqueteDAO#findDetPaquete(int)
	 */
	public DetPaqueteSie findDetPaquete(int id) {
		DetPaqueteSie p = new DetPaqueteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetPaquete");
			}
			p = em.find(DetPaqueteSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPaqueteDAO#listarDetPaquetes(int)
	 */
	public List listarDetPaquetes(int paquete) {
		List lista = null;
		try {
			log.info("Antes del QUERY DAOIMPL");
			Query q = em.createQuery("select p from DetPaqueteSie p where p.tbPaquete.idpaquete = "+ paquete);
			lista = q.getResultList();
			log.info("dspues de la lista tama�o lista DetPaqueteSie --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPaqueteDAO#insertDetPaquete(com.edicsem.pe.sie.entity.DetPaqueteSie)
	 */
	public void insertDetPaquete(DetPaqueteSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetPaquete "+ p.getIdDetPaquete());
			}
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPaqueteDAO#updateDetPaquete(com.edicsem.pe.sie.entity.DetPaqueteSie)
	 */
	public void updateDetPaquete(DetPaqueteSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetPaqueteSie");
			}
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}

