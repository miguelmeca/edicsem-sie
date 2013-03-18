package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.GrupoVentaSie;
@Local
public interface GrupoVentaService {
	
	public abstract void insertGrupo(GrupoVentaSie g, int idTipoEvento);
	public abstract void updateGrupo(GrupoVentaSie g, int idTipoEvento);
	public abstract GrupoVentaSie findGrupoVenta(int id);
	public abstract List  listarGrupoVenta(int tipoVenta);
	public abstract List  listarGrupoVenta();
}
