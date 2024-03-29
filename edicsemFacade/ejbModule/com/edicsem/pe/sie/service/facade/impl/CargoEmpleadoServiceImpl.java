package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
 
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.CargoEmpleadoService;
@Stateless
public class CargoEmpleadoServiceImpl implements CargoEmpleadoService{
	
	public static Log log = LogFactory.getLog(CargoEmpleadoServiceImpl.class);
	@EJB
	private CargoEmpleadoDAO objCargoEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		cargoempleado.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(1));
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
		return objCargoEmpleadoDao.findCargoEmpleado(id);
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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CargoEmpleadoService#buscarCargoEmpleado(java.lang.String)
	 */
	public CargoEmpleadoSie buscarCargoEmpleado(String cargo) {
		return objCargoEmpleadoDao.buscarCargoEmpleado(cargo);
	}
	
}
