package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetEmpresaEmpleadoDAOImpl implements DetEmpresaEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetEmpresaEmpleadoDAOImpl.class);
	 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO#insertDetEmpresaEmpleadoSie(com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie)
	 */
	public void insertDetEmpresaEmpleadoSie(DetEmpresaEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetEmpresaEmpleado ");
			} 
			em.persist(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO#updateDetEmpresaEmpleadoSie(com.edicsem.pe.sie.entity.DetEmpresaEmpleadoSie)
	 */
	public void updateDetEmpresaEmpleadoSie(DetEmpresaEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetEmpresaEmpleado ");
			} 
			em.merge(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO#findDetEmpresaEmpleadoSie(int)
	 */
	public DetEmpresaEmpleadoSie findDetEmpresaEmpleadoSie(int id) {
		DetEmpresaEmpleadoSie d= new DetEmpresaEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetEmpresaEmpleadoSie");
			} 
		d=	em.find(DetEmpresaEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO#listarDetEmpresaEmpleadoSie()
	 */
	public List listarDetEmpresaEmpleadoSie() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetEmpresaEmpleadoSie p where p.tbEstadoGeneral.idestadogeneral =33 ");
			lista =  q.getResultList();
			log.info("tama�o lista DetEmpresaEmpleadoSie --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetEmpresaEmpleadoDAO#listarDetEmpresaEmpleadoSieXEmpresa(int)
	 */
	public List listarDetEmpresaEmpleadoSieXEmpresa(int idempresa) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetEmpresaEmpleadoSie p where p.tbEmpresa.idempresa = " + idempresa);
			lista =  q.getResultList(); 
			log.info("tama�o lista DetEmpresaEmpleadoSie x empresa --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List listarDetEmpresaEmpleadoSieXEmpresaXempleado(int idempresa, int idempleado) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetEmpresaEmpleadoSie p where p.tbEmpresa.idempresa = " +
		idempresa +" and  tbEmpleado.idempleado ="+ idempleado);
			lista =  q.getResultList(); 
			log.info("tama�o lista DetEmpresaEmpleadoSie x empresa x empleado --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
