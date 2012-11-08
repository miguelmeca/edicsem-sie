package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
@Local
public interface FiltroHorarioVentaService {

	public abstract void insertFiltroHorarioVenta( FiltroHorarioVentaSie f,int idPuntoVenta,int idvendedor,int idtipoFiltro,List<String> diaList);
	public abstract void updateFiltroHorarioVenta(FiltroHorarioVentaSie f);
	public abstract FiltroHorarioVentaSie findFiltroHorarioVenta (int id);
	public abstract List  listarFiltroHorarioVenta();
	
}
