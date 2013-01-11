package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.model.dao.PaqueteDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class PaqueteDAOImpl implements PaqueteDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(PaqueteDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PaqueteDAO#insertPaquete(com.edicsem.pe.sie.entity.PaqueteSie)
	 */
	public void insertPaquete(PaqueteSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Paquete "+ p.getIdpaquete());
			}
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PaqueteDAO#findPaquete(int)
	 */
	public PaqueteSie findPaquete(int id) {
		log.info("buscar Paquetebiblico " +id);
		PaqueteSie p = new PaqueteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Paquete" +id);
			}
			p = em.find(PaqueteSie.class, id);
			log.info(" PaqueteBiblico " + id);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("DENTRO DEL CATCH"+ e.getMessage());
			log.info("findPaquete" );
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PaqueteDAO#listarPaquetes()
	 */
	public List listarPaquetes() {
		log.info("***************** antes del QUERY listar Paquete biblico");
		List lista = null;
		try {
			Query q = em.createQuery("select p from PaqueteSie p where p.tbEstadoGeneral.idestadogeneral = "+ 60);
			lista = q.getResultList();									
			log.info("tamaño lista DAOIMPL Paquetes --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PaqueteDAO#updatePaquete(com.edicsem.pe.sie.entity.PaqueteSie)
	 */
	public void updatePaquete(PaqueteSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Paquete");
			}
			
			log.info("DAOIMPL bean" + p.getIdpaquete() + " " + p.getCodpaquete()+ " " + p.getDescripcionpaquete() );
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
