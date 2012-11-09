package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetContratoEmpleadoDAOImpl implements DetContratoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetContratoEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#insertDetContratoEmpleado(com.edicsem.pe.sie.entity.DetContratoEmpleadoSie)
	 */
	public void insertDetContratoEmpleado(DetContratoEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetContratoEmpleadoSie");
			} 
			em.persist(d);
		} catch (Exception e) {
			log.info(" msg  -->  " + e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#updateDetContratoEmpleado(com.edicsem.pe.sie.entity.DetContratoEmpleadoSie)
	 */
	public void updateDetContratoEmpleado(DetContratoEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetContratoEmpleadoSie");
			} 
			em.merge(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#findDetContratoEmpleado(int)
	 */
	public DetContratoEmpleadoSie findDetContratoEmpleado(int id) {
		DetContratoEmpleadoSie d= new DetContratoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetContratoEmpleadoSie");
			}
		d=	em.find(DetContratoEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#listarDetContratoEmpleado()
	 */
	public List listarDetContratoEmpleado() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetContratoEmpleadoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista DetContratoEmpleadoSie --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
}
