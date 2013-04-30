package com.edicsem.pe.sie.service.facade.impl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.ProductoDTO;
import com.edicsem.pe.sie.entity.DetPaqueteSie;
import com.edicsem.pe.sie.entity.PaqueteSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.model.dao.DetPaqueteDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.PaqueteDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;
import com.edicsem.pe.sie.model.dao.TipoProductoDAO;
import com.edicsem.pe.sie.service.facade.ProductoService;

@Stateless
public class ProductoServiceImpl implements ProductoService {

	@EJB
	private  ProductoDAO objProductoDao;
	@EJB
	private TipoProductoDAO objTipoProductoDao;
	@EJB
	private EstadoGeneralDAO objestadoDao;
	@EJB
	private PaqueteDAO objPaqueteDao;
	@EJB
	private DetPaqueteDAO objDetPaqueteDao;
	
	public static Log log = LogFactory.getLog(EmpleadoSieServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#insertProducto(com.edicsem.pe.sie.entity.ProductoSie, int)
	 */
	public void insertProducto(ProductoSie producto,int TipoProducto) {
		
		producto.setTbTipoProducto(objTipoProductoDao.findTipoProducto(TipoProducto));
		producto.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(5));
		objProductoDao.insertProducto(producto);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#updateProducto(com.edicsem.pe.sie.entity.ProductoSie, int)
	 */
	public void updateProducto(ProductoSie producto,int TipoProducto) {
		producto.setTbTipoProducto(objTipoProductoDao.findTipoProducto(TipoProducto));
		producto.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(6));
		objProductoDao.updateProducto(producto);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#findProducto(java.lang.String)
	 */
	public ProductoSie findProducto(int id) {
		return objProductoDao.findProducto(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductos()
	 */
	public List listarProductos() {
		return objProductoDao.listarProductos();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductosXTipo(int)
	 */
	public List listarProductosXTipo(int tipoProducto) { 
		return objProductoDao.listarProductosXTipo(tipoProducto); 
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarProductoxEmpresas(int)
	 */
	public List listarProductoxEmpresas(int parametroObtenido) {
		log.info("dentro del servicio listar Producto x Empresas ");
		return objProductoDao.listarProductoxEmpresas(parametroObtenido);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#verificarTipoProducto(int)
	 */
	public boolean verificarTipoProducto(int tipoProducto) {
		log.info("en el servicio" + tipoProducto);
		return objProductoDao.verificarTipoProducto(tipoProducto);
	}

	
	public ProductoSie buscarXcodigoProducto(String codProducto) {
		log.info("en el SERVICIO BUSCANDO ID-PRODUCTO"+ codProducto);
		return objProductoDao.buscarXcodigoProducto(codProducto);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#listarCodigosProductos()
	 */
	public List listarCodigosProductos() {
		return objProductoDao.listarCodigosProductos();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ProductoService#migrarProducto(java.util.List, java.lang.String)
	 */
	public void migrarProducto(List<ProductoDTO> listaProducto,String usuarioCreacion) {
		int  insertPaq=0;
		int insertPro=0;
		for (int i = 0; i < listaProducto.size(); i++) {
			String codigo = listaProducto.get(i).getCodproducto();
			String descripcion = listaProducto.get(i).getDescripcionproducto().toUpperCase();
			ProductoSie pro = null;
			PaqueteSie paq = null;
			descripcion = descripcion.replace("'", "");
			descripcion = descripcion.replace("(", "");
			descripcion = descripcion.replace(")", "");
			descripcion = descripcion.replace("\"", "");
			log.info("aca ");
			if(descripcion.contains("-")||descripcion.contains("+")||descripcion.contains(",")){
				//Es paquete
				paq = objPaqueteDao.buscarXcodigoPaquete(codigo);
				if(paq==null){
					log.info("insert paquete ");
					paq = new PaqueteSie();
					paq.setCodpaquete(codigo);
					paq.setDescripcionpaquete(descripcion.trim().toUpperCase());
					paq.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(60));
					insertPaq+=1;
					objPaqueteDao.insertPaquete(paq);
					List<DetPaqueteSie> lstDetPAquete= new ArrayList<DetPaqueteSie>();
					DetPaqueteSie det = new DetPaqueteSie();
					//insertar detalle de paquete
					String[] arrDescripcion = null;
					if(descripcion.contains("-")){
						arrDescripcion = descripcion.split("-");
					}else if(descripcion.contains("+")){
						arrDescripcion = descripcion.split("\\+");
					}else if(descripcion.contains(",")){
						arrDescripcion = descripcion.split(",");
					}
					for (int j = 0; j < arrDescripcion.length; j++) {
						String des="";
						int cant=1;
						log.info("producto "+arrDescripcion[j].trim());
						log.info("Ver cantidAD "+arrDescripcion[j].trim().substring(0, 2));
						if(arrDescripcion[j].trim().substring(0, 2).trim().matches("([0-9])")){
							log.info("Producto  cantida "+cant+" desc "+des);
							cant = Integer.parseInt(arrDescripcion[j].trim().substring(0, 2).trim());
							des=arrDescripcion[j].substring(2, arrDescripcion[j].length()).trim().toUpperCase();
						}else{
							des=arrDescripcion[j].trim().toUpperCase();
							log.info("Des "+des);
						}
						des = des.replace("'", "");
						des = des.replace("(", "");
						des = des.replace(")", "");
						des = des.replace("\"", "");
						pro = objProductoDao.findProductoporDescripcion(des);
						
						if(pro==null){
							pro= new ProductoSie();
							pro.setDescripcionproducto(des.toUpperCase());
							log.info("--> "+des.trim());
							//buscar ultimo de codigo producto
							String codig= objProductoDao.buscarUltimocodigoProducto(pro.getDescripcionproducto().substring(0, 3).toUpperCase());
							if(codig.equals("")){
								log.info("-->nuevo");
								codig=pro.getDescripcionproducto().substring(0, 3)+"-001";
							}else{
								//selecciona los ultimos 3 digitos para el producto
								log.info("codig1 "+codig.substring(codig.length()-3, codig.length()));
								codig=codig.substring(codig.length()-3, codig.length());
								log.info("codig "+codig);
								int c = Integer.parseInt(codig);
								c=c+1;
								log.info("cccccc  "+c);
								for (int k = 0; k < (c+"").length(); k++) {
									codig="0";
								}
								codig = pro.getDescripcionproducto().substring(0, 3)+"-"+codig+c;
								log.info(""+codig);
							}
							pro.setCodproducto(codig);
							pro.setUsuariocreacion(usuarioCreacion);
							pro.setTbTipoProducto(objTipoProductoDao.findTipoProducto(1));
							pro.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(5));
							insertPro+=1;
							objProductoDao.insertProducto(pro);
						}
						det.setUsuariocreacion(usuarioCreacion);
						det.setTbPaquete(paq);
						det.setTbProducto(pro);
						det.setCantidad(cant);
						lstDetPAquete.add(det);
					}
					for (int j = 0; j < lstDetPAquete.size(); j++) {
						lstDetPAquete.get(j).setUsuariocreacion(usuarioCreacion);
						lstDetPAquete.get(j).setTbEstadoGeneral(objestadoDao.findEstadoGeneral(106));
						objDetPaqueteDao.insertDetPaquete(lstDetPAquete.get(j));
					}
				}else{
					//si existe el paquete
					log.info("Si existe el paquete ");
				}
			}else{
				//Es producto
				//Buscamos el producto
				
				pro = objProductoDao.findProductoporDescripcion(descripcion);
				if(pro!=null){
					log.info("Si existe el producto ");
				}else{
					pro= new ProductoSie();
					pro.setCodproducto(codigo);
					pro.setDescripcionproducto(descripcion);
					pro.setUsuariocreacion(usuarioCreacion);
					pro.setTbTipoProducto(objTipoProductoDao.findTipoProducto(1));
					pro.setTbEstadoGeneral(objestadoDao.findEstadoGeneral(5));
					insertPro+=1;
					objProductoDao.insertProducto(pro);
				}
			}
		}
		log.info("Mensaje : se insertaron "+insertPaq +" paquetes, y se insertaron "+insertPro+" productos ");
	}
}
