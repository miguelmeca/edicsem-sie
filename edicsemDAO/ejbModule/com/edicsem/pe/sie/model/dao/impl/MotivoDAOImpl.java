package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MotivoSie;
import com.edicsem.pe.sie.model.dao.MotivoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class MotivoDAOImpl implements MotivoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(MotivoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MotivoDAO#insertMotivo(com.edicsem.pe.sie.entity.MotivoSie)
	 */
	public void insertMotivo(MotivoSie m) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar motivo");
			} 
			em.persist(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MotivoDAO#updateMotivo(com.edicsem.pe.sie.entity.MotivoSie)
	 */
	public void updateMotivo(MotivoSie m) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar motivo");
			} 
			em.merge(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MotivoDAO#findMotivo(int)
	 */
	public MotivoSie findMotivo(int id) {
		MotivoSie m= new MotivoSie();
		try {
			m=	em.find(MotivoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MotivoDAO#listarMotivo()
	 */
	public List listarMotivo() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from MotivoSie p where p.tbEstadoGeneral.idestadogeneral = 74 ");
			lista =  q.getResultList(); 						
		   log.info("tamaño lista Motivo -> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
