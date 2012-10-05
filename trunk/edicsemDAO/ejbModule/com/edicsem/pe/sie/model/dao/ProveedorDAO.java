package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ProveedorSie;

@Local
public interface ProveedorDAO {
	
	public abstract void insertarProveedor (ProveedorSie proveedor);
	public abstract void actualizarProveedor (ProveedorSie proveedor);
	public abstract void eliminarProveedor (int id);
	public abstract ProveedorSie findProveedor (int id);
	public abstract List listarProveedores ();
}