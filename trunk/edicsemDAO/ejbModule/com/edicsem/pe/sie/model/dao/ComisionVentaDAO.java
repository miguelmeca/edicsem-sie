package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ComisionVentaSie;
 

@Local
public interface ComisionVentaDAO {
	
	public abstract void insertComisionVenta(ComisionVentaSie c);
	public abstract void updateComisionVenta(ComisionVentaSie c);
	public abstract ComisionVentaSie findComisionVenta (int id);
	public abstract List  listarComisionVenta();
}
