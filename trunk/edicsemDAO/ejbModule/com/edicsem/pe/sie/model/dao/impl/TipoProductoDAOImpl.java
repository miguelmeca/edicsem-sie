package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
  
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class TipoProductoDAOImpl implements TipoProductoDAO{


	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	
	private static Log log = LogFactory.getLog(TipoProductoDAOImpl.class);
	
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.TipoProductoDAO#listarTipoProductos()
	 */
	public List listarTipoProductos() {
		 
		List lista = null;
		try {
			Query q = em.createQuery("select p from TipoProductoSie p where p.tbEstadoGeneral.idestadogeneral = "+ 72 );
			lista =  q.getResultList();  
			log.info("tamaño lista Tipo de Productos --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void insertTipoProducto(TipoProductoSie tipoproducto) {
		 
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Tipo Producto "+ tipoproducto.getCodtipoproducto()+" nombre "+ tipoproducto.getNombretipoproducto());
			} 
			em.persist(tipoproducto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TipoProductoSie findTipoProducto(int idtipoproducto) {
		TipoProductoSie tipoproducto= null;
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar TipoProducto");
			} 
			tipoproducto=	em.find(TipoProductoSie.class, idtipoproducto);
			log.info(" TipoProducto " +idtipoproducto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoproducto;
	}
	
	public void updateTipoProducto(TipoProductoSie producto) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el tipo de producto");
			}
			em.merge(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
