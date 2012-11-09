package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HorarioPuntoVentaSie;
import com.edicsem.pe.sie.model.dao.HorarioPuntoVentaDAO;


@Stateless
public class HorarioPuntoVentaDAOImpl implements HorarioPuntoVentaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(HorarioPuntoVentaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPuntoVentaDAO#insertHorarioPunto(com.edicsem.pe.sie.entity.HorarioPuntoVentaSie)
	 */
	public void insertHorarioPunto(HorarioPuntoVentaSie h) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar el HorarioPuntoVenta");
			}
			em.persist(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPuntoVentaDAO#updateHorarioPunto(com.edicsem.pe.sie.entity.HorarioPuntoVentaSie)
	 */
	public void updateHorarioPunto(HorarioPuntoVentaSie h) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el HorarioPuntoVenta");
			}
			em.merge(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPuntoVentaDAO#findHorarioPunto(int)
	 */
	public HorarioPuntoVentaSie findHorarioPunto(int id) {
		HorarioPuntoVentaSie e= new HorarioPuntoVentaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar HorarioPuntoVenta " +id);
			} 
		e=	em.find(HorarioPuntoVentaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPuntoVentaDAO#listarHorarioPunto()
	 */
	public List listarHorarioPunto() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HorarioPuntoVentaSie p");
			lista =  q.getResultList(); 
			log.info("tamaño lista horario punto  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
