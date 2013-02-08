package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.SeguimientoContratoSie;
import com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class SeguimientoContratoDAOImpl implements SeguimientoContratoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(SeguimientoContratoDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO#insertSeguimientoContrato(com.edicsem.pe.sie.entity.SeguimientoContratoSie)
	 */
	public void insertSeguimientoContrato(SeguimientoContratoSie s) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Seguimiento Contrato");
			} 
			em.persist(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO#updateSeguimientoContrato(com.edicsem.pe.sie.entity.SeguimientoContratoSie)
	 */
	public void updateSeguimientoContrato(SeguimientoContratoSie s) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Seguimiento Contrato");
			} 
			em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO#findSeguimientoContrato(int)
	 */
	public SeguimientoContratoSie findSeguimientoContrato(int id) {
		SeguimientoContratoSie s= new SeguimientoContratoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
			}
			s =	em.find(SeguimientoContratoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO#listarSeguimientoContrato(int)
	 */
	public List listarSeguimientoContrato(int codcontrato) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from SeguimientoContratoSie p where p.tbContrato.idcontrato= "+codcontrato);
			lista =  q.getResultList(); 						
		   log.info("tamaño lista PuntoVenta DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
