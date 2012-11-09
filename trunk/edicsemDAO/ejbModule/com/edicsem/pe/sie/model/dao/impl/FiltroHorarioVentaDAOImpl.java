package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
import com.edicsem.pe.sie.model.dao.FiltroHorarioVentaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class FiltroHorarioVentaDAOImpl implements FiltroHorarioVentaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(FiltroHorarioVentaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FiltroHorarioVentaDAO#insertFiltroHorarioVenta(com.edicsem.pe.sie.entity.FiltroHorarioVentaSie)
	 */
	public void insertFiltroHorarioVenta(FiltroHorarioVentaSie f) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar FiltroHorarioVenta");
			} 
			em.persist(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FiltroHorarioVentaDAO#updateFiltroHorarioVenta(com.edicsem.pe.sie.entity.FiltroHorarioVentaSie)
	 */
	public void updateFiltroHorarioVenta(FiltroHorarioVentaSie f) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar FiltroHorarioVenta");
			} 
			em.merge(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FiltroHorarioVentaDAO#findFiltroHorarioVenta(int)
	 */
	public FiltroHorarioVentaSie findFiltroHorarioVenta(int id) {
		FiltroHorarioVentaSie f= new FiltroHorarioVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar FiltroHorarioVenta");
			} 
		f=	em.find(FiltroHorarioVentaSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.FiltroHorarioVentaDAO#listarFiltroHorarioVenta()
	 */
	public List listarFiltroHorarioVenta() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from FiltroHorarioVentaSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista FiltroHorarioVenta --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
