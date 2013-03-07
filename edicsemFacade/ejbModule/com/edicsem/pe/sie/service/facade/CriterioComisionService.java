package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.CriterioComisionSie;
@Local
public interface CriterioComisionService {
	
	public abstract void insertCriterioComision(CriterioComisionSie c);
	public abstract void updateCriterioComision(CriterioComisionSie c);
	public abstract CriterioComisionSie findCriterioComision (int id);
	public abstract List  listarCriterioComision();
	
}
