package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigNotificacionSie;
import com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ConfigNotificacionDAOImpl implements ConfigNotificacionDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ConfigNotificacionDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO#insertConfigNotificacion(com.edicsem.pe.sie.entity.ConfigNotificacionSie)
	 */
	public void insertConfigNotificacion(ConfigNotificacionSie n) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar config notificacion");
			} 
			em.persist(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO#updateConfigNotificacion(com.edicsem.pe.sie.entity.ConfigNotificacionSie)
	 */
	public void updateConfigNotificacion(ConfigNotificacionSie n) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar config cobranza");
			} 
			em.merge(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO#findConfigNotificacion(int)
	 */
	public ConfigNotificacionSie findConfigNotificacion(int id) {
		ConfigNotificacionSie c= new ConfigNotificacionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar config notificacion "+ id);
			}
			c =	em.find(ConfigNotificacionSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigNotificacionDAO#listarConfigNotificacion()
	 */
	public List listarConfigNotificacion() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ConfigNotificacionSie p where p.tbEstadoGeneral.idestadogeneral = "+ 113);
			lista =  q.getResultList(); 
		   log.info("tamaño lista config notificacion  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
