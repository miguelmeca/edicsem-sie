package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ContratoSie;
 

@Local
public interface ContratoDAO {
	
	public abstract void insertContrato( ContratoSie contrato);
	public abstract void updateContrato(ContratoSie contrato);
	public abstract ContratoSie findContrato (int id);
	public abstract List  listarContratos();
	public abstract List listarContratosDeudores();
	public abstract List listarClientePorParametro(String numDocumento,String codigoContrato,String nombreCliente, String apePat,String apeMat );
}
