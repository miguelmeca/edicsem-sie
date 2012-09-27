package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.KardexDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class KardexDAOImpl implements KardexDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(KardexDAOImpl.class);

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
		log.info("fechas " + fechaDesde + " fecha hasta " + fechaHasta);
		try {
			consulta = "select p from KardexSie p where p.tbProducto.idproducto =:x1 and "
					+ "p.tbPuntoVenta.idpuntoventa =:x2 ";
			if (fechaDesde != "" && fechaHasta != "")
				consulta += " and p.fechacreacion between '" + fechaDesde
						+ "' and  '" + fechaHasta + "'";

			Query q = em.createQuery(consulta);
			q.setParameter("x1", idproducto);
			q.setParameter("x2", idalmacen);
			lista = q.getResultList();

			log.info("tama�o lista Productos kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void insertMovimiento(int idProducto, KardexSie kardex) {

		KardexSie kardextmp = null;
		int cantiExist = 0;
		try {
//			if (kardex.getCantentrada() == null)
//				kardex.setCantentrada(0);
//			if (kardex.getCantsalida() == null)
//				kardex.setCantsalida(0);

			if (log.isInfoEnabled()) {

				log.info("insertar Movimiento- kardex "+ kardex.getIdkardex()+" " + kardex.getCantentrada() +" " + kardex.getCantsalida()+" "
						+ kardex.getTbTipoKardexProducto().getDescripcion()
						+ " - " + kardex.getTbPuntoVenta().getDescripcion() 
						+ " - "	+ kardex.getTbProducto().getDescripcionproducto());
			}
			//Salida o entrada al almacen 
			Query q = em.createQuery(" select p from KardexSie p where p.tbProducto.idproducto =:x1  " +
					"and p.tbPuntoVenta.idpuntoventa  =:x2 " +  " order by idKardex desc limit 1  ");
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
			if (kardextmp == null)
				cantiExist = 0;
			else
				cantiExist = kardextmp.getCantexistencia();
			log.info("cantiExist --> " + cantiExist);

			if (kardex.getCantsalida() != 0) {
				kardex.setCantexistencia(cantiExist - kardex.getCantsalida());
			} else {
				kardex.setCantexistencia(cantiExist + kardex.getCantentrada());
			}
			log.info("insertando.... 1 "+ kardex.getIdkardex()+" cant exis " + kardex.getCantexistencia());
			em.persist(kardex);
			
		
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
					.createQuery("select p from KardexSie p where p.fechacreacion = CURRENT_DATE order by p.idkardex asc");
			lista = q.getResultList();

			log.info("tama�o lista Productos kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* 
	 * (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#ConsultaStockActual(int)
	 */
	
	public KardexSie ConsultaStockActual(int idProducto) {
	log.info(" ID: " + idProducto);
	 KardexSie  k=null;
	 List<KardexSie> lista= new ArrayList();
	 List<PuntoVentaSie> listaAlmacen= new ArrayList();
	 int cantExistTotalAlm=0;
		try {
			Query qa = em.createQuery(" select  p  from PuntoVentaSie  p ");
			listaAlmacen =  qa.getResultList();
			
			for (int i = 0; i < listaAlmacen.size(); i++) {
				
			Query q = em.createQuery(" select  a  from  KardexSie a where " +
					" a.tbProducto.idproducto =" + idProducto + " and a.tbPuntoVenta.idpuntoventa =" + listaAlmacen.get(i).getIdpuntoventa()+
					" order by a.idkardex ");
			lista =  q.getResultList();
			
				cantExistTotalAlm += lista.get(lista.size()-1).getCantexistencia();
				log.info("i "+i+" "+ lista.get(lista.size()-1).getCantexistencia());
			}
			
			log.info("tama�o --> " + " *  " + lista.size());
			k = lista.get(lista.size()-1);
			k.setCantexistencia(cantExistTotalAlm);
			log.info("tama�o --> " + " *  " + cantExistTotalAlm + " min "+ k.getTbProducto().getStkminimoproducto() + " max " +
					"  "+ k.getTbProducto().getStkmaximo() +" existe "+ k.getCantexistencia() ); 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
}
