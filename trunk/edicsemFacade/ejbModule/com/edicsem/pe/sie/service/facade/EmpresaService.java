package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.EmpresaSie;

@Local
public interface EmpresaService {

	public abstract void insertEmpresa (EmpresaSie empresa);
	public abstract void updateEmpresa(EmpresaSie empresa);
	public abstract EmpresaSie findEmpresa (int id);
	public abstract List<EmpresaSie>  listarEmpresas();
	public abstract EmpresaSie buscarIdEmpresa (String razonSocial);

}
