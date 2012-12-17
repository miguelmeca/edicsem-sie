package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MetaEmpleadoSie;
import com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO;

@Stateless
public class MetaEmpleadoDAOImpl implements MetaEmpleadoDAO{

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;

	private static Log log = LogFactory.getLog(MetaEmpleadoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO#insertMetaEpleado(com.edicsem.pe.sie.entity.MetaEmpleadoSie)
	 */
	public void insertMetaEpleado(MetaEmpleadoSie m) {
		log.info(" insertar  " );
		try {
			em.persist(m);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO#updateMetaEpleado(com.edicsem.pe.sie.entity.MetaEmpleadoSie)
	 */
	public void updateMetaEpleado(MetaEmpleadoSie m) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Factor MetaEmpleadoSie");
			}
			em.merge(m);
			log.info("--- ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO#findMetaEmpleado(int)
	 */
	public MetaEmpleadoSie findMetaEmpleado(int id) {
		MetaEmpleadoSie s = new MetaEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar   " +id);
			}
			s = em.find(MetaEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO#listarMetaEmpleado()
	 */
	public List listarMetaEmpleado() {
		List lista = null;
		try {
			Query q = em.createQuery("select c from MetaEmpleadoSie c ");
			lista = q.getResultList();
			log.info("  tamaño  -->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
