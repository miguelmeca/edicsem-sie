package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.DetProductoContratoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

@Local
public interface ContratoService {
	
	public abstract void insertContrato(ClienteSie  cliente, List<TelefonoPersonaSie> telefonoList, DomicilioPersonaSie domicilio,  ContratoSie contrato,List<DetProductoContratoSie> detprodcont, List<CobranzaSie> cobranza);
	public abstract void updateContrato(ContratoSie contrato);
	public abstract ContratoSie findContrato (int id);
	public abstract List  listarContratos();
}
