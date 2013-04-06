package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HistorialParametroSistemaSie;
import com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class HistorialParametroSistemaDAOImpl implements HistorialParametroSistemaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(HistorialParametroSistemaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#insertHistorial(com.edicsem.pe.sie.entity.HistorialParametroSistemaSie)
	 */
	public void insertHistorial(HistorialParametroSistemaSie h) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar historial parametro del sistema");
			} 
			em.persist(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#updateHistorial(com.edicsem.pe.sie.entity.HistorialParametroSistemaSie)
	 */
	public void updateHistorial(HistorialParametroSistemaSie h) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar historial parametro del sistema");
			}
			em.merge(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#findHistorial(int)
	 */
	public HistorialParametroSistemaSie findHistorial(int id) {
		HistorialParametroSistemaSie c= new HistorialParametroSistemaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar historial parametro del sistema  "+ id);
			}
			c =	em.find(HistorialParametroSistemaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO#listarhistorial()
	 */
	public List listarhistorial() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HistorialParametroSistemaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista HistorialParametroSistema --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
