package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	private Log log = LogFactory.getLog(KardexServiceImpl.class);
	
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
		log.info(" "+ obj.getCantexistencia());
		objKardex.setTbProducto(objProductoDao.findProducto(idproducto));
		objKardex.setTbPuntoVenta(objAlmacenDao.findAlmacen(idAlmacenSalida));
		objKardex.setTbTipoKardexProducto(objTipoKardexDao.findTipoKardex(idtipokardexproducto));
		objKardex.setCantentrada(obj.getCantentrada());
		objKardex.setCantsalida(obj.getCantsalida());
		objKardex.setCantexistencia(0);
		objKardexDao.insertMovimiento(idproducto,objKardex);
		log.info("entrad "+obj.getValorunitarioentrada()+" sali "+obj.getValorunitariosalida());
		/**Si hubiese otro id de almacen 
		 * */
		
		if(idAlmacenEntrada!=0){
			log.info(" almacen 2 ");
			KardexSie objKardex2 = new KardexSie();
			objKardex2.setTbProducto(objProductoDao.findProducto(idproducto));
			objKardex2.setDetallekardex(obj.getDetallekardex());
			objKardex2.setTbPuntoVenta(objAlmacenDao.findAlmacen(idAlmacenEntrada));
			objKardex2.setCantentrada(obj.getCantsalida());
			objKardex2.setCantsalida(obj.getCantentrada());
			objKardex2.setValorunitarioentrada(obj.getValorunitariosalida());
			objKardex2.setValorunitariosalida(obj.getValorunitarioentrada());
			objKardex2.setValortotal(obj.getValortotal());
			log.info(" almacen 2 "+objKardex2.getValorunitarioentrada()+" sali "+objKardex2.getValorunitariosalida());
			if(idtipokardexproducto==1){
				idtipokardexproducto=2;
				objKardex2.setValorunitarioentrada("0.0");
				objKardex2.setValorunitariosalida(obj.getValorunitarioentrada());
			}else if(idtipokardexproducto==2){
				idtipokardexproducto=1;
				objKardex2.setValorunitariosalida("0.0");
				objKardex2.setValorunitarioentrada(obj.getValorunitarioentrada());
			}
			objKardex2.setTbTipoKardexProducto(objTipoKardexDao.findTipoKardex(idtipokardexproducto));
			objKardexDao.insertMovimiento(idproducto,objKardex2);
		}
		ComprobanteSie comp = new ComprobanteSie();
		DetalleComprobanteSiePK oj = new DetalleComprobanteSiePK();
		int idcompro = 0;
		
		if(objcomprobante.getCodcomprobante()!=null){
			log.info(" " + objcomprobante.getCodcomprobante() );
			comp= objComprobanteDao.findComprobantePorNumero(objcomprobante.getCodcomprobante());
			log.info(" comp :)b" );
			log.info(" comp "+comp.getCodcomprobante() );
			if(comp.getCodcomprobante()== null){
				log.info(" null " );
				objComprobanteDao.insertComprobante(objcomprobante);
				idcompro = objcomprobante.getIdcomprobante();
			}
			else{log.info(" no null " );
				oj.setIdcomprobante(comp.getIdcomprobante());
				idcompro = oj.getIdcomprobante();
			}
			log.info(" objDetComprobante ");
			if(objDetComprobante!=null){
				log.info(" objDetComprobante id kardex "+ objKardex.getIdkardex());
				oj.setIdkardex(objKardexDao.findKardex(objKardex.getIdkardex()).getIdkardex());
				
				if(oj.getIdcomprobante()==null){
					
				oj.setIdcomprobante(objComprobanteDao.findComprobante(idcompro).getIdcomprobante());
				}
				
				objDetComprobante.setId(oj);
				objDetComprobanteDao.insertComprobante(objDetComprobante);
			}
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



	public boolean verificarProductoConEmpresa(int idcargo) {
		log.info("En el servicio verificar Producto Con Empresa ");
		return objKardexDao.verificarProductoConEmpresa(idcargo) ;
	}
	

	
}
