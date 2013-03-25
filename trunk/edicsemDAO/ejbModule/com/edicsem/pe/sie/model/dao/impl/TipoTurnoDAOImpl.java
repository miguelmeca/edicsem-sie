package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoTurnoSie;
import com.edicsem.pe.sie.model.dao.TipoTurnoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoTurnoDAOImpl implements TipoTurnoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoTurnoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoTurnoDAO#insertTipoTurno(com.edicsem.pe.sie.entity.TipoTurnoSie)
	 */
	public void insertTipoTurno(TipoTurnoSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Tipo Turno");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoTurnoDAO#updateTipoTurno(com.edicsem.pe.sie.entity.TipoTurnoSie)
	 */
	public void updateTipoTurno(TipoTurnoSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar tipo turno ");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoTurnoDAO#findTipoTurno(int)
	 */
	public TipoTurnoSie findTipoTurno(int id) {
		TipoTurnoSie t= new TipoTurnoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Tipo Turno "+ id);
			}
			t=	em.find(TipoTurnoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoTurnoDAO#listarTipoTurno()
	 */
	public List listarTipoTurno() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoTurnoSie" );
			lista =  q.getResultList(); 
		   log.info("tamaño lista tipo turno  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
