package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ImporteSie;
import com.edicsem.pe.sie.model.dao.ImporteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ImporteDAOImpl implements ImporteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ImporteDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ImporteDAO#insertImporte(com.edicsem.pe.sie.entity.ImporteSie)
	 */
	public void insertImporte(ImporteSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Importe");
			} 
			em.persist(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ImporteDAO#updateImporte(com.edicsem.pe.sie.entity.ImporteSie)
	 */
	public void updateImporte(ImporteSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Importe");
			} 
			em.merge(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ImporteDAO#findImporte(int)
	 */
	public ImporteSie findImporte(int id) {
		ImporteSie a= new ImporteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Importe");
			} 
		a=	em.find(ImporteSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ImporteDAO#listarImporte(int)
	 */
	public List listarImporte(int tipo) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ImporteSie p where p.tbTipoImporte.idtipoimporte = "+tipo);
			lista =  q.getResultList(); 
			log.info("tamaño lista Importe --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
