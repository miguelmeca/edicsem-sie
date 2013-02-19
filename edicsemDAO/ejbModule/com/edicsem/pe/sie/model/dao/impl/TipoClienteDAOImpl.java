package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.model.dao.TipoClienteDAO;

@Stateless
public class TipoClienteDAOImpl implements TipoClienteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoClienteDAOImpl.class);
	
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
