package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local;

import com.edicsem.pe.sie.entity.KardexSie;
 
@Local
public interface KardexService {

	public abstract List  ConsultaProductos(int idproducto, int idalmacen, String fechaDesde, String fechaHasta);
	public abstract void insertMovimiento (KardexSie obj,int idproducto,int idtipokardexproducto,int idAlmacenSalida, int idAlmacenEntrada );
	public abstract List  ConsultaKardexDiario();
	public abstract List<KardexSie> ConsultaStockActual(int idProducto);
	public abstract void updateKardex(KardexSie kardex);
	public abstract KardexSie findKardex (int id);
	
}
