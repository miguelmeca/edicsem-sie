package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;

@Stateless
public class TipoDocumentoDAOImpl implements TipoDocumentoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoDocumentoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarTipoDocumento(TipoDocumentoIdentidadSie tipodocumento) {
	 
		try { 
			em.persist(tipodocumento);
			 
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarTipoDocumento(TipoDocumentoIdentidadSie tipodocumento) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
			em.merge(tipodocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarTipoDocumento(int id) {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public TipoDocumentoIdentidadSie buscarTipoDocumento(int id) {
		// TODO Auto-generated method stub
				TipoDocumentoIdentidadSie tipodocumento= new TipoDocumentoIdentidadSie();
				try {
				if (log.isInfoEnabled()) {
				log.info("buscar TipoDocumento");
				} 
				tipodocumento=em.find(TipoDocumentoIdentidadSie.class, id);
				log.info(" TipoDocumento " +tipodocumento);
				} catch (Exception e) {
				e.printStackTrace();
				}
				return tipodocumento;
	}
  
	public List listarTipoDocumentos() {
		log.info("***************** delistar tipodocumento");
		List lista= null;
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar tipodocumento");
			}
			Query q= em.createQuery("select p from TipoDocumentoIdentidadSie p");
			lista= q.getResultList();
			log.info(" tamaño " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
