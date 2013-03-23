package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoAsistenciaSie;
import com.edicsem.pe.sie.model.dao.TipoAsistenciaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoAsistenciaDAOImpl implements TipoAsistenciaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoAsistenciaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoAsistenciaDAO#findTipoAsistencia(int)
	 */
	public TipoAsistenciaSie findTipoAsistencia(int id) {
		TipoAsistenciaSie en= new TipoAsistenciaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar tipo asistencia "+ id);
			}
			en=	em.find(TipoAsistenciaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return en;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoAsistenciaDAO#listarTipoAsistencia()
	 */
	public List listarTipoAsistencia() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoAsistenciaSie p " );
			lista =  q.getResultList(); 						
		   log.info("tamaño lista tipo asistencia DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
