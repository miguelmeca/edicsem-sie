package com.edicsem.pe.sie.model.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.VerificaProductoSie;
import com.edicsem.pe.sie.model.dao.VerificaProductoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class VerificaProductoDAOImpl implements VerificaProductoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(VerificaProductoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaProductoDAO#insertVerificaProducto(com.edicsem.pe.sie.entity.VerificaProductoSie)
	 */
	public void insertVerificaProducto(VerificaProductoSie v) {
		try {
			em.persist(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaProductoDAO#updateVerificaProducto(com.edicsem.pe.sie.entity.VerificaProductoSie)
	 */
	public void updateVerificaProducto(VerificaProductoSie v) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar verifica producto");
			}
			em.merge(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaProductoDAO#findVerificaProducto(int)
	 */
	public VerificaProductoSie findVerificaProducto(int id) {
		VerificaProductoSie v= new VerificaProductoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Verifica Cliente "+ id);
			} 
		v =	em.find(VerificaProductoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaProductoDAO#listarVerificaProducto()
	 */
	public List listarVerificaProducto() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from VerificaProductoSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista Verifica Producto --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.VerificaClienteDAO#listarVerificacionXFechaXalmacen(java.util.Date, java.util.Date, int)
	 */
	public List listarVerificacionXFechaXalmacen(Date fechaDesde, Date fechaHasta, int idalmacen) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from VerificaProductoSie p where DATE(p.tbVerificaCliente.fechacreacion) " +
					" between DATE('"+fechaDesde+"') and DATE('"+fechaHasta+"')  and " +
					" p.tbVerificaCliente.tbPuntoVenta.idpuntoventa = "+idalmacen );
			lista =  q.getResultList();
		   log.info("tamaño lista Verifica x fecha x almacen --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
