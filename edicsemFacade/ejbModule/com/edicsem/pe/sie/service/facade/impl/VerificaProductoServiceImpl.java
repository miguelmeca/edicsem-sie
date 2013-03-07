package com.edicsem.pe.sie.service.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.VerificaProductoSie;
import com.edicsem.pe.sie.model.dao.VerificaProductoDAO;
import com.edicsem.pe.sie.service.facade.VerificaProductoService;

@Stateless
public class VerificaProductoServiceImpl implements VerificaProductoService {
	@EJB
	private  VerificaProductoDAO objverificaproductoDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaProductoService#insertVerificaProducto(com.edicsem.pe.sie.entity.VerificaProductoSie)
	 */
	public void insertVerificaProducto(VerificaProductoSie v) {
		objverificaproductoDao.insertVerificaProducto(v);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaProductoService#updateVerificaProducto(com.edicsem.pe.sie.entity.VerificaProductoSie)
	 */
	public void updateVerificaProducto(VerificaProductoSie v) {
		objverificaproductoDao.updateVerificaProducto(v);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaProductoService#findVerificaProducto(int)
	 */
	public VerificaProductoSie findVerificaProducto(int id) {
		return objverificaproductoDao.findVerificaProducto(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaProductoService#listarVerificaProducto()
	 */
	public List listarVerificaProducto() {
		return objverificaproductoDao.listarVerificaProducto();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.VerificaProductoService#listarVerificacionXFechaXalmacen(java.util.Date, java.util.Date, int)
	 */
	public List listarVerificacionXFechaXalmacen(Date fechaDesde, Date fechaHasta, int idalmacen) {
		return objverificaproductoDao.listarVerificacionXFechaXalmacen(fechaDesde, fechaHasta, idalmacen);
	}

}
