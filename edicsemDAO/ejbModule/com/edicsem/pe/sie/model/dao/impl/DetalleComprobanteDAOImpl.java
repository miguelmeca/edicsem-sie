package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetalleComprobanteDAO#listarDetComprobantes(int)
	 */
	public List listarDetComprobantes(int codcomp) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetalleComprobanteSie p where p.tbComprobante.idcomprobante = "+codcomp );
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista Detalle Comprobante --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
