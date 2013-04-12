package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.PermisoDAO;
import com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService;

@Stateless
public class DetPermisoEmpleadoServiceImpl implements DetPermisoEmpleadoService {

	@EJB
	private  DetPermisoEmpleadoDAO objPermisoEmpleadoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private  PermisoDAO objPermisoDao;
	@EJB
	private  EmpleadoSieDAO objEmpleadoDao;
	
	private static Log log = LogFactory.getLog(DetPermisoEmpleadoServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#insertDetPermisoEmpleado(java.util.List, int)
	 */
	public void insertDetPermisoEmpleado(List<String> p, int idEmpleado) {
		 
		List<String> listaPermisosActual= objPermisoDao.listarPermisosXEmpleado(idEmpleado);
		
				for (int i = 0; i < p.size(); i++) {
					log.info(" Permiso "+ p.get(i));
					if(listaPermisosActual.contains(p.get(i))){
						log.info("*** 1");
					}else{
						//Permisos Nuevos
						log.info("Permisos Nuevos  "+p.get(i));
					DetPermisoEmpleadoSie d = new DetPermisoEmpleadoSie();
					d.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(62));
					d.setTbPermisos(objPermisoDao.findPermiso(p.get(i)));
					d.setTbEmpleado(objEmpleadoDao.buscarEmpleado(idEmpleado));
					objPermisoEmpleadoDao.insertDetPermisoEmpleado(d);
					}
				}
				for (int j = 0; j < listaPermisosActual.size(); j++) {
					log.info(" Permiso "+ listaPermisosActual.get(j));
					if(p.contains(listaPermisosActual.get(j))){
						log.info("*** 2");
					}else{
						//Desactivar los permisos 
					log.info("Desactivar los permisos   "+listaPermisosActual.get(j));
					log.info("  "+idEmpleado+"  "+listaPermisosActual.get(j));
					DetPermisoEmpleadoSie det = objPermisoEmpleadoDao.findDetPermisoEmpleado(idEmpleado,listaPermisosActual.get(j));
					det.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(63));
					objPermisoEmpleadoDao.updateDetPermisoEmpleado(det);
					}
				}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#updateDetPermisoEmpleado(com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie)
	 */
	public void updateDetPermisoEmpleado(DetPermisoEmpleadoSie p) {
		objPermisoEmpleadoDao.updateDetPermisoEmpleado(p);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#findDetPermisoEmpleado(int)
	 */
	public DetPermisoEmpleadoSie findDetPermisoEmpleado(int id) {
		return objPermisoEmpleadoDao.findDetPermisoEmpleado(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DetPermisoEmpleadoService#listarPermisoXUsuario(java.lang.String)
	 */
	public List<MenuDTO> listarPermisoXUsuario(String usuario) {
		return objPermisoEmpleadoDao.listarPermisoXUsuario(usuario);
	}
	
}
