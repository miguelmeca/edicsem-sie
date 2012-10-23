package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
 
import com.edicsem.pe.sie.model.dao.ParametroDAO;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;
import com.edicsem.pe.sie.service.facade.ParametroService;
import com.edicsem.pe.sie.service.facade.ProveedorService;
@Stateless
public class ParametroServiceImpl implements ParametroService{
	//llamo a mi EJB y redirecciono todo al DAO
	public static Log log = LogFactory.getLog(ParametroServiceImpl.class);
	@EJB
	private ParametroDAO objParametroDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarParametro(ParametroSistemaSie parametro) {
		//si tengo que insertar a mas de 1 tabla todo lo hago aqui, llamando a todas las entidades que
		//mi interfaz DAO tiene y si algo falla, el EJB hace un rollback de todo  lo que se hizo, 
		//para eso sirve el Service
		
		objParametroDao.insertarParametro(parametro);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	
	public void actualizarParametro(ParametroSistemaSie parametro) {
		objParametroDao.actualizarParametro(parametro);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#deleteDemo(java.lang.String)
	 */
	public void eliminarParametro(int id) {
		objParametroDao.eliminarParametro(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#findDemo(java.lang.String)
	 */
	public ParametroSistemaSie findParametro(int id) {
		return objParametroDao.findParametro(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.DemoService#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarParametros() {
		log.info("En el servicio ");

		return objParametroDao.listarParametros();
	}


	
		
}
