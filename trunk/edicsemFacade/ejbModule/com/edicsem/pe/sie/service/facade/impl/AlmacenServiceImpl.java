package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;
import com.edicsem.pe.sie.service.facade.AlmacenService;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.service.facade.TipoProductoService;

@Stateless
public class AlmacenServiceImpl implements AlmacenService {

	@EJB
	private  AlmacenDAO objAlmacenDao;
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AlmacenService#insertAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */
	
	public void insertAlmacen(PuntoVentaSie almacen) {
		objAlmacenDao.insertAlmacen(almacen);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AlmacenService#updateAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */
	
	public void updateAlmacen(PuntoVentaSie almacen) {
		objAlmacenDao.updateAlmacen(almacen);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AlmacenService#findAlmacen(java.lang.String)
	 */
	
	public PuntoVentaSie findAlmacen(int id) {
		 
		return objAlmacenDao.findAlmacen(id); 
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.AlmacenService#listarAlmacenes()
	 */
	
	public List listarAlmacenes() {
		 
		return objAlmacenDao.listarAlmacenes();
	}
}
