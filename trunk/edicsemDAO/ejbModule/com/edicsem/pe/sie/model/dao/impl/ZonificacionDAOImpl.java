package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ZonificacionSie;
import com.edicsem.pe.sie.model.dao.ZonificacionDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ZonificacionDAOImpl implements ZonificacionDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ZonificacionDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#insertZonificacion(com.edicsem.pe.sie.entity.ZonificacionSie)
	 */
	public void insertZonificacion(ZonificacionSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar zonificacion");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#updateZonificacion(com.edicsem.pe.sie.entity.ZonificacionSie)
	 */
	public void updateZonificacion(ZonificacionSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar zonificacion");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#findZonificacion(int)
	 */
	public ZonificacionSie findZonificacion(int id) {
		ZonificacionSie c= new ZonificacionSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar zonificacion "+ id);
			}
			c = em.find(ZonificacionSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#listarZonificacion()
	 */
	public List listarZonificacion() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ZonificacionSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista zonificacion --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#listarZonificacionXDistrito(java.lang.String)
	 */
	public List listarZonificacionXDistrito(String idUbigeo) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ZonificacionSie p where p.idubigeo = "+idUbigeo);
			lista =  q.getResultList();
		   log.info("tamaño lista zonificacion X Distrito --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#listarZonificacionXPlano(java.lang.String, java.util.List)
	 */
	public List listarZonificacionXPlano(String idUbigeo,List<String> planoList) {
		log.info(" Lista de planos "+ planoList);
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ZonificacionSie p where p.idubigeo like '"+idUbigeo+"' and " +
					" p.codPlano in "+planoList);
			lista =  q.getResultList();
		   log.info("tamaño lista zonificacion X Plano --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ZonificacionDAO#listarZonificacionXPlanoXLetra(java.lang.String, java.util.List, java.util.List)
	 */
	public List listarZonificacionXPlanoXLetra(String idUbigeo,List<String> planoList, List<String> letraList) {
		log.info(" Lista de planos "+ planoList);
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ZonificacionSie p where p.idubigeo like '"+idUbigeo+"' and " +
					" p.codPlano in "+planoList+" and p.codLetra in "+letraList);
			lista =  q.getResultList();
		   log.info("tamaño lista zonificacion X Letra --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
