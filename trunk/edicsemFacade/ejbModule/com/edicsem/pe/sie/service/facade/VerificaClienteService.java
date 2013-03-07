package com.edicsem.pe.sie.service.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.VerificaClienteSie;
import com.edicsem.pe.sie.entity.VerificaProductoSie;
import com.edicsem.pe.sie.entity.VerificaTelefonoSie;
@Local
public interface VerificaClienteService {

	public abstract void insertVerificaCliente(VerificaClienteSie v,List<VerificaProductoSie> lstProducto, List<VerificaTelefonoSie> lstTelefono, int idEmpleado,int idtipodoc);
	public abstract void updateVerificaCliente(VerificaClienteSie v);
	public abstract VerificaClienteSie findVerificaCliente (int id);
	public abstract List listarVerificaCliente();
	public abstract List listarVerificacionXFechaXalmacen(Date fechaDesde, Date fechaHasta, int idalmacen);
}
