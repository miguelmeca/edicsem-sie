package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class AlmacenDAOImpl implements AlmacenDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(AlmacenDAOImpl.class);
	 

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#insertAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */
	@Override
	public void insertAlmacen(PuntoVentaSie almacen) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Almacen");
			} 
			em.persist(almacen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#updateAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */
	@Override
	public void updateAlmacen(PuntoVentaSie almacen) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Almacen");
			} 
			em.merge(almacen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#findAlmacen(java.lang.String)
	 */
	@Override
	public PuntoVentaSie findAlmacen(String id) {
		PuntoVentaSie almacen= new PuntoVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen");
			} 
		almacen=	em.find(PuntoVentaSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return almacen;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#listarAlmacenes()
	 */
	@Override
	public List listarAlmacenes() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from PuntoVentaSie p");
			lista =  q.getResultList(); 
		   System.out.println("tama�o lista Almacen --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
