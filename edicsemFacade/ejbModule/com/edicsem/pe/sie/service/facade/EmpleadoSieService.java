package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

@Local
public interface EmpleadoSieService {
	public abstract void insertarEmpleado (EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, int codigoTipoDocumento, int codigoCargoEmpleado,
    String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList);
	public abstract void actualizarEmpleado (EmpleadoSie objEmpleado, DomicilioPersonaSie objDomicilio, int codigoTipoDocumento, int codigoCargoEmpleado, 
	String idUbigeo, int tipo, int idCargo, int DomicilioPersona, int TelefonoPersona, int TipoDocumento, int idEmpresa, int idTipoPago, int codigoEmpleado, List<ContratoEmpleadoSie> contratoEmpleadoList, List<TelefonoPersonaSie> TelefonoPersonaList, List<TelefonoPersonaSie> TelefonoDeshabilitado, List<ContratoEmpleadoSie> ContratoDeshabilitado, List<DetEmpresaEmpleadoSie> detEmpresaEmpList);
	public abstract void eliminarEmpleado (int id);
	public abstract EmpleadoSie buscarEmpleado (int id);
	public abstract List listarEmpleados ();
	public abstract List listarEmpleadosXCargo(int idCargo);
	// MANTENIMIENTO EMPRESA VALIDACION DE EMPLEADO Y PRODUCTO
	public abstract List listarEmpleadoxEmpresas(int parametroObtenido);
	public abstract List listarEmpleadoxCargo(int parametroObtenido);

}
