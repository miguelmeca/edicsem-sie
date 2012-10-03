package com.edicsem.pe.sie.model.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetalleComprobanteSie;
import com.edicsem.pe.sie.model.dao.DetalleComprobanteDAO;

@Stateless
public class DetalleComprobanteDAOImpl implements DetalleComprobanteDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetalleComprobanteDAOImpl.class);
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetalleComprobanteDAO#insertComprobante(com.edicsem.pe.sie.entity.DetalleComprobanteSie)
	 */
	
	public void insertComprobante(DetalleComprobanteSie comp) {
		try {
		     em.persist(comp);
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}	
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
}
