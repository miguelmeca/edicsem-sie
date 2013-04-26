package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;
import com.edicsem.pe.sie.service.facade.TipoProductoService;

@Stateless
public class TipoProductoServiceImpl implements TipoProductoService {
	private Log log = LogFactory.getLog(TipoProductoServiceImpl.class);

	@EJB
	private TipoProductoDAO objTipoProductoDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#insertTipoProducto(com.edicsem.pe.sie.entity.TipoProductoSie)
	 */
	public void insertTipoProducto(TipoProductoSie producto) {
		producto.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(72));
		objTipoProductoDao.insertTipoProducto(producto);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#findTipoProducto(int)
	 */
	public TipoProductoSie findTipoProducto(int idtipoproducto) {
		return  objTipoProductoDao.findTipoProducto(idtipoproducto);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#updateTipoProducto(com.edicsem.pe.sie.entity.TipoProductoSie)
	 */
	public void updateTipoProducto(TipoProductoSie producto) {
		objTipoProductoDao.updateTipoProducto(producto);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#eliminarTipoProducto(int)
	 */
	public void eliminarTipoProducto(int parametroObtenido) {
		objTipoProductoDao.eliminarTipoProducto(parametroObtenido);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#listarTipo()
	 */
	public List listarTipo() {
		return objTipoProductoDao.listarTipoProductos();
	}
}
