package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoImporteSie;
import com.edicsem.pe.sie.model.dao.TipoImporteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoImporteDAOImpl implements TipoImporteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoImporteDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoImporteDAO#insertTipoImporte(com.edicsem.pe.sie.entity.TipoImporteSie)
	 */
	public void insertTipoImporte(TipoImporteSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar TipoImporte");
			} 
			em.persist(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoImporteDAO#updateTipoImporte(com.edicsem.pe.sie.entity.TipoImporteSie)
	 */
	public void updateTipoImporte(TipoImporteSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar TipoImporte");
			} 
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoImporteDAO#findTipoImporte(int)
	 */
	public TipoImporteSie findTipoImporte(int id) {
		TipoImporteSie a= new TipoImporteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar TipoImporte");
			} 
		a=	em.find(TipoImporteSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoImporteDAO#listarTipoImporte()
	 */
	public List listarTipoImporte() {
		List  lista = null;
		try {
			Query q = em.createQuery("select a from TipoImporteSie a ");
			lista =  q.getResultList(); 
			log.info("tamaño lista TipoImporte --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
