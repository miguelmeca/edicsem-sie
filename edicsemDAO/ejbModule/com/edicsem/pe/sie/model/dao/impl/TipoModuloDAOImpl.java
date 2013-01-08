package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoModuloSie;
import com.edicsem.pe.sie.model.dao.TipoModuloDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoModuloDAOImpl implements TipoModuloDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoModuloDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoModuloDAO#insertTipoModulo(com.edicsem.pe.sie.entity.TipoModuloSie)
	 */
	public void insertTipoModulo(TipoModuloSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Tipo Modulo");
			} 
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoModuloDAO#updateTipoModulo(com.edicsem.pe.sie.entity.TipoModuloSie)
	 */
	public void updateTipoModulo(TipoModuloSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Tipo Modulo");
			}
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoModuloDAO#findTipoModulo(int)
	 */
	public TipoModuloSie findTipoModulo(int id) {
		TipoModuloSie p= new TipoModuloSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Tipo Modulo"+ id);
			}
			p=	em.find(TipoModuloSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoModuloDAO#listarTipoModulo()
	 */
	public List<String> listarTipoModulo() {
		List<String> lista = null;
		try {
			lista = new ArrayList<String>();
			lista = em.createQuery("Select DISTINCT(t.descripcion) from TipoModuloSie t ")
					.getResultList();
			 log.info("tamaño lista Tipo Modulo DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
