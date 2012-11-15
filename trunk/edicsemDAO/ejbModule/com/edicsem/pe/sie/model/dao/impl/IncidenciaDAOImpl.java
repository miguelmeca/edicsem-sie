package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.IncidenciaDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;

@Stateless
public class IncidenciaDAOImpl implements IncidenciaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(IncidenciaDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.UbigeoDAO#insertUbigeo(com.edicsem.pe.sie
	 * .entity.UbigeoSie)
	 */
	public void insertIncidencia(IncidenciaSie incidencia) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Incidencia");
			}
			em.persist(incidencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.UbigeoDAO#updateUbigeo(com.edicsem.pe.sie
	 * .entity.UbigeoSie)
	 */
	public void updateIncidencia(IncidenciaSie incidencia) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar Incidencia");
			}
			em.merge(incidencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#eliminarUbigeo(int)
	 */
	public void eliminarIncidencia(int id) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#findUbigeo(int)
	 */
	public IncidenciaSie findIncidencia(int id) {
		IncidenciaSie u = new IncidenciaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Incidencia");
			}
			u = em.find(IncidenciaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}

	public List listarIncidencia() {
		List lista = null;
		try {

			Query q = em.createQuery("select p from IncidenciaSie p where p.tbEstadoGeneral.idestadogeneral = "+42+"or p.tbEstadoGeneral.idestadogeneral = "+43+"or p.tbEstadoGeneral.idestadogeneral = "+44+"or p.tbEstadoGeneral.idestadogeneral = "+45);
			lista = q.getResultList();
			log.info("tamaño lista Incidencia  --> " + lista.size()+ "  ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
