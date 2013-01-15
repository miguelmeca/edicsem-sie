package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.PermisoSie;
import com.edicsem.pe.sie.model.dao.PermisoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class PermisoDAOImpl implements PermisoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(PermisoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PermisoDAO#insertPermiso(com.edicsem.pe.sie.entity.PermisoSie)
	 */
	public void insertPermiso(PermisoSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Permiso");
			} 
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PermisoDAO#updatePermiso(com.edicsem.pe.sie.entity.PermisoSie)
	 */
	public void updatePermiso(PermisoSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Permiso");
			} 
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PermisoDAO#findPermiso(int)
	 */
	public PermisoSie findPermiso(int id) {
		PermisoSie p= new PermisoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
			}
			p=	em.find(PermisoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PermisoDAO#findPermiso(java.lang.String)
	 */
	public PermisoSie findPermiso(String permiso) {
		PermisoSie p = null;
		try {
			Query q = em.createQuery("select p from PermisoSie p where  p.tbEstadoGeneral.idestadogeneral = "+ 68 +" and p.nombrePermiso ='"+permiso+"'");
			p =  (PermisoSie) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.PermisoDAO#listarPermiso()
	 */
	public List listarPermiso() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from PermisoSie p where  p.tbEstadoGeneral.idestadogeneral = "+ 68 );
			lista =  q.getResultList(); 						
		   log.info("tamaño lista Permiso DAOIMPL --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO#listarPermisosXEmpleado(int)
	 */
	public List listarPermisosXEmpleado(int idEmpleado) {
		List lista = null;
		log.info("  idEmpleado "+idEmpleado );
		try {
			Query q = em.createQuery("select p from PermisoSie p inner join p.tbDetEmpleadoEmpleados q inner join q.tbEmpleado r where r.idempleado  = "+ idEmpleado);
			lista =  q.getResultList();
		   log.info("tamaño lista listarPermisosXEmpleado --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
