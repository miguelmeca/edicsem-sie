package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.IncidenciaSie;
import com.edicsem.pe.sie.entity.ObservacionIncidenciaSie;
import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.IncidenciaDAO;
import com.edicsem.pe.sie.model.dao.ObsIncidenciaDAO;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;

@Stateless
public class ObsIncidenciaDAOImpl implements ObsIncidenciaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ObsIncidenciaDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.UbigeoDAO#insertUbigeo(com.edicsem.pe.sie
	 * .entity.UbigeoSie)
	 */
	public void insertObsIncidencia(ObservacionIncidenciaSie obsincidencia) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar obsIncidencia");
			}
			em.persist(obsincidencia);
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
	public void updateObsIncidencia(ObservacionIncidenciaSie obsincidencia) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar obsIncidencia");
			}
			em.merge(obsincidencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#eliminarUbigeo(int)
	 */
	public void eliminarObsIncidencia(int id) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#findUbigeo(int)
	 */
	public ObservacionIncidenciaSie findObsIncidencia(int id) {
		ObservacionIncidenciaSie u = new ObservacionIncidenciaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar obsIncidencia");
			}
			u = em.find(ObservacionIncidenciaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}

	public List listarObsIncidencia(int id) {
		List lista = null;
		try {

			Query q = em.createQuery("select p from ObservacionIncidenciaSie p where p.tbIncidencia.idincidencia ="+id+"and p.tbEstadoGeneral.idestadogeneral != "+41);
			lista = q.getResultList();
			log.info("tamaño lista obsIncidencia  --> " + lista.size()+ "  ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
