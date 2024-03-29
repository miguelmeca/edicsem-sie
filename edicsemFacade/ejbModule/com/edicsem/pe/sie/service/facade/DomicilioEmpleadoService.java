package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DomicilioPersonaSie;


@Local
public interface DomicilioEmpleadoService {
	
	public abstract void insertarDomicilioEmpleado (DomicilioPersonaSie domiciliopersona);
	public abstract void actualizarDomicilioEmpleado (DomicilioPersonaSie domiciliopersona);
	public abstract void eliminarDomicilioEmpleado (int id);
	public abstract DomicilioPersonaSie buscarDomicilioEmpleado (int id);
	public abstract List listarDomicilioEmpleados ();
	public abstract DomicilioPersonaSie buscarDomicilioXIdempleado(int id);
	public abstract List listarDomicilioCliente (int id);
	public abstract DomicilioPersonaSie buscarDomicilioXIdcliente(int id);
	public abstract List listarClientesXZonificacion(String idUbigeo,List<String> planoList, List<String> letraList,List<String> sectorList);
	
}
