package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.KardexDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class KardexDAOImpl implements KardexDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(KardexDAOImpl.class);

	@EJB
	private AlmacenDAO almacenDAOService;
	@EJB
	private ProductoDAO productoDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#ConsultaProductos(int, int,
	 * java.lang.String, java.lang.String)
	 */
	public List ConsultaProductos(int idproducto, int idalmacen,
			String fechaDesde, String fechaHasta) {
		List lista = new ArrayList();
		String consulta = "";
		log.info(" producto "+idproducto+" almacen"+ idalmacen +"fechas " + fechaDesde + " fecha hasta " + fechaHasta);
		try {
			consulta = "select p from KardexSie p ";
			if(idproducto!=0){
				consulta+=" where p.tbProducto.idproducto =  "+idproducto;
			}
			if(idalmacen!=0){
				if(idproducto!=0){
					consulta+=" and p.tbPuntoVenta.idpuntoventa = "+idalmacen;
				}else{
					consulta+=" where p.tbPuntoVenta.idpuntoventa = "+idalmacen;
				}
			}
			if (fechaDesde != "" || fechaHasta != ""){
				consulta += " and DATE(p.fechacreacion) between DATE('"
						+ fechaDesde + "') and  DATE('" + fechaHasta
						+ "')  ";
			}
			consulta += "  ORDER BY p.fechacreacion ASC  ";
			log.info("CONSULTA  "+consulta);
			Query q = em.createQuery(consulta);
			lista = q.getResultList();
			
			log.info("tama�o lista Productos kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void insertMovimiento(int idProducto, KardexSie kardex, int stockTotalAlmacenado) {

		KardexSie kardextmp = null;
		int cantiExist = 0;
		double valorExist=0.0;
		try {

			if (log.isInfoEnabled()) {

				log.info("insertar Movimiento- kardex " + kardex.getIdkardex()
						+ " " + kardex.getCantentrada() + " "
						+ kardex.getCantsalida() + " "
						+ kardex.getTbTipoKardexProducto().getDescripcion()
						+ " - " + kardex.getTbPuntoVenta().getDescripcion()
						+ " - "
						+ kardex.getTbProducto().getDescripcionproducto());
			}
			Query q = null;
			String consulta;
			// Salida o entrada al almacen
			if(kardex.getTbTipoKardexProducto().getIdtipokardexproducto()<3){
				log.info(" menor 3 " );
				consulta=" select p from KardexSie p  inner join p.tbTipoKardexProducto q where p.tbProducto.idproducto =:x1  "
						+ "and p.tbPuntoVenta.idpuntoventa  =:x2 and q.idtipokardexproducto < 3"
						+ " order by idKardex desc limit 1  ";
			}else{
				log.info("   3 " );
				consulta=" select p from KardexSie p  inner join p.tbTipoKardexProducto q where p.tbProducto.idproducto =:x1  "
							+ "and p.tbPuntoVenta.idpuntoventa  =:x2 and q.idtipokardexproducto= 3"
							+ " order by idKardex desc limit 1  ";
			}
			q = em.createQuery(consulta);
			q.setParameter("x1", idProducto);
			q.setParameter("x2", kardex.getTbPuntoVenta().getIdpuntoventa());
				if (q.getResultList().size() > 0) {
					kardextmp = (KardexSie) q.getResultList().get(0);
				}
			/*
			 * Si No existe ningun kardex de dicho producto empieza en 0 su
			 * cantidad existente Si existe kardex se pasa la info, y a
			 * continuaci�n se editar� la cantidad existente*
			 */
			if (kardextmp == null){
				cantiExist = 0;
				valorExist = 0.0;
			}else{
				cantiExist = kardextmp.getCantexistencia();
				valorExist = Double.parseDouble(kardextmp.getValorunitarioexistencia());
			}
			log.info("cantiExist --> " + cantiExist);
			double p =0.0;
			if (kardex.getCantsalida() != 0) {
				kardex.setCantexistencia(cantiExist - kardex.getCantsalida());
				p =valorExist - Double.parseDouble(kardex.getValortotal());
				log.info("****** "+valorExist +" "+kardex.getValortotal()+" "+p);
				kardex.setValorunitarioexistencia(""+p);
			} else {
				kardex.setCantexistencia(cantiExist + kardex.getCantentrada());
				p =valorExist + Double.parseDouble(kardex.getValortotal());
				log.info("****** "+valorExist +" "+kardex.getValortotal()+" "+p);
				kardex.setValorunitarioexistencia(""+p);
			}
			log.info("insertando.... 1 " + kardex.getIdkardex() + " cant exis "
					+ kardex.getCantexistencia()+" stock almacenado   "+stockTotalAlmacenado);
			em.persist(kardex);
			
			if(kardex.getTbTipoKardexProducto().getIdtipokardexproducto()!=3){
				//insertando las unidades actuales de un producto, solo si es entrada y salida , no deposito
				ProductoSie objProducto = productoDao.findProducto(idProducto);
				objProducto.setUnidproducto(stockTotalAlmacenado);
				productoDao.updateProducto(objProducto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#ConsultaKardexDiario()
	 */

	public List ConsultaKardexDiario() {
		List lista = new ArrayList();
		try {
			Query q = em
					.createQuery("select p from KardexSie p where DATE(p.fechacreacion) = CURRENT_DATE ORDER BY p.idkardex ASC ");
			lista = q.getResultList();

			log.info("tama�o lista Productos kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#ConsultaStockActual(int)
	 */

	public List<KardexSie> ConsultaStockActual(int idProducto) {
		log.info(" ID: ** " + idProducto);
		List<KardexSie> listaTmp = null;
		List<KardexSie> lista = new ArrayList();
		List<KardexSie> lista2 = new ArrayList();
		int cantExistTotalAlm = 0;
		List<PuntoVentaSie> listaAlmacenes = almacenDAOService
				.listarAlmacenes();
		int idMayor = 0;
		try {

			for (int i = 0; i < listaAlmacenes.size(); i++) {

				Query q = em.createQuery(" select  a  from  KardexSie a inner join a.tbTipoKardexProducto b where "
						+ " a.tbProducto.idproducto = " + idProducto
						+ " and a.tbPuntoVenta.idpuntoventa = "
						+ listaAlmacenes.get(i).getIdpuntoventa()
						+ " and b.idtipokardexproducto< 3 ORDER BY a.idkardex ASC  ");
				listaTmp = q.getResultList();

				if (listaTmp.size() - 1 != -1) {
					if (listaTmp.get(listaTmp.size() - 1) != null) {
						lista.add(listaTmp.get(listaTmp.size() - 1));
					}
				}
				log.info(" * tamano " + lista.size());
			}

			KardexSie temporal;

			for (int j = 0; j < lista.size() - 1; j++) {

				for (int i = 0; i < lista.size() - 1; i++) {

					if (lista.get(i).getIdkardex() < lista.get(i + 1)
							.getIdkardex()) {
						temporal = lista.get(i + 1);
						lista.set(i + 1,lista.get(i));
						lista.set(i,temporal);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.KardexDAO#updateKardex(com.edicsem.pe.sie
	 * .entity.KardexSie)
	 */

	public void updateKardex(KardexSie kardex) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Kardex");
			}
			em.merge(kardex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#findKardex(int)
	 */

	public KardexSie findKardex(int id) {
		KardexSie obj = new KardexSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Kardex");
			}
			obj = em.find(KardexSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


	public boolean verificarProductoConEmpresa(int idcargo) {
		boolean bandera = true;
		List lista = null;
		try {
			Query q = em.createQuery("select p from KardexSie p where p.tbEmpresa.idempresa = "+ idcargo);
			lista = q.getResultList();
			log.info("tama�o lista empresas --> " + lista.size());
			if(lista.size()>0){ //hay uno o mas empresas retorna y muestra el msj de que no se podra eliminar.
				bandera=false;
			}else{//no hay empresas, entonces puede proseguir
				bandera=true;//se ejecuta el query
			}
			
		} catch (Exception e) {
			bandera=false;
			e.printStackTrace();
		}
		return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#ConsultaKardexAlmacen(int)
	 */
	public List<KardexSie> ConsultaKardexAlmacen(int idAlmacen) {
		List<KardexSie> lista = new ArrayList<KardexSie>();
		List<Integer> listaTmp = null;
		try {
			
			Query q = em.createQuery("select distinct tbProducto.idproducto from KardexSie p where p.tbPuntoVenta.idpuntoventa = "+idAlmacen+
					"and p.tbTipoKardexProducto.idtipokardexproducto< 3 ");
			listaTmp= q.getResultList();
			for (int i = 0; i < listaTmp.size(); i++) {
				log.info(" producto --> " + listaTmp.get(i));
			}
			for (int i = 0; i < listaTmp.size(); i++) {
				Query q2 = em.createQuery("select p from KardexSie p where p.tbPuntoVenta.idpuntoventa = "+idAlmacen+
						" and p.tbProducto.idproducto = "+listaTmp.get(i)  +" and p.tbTipoKardexProducto.idtipokardexproducto< 3 ORDER BY p.idkardex DESC LIMIT 1 ");
				log.info(" kardex --> " + q2.getResultList().size());
				if( q2.getResultList().size()>0){
				KardexSie k = (KardexSie) q2.getResultList().get(0);
				lista.add(k);
				}
			}
			
			log.info("tama�o lista kardex x almacen--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
