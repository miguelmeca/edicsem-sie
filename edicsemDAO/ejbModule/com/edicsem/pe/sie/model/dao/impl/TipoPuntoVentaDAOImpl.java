package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.TipoPuntoVentaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoPuntoVentaDAOImpl implements TipoPuntoVentaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoPuntoVentaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoPuntoVentaDAO#listarTipoPuntoVenta()
	 */
	public List listarTipoPuntoVenta() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoPuntoVentaSie p " );
			lista =  q.getResultList(); 						
		   log.info("tamaño lista TipoPuntoVentaSie DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
