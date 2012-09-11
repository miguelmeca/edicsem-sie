package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.ProductoSie;
 

@Local
public interface KardexDAO {
	 
	public abstract List  ConsultaProductos(int idproducto, int idalmacen, String fechaDesde, String fechaHasta);
	
	public abstract void insertMovimiento (KardexSie kardex);
	
}
