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
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.KardexDAO#ConsultaProductos(int, int, java.lang.String, java.lang.String)
	 */
	public List ConsultaProductos(int idproducto, int idalmacen,
			String fechaDesde, String fechaHasta) {
		List lista = new ArrayList();
		
		log.info("fechas " + fechaDesde +" fecha hasta " + fechaHasta);
		try {
			Query q = em.createQuery("select p from KardexSie p where p.tbProducto.idproducto =:x1 and " +
					"p.tbPuntoVenta.idpuntoventa =:x2 and p.fechacreacion between '"+ fechaDesde + "' and  '" +fechaHasta +"'");
			q.setParameter("x1", idproducto);
			q.setParameter("x2", idalmacen); 
			lista = q.getResultList();

			log.info("tamaño lista Productos kardex --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


	
	public void insertMovimiento(KardexSie kardex) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Movimiento- kardex"+ kardex.getTipokardex()+" - "+ kardex.getTbPuntoVenta().getAlmacen()+" - "+ 
			kardex.getCantentrada()+" - "+ kardex.getTbProducto().getDescripcionproducto());
			}
			em.persist(kardex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

 
}
