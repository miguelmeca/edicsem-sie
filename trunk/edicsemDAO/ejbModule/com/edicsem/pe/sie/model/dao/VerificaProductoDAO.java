package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.VerificaProductoSie;
 

@Local
public interface VerificaProductoDAO {
	
	public abstract void insertVerificaProducto(VerificaProductoSie v);
	public abstract void updateVerificaProducto(VerificaProductoSie v);
	public abstract VerificaProductoSie findVerificaProducto(int id);
	public abstract List  listarVerificaProducto();
	
}
