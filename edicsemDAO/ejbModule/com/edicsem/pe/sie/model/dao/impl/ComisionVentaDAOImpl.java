package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComisionVentaSie;
import com.edicsem.pe.sie.model.dao.ComisionVentaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ComisionVentaDAOImpl implements ComisionVentaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ComisionVentaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComisionVentaDAO#insertComisionVenta(com.edicsem.pe.sie.entity.ComisionVentaSie)
	 */
	public void insertComisionVenta(ComisionVentaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Almacen");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComisionVentaDAO#updateComisionVenta(com.edicsem.pe.sie.entity.ComisionVentaSie)
	 */
	public void updateComisionVenta(ComisionVentaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar comision venta ");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComisionVentaDAO#findComisionVenta(int)
	 */
	public ComisionVentaSie findComisionVenta(int id) {
		ComisionVentaSie c= new ComisionVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
			} 
		c=	em.find(ComisionVentaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComisionVentaDAO#listarComisionVenta()
	 */
	public List listarComisionVenta() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ComisionVentaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista Comision Venta --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
