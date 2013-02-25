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
				log.info("insertar Producto "+ producto.getIdproducto());
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
				log.info("buscar Producto"+id);
			}
			producto = em.find(ProductoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return producto;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ProductoDAO#listarProductos()
	 */
	public List listarProductos() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ProductoSie p");
			lista = q.getResultList();
			log.info("tama�o lista Productos en el DAOIMPL--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ProductoDAO#listarProductosXTipo(int)
	 */
	public List listarProductosXTipo(int tipoProducto) {
		log.info(" idtipo "+ tipoProducto);
		List lista = null;
		try {
			Query q = em
					.createQuery("select p from ProductoSie p where p.tbTipoProducto.idtipoproducto = "+ tipoProducto);
			lista = q.getResultList();
			log.info("tama�o lista Productos x Tipo  --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ProductoDAO#listarProductoxEmpresas(int)
	 */
	public List listarProductoxEmpresas(int parametroObtenido) {
		List lista = null;
		log.info("por entrar al QUERY PRODUCTO  "+ parametroObtenido);
		try {
			Query q = em.createQuery("SELECT e FROM ProductoSie e inner join e.tbKardexs f  " +
					"inner join f.tbEmpresa g  where g.idempresa = " + parametroObtenido);
			lista = q.getResultList();
			log.info("despues del QUERY tama�o lista Producto X  parametroObtenido--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	public boolean verificarTipoProducto(int tipoProducto) {
		boolean bandera = true;
		List lista = null;
		try {
			Query q = em.createQuery("select p from ProductoSie p where p.tbTipoProducto.idtipoproducto = "+ tipoProducto);
			lista = q.getResultList();											
			log.info("tama�o lista de Tipo Producto --> " + lista.size());
			if(lista.size()>0){ //hay uno o mas empleados retornados.
				bandera=false;
			}else{//no hay empleados, entonces puede proseguir
				bandera=true;
			}
			
		} catch (Exception e) {
			bandera=false;
			e.printStackTrace();
		}
		return bandera;
	}


	public ProductoSie buscarXcodigoProducto(String codProducto) {
	
		ProductoSie p = new ProductoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Codigo de Producto" +"  "+ codProducto );
			}

Query q = em.createQuery("select p from ProductoSie p where p.tbEstadoGeneral.idestadogeneral = 5 AND p.codproducto like  '"+ codProducto + "'");
			if (q.getResultList().size() == 1) {

				p = (ProductoSie) q.getResultList().get(0);
				// casteado tiene columnas pero no se ah mencionado cuales son p=(ProductoSie) q.getResultList().get(0);
				
			}
			log.info("Aquita PRODUCTO-->"+ p.getCodproducto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	
	
	
	
	public List<String> listarCodigosProductos() {
		List lista = null;
		try {
			Query q = em.createQuery("SELECT e.codproducto FROM ProductoSie ");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
