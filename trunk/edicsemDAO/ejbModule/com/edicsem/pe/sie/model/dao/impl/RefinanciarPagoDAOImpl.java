package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.RefinanciarPagoSie;
import com.edicsem.pe.sie.model.dao.RefinanciarPagoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class RefinanciarPagoDAOImpl implements RefinanciarPagoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(RefinanciarPagoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.RefinanciarPagoDAO#insertRefinanPago(com.edicsem.pe.sie.entity.RefinanciarPagoSie)
	 */
	public void insertRefinanPago(RefinanciarPagoSie r) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar refinanciar pago");
			} 
			em.persist(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.RefinanciarPagoDAO#updateRefinanPago(com.edicsem.pe.sie.entity.RefinanciarPagoSie)
	 */
	public void updateRefinanPago(RefinanciarPagoSie r) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar refinanciar pago");
			} 
			em.merge(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.RefinanciarPagoDAO#findRefinanPago(int)
	 */
	public RefinanciarPagoSie findRefinanPago(int id) {
		RefinanciarPagoSie r= new RefinanciarPagoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar refinanciar pago "+ id);
			}
			r=	em.find(RefinanciarPagoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.RefinanciarPagoDAO#listarRefinanciarPago()
	 */
	public List listarRefinanciarPago() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from RefinanciarPagoSie p where p.tbEstadoGeneral.idestadogeneral = "+ 13);
			lista =  q.getResultList(); 
		   log.info("tamaño lista refinanciar pago --> " + lista.size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
