package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local; 
 
@Local
public interface KardexService {

	public abstract List  ConsultaProductos(int idproducto, int idalmacen, String fechaDesde, String fechaHasta);
	
}
