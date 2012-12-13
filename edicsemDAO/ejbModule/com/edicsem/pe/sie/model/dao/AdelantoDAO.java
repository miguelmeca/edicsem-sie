package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.AdelantoSie;
 

@Local
public interface AdelantoDAO {
	
	public abstract void insertAdelanto(AdelantoSie a);
	public abstract void updateAdelanto(AdelantoSie a);
	public abstract AdelantoSie findAdelanto(int id);
	public abstract List  listarAdelantos();
}
