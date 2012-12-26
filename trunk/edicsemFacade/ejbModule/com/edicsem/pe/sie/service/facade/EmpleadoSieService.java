package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

@Local
public interface EmpleadoSieService {
	public abstract void insertarEmpleado (EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, int codigoTipoDocumento, int codigoCargoEmpleado,
			int idUbigeo, int tipo, int CargoEmpleado, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList);
	public abstract void actualizarEmpleado (EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, int codigoTipoDocumento, int codigoCargoEmpleado, 
			String fijo, int estado, int idUbigeo, int tipo, int CargoEmpleado, 
			int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado,  List<ContratoEmpleadoSie> contratoEmpleadoList);
	public abstract void eliminarEmpleado (int id);
	public abstract EmpleadoSie buscarEmpleado (int id);
	public abstract List listarEmpleados ();
	public abstract List listarEmpleadosXCargo(int idCargo);
	public abstract List listarEmpleadoxEmpresas(int idCargo);
	
	
}
