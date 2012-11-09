package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;


@Stateless
public class TipoCasaDAOImpl implements TipoCasaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoCasaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#insertTipoCasa(com.edicsem.pe.sie.entity.TipoCasaSie)
	 */
	public void insertTipoCasa(TipoCasaSie tipocasa) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar el tipo de casa");
			}
			em.persist(tipocasa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#updateTipoCasa(com.edicsem.pe.sie.entity.TipoCasaSie)
	 */
	public void updateTipoCasa(TipoCasaSie tipocasa) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el tipo de casa");
			}
			em.merge(tipocasa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#eliminarTipoCasa(int)
	 */
	public void eliminarTipoCasa(int id) {
		// TODO Auto-generated method stub
		
	}

 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#findTipoCasa(int)
	 */
	public TipoCasaSie findTipoCasa(int id) {
		TipoCasaSie e= new TipoCasaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar TipoCasa");
			} 
		e=	em.find(TipoCasaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#listarTipoCasa()
	 */
	public List listarTipoCasa() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoCasaSie p");
			lista =  q.getResultList(); 
			log.info("tamaño lista TipoCasa  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
 
	
}
