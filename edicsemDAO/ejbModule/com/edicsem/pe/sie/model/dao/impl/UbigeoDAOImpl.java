package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#findDepaProv(java.lang.String, java.lang.String)
	 */
	public String findDepaProv(String idDepartamento, String idProvincia) {
		log.info("findDepaProv --> "+ "  "+idProvincia+"- "+ idDepartamento);
		String depaprov="";
		List lista = null;
		List lista2 = null;
		try {
			Query q = em.createQuery("select p.nombre from UbigeoSie p where  p.coddepartamento = '"
							+ idDepartamento + "'  " + " and p.codprovincia = '00'  and p.coddistrito = '00' ");
			lista = q.getResultList();
			depaprov=(String) lista.get(0);
			
			Query q2 = em.createQuery("select p.nombre from UbigeoSie p where  p.coddepartamento = '"
							+ idDepartamento + "'  " + " and p.codprovincia = '"+ idProvincia +"'  and p.coddistrito = '00' ");
			lista2 = q2.getResultList();
			String prov =(String) lista2.get(0);
			depaprov = depaprov+" - "+ prov;
			log.info("findDepaProv --> "+depaprov+" "+
					+ lista.size() + "  "+idProvincia+"- "+ idDepartamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depaprov;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.UbigeoDAO#findUbigeoXDescripcion(java.lang.String)
	 */
	public List<UbigeoSie> findUbigeoXDescripcion(String distrito) {
		log.info("findDepaProv --> "+ distrito);
		List<UbigeoSie> lista= new ArrayList<UbigeoSie>(),lista2 = new ArrayList<UbigeoSie>();
		List<UbigeoSie> obj= new ArrayList<UbigeoSie>();
		try {
			Query q = em.createQuery("select p from UbigeoSie p where trim(p.nombre) like '"+ distrito+"'");
			lista2 = q.getResultList();
			log.info(" gral "+lista2.size());
			if(lista2.size()==1){
				lista = q.getResultList();
			}
			else if(q.getResultList().size()>1){
				lista = null;
				Query q2 = em.createQuery("select p from UbigeoSie p where  p.coddepartamento = '15'  " +
				" and p.codprovincia = '01'  and trim(p.nombre) like  '" +distrito+"'" );
				lista= q2.getResultList();
				log.info("aki TAMANO LIMA --> "+ lista.size());
			}
			if(lista.size()==0){
				Query q2 = em.createQuery("select p from UbigeoSie p where  p.coddepartamento = '07'  " +
				" and p.codprovincia = '01'  and trim(p.nombre) like  '" +distrito+"'" );
				lista= q2.getResultList();
				log.info("aki TAMANO CALLAO --> "+ lista.size());
			}
			log.info(" gral2 "+lista2.size()+"  "+lista.size());
			if(lista2.size()>0 && lista.size()==0){
				log.info("departamento");
				for (int i = 0; i < lista2.size(); i++) {
					UbigeoSie obj2= (UbigeoSie) lista2.get(i);
					if(obj2.getCodprovincia().equals("00")&&obj2.getCoddistrito().equals("00")){
						obj.add(obj2);
						log.info("depa --> "+ obj2.getNombre());
						break;
					}
				}
			}
			if(lista2.size()>0 && obj == null && lista.size()==0){
				log.info("provincia");
				for (int i = 0; i < lista2.size(); i++) {
					UbigeoSie obj2= (UbigeoSie) lista2.get(i);
					if(obj2.getCoddistrito().equals("00")){
						obj.add(obj2);
						log.info("prov --> "+ obj2.getNombre());
						break;
					}
				}
			}
			if(lista.size()>=1){
				obj.add(lista.get(0));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
