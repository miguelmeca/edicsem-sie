package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetSancionCargoSie;
import com.edicsem.pe.sie.entity.DetSancionEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetSancionCargoDAO;
import com.edicsem.pe.sie.model.dao.DetSancionEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.DetSancionEmpleadoService;
import com.edicsem.pe.sie.service.facade.EmpleadoSieService;
import com.edicsem.pe.sie.service.facade.SancionService;
@Stateless
public class DetSancionEmpleadoServiceImpl implements DetSancionEmpleadoService{
	
	public static Log log = LogFactory.getLog(DetSancionEmpleadoServiceImpl.class);
	
	@EJB
	private DetSancionEmpleadoDAO objDetSancionEmpleadoDao;
	@EJB
	private DetSancionCargoDAO objDetSancionCargoDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionEmpleadoService#insertDetSancionEmpleado(com.edicsem.pe.sie.entity.DetSancionEmpleadoSie, int, int)
	 */
	public void insertDetSancionEmpleado(DetSancionEmpleadoSie d, int idSancion, int idEmpleado,int idcargo) {
		log.info(" -->   ***    "+idSancion+"   "+idEmpleado);
		d.setTbEmpleado(objEmpleadoDao.buscarEmpleado(idEmpleado));
		log.info(" "+idSancion +" "+idcargo);
		DetSancionCargoSie det=objDetSancionCargoDao.findDetSancionCargo(idSancion,idcargo);
		log.info(" "+det.getIddetsancioncargo());
		d.setTbDetsancioncargo(det);
		d.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(1));
		objDetSancionEmpleadoDao.insertDetSancionEmpleado(d);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionEmpleadoService#updateDetSancionEmpleado(com.edicsem.pe.sie.entity.DetSancionEmpleadoSie)
	 */
	public void updateDetSancionEmpleado(DetSancionEmpleadoSie d) {
		objDetSancionEmpleadoDao.updateDetSancionEmpleado(d);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionEmpleadoService#findContrato(int)
	 */
	public DetSancionEmpleadoSie findContrato(int id) {
		return objDetSancionEmpleadoDao.findContrato(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetSancionEmpleadoService#listarDetSancionEmpleado(int, java.lang.String, java.lang.String)
	 */
	public List listarDetSancionEmpleado(int idEmpleado,String fechaInicio,String fechaFin) {
		return objDetSancionEmpleadoDao.listarDetSancionEmpleado(idEmpleado, fechaInicio, fechaFin);
	}

}
