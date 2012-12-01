package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.model.dao.DetSancionCargoDAO;

@Stateless
public class DetSancionCargoDAOImpl implements DetSancionCargoDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetSancionCargoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionCargoDAO#insertDetSancionCargo(com.edicsem.pe.sie.entity.DetSancionCargoSie)
	 */
	public void insertDetSancionCargo(DetSancionCargoSie d) {
		try {
			em.persist(d);
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar DetSancionCargoSie");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionCargoDAO#updateDetSancionCargo(com.edicsem.pe.sie.entity.DetSancionCargoSie)
	 */
	public void updateDetSancionCargo(DetSancionCargoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar DetSancionCargoSie");
			}
			em.merge(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionCargoDAO#findDetSancionCargo(int)
	 */
	public DetSancionCargoSie findDetSancionCargo(int id) {
		DetSancionCargoSie d= new DetSancionCargoSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar detallecaremp");
		} 
		d=	em.find(DetSancionCargoSie.class, id);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return d;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionCargoDAO#listarDetSancionCargo(int)
	 */
	public List listarDetSancionCargo(int idSancion) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from DetSancionCargoSie p where p.tbSancion.idsancion = "+idSancion);
			lista = q.getResultList();
			log.info("tamaño lista detalleCargo --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
