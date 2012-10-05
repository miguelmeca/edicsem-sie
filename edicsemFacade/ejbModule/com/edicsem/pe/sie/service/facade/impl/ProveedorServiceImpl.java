package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ProveedorSie;
 
import com.edicsem.pe.sie.model.dao.ProveedorDAO;
import com.edicsem.pe.sie.service.facade.ProveedorService;
@Stateless
public class ProveedorServiceImpl implements ProveedorService{
	//llamo a mi EJB y redirecciono todo al DAO
	public static Log log = LogFactory.getLog(ProveedorServiceImpl.class);
	@EJB
	private ProveedorDAO objProveedorDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarProveedor(ProveedorSie proveedor) {
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		
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
		log.info("En el servicio ");

		return objProveedorDao.listarProveedores();
	}


	
		
}
