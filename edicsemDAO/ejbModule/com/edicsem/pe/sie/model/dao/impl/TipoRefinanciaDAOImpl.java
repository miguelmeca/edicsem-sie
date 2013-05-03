package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoRefinanciaSie;
import com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoRefinanciaDAOImpl implements TipoRefinanciaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoRefinanciaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO#insertTipoRefinancia(com.edicsem.pe.sie.entity.TipoRefinanciaSie)
	 */
	public void insertTipoRefinancia(TipoRefinanciaSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar tipo refinancia");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO#updateTipoRefinancia(com.edicsem.pe.sie.entity.TipoRefinanciaSie)
	 */
	public void updateTipoRefinancia(TipoRefinanciaSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar tipo refinancia");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO#findTipoRefinancia(int)
	 */
	public TipoRefinanciaSie findTipoRefinancia(int id) {
		TipoRefinanciaSie t= new TipoRefinanciaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar tipo refinancia "+ id);
			}
			t=	em.find(TipoRefinanciaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO#listarTipoRefinancia()
	 */
	public List listarTipoRefinancia() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoRefinanciaSie p where p.tbEstadoGeneral.idestadogeneral = "+ 21);
			lista =  q.getResultList(); 
		   log.info("tamaño lista tipo refinancia --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoRefinanciaDAO#listarTipoRefinanciaXTipoCliente(int)
	 */
	public List listarTipoRefinanciaXTipoCliente(int tipocliente) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoRefinanciaSie p where p.tbTipoCliente.idtipocliente = "+tipocliente +
					" and p.tbEstadoGeneral.idestadogeneral = "+ 21);
			lista =  q.getResultList(); 						
		   log.info("tamaño lista tipo refinancia por tipo cliente --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
