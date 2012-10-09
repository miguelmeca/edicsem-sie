package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local;

import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie; 
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

@Local
public interface EmpleadoSieService {
	public abstract void insertarEmpleado (EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, int codigoTipoDocumento, int codigoCargoEmpleado, String mensaje, 
			String fijo, int estado, String direccion, String idDistrito, int estado2, int tipo, String nombre, int CargoEmpleado, 
			int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado, int estadoe);
	public abstract void actualizarEmpleado (EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, TelefonoPersonaSie objTelefono, int codigoTipoDocumento, int codigoCargoEmpleado, String mensaje, 
			String fijo, int estado, String direccion, String idDistrito, int estado2, int tipo, String nombre, int CargoEmpleado, 
			int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int codigoEmpleado, int estadoe);
	public abstract void eliminarEmpleado (int id);
	public abstract EmpleadoSie buscarEmpleado (int id);
	public abstract List listarEmpleados ();
}
