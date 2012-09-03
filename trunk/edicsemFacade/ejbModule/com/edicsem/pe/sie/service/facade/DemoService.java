package com.edicsem.pe.sie.service.facade;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.Usuario;

@Local
public interface DemoService {
	
	public abstract void insertDemo (Usuario usuario);
	public abstract void updateDemo (Usuario usuario);
	public abstract void deleteDemo (String id);
	public abstract Usuario findDemo (String id);
	public abstract List listarUsuarios ();
	
}
