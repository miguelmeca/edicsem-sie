package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService;
@Stateless
public class DomicilioEmpleadoServiceImpl implements DomicilioEmpleadoService{
	
	private static Log log = LogFactory.getLog(DomicilioEmpleadoServiceImpl.class);
	
	@EJB
	private DomicilioEmpleadoDAO objDomicilioEmpleadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarDomicilioEmpleado(DomicilioPersonaSie domicilioempleado) {
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		
		objDomicilioEmpleadoDao.insertarDomicilioEmpleado(domicilioempleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarDomicilioEmpleado(DomicilioPersonaSie domicilioempleado) {
		objDomicilioEmpleadoDao.actualizarDomicilioEmpleado(domicilioempleado);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarDomicilioEmpleado(int id) {
		objDomicilioEmpleadoDao.eliminarDomicilioEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#findDemo(java.lang.String)
	 */
	public DomicilioPersonaSie buscarDomicilioEmpleado(int id) {
		log.info("en el servicio"+id);
		return objDomicilioEmpleadoDao.buscarDomicilioEmpleado(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarDomicilioEmpleados() {
		return objDomicilioEmpleadoDao.listarDomicilioEmpleados();
	}

    public DomicilioPersonaSie buscarDomicilioXIdempleado(int id) { 
		return objDomicilioEmpleadoDao.buscarDomicilioXIdempleado(id); 
	}
    
    /* (non-Javadoc)
     * @see com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService#listarDomicilioCliente(int)
     */
    public List listarDomicilioCliente(int id) {
		return objDomicilioEmpleadoDao.listarDomicilioCliente(id);
	}
    
    /* (non-Javadoc)
     * @see com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService#buscarDomicilioXIdcliente(int)
     */
    public DomicilioPersonaSie buscarDomicilioXIdcliente(int id) {
    	return objDomicilioEmpleadoDao.buscarDomicilioXIdcliente(id); 
	}
    
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DomicilioEmpleadoService#listarClientesXZonificacion(java.lang.String, java.util.List, java.util.List, java.util.List)
	 */
	public List listarClientesXZonificacion(String idUbigeo,List<String> planoList, List<String> letraList,List<String> sectorList) {
		return objDomicilioEmpleadoDao.listarClientesXZonificacion(idUbigeo, planoList, letraList, sectorList);
	}
    
}
