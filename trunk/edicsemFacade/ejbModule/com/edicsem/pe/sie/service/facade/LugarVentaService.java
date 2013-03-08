package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.LugarVentaSie;
@Local
public interface LugarVentaService {
	
	public abstract void insertLugarVenta(LugarVentaSie l);
	public abstract void updateLugarVenta(LugarVentaSie l);
	public abstract LugarVentaSie findLugarVenta (int id);
	public abstract List  listarLugarVenta();
	
}
