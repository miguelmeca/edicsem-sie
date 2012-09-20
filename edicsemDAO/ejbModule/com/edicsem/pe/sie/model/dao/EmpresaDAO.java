package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.EmpresaDTO;
import com.edicsem.pe.sie.entity.EmpresaSie;

@Local
public interface EmpresaDAO {
	
	public abstract void insertEmpresa (EmpresaSie empresa);
	public abstract void updateEmpresa(EmpresaSie empresa);
	public abstract EmpresaSie findProducto (int id);
	public abstract List<EmpresaSie>  listarEmpresas(); 
	
}
