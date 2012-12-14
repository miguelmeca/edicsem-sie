package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ImporteSie;
 

@Local
public interface ImporteDAO {
	
	public abstract void insertImporte(ImporteSie a);
	public abstract void updateImporte(ImporteSie a);
	public abstract ImporteSie findImporte(int id);
	public abstract List  listarImporte(int tipo);
}
