package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ParametroActividadSie;
import com.edicsem.pe.sie.model.dao.ParametroActividadDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ParametroActividadDAOImpl implements ParametroActividadDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ParametroActividadDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ParametroActividadDAO#insertParametroActividad(com.edicsem.pe.sie.entity.ParametroActividadSie)
	 */
	public void insertParametroActividad(ParametroActividadSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar parametro actividad");
			} 
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ParametroActividadDAO#updateParametroActividad(com.edicsem.pe.sie.entity.ParametroActividadSie)
	 */
	public void updateParametroActividad(ParametroActividadSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar parametro actividad");
			} 
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ParametroActividadDAO#findParametroActividad(int)
	 */
	public ParametroActividadSie findParametroActividad(int id) {
		ParametroActividadSie p= new ParametroActividadSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen "+ id);
			}
			p = em.find(ParametroActividadSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ParametroActividadDAO#listarParametroActividad()
	 */
	public List listarParametroActividad() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ParametroActividadSie p where p.tbEstadoGeneral.idestadogeneral = "+ 117);
			lista =  q.getResultList(); 
		   log.info("tamaño lista parametro actividad  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
