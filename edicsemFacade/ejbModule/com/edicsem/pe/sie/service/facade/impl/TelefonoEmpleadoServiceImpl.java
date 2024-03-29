package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;
import com.edicsem.pe.sie.service.facade.EstadogeneralService;
import com.edicsem.pe.sie.service.facade.TelefonoEmpleadoService;
@Stateless
public class TelefonoEmpleadoServiceImpl implements TelefonoEmpleadoService{
	//llamo a mi EJB y redirecciono todo al DAO
	
	public static Log log = LogFactory.getLog(TelefonoEmpleadoServiceImpl.class);
	
	
	@EJB
	private TelefonoEmpleadoDAO objTelefonoEmpleadoDao;
	
	@EJB
	private EstadogeneralService objEstadoGeneralService;
	
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarTelefonoEmpleado(TelefonoPersonaSie telefonoempleado) {
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
	log.info("en el servicio TELEFONO -->" + telefonoempleado );
		
		objTelefonoEmpleadoDao.insertarTelefonoEmpleado(telefonoempleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarTelefonoEmpleado(TelefonoPersonaSie telefonoempleado) {
		objTelefonoEmpleadoDao.actualizarTelefonoEmpleado(telefonoempleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarTelefonoEmpleado(int id) {
		objTelefonoEmpleadoDao.eliminarTelefonoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#findDemo(java.lang.String)
	 */
	public TelefonoPersonaSie buscarTelefonoEmpleado(int id) {
		return objTelefonoEmpleadoDao.buscarTelefonoEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarTelefonoEmpleados() {
		return objTelefonoEmpleadoDao.listarTelefonoEmpleados();
	}
	
	
	public List listarTelefonoCliente(int id) {
		return objTelefonoEmpleadoDao.listarTelefonoCliente(id);
	}
	/*Listar todos los telefonos por codigo de cliente*/

	
//	public void actualizarTelefonoCliente(TelefonoPersonaSie telefonocliente) {
//		
//		objTelefonoEmpleadoDao.actualizarTelefonoCliente(telefonocliente);
//		
//	}
	
	
	
    public TelefonoPersonaSie buscarTelefonoXIdempleado(int id) { 
		
		return objTelefonoEmpleadoDao.buscarTelefonoXIdempleado(id); 
	}
    
    public List listarTelefonoEmpleadosXidcliente(int idcliente) {
		return objTelefonoEmpleadoDao.listarTelefonoEmpleadosXidcliente(idcliente);
	}
    
    public TelefonoPersonaSie buscarTelefonoXIdcliente(int id) { 
		
		return objTelefonoEmpleadoDao.buscarTelefonoXIdcliente(id); 
	}
    
    public List listarTelefonoEmpleadosXidempleado(int idempleado) {
		return objTelefonoEmpleadoDao.listarTelefonoEmpleadosXidempleado(idempleado);
	}	
    
    public TelefonoPersonaSie buscarTelefonoCliente(int id) {
		return objTelefonoEmpleadoDao.buscarTelefonoCliente(id);
	}


	public void actualizarTelefonoCliente(List<TelefonoPersonaSie> telefonoDeshabilitado) {
		for (int i = 0; i < telefonoDeshabilitado.size() ; i++) {			
			TelefonoPersonaSie s = telefonoDeshabilitado.get(i);
			log.info("DENTRO DEL SERVICIO");
			
	s.setTbEstadoGeneral(objEstadoGeneralService.findEstadogeneral(18));
 objTelefonoEmpleadoDao.actualizarTelefonoCliente(s);
					}		
				}


	public void insertarTelefonoCliente(TelefonoPersonaSie telefonopersona) {
log.info("en el servicio TELEFONO CLIENTE-->" + telefonopersona );
		
		objTelefonoEmpleadoDao.insertarTelefonoCliente(telefonopersona);
	}

	
	
	
}
