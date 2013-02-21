package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.MotivoSie;
@Local
public interface MotivoService {
	
	public abstract void insertMotivo(MotivoSie m);
	public abstract void updateMotivo(MotivoSie m);
	public abstract MotivoSie findMotivo (int id);
	public abstract List listarMotivo();
}
