package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ModuloOpcionSie;
import com.edicsem.pe.sie.model.dao.ModuloOpcionDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ModuloOpcionDAOImpl implements ModuloOpcionDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ModuloOpcionDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ModuloOpcionDAO#insertModuloOpcion(com.edicsem.pe.sie.entity.ModuloOpcionSie)
	 */
	public void insertModuloOpcion(ModuloOpcionSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Modulo Opcion");
			} 
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ModuloOpcionDAO#updateModuloOpcion(com.edicsem.pe.sie.entity.ModuloOpcionSie)
	 */
	public void updateModuloOpcion(ModuloOpcionSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Modulo Opcion");
			}
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ModuloOpcionDAO#findModuloOpcion(int)
	 */
	public ModuloOpcionSie findModuloOpcion(int id) {
		ModuloOpcionSie p= new ModuloOpcionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Modulo Opcion "+ id);
			}
			p=	em.find(ModuloOpcionSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ModuloOpcionDAO#listarModuloOpcion()
	 */
	public List<String> listarModuloOpcion() {
		List<String>  lista = null;
		try {
			Query q = em.createQuery("select DISTINCT(p.nombremodulo) from ModuloOpcionSie p ");
			lista =  q.getResultList(); 						
		   log.info("tamaño lista ModuloOpcion DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
