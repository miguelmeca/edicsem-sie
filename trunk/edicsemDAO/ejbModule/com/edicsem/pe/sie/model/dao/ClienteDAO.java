package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.EmpresaSie;
 

@Local
public interface ClienteDAO {
	
	public abstract void insertCliente(ClienteSie Cliente);
	public abstract void updateCliente(ClienteSie Cliente);
	public abstract ClienteSie findCliente (int id);
	public abstract List  listarClientes();
	public abstract List  listarClientesXTipo(int tipoCliente);
	public abstract ClienteSie buscarIdCliente(String dni);
}
