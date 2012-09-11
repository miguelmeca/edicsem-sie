package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoProductoSie;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;
import com.edicsem.pe.sie.service.facade.TipoProductoService;

@Stateless
public class TipoProductoServiceImpl implements TipoProductoService {

	@EJB
	private TipoProductoDAO objTipoProductoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#listarTipo()
	 */
	public List listarTipo() {
		return objTipoProductoDao.listarTipoProductos();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#insertTipoProducto(com.edicsem.pe.sie.entity.TipoProductoSie)
	 */
	@Override
	public void insertTipoProducto(TipoProductoSie producto) {
		  objTipoProductoDao.insertTipoProducto(producto);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.TipoProductoService#findTipoProducto(int)
	 */
	@Override
	public TipoProductoSie findTipoProducto(int idtipoproducto) {
		return  objTipoProductoDao.findTipoProducto(idtipoproducto);
	}
}
