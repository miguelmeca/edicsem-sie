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

	@Override
	public void insertarTipoDocumento(TipoDocumentoIdentidadSie tipodocumento) {
		objTipoDocumentoDao.insertarTipoDocumento(tipodocumento);
	}

	@Override
	public void actualizarTipoDocumento(TipoDocumentoIdentidadSie tipodocumento) {
		objTipoDocumentoDao.actualizarTipoDocumento(tipodocumento);
	}

	@Override
	public void eliminarTipoDocumento(int id) {
		objTipoDocumentoDao.eliminarTipoDocumento(id);
	}

	@Override
	public TipoDocumentoIdentidadSie buscarTipoDocumento(int id) {
		// TODO Auto-generated method stub
		return objTipoDocumentoDao.buscarTipoDocumento(id);
	}
 
	public List listarTipoDocumentos() {
		
		return objTipoDocumentoDao.listarTipoDocumentos();
	}
	
		
}
