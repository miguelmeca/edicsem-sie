package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HistorialParametroSistemaSie;
import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.HistorialParametroSistemaDAO;
import com.edicsem.pe.sie.model.dao.ParametroDAO;
import com.edicsem.pe.sie.service.facade.ParametroService;

@Stateless
public class ParametroServiceImpl implements ParametroService{
	
	public static Log log = LogFactory.getLog(ParametroServiceImpl.class);
	@EJB
	private ParametroDAO objParametroDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	@EJB
	private HistorialParametroSistemaDAO objHistorialDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroService#insertarParametro(com.edicsem.pe.sie.entity.ParametroSistemaSie)
	 */
	public void insertarParametro(ParametroSistemaSie parametro) {
		parametro.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(29));
		objParametroDao.insertarParametro(parametro);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroService#actualizarParametro(com.edicsem.pe.sie.entity.ParametroSistemaSie, java.lang.String)
	 */
	public void actualizarParametro(ParametroSistemaSie parametro, String valorAnterior) {
		//Actualizar el parámetro
		objParametroDao.actualizarParametro(parametro);
		//Insertar el historial del cambio realizado
		//transaccion: valorAnterior|valorNuevo|usuarioModificacion|fechaModificacion
		HistorialParametroSistemaSie h = new HistorialParametroSistemaSie();
		h.setTbparametroSistemas(parametro);
		log.info("-->>> "+parametro.getValor()+"|"+valorAnterior+"|"+ parametro.getFechamodifica()+"|"+parametro.getUsuariomodifica());
		h.setDescripcion(parametro.getValor()+"|"+valorAnterior+"|"+ parametro.getFechamodifica()+"|"+parametro.getUsuariomodifica());
		log.info("--> "+h.getDescripcion());
		objHistorialDao.insertHistorial(h);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroService#eliminarParametro(int)
	 */
	public void eliminarParametro(int id) {
		objParametroDao.eliminarParametro(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroService#findParametro(int)
	 */
	public ParametroSistemaSie findParametro(int id) {
		return objParametroDao.findParametro(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroService#listarParametros()
	 */
	public List listarParametros() {
		return objParametroDao.listarParametros();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ParametroService#buscarPorDescripcion(java.lang.String)
	 */
	public ParametroSistemaSie buscarPorDescripcion(String paramEfectividadVentas) {
		return objParametroDao.buscarPorDescripcion(paramEfectividadVentas);
	}
	
}
