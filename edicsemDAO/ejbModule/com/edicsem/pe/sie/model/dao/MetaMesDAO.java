package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.MetaMesSie;

@Local
public interface MetaMesDAO {
	
	public abstract void insertMetaMes (MetaMesSie metames);
	public abstract void updateMetaMes(MetaMesSie metames);
	public abstract MetaMesSie findMetaMes (int id);
	public abstract List<MetaMesSie>  listarMetaMeses(); 
	
}
