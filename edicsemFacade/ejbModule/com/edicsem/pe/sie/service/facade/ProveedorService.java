package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ProveedorSie;
@Local
public interface ProveedorService {

	public abstract List  listarProveedores();
	public abstract ProveedorSie findProducto (int id);
}
