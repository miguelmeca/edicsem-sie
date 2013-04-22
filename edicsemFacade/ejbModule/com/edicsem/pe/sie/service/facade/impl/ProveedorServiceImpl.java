package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ProveedorSie;
 
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;
import com.edicsem.pe.sie.service.facade.ProveedorService;
@Stateless
public class ProveedorServiceImpl implements ProveedorService{
	
	public static Log log = LogFactory.getLog(ProveedorServiceImpl.class);
	@EJB
	private ProveedorDAO objProveedorDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarProveedor(ProveedorSie proveedor) {
		proveedor.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(9));
		objProveedorDao.insertarProveedor(proveedor);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarProveedor(ProveedorSie proveedor) {
		objProveedorDao.actualizarProveedor(proveedor);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarProveedor(int id) {
		objProveedorDao.eliminarProveedor(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#findDemo(java.lang.String)
	 */
	public ProveedorSie findProveedor(int id) {
		return objProveedorDao.findProveedor(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarProveedores() {
		return objProveedorDao.listarProveedores();
	}
	
}
