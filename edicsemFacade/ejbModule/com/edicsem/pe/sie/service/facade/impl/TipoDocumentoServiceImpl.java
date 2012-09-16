package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.model.dao.TipoDocumentoDAO;
import com.edicsem.pe.sie.service.facade.TipoDocumentoService;
@Stateless
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
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
		// TODO Auto-generated method stub
		return objTipoDocumentoDao.buscarTipoDocumento(id);
	}
 
	public List listarTipoDocumentos() {
		
		return objTipoDocumentoDao.listarTipoDocumentos();
	}
	
		
}
