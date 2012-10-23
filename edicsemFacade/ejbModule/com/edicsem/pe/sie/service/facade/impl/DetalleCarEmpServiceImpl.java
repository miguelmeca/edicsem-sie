package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetCargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
 
import com.edicsem.pe.sie.model.dao.DetalleCarEmpDAO;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;
import com.edicsem.pe.sie.service.facade.DetalleCarEmpService;
@Stateless
public class DetalleCarEmpServiceImpl implements DetalleCarEmpService{
	//llamo a mi EJB y redirecciono todo al DAO
	public static Log log = LogFactory.getLog(DetalleCarEmpServiceImpl.class);
	@EJB
	private DetalleCarEmpDAO objDetCargoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarDetalleCarEmp(DetCargoEmpleadoSie detallecaremp) {
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		
		objDetCargoDao.insertarDetalleCarEmp(detallecaremp);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	
	public void actualizarDetalleCarEmp(DetCargoEmpleadoSie detallecaremp) {
		objDetCargoDao.actualizarDetalleCarEmp(detallecaremp);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarDetalleCarEmp(int id) {
		objDetCargoDao.eliminarDetalleCarEmp(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#findDemo(java.lang.String)
	 */
	public DetCargoEmpleadoSie findDetalleCarEmp(int id) {
		return objDetCargoDao.findDetalleCarEmp(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarDetalleCarEmp() {
		log.info("En el servicio ");

		return objDetCargoDao.listarDetalleCarEmp();
	}


	
		
}
