package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

@Local
public interface ClienteService {
	
	public abstract void insertCliente(ClienteSie Cliente);
	public abstract void updateCliente(ClienteSie Cliente, DomicilioPersonaSie objDomicilio,String idUbigeo1,int idUbigeo, int tipo,int Tipocasanuevo, int TelefonoPersona, List<TelefonoPersonaSie> TelefonoPersonaList, List<TelefonoPersonaSie> TelefonoDeshabilitado, List<DomicilioPersonaSie> DomicilioPersonaList, List<DomicilioPersonaSie> DomicilioPersonaDeshabilitado);
	public abstract ClienteSie findCliente (int id);
	public abstract List  listarClientes();
	public abstract List  listarClientesXTipo(int tipoCliente);
}