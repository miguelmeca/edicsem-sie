package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.TipoKardexProductoSie;
import com.edicsem.pe.sie.model.dao.TipoKardexProductoDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class TipoKardexDAOImpl implements TipoKardexProductoDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoKardexDAOImpl.class);
 
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoKardexProductoDAO#listaTipoKardex()
	 */
	 
	public List listaTipoKardex() {
	
		List lista = new ArrayList();
		try {
			Query q = em.createQuery("select p from TipoKardexProductoSie p ");
			lista = q.getResultList();
			 
			log.info("tamaño lista Tipo kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoKardexProductoDAO#findTipoKardex(int)
	 */ 
	public TipoKardexProductoSie findTipoKardex(int id) {
		TipoKardexProductoSie tipo = new TipoKardexProductoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Tipo Kardex "+ id);
			}
			tipo = em.find(TipoKardexProductoSie.class, id);
			log.info("obtenido "+   tipo.getDescripcion() +" " + tipo.getIdtipokardexproducto() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipo;
	}
 
}
