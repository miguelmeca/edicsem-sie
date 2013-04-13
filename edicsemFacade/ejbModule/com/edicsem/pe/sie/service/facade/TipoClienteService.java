package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoClienteSie;

@Local
public interface TipoClienteService {
	
	public abstract List listarTipoCliente();
	public abstract void insertTipoCliente(TipoClienteSie objTipoClienteSie);
	public abstract void updateTipoCliente(TipoClienteSie objTipoClienteSie);
	
}
