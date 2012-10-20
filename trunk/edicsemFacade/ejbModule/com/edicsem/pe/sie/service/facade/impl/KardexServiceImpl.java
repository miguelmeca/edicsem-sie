package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ComprobanteSie;
import com.edicsem.pe.sie.entity.DetalleComprobanteSie;
import com.edicsem.pe.sie.entity.DetalleComprobanteSiePK;
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.ComprobanteDAO;
import com.edicsem.pe.sie.model.dao.DetalleComprobanteDAO;
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
	@EJB
	private ComprobanteDAO objComprobanteDao;
	@EJB
	private DetalleComprobanteDAO objDetComprobanteDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#ConsultaProductos(int, int, java.lang.String, java.lang.String)
	 */
	public List ConsultaProductos(int idproducto, int idalmacen,
			String fechaDesde, String fechaHasta) { 
		
		return objKardexDao.ConsultaProductos(idproducto, idalmacen, fechaDesde, fechaHasta); 
	} 
 

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#insertMovimiento(com.edicsem.pe.sie.entity.KardexSie, com.edicsem.pe.sie.entity.ComprobanteSie, com.edicsem.pe.sie.entity.DetalleComprobanteSie, int, int, int, int)
	 */
	public void insertMovimiento( KardexSie obj, ComprobanteSie objcomprobante, DetalleComprobanteSie objDetComprobante,
			int idproducto, int idtipokardexproducto, int idAlmacenSalida, int idAlmacenEntrada) {
		
		KardexSie objKardex =obj; 
		objKardex.setTbProducto(objProductoDao.findProducto(idproducto));
		objKardex.setTbPuntoVenta(objAlmacenDao.findAlmacen(idAlmacenSalida));
		objKardex.setTbTipoKardexProducto(objTipoKardexDao.findTipoKardex(idtipokardexproducto));
		objKardex.setCantentrada(obj.getCantentrada());
		objKardex.setCantsalida(obj.getCantsalida());
		objKardex.setDetallekardex(obj.getDetallekardex());
		objKardexDao.insertMovimiento(idproducto,objKardex);
		
		/**Si hubiese otro id de almacen 
		 * */
		
		if(idAlmacenEntrada!=0){
			KardexSie objKardex2 = new KardexSie();
			objKardex2.setTbProducto(objProductoDao.findProducto(idproducto));
			objKardex2.setDetallekardex(obj.getDetallekardex());
			objKardex2.setTbPuntoVenta(objAlmacenDao.findAlmacen(idAlmacenEntrada));
			objKardex2.setCantentrada(obj.getCantsalida());
			objKardex2.setCantsalida(obj.getCantentrada());
			if(idtipokardexproducto==1){
				idtipokardexproducto=2;
			}else if(idtipokardexproducto==2){
				idtipokardexproducto=1;
			}
			objKardex2.setTbTipoKardexProducto(objTipoKardexDao.findTipoKardex(idtipokardexproducto));
			objKardexDao.insertMovimiento(idproducto,objKardex2);
		}
		if(objcomprobante!=null){
			objComprobanteDao.insertComprobante(objcomprobante);
		}
		if(objDetComprobante!=null){
			DetalleComprobanteSiePK oj = new DetalleComprobanteSiePK();
			oj.setIdkardex(objKardexDao.findKardex(objKardex.getIdkardex()).getIdkardex());
			oj.setIdcomprobante(objComprobanteDao.findComprobante(objcomprobante.getIdcomprobante()).getIdcomprobante());
			objDetComprobante.setId(oj);
			objDetComprobanteDao.insertComprobante(objDetComprobante);
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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.KardexService#findKardex(int)
	 */
	
	public KardexSie findKardex(int id) {
		return objKardexDao.findKardex(id);
	}
	
}
