package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.UbigeoSie;
import com.edicsem.pe.sie.model.dao.UbigeoDAO;

@Stateless
public class UbigeoDAOImpl implements UbigeoDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(UbigeoDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.UbigeoDAO#insertUbigeo(com.edicsem.pe.sie
	 * .entity.UbigeoSie)
	 */
	public void insertUbigeo(UbigeoSie ubigeo) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar ubigeo");
			}
			em.persist(ubigeo);
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
	public void updateUbigeo(UbigeoSie ubigeo) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar ubigeo");
			}
			em.merge(ubigeo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#eliminarUbigeo(int)
	 */
	public void eliminarUbigeo(int id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#findUbigeo(int)
	 */
	public UbigeoSie findUbigeo(int id) {
		UbigeoSie u = new UbigeoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Ubigeo");
			}
			u = em.find(UbigeoSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#listarUbigeoDepartamentos()
	 */
	public List listarUbigeoDepartamentos() {

		List lista = null;
		try {

			Query q = em.createQuery("select p from UbigeoSie p where p.codprovincia ='00' and p.coddistrito ='00' ORDER BY p.nombre ASC ");
			lista = q.getResultList();
			log.info("tamaño lista Ubigeo  --> " + lista.size()+ "  ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.UbigeoDAO#listarUbigeoProvincias(java.lang
	 * .String)
	 */
	public List listarUbigeoProvincias(String idDepartamento) {
		List lista = null;
		try {
			Query q = em
					.createQuery("select p from UbigeoSie p where  p.coddepartamento = '" + idDepartamento + "' " +
							"and p.codprovincia!='00' and p.coddistrito='00'" +
							" ORDER BY  p.nombre ASC ");
			lista = q.getResultList();
			log.info("tamaño lista Ubigeo Provincias  --> " + lista.size() + "  "+idDepartamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.UbigeoDAO#listarUbigeoDistritos(java.lang
	 * .String, java.lang.String)
	 */

	public List listarUbigeoDistritos(String idDepartamento, String idProvincia) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from UbigeoSie p where  p.coddepartamento = '"
							+ idDepartamento + "'  " + " and p.codprovincia = '" + idProvincia + "'  ORDER BY  p.nombre ASC ");
			lista = q.getResultList();
			log.info("tamaño lista Ubigeo  Distritos --> "
					+ lista.size() + "  "+idProvincia+"- "+ idDepartamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
