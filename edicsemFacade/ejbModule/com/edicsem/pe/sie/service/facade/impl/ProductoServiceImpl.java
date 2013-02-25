package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;
import com.edicsem.pe.sie.service.facade.ProductoService;

@Stateless
public class ProductoServiceImpl implements ProductoService {

	@EJB
	private  ProductoDAO objProductoDao;
	@EJB
	private TipoProductoDAO objTipoProductoDao;
	@EJB
	private EstadoGeneralDAO objestadoDao;
	
	public static Log log = LogFactory.getLog(EmpleadoSieServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#insertProducto(com.edicsem.pe.sie.entity.ProductoSie, int)
	 */
	public void insertProducto(ProductoSie producto,int TipoProducto) {
		
		producto.setTbTipoProducto(objTipoProductoDao.findTipoProducto(TipoProducto));
		producto.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(5));
		objProductoDao.insertProducto(producto);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#updateProducto(com.edicsem.pe.sie.entity.ProductoSie, int)
	 */
	public void updateProducto(ProductoSie producto,int TipoProducto) {
		producto.setTbTipoProducto(objTipoProductoDao.findTipoProducto(TipoProducto));
		producto.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(6));
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
		log.info("dentro del servicio ");
		return objProductoDao.listarProductos();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductosXTipo(int)
	 */
	public List listarProductosXTipo(int tipoProducto) { 
		return objProductoDao.listarProductosXTipo(tipoProducto); 
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductoxEmpresas(int)
	 */
	public List listarProductoxEmpresas(int parametroObtenido) {
		log.info("dentro del servicio listar Producto x Empresas ");
		return objProductoDao.listarProductoxEmpresas(parametroObtenido);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#verificarTipoProducto(int)
	 */
	public boolean verificarTipoProducto(int tipoProducto) {
		log.info("en el servicio" + tipoProducto);
		return objProductoDao.verificarTipoProducto(tipoProducto);
	}

	
	public ProductoSie buscarXcodigoProducto(String codProducto) {
		log.info("en el SERVICIO BUSCANDO ID-PRODUCTO"+ codProducto);
		return objProductoDao.buscarXcodigoProducto(codProducto);
	}
	
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarCodigosProductos()
	 */
	public List listarCodigosProductos() {
		return objProductoDao.listarCodigosProductos();
	}


}
