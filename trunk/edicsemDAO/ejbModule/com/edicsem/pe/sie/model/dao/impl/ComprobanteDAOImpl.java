package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ComprobanteSie;
import com.edicsem.pe.sie.model.dao.ComprobanteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ComprobanteDAOImpl implements ComprobanteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ComprobanteDAOImpl.class);
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#insertComprobante(com.edicsem.pe.sie.entity.ComprobanteSie)
	 */
	
	public void insertComprobante(ComprobanteSie comp) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Comprobante");
			} 
			em.persist(comp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#findComprobante(int)
	 */
	
	public ComprobanteSie findComprobante(int id) {
		ComprobanteSie com = new ComprobanteSie();
		try {
			if (log.isInfoEnabled()) {
			log.info("buscar comprobante");
			} 
			com = em.find(ComprobanteSie.class, id);
			log.info(" comprobante " +id);
			} catch (Exception e) {
			e.printStackTrace();
			}
		return com;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#listarComprobantes()
	 */
	public List listarComprobantes() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ComprobanteSie p " );
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista Comprobante --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#updateComprobante(com.edicsem.pe.sie.entity.ComprobanteSie)
	 */
	public void updateComprobante(ComprobanteSie comp) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Comprobante");
			} 
			em.merge(comp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ComprobanteDAO#findComprobantePorNumero(java.lang.String)
	 */
	public ComprobanteSie findComprobantePorNumero(String num) {
		ComprobanteSie com = new ComprobanteSie();
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ComprobanteSie p where codcomprobante = "+num );
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista Comprobante --> " + lista.size()+"  ");
		   com = (ComprobanteSie) lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return com;
	}

}
