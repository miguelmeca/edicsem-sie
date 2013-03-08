package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoEventoVentaSie;
import com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoEventoVentaDAOImpl implements TipoEventoVentaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoEventoVentaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO#insertTipoEventoVenta(com.edicsem.pe.sie.entity.TipoEventoVentaSie)
	 */
	@Override
	public void insertTipoEventoVenta(TipoEventoVentaSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar tipo evento venta");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO#updateTipoEventoVenta(com.edicsem.pe.sie.entity.TipoEventoVentaSie)
	 */
	public void updateTipoEventoVenta(TipoEventoVentaSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar tipo evento");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO#findTipoEventoVenta(int)
	 */
	public TipoEventoVentaSie findTipoEventoVenta(int id) {
		TipoEventoVentaSie t= new TipoEventoVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar tipo evento "+ id);
			}
			t =	em.find(TipoEventoVentaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoEventoVentaDAO#listarTipoEventoVenta()
	 */
	public List listarTipoEventoVenta() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoEventoVentaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista tipo evento venta --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
