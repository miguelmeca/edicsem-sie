package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetProductoContratoSie;
@Local
public interface DetProductoContratoService {
	
	public abstract void insertDetProductoContrato(DetProductoContratoSie det);
	public abstract void updateDetProductoContrato(DetProductoContratoSie det);
	public abstract DetProductoContratoSie findDetProductoContrato (int id);
	public abstract List  listarDetProductoContrato();
	
}
