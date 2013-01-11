package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;

@Local
public interface ClienteService {
	
	public abstract void insertCliente(ClienteSie Cliente);
	public abstract void updateCliente(ClienteSie Cliente, List<TelefonoPersonaSie> TelefonoPersonaList,int tipo,DomicilioPersonaSie objDomicilio,String idUbigeo );
	public abstract ClienteSie findCliente (int id);
	public abstract List  listarClientes();
}
