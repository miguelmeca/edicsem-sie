package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.EstadoGeneralSie;

@Local
public interface EstadoGeneralDAO {
	
	public abstract void insertEstadogeneral (EstadoGeneralSie estadogeneral);
	public abstract void updateEstadogeneral(EstadoGeneralSie estadogeneral);
	public abstract void eliminarEstadogeneral(int id);
	public abstract EstadoGeneralSie findEstadoGeneral (int id);
	public abstract List  listarEstados(); 
	
}
