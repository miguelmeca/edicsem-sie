package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DetpagoSie;
 

@Local
public interface DetPagoDAO {
	
	public abstract void insertDetpago(DetpagoSie a);
	public abstract void updateDetpago(DetpagoSie a);
	public abstract DetpagoSie findDetpago(int id);
	public abstract List  listarDetpago();
}
