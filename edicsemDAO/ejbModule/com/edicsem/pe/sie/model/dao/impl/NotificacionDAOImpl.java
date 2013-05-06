package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.NotificacionSie;
import com.edicsem.pe.sie.model.dao.NotificacionDAO;

/**
 * @author karen
 *
 */
@Stateless
public class NotificacionDAOImpl implements NotificacionDAO{
	
	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(NotificacionDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.NotificacionDAO#insertNotificacion(com.edicsem.pe.sie.entity.NotificacionSie)
	 */
	public void insertNotificacion(NotificacionSie n) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar notificacion");
			} 
			em.persist(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.NotificacionDAO#updateNotificacion(com.edicsem.pe.sie.entity.NotificacionSie)
	 */
	public void updateNotificacion(NotificacionSie n) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar notificacion");
			} 
			em.merge(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.NotificacionDAO#findNotificacion(int)
	 */
	public NotificacionSie findNotificacion(int id) {
		NotificacionSie n= new NotificacionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen "+ id);
			}
			n = em.find(NotificacionSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.NotificacionDAO#listarNotificacion()
	 */
	public List listarNotificacion() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from NotificacionSie p where p.tbEstadoGeneral.idestadogeneral = "+ 112);
			lista =  q.getResultList(); 
		   log.info("tamaño lista Notificacion --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
