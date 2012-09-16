package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.model.dao.ProductoDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class ProductoDAOImpl implements ProductoDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ProductoDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.ProductoDAO#insertProducto(com.edicsem.pe
	 * .sie.entity.TbProductoSie)
	 */
	public void insertProducto(ProductoSie producto) {

		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Producto");
			}
			em.persist(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.ProductoDAO#updateProducto(com.edicsem.pe
	 * .sie.entity.TbProductoSie)
	 */

	public void updateProducto(ProductoSie producto) {
		em.getTransaction().begin();
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Producto");
			}
			em.merge(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.ProductoDAO#findProducto(java.lang.String)
	 */

	public ProductoSie findProducto(int id) {

		ProductoSie producto = new ProductoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Producto");
			}
			producto = em.find(ProductoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.ProductoDAO#listarProductos()
	 */

	public List listarProductos() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ProductoSie p");
			lista = q.getResultList();
			System.out.println("tamaño lista Productos --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List listarProductosXTipo(int tipoProducto) {
		log.info(" idtipo "+ tipoProducto);
		List lista = null;
		try {
			Query q = em
					.createQuery("select p from ProductoSie p where p.tbTipoProducto.idtipoproducto = "+ tipoProducto);
			//q.setParameter("x1", tipoProducto);
			lista = q.getResultList();
			System.out.println("tamaño lista Productos x Tipo  --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
