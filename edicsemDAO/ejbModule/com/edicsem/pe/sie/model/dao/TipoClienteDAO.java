package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoClienteSie;

@Local
public interface TipoClienteDAO {
	
	public abstract void insertTipoCliente(TipoClienteSie objTipoClienteSie);
	public abstract void updateTipoCliente(TipoClienteSie objTipoClienteSie);
	public abstract TipoClienteSie findTipoCliente(int id);
	public abstract List listarTipoCliente();
}
