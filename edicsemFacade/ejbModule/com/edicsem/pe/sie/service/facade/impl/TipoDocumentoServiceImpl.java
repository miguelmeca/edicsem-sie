package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
@Stateless
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
	
	private Log log = LogFactory.getLog(ClienteServiceImpl.class);
	//llamo a mi EJB y redirecciono todo al DAO
	@EJB
	private TipoDocumentoDAO objTipoDocumentoDao;
 
	
	public void insertarTipoDocumento(TipoDocumentoIdentidadSie tipodocumento) {
		objTipoDocumentoDao.insertarTipoDocumento(tipodocumento);
	}

	
	public void actualizarTipoDocumento(TipoDocumentoIdentidadSie tipodocumento) {
		objTipoDocumentoDao.actualizarTipoDocumento(tipodocumento);
	}

	
	public void eliminarTipoDocumento(int id) {
		objTipoDocumentoDao.eliminarTipoDocumento(id);
	}

	
	public TipoDocumentoIdentidadSie buscarTipoDocumento(int id) {
		log.info("Dentro del Imple Servicio-->"+ id);
		return objTipoDocumentoDao.buscarTipoDocumento(id);
	}
 
	public List listarTipoDocumentos() {
		
		return objTipoDocumentoDao.listarTipoDocumentos();
	}
	
		
}
