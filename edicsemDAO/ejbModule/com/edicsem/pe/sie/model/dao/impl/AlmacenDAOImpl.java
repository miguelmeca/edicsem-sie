package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;

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
	public PuntoVentaSie findAlmacen(int id) {
		PuntoVentaSie almacen= new PuntoVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
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
	public List listarAlmacenes() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from PuntoVentaSie p where p.tbEstadoGeneral.idestadogeneral = "+ 13);
			lista =  q.getResultList(); 
		   log.info("tamaño lista Almacen --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#listarPuntoVenta(int)
	 */
	public List listarPuntoVenta(int tipo) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from PuntoVentaSie p where p.tbTipoPuntoVenta.idtipopuntoventa =  '"+tipo +"' and p.tbEstadoGeneral.idestadogeneral = "+ 13 );
			lista =  q.getResultList(); 						
		   log.info("tamaño lista PuntoVenta DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	public PuntoVentaSie buscarIdpuntoVenta(String puntoVenta) {
	

		PuntoVentaSie p = new PuntoVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar puntoVenta en la IMPLEMENTACION DAO" + puntoVenta );
			}

Query q = em.createQuery("select p from  PuntoVentaSie p where p.tbEstadoGeneral.idestadogeneral = 13 AND p.descripcion like  '"+ puntoVenta + "'");
			if (q.getResultList().size() == 1) {

				p = (PuntoVentaSie) q.getResultList().get(0);
				// casteado tiene columnas pero no se ah mencionado cuales son p=(ContratoSie) q.getResultList().get(0);
				
			}
			log.info("Aquita PUNTO-VENTA-->"+ p.getDescripcion()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;	
		
	}
	
	
	
}
