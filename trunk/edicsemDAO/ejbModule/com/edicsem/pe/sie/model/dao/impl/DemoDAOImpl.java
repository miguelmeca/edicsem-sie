package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.model.dao.DemoDAO;

@Stateless
public class DemoDAOImpl implements DemoDAO{

	 @PersistenceContext(name="edicsemJPASie")
 	private EntityManager em;
 	private static Log log = LogFactory.getLog(DemoDAOImpl.class);
	
	/*(non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 * 
	public void insertDemo(Usuario usuario) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Usuario");
			}
			em.persist(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 
	public void updateDemo(Usuario usuario) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de actualizar Usuario");
			}
			em.merge(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 *
	public void deleteDemo(String id) {
	
	}

	 (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 *
	public Usuario findDemo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	 (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 
	public List listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}
