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
	//llamo a mi EJB y redirecciono todo al DAO
	public static Log log = LogFactory.getLog(CargoEmpleadoServiceImpl.class);
	@EJB
	private CargoEmpleadoDAO objCargoEmpleadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		
		objCargoEmpleadoDao.insertarCargoEmpleado(cargoempleado);
	}

	public void actualizarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		objCargoEmpleadoDao.actualizarCargoEmpleado(cargoempleado);
	}

	public void eliminarCargoEmpleado(int id) {
		objCargoEmpleadoDao.eliminarCargoEmpleado(id);
	}

	public CargoEmpleadoSie buscarCargoEmpleado(int id) {
		log.info("buscar CargoEmpleado service" +id);
		return objCargoEmpleadoDao.buscarCargoEmpleado(id);
	}

	public List listarCargoEmpleado() {
		log.info("En el servicio ");

		return objCargoEmpleadoDao.listarCargoEmpleado();
	}

}
