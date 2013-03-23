package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CarroSie;
import com.edicsem.pe.sie.model.dao.CarroDAO;

/**
 * @author karen
 *
 */
@Stateless
public class CarroDAOImpl implements CarroDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CarroDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CarroDAO#insertCarro(com.edicsem.pe.sie.entity.CarroSie)
	 */
	public void insertCarro(CarroSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Carro");
			} 
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CarroDAO#updateCarro(com.edicsem.pe.sie.entity.CarroSie)
	 */
	public void updateCarro(CarroSie t) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Carro");
			} 
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CarroDAO#findCarro(int)
	 */
	public CarroSie findCarro(int id) {
		CarroSie t= new CarroSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Carro "+ id);
			}
			t =	em.find(CarroSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CarroDAO#listarCarro()
	 */
	public List listarCarro() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CarroSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista Carro --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
