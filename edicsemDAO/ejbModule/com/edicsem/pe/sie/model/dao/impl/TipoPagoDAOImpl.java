package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoPagoSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.TipoPagoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoPagoDAOImpl implements TipoPagoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoPagoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoPagoDAO#findTipoPago(int)
	 */
	public TipoPagoSie findTipoPago(int id) {
		TipoPagoSie t= new TipoPagoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Tipo Pago"+ id);
			} 
		t=	em.find(TipoPagoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoPagoDAO#listarTipoPago()
	 */
	public List listarTipoPago() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoPagoSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño   --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
