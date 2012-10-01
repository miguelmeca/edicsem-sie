package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.KardexDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TipoKardexProductoDAO;
import com.edicsem.pe.sie.service.facade.KardexService;

/**
 * @author karen
 *
 */
@Stateless
public class KardexServiceImpl implements KardexService {

	@EJB
	private  KardexDAO objKardexDao;
	@EJB
	private  TipoKardexProductoDAO objTipoKardexDao;
	@EJB
	private  ProductoDAO objProductoDao;
	@EJB
	private  AlmacenDAO objAlmacenDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#ConsultaProductos(int, int, java.lang.String, java.lang.String)
	 */
	public List ConsultaProductos(int idproducto, int idalmacen,
			String fechaDesde, String fechaHasta) { 
		
		return objKardexDao.ConsultaProductos(idproducto, idalmacen, fechaDesde, fechaHasta); 
	} 
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#insertMovimiento(int, int, java.lang.String, int, int, int, int)
	 */
	public void insertMovimiento(int cantsalida, int cantentrada,String detalle,
			int idproducto, int idtipokardexproducto, int idAlmacenSalida, int idAlmacenEntrada) {
		
		KardexSie objKardex = new KardexSie(); 
		objKardex.setTbProducto(objProductoDao.findProducto(idproducto));
		objKardex.setTbPuntoVenta(objAlmacenDao.findAlmacen(idAlmacenSalida));
		objKardex.setTbTipoKardexProducto(objTipoKardexDao.findTipoKardex(idtipokardexproducto));
		objKardex.setCantentrada(cantentrada);
		objKardex.setCantsalida(cantsalida);
		objKardex.setDetallekardex(detalle);
		objKardexDao.insertMovimiento(idproducto,objKardex);
		
		/**Si hubiese otro id de almacen 
		 * */
		
		if(idAlmacenEntrada!=0){
			KardexSie objKardex2 = new KardexSie();
			objKardex2.setTbProducto(objProductoDao.findProducto(idproducto));
			objKardex2.setDetallekardex(detalle);
			objKardex2.setTbPuntoVenta(objAlmacenDao.findAlmacen(idAlmacenEntrada));
			objKardex2.setCantentrada(cantsalida);
			objKardex2.setCantsalida(cantentrada);
			if(idtipokardexproducto==1){
				idtipokardexproducto=2;
			}else if(idtipokardexproducto==2){
				idtipokardexproducto=1;
			}
			objKardex2.setTbTipoKardexProducto(objTipoKardexDao.findTipoKardex(idtipokardexproducto));
			objKardexDao.insertMovimiento(idproducto,objKardex2);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#ConsultaKardexDiario()
	 */
	public List ConsultaKardexDiario() {
		return objKardexDao.ConsultaKardexDiario();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#ConsultaStockActual(int)
	 */
	public List<KardexSie> ConsultaStockActual(int idProducto) {
		return objKardexDao.ConsultaStockActual(idProducto);
	}
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#updateKardex(com.edicsem.pe.sie.entity.KardexSie)
	 */
	public void updateKardex(KardexSie kardex) {
		objKardexDao.updateKardex(kardex);
	}
	
}
