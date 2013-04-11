package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie;
import com.edicsem.pe.sie.model.dao.ConfigCorreosIncidenteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ConfigCorreosIncidenteDAOImpl implements ConfigCorreosIncidenteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ConfigCorreosIncidenteDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCorreosIncidenteDAO#insertConfigCorreos(com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie)
	 */
	public void insertConfigCorreos(ConfigCorrreosIncidenteSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Almacen");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCorreosIncidenteDAO#updateConfigCorreos(com.edicsem.pe.sie.entity.ConfigCorrreosIncidenteSie)
	 */
	public void updateConfigCorreos(ConfigCorrreosIncidenteSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar canfiguracion de correos por incidente");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCorreosIncidenteDAO#findConfigCorreo(int)
	 */
	public ConfigCorrreosIncidenteSie findConfigCorreo(int id) {
		ConfigCorrreosIncidenteSie c= new ConfigCorrreosIncidenteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar configuracion "+ id);
			}
			c =	em.find(ConfigCorrreosIncidenteSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCorreosIncidenteDAO#listarConfigCorreos()
	 */
	public List listarConfigCorreos() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ConfigCorrreosIncidenteSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista configuracion de correos incidente --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
