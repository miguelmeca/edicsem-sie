package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.VerificaProductoSie;
@Local
public interface VerificaProductoService {

	public abstract void insertVerificaProducto(VerificaProductoSie v);
	public abstract void updateVerificaProducto(VerificaProductoSie v);
	public abstract VerificaProductoSie findVerificaProducto(int id);
	public abstract List  listarVerificaProducto();
	
}
