package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;

@Stateless
public class ProductoServiceImpl implements ProductoService {

	@EJB
	private  ProductoDAO objProductoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#insertProducto(com.edicsem.pe.sie.entity.ProductoSie)
	 */
	
	public void insertProducto(ProductoSie producto) {
		 
		objProductoDao.insertProducto(producto);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#updateProducto(com.edicsem.pe.sie.entity.ProductoSie)
	 */
	
	public void updateProducto(ProductoSie producto) {
		objProductoDao.updateProducto(producto);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#findProducto(java.lang.String)
	 */
	
	public ProductoSie findProducto(int id) {
	return 	objProductoDao.findProducto(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductos()
	 */
	
	public List listarProductos() {
		 
		return objProductoDao.listarProductos();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductosXTipo(int)
	 */
	
	public List listarProductosXTipo(int tipoProducto) { 
		
		return objProductoDao.listarProductosXTipo(tipoProducto); 
	}

 


}
