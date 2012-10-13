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
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
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

	@EJB
	private AlmacenDAO almacenDAOService;

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
				consulta += " and DATE(p.fechacreacion) between DATE('"
						+ fechaDesde + "') and  DATE('" + fechaHasta
						+ "')   ORDER BY p.idkardex ASC  ";

			Query q = em.createQuery(consulta);
			q.setParameter("x1", idproducto);
			q.setParameter("x2", idalmacen);
			lista = q.getResultList();

			log.info("tamaño lista Productos kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void insertMovimiento(int idProducto, KardexSie kardex) {

		KardexSie kardextmp = null;
		int cantiExist = 0;
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
			// Salida o entrada al almacen
			Query q = em
					.createQuery(" select p from KardexSie p where p.tbProducto.idproducto =:x1  "
							+ "and p.tbPuntoVenta.idpuntoventa  =:x2 "
							+ " order by idKardex desc limit 1  ");
			q.setParameter("x1", idProducto);
			q.setParameter("x2", kardex.getTbPuntoVenta().getIdpuntoventa());
			if (q.getResultList().size() > 0) {
				kardextmp = (KardexSie) q.getResultList().get(0);
			}

			/*
			 * Si No existe ningun kardex de dicho producto empieza en 0 su
			 * cantidad existente Si existe kardex se pasa la info, y a
			 * continuación se editará la cantidad existente*
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
			log.info("insertando.... 1 " + kardex.getIdkardex() + " cant exis "
					+ kardex.getCantexistencia());
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
					.createQuery("select p from KardexSie p where DATE(p.fechacreacion) = CURRENT_DATE ORDER BY p.idkardex ASC ");
			lista = q.getResultList();

			log.info("tamaño lista Productos kardex --> " + lista.size());
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

				Query q = em.createQuery(" select  a  from  KardexSie a where "
						+ " a.tbProducto.idproducto = " + idProducto
						+ " and a.tbPuntoVenta.idpuntoventa = "
						+ listaAlmacenes.get(i).getIdpuntoventa()
						+ " ORDER BY a.idkardex ASC ");
				listaTmp = q.getResultList();

				if (listaTmp.size() - 1 != -1) {
					if (listaTmp.get(listaTmp.size() - 1) != null) {
						lista.add(listaTmp.get(listaTmp.size() - 1));
					}
				}
				log.info(" * tamano " + lista.size());
			}
			// reordenar por idkardex 14,10,9,17,15

			// for (int i = 0; i < lista.size() ; i++) {
			// if(lista.get(i).getIdkardex() < lista.get(i+1).getIdkardex() ){
			// // 10>11
			// lista2.add( lista.get(i));
			//
			// }
			// }

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

}
