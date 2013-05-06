package com.edicsem.pe.sie.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CobranzaSie;
 

@Local
public interface CobranzaDAO {
	
	public abstract void insertCobranza(CobranzaSie Cobranza);
	public abstract void updateCobranza(CobranzaSie Cobranza);
	public abstract CobranzaSie findCobranza (int id);
	public abstract List<CobranzaSie>  listarCobranzas();
	public abstract List  listarCobranzasXidcontrato(int idcontrato);
	public abstract List  calcularEfectividad(int idEmpleado,String fechaInicio, String fechaFin);
	public abstract CobranzaSie buscarCobranzaXcodigo(String codigo, Date fechaVencimiento,double montototalpagado);
	public abstract List buscarCobranzaXcodigoContrato(Integer idcontrato);
	public abstract List listarCobranzasporParametro(int idTipocliente,int idCalificacion, int cuotasxpagar, 
			int diasRetrazo,Date fechaEntregaDesde, Date fechaEntregaHasta);
}
