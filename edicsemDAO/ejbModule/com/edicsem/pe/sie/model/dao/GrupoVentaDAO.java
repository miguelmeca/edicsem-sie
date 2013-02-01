package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.GrupoVentaSie;
 

@Local
public interface GrupoVentaDAO {
	
	public abstract void insertGrupo(GrupoVentaSie g);
	public abstract void updateGrupo(GrupoVentaSie g);
	public abstract GrupoVentaSie findGrupoVenta(int id);
	public abstract List  listarGrupoVenta(int tipoVenta);
}
