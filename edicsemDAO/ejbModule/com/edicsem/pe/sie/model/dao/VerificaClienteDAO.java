package com.edicsem.pe.sie.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.VerificaClienteSie;
 

@Local
public interface VerificaClienteDAO {
	
	public abstract void insertVerificaCliente(VerificaClienteSie v);
	public abstract void updateVerificaCliente(VerificaClienteSie v);
	public abstract VerificaClienteSie findVerificaCliente (int id);
	public abstract List  listarVerificaCliente();
}
