package com.edicsem.pe.sie.model.dao;

import java.util.List;
import javax.ejb.Local;
import com.edicsem.pe.sie.entity.PermisoSie;

@Local
public interface PermisoDAO {
	
	public abstract void insertPermiso(PermisoSie p);
	public abstract void updatePermiso(PermisoSie p);
	public abstract PermisoSie findPermiso (int id);
	public abstract List  listarPermiso();
}
