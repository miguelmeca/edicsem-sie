package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.TipoCasaSie;

@Local
public interface TipoCasaDAO {
	
	public abstract void insertTipoCasa(TipoCasaSie tipocasa);
	public abstract void updateTipoCasa(TipoCasaSie tipocasa);
	public abstract void eliminarTipoCasa(int id);
	public abstract TipoCasaSie findTipoCasa(int id);
	public abstract List  listarTipoCasa(); 
	
}
