package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.LugarVentaSie;
import com.edicsem.pe.sie.model.dao.LugarVentaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class LugarVentaDAOImpl implements LugarVentaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(LugarVentaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.LugarVentaDAO#insertLugarVenta(com.edicsem.pe.sie.entity.LugarVentaSie)
	 */
	public void insertLugarVenta(LugarVentaSie l) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar lugar venta");
			} 
			em.persist(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.LugarVentaDAO#updateLugarVenta(com.edicsem.pe.sie.entity.LugarVentaSie)
	 */
	public void updateLugarVenta(LugarVentaSie l) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar lugar venta");
			} 
			em.merge(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.LugarVentaDAO#findLugarVenta(int)
	 */
	public LugarVentaSie findLugarVenta(int id) {
		LugarVentaSie l= new LugarVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
			}
			l =	em.find(LugarVentaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.LugarVentaDAO#listarLugarVenta()
	 */
	public List listarLugarVenta() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from LugarVentaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista lugar venta --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.LugarVentaDAO#findLugarVenta(java.lang.String)
	 */
	public LugarVentaSie findLugarVenta(String lugardelaentrega) {
		List  lista = null;
		LugarVentaSie l= null;
		try {
			Query q = em.createQuery("select p from LugarVentaSie p ");
			lista =  q.getResultList();
			if(lista.size()>0){
				l= (LugarVentaSie) lista.get(0);
			}
		   log.info("lista lugar venta --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
}
