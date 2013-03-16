package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.GrupoVentaSie;
import com.edicsem.pe.sie.model.dao.GrupoVentaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class GrupoVentaDAOImpl implements GrupoVentaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(GrupoVentaDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.GrupoVentaDAO#insertGrupo(com.edicsem.pe.sie.entity.GrupoVentaSie)
	 */
	public void insertGrupo(GrupoVentaSie g) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Grupo Venta");
			} 
			em.persist(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.GrupoVentaDAO#updateGrupo(com.edicsem.pe.sie.entity.GrupoVentaSie)
	 */
	public void updateGrupo(GrupoVentaSie g) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar grupo");
			} 
			em.merge(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.GrupoVentaDAO#findGrupoVenta(int)
	 */
	public GrupoVentaSie findGrupoVenta(int id) {
		GrupoVentaSie g= new GrupoVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar grupo"+ id);
			} 
		g=	em.find(GrupoVentaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.GrupoVentaDAO#listarGrupoVenta()
	 */
	public List listarGrupoVenta(int tipoVenta) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from GrupoVentaSie p where p.tbTipoEventoVenta.idtipoevento = "+ tipoVenta);
			lista =  q.getResultList(); 
		   log.info("tamaño lista grupo x tipo --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.GrupoVentaDAO#listarGrupoVenta()
	 */
	public List listarGrupoVenta() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from GrupoVentaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista grupo --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
