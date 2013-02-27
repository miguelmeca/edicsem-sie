package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ControlKardexSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.ControlMercaderiaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ControlMercaderiaDAOImpl implements ControlMercaderiaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ControlMercaderiaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ControlMercaderiaDAO#insertControlKardex(com.edicsem.pe.sie.entity.ControlKardexSie)
	 */
	public void insertControlKardex(ControlKardexSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Control Kardex");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ControlMercaderiaDAO#updateControlKardex(com.edicsem.pe.sie.entity.ControlKardexSie)
	 */
	public void updateControlKardex(ControlKardexSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar control");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ControlMercaderiaDAO#findControlKardex(int)
	 */
	public ControlKardexSie findControlKardex(int id) {
		ControlKardexSie c= new ControlKardexSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
			}
			c=	em.find(ControlKardexSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ControlMercaderiaDAO#listarControlKardex()
	 */
	public List listarControlKardex() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ControlKardexSie p " );
			lista =  q.getResultList(); 						
		   log.info("tamaño lista control kardex DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
