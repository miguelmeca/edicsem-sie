package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoCobranzaSie;
import com.edicsem.pe.sie.model.dao.TipoCobranzaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoCobranzaDAOImpl implements TipoCobranzaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoCobranzaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCobranzaDAO#insertTipoCobranza(com.edicsem.pe.sie.entity.TipoCobranzaSie)
	 */
	public void insertTipoCobranza(TipoCobranzaSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar tipo de cobranza");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCobranzaDAO#updateTipoCobranza(com.edicsem.pe.sie.entity.TipoCobranzaSie)
	 */
	public void updateTipoCobranza(TipoCobranzaSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar tipo de cobranza ");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCobranzaDAO#findTipoCobranza(int)
	 */
	public TipoCobranzaSie findTipoCobranza(int id) {
		TipoCobranzaSie t= new TipoCobranzaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar tipo de cobranza "+ id);
			}
			t =	em.find(TipoCobranzaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCobranzaDAO#listarTipoCobranza()
	 */
	public List listarTipoCobranza() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoCobranzaSie p where p.tbEstadoGeneral.idestadogeneral = "+ 102);
			lista =  q.getResultList(); 
		   log.info("tamaño lista Cobranza --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
