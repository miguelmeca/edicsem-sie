package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class ProveedorDAOImpl implements ProveedorDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ProveedorDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ProveedorDAO#listarProveedores()
	 */
	
	public List listarProveedores() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ProveedorSie p");
			lista = q.getResultList();
			System.out.println("tamaño lista Proveedores --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ProveedorDAO#findProducto(int)
	 */
	
	public ProveedorSie findProducto(int id) {
		ProveedorSie proveedor = new ProveedorSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Producto");
			}
			proveedor = em.find(ProveedorSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proveedor;
	}
}
