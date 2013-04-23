package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoClienteSie;
import com.edicsem.pe.sie.model.dao.TipoClienteDAO;

@Stateless
public class TipoClienteDAOImpl implements TipoClienteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoClienteDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoClienteDAO#insertTipoCliente(com.edicsem.pe.sie.entity.TipoClienteSie)
	 */
	public void insertTipoCliente(TipoClienteSie objTipoClienteSie) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar tipo cliente");
			} 
			em.persist(objTipoClienteSie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoClienteDAO#updateTipoCliente(com.edicsem.pe.sie.entity.TipoClienteSie)
	 */
	public void updateTipoCliente(TipoClienteSie objTipoClienteSie) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar tipo cliente");
			}
			em.merge(objTipoClienteSie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoClienteDAO#findTipoCliente(int)
	 */
	public TipoClienteSie findTipoCliente(int id) {
		TipoClienteSie t= new TipoClienteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar tipo de cliente "+ id);
			}
			t =	em.find(TipoClienteSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoClienteDAO#listarTipoCliente()
	 */
	public List listarTipoCliente() {
		List lista= null;
		try {
			Query q= em.createQuery("select p from TipoClienteSie p");
			lista= q.getResultList();
			log.info(" tamaño " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
