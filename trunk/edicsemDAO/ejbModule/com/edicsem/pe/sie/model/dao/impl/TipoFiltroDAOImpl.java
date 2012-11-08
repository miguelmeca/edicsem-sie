package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoFiltroSie;
import com.edicsem.pe.sie.model.dao.TipoFiltroDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoFiltroDAOImpl implements TipoFiltroDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoFiltroDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoFiltroDAO#findTipoFiltro(int)
	 */
	public TipoFiltroSie findTipoFiltro(int id) {
		TipoFiltroSie t= new TipoFiltroSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Tipo Filtro");
			} 
		t=	em.find(TipoFiltroSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoFiltroDAO#listarTipoFiltro()
	 */
	public List listarTipoFiltro() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoFiltroSie p ");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista TipoFiltroSie --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
