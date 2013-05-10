package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigPuntajeSie;
import com.edicsem.pe.sie.model.dao.ConfigPuntajeDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ConfigPuntajeDAOImpl implements ConfigPuntajeDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ConfigPuntajeDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigPuntajeDAO#insertConfigPuntaje(com.edicsem.pe.sie.entity.ConfigPuntajeSie)
	 */
	public void insertConfigPuntaje(ConfigPuntajeSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar config puntaje");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigPuntajeDAO#updateConfigPuntaje(com.edicsem.pe.sie.entity.ConfigPuntajeSie)
	 */
	public void updateConfigPuntaje(ConfigPuntajeSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar config puntaje");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigPuntajeDAO#findConfigPuntaje(int)
	 */
	public ConfigPuntajeSie findConfigPuntaje(int id) {
		ConfigPuntajeSie c= new ConfigPuntajeSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar config puntaje "+ id);
			}
			c = em.find(ConfigPuntajeSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigPuntajeDAO#listarConfigPuntaje()
	 */
	public List listarConfigPuntaje() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ConfigPuntajeSie p where p.tbEstadoGeneral.idestadogeneral = 119 ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista config puntaje --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
