package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ProveedorSie;
 

@Local
public interface ProveedorDAO {
	
	public abstract List  listarProveedores();
	public abstract ProveedorSie findProveedor (int id);
}
