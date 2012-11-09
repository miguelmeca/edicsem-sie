package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoLlamadaSie;
import com.edicsem.pe.sie.model.dao.TipoLLamadaDAO;


@Stateless
public class TipoLLamadaDAOImpl implements TipoLLamadaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoLLamadaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#insertTipoCasa(com.edicsem.pe.sie.entity.TipoCasaSie)
	 */
	public void insertTipoLLamada(TipoLlamadaSie tipollamada) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar el tipollamada");
			}
			em.persist(tipollamada);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#updateTipoCasa(com.edicsem.pe.sie.entity.TipoCasaSie)
	 */
	public void updateTipoLLamada(TipoLlamadaSie tipollamada) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el tipollamada");
			}
			em.merge(tipollamada);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#eliminarTipoCasa(int)
	 */
	public void eliminarTipoLLamada(int id) {
		// TODO Auto-generated method stub
		
	}

 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#findTipoCasa(int)
	 */
	public TipoLlamadaSie findTipoLLamada(int id) {
		TipoLlamadaSie e= new TipoLlamadaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar TipoLlamada");
			} 
		e=	em.find(TipoLlamadaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoCasaDAO#listarTipoCasa()
	 */
	public List listarTipoLLamada() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoLlamadaSie p");
			lista =  q.getResultList(); 
			log.info("tamaño lista TipoLLamada  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
 
	
}
