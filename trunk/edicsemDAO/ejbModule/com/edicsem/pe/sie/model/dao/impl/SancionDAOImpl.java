package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.SancionSie;
import com.edicsem.pe.sie.model.dao.SancionDAO;

@Stateless
public class SancionDAOImpl implements SancionDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;

	private static Log log = LogFactory.getLog(SancionDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SancionDAO#insertSancion(com.edicsem.pe.sie.entity.SancionSie)
	 */
	public void insertSancion(SancionSie s) {
		log.info(" insertar  "+ s.getDescripcion()+
				" - ");
		try {
			em.persist(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SancionDAO#updateSancion(com.edicsem.pe.sie.entity.SancionSie)
	 */
	public void updateSancion(SancionSie s) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar sancion");
			}
			log.info("bean" + s.getDescripcion() + " " + s.getIdsancion());
			em.merge(s);
			log.info("--- ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SancionDAO#findSancion(int)
	 */
	public SancionSie findSancion(int id) { 
		SancionSie s = new SancionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar   " +id);
			}
			s = em.find(SancionSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SancionDAO#listarSanciones()
	 */
	public List listarSanciones(int id) {
		List lista = null;
		try {
			Query q = em.createQuery("select c from SancionSie c where c.tbFactorSancion.idfactor = "+ id);
			lista = q.getResultList();
			log.info("  tamaño  -->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
