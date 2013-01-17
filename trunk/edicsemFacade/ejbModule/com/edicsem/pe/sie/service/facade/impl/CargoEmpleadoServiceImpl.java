package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
 
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
@Stateless
public class CargoEmpleadoServiceImpl implements CargoEmpleadoService{
	
	public static Log log = LogFactory.getLog(CargoEmpleadoServiceImpl.class);
	@EJB
	private CargoEmpleadoDAO objCargoEmpleadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		objCargoEmpleadoDao.insertarCargoEmpleado(cargoempleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CargoEmpleadoService#actualizarCargoEmpleado(com.edicsem.pe.sie.entity.CargoEmpleadoSie)
	 */
	public void actualizarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		objCargoEmpleadoDao.actualizarCargoEmpleado(cargoempleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CargoEmpleadoService#eliminarCargoEmpleado(int)
	 */
	public void eliminarCargoEmpleado(int id) {
		objCargoEmpleadoDao.eliminarCargoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CargoEmpleadoService#buscarCargoEmpleado(int)
	 */
	public CargoEmpleadoSie buscarCargoEmpleado(int id) {
		log.info("buscar CargoEmpleado en el servicio" +id);
		return objCargoEmpleadoDao.buscarCargoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CargoEmpleadoService#listarCargoEmpleado()
	 */
	public List listarCargoEmpleado() {
		return objCargoEmpleadoDao.listarCargoEmpleado();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CargoEmpleadoService#listarCargosXEmpleado(int)
	 */
	public List listarCargosXEmpleado(int idEmpleado){
		return objCargoEmpleadoDao.listarCargosXEmpleado(idEmpleado);
	}
}
