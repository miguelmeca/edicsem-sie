package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.EmpleadoDTO;
import com.edicsem.pe.sie.beans.EntregasPeruDTO;
import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

@Local
public interface ContratoService {
	
	public abstract void insertContrato(int idtipodoc,int Tipocasa,int idUbigeo,int  idempresa, ClienteSie  cliente, List<TelefonoPersonaSie> telefonoList, DomicilioPersonaSie domicilio,  ContratoSie contrato,List<DetProductoContratoSie> detprodcont, List<CobranzaSie> cobranza, List<EmpleadoDTO>  detidEmpleadosList,int tipoVenta,int idpuntoventa);
	public abstract void updateContrato(ContratoSie contrato);
	public abstract ContratoSie findContrato (int id);
	public abstract List  listarContratos();
	public abstract List listarClientePorParametro(String numDocumento,String codigoContrato,String nombreCliente, String apePat,String apeMat);
	public abstract String insertMigracion(List<SistemaIntegradoDTO> sis, String usuariocreacion);
	public abstract int obtenerCodigo();
	public abstract String updateEntregasPeru(List<EntregasPeruDTO> credito);
	public abstract ContratoSie buscarXcodigoContrato (String codContrato);
	public abstract List listarContratoEntregaLetraObsequio(String numDocumento,String codigoContrato,String nombreCliente, String apePat,String apeMat);
	public abstract Integer findcantContratoFacturado(Integer idEmpleado, Integer cargo, String fechaInicio, String fechaFin );
	public abstract Integer findcantContratoEntregado(Integer idEmpleado, Integer cargo, String fechaInicio, String fechaFin );
}
