package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.model.dao.ParametroDAO;

@Stateless
public class ParametroDAOImpl implements ParametroDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ParametroDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarParametro(ParametroSistemaSie parametro) {
		try {                              
			em.persist(parametro);
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar parametro");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarParametro(ParametroSistemaSie parametro) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar parametro");
			}
			em.merge(parametro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarParametro(int id) {
		try {
			em.remove(id);
			if (log.isInfoEnabled()) {
				log.info("apunto de eliminar parametro");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public ParametroSistemaSie findParametro(int id) {
		ParametroSistemaSie parametro= new ParametroSistemaSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar proveedor");
		} 
		parametro=	em.find(ParametroSistemaSie.class, id);
		log.info(" parametro " +parametro);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return parametro;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarParametros() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ParametroSistemaSie p where p.tbEstadoGeneral.idestadogeneral = "+29);
			lista = q.getResultList();
			log.info("tamaño lista parametro --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
