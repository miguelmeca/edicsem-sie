package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.FechaSie;
import com.edicsem.pe.sie.model.dao.FechaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class FechaDAOImpl implements FechaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(FechaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FechaDAO#listarFechas()
	 */
	public List listarFechas() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from FechaSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Fechas --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FechaDAO#findFecha(int)
	 */
	public FechaSie findFecha(int id) {
		FechaSie f= new FechaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar FechaSie"+ id);
			} 
		f=	em.find(FechaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
}
