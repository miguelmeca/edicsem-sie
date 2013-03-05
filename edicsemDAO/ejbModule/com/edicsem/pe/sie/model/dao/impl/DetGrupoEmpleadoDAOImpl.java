package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetGrupoEmpleadoDAOImpl implements DetGrupoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetGrupoEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO#insertDetGrupoEmpleado(com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie)
	 */
	public void insertDetGrupoEmpleado(DetGrupoEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetGrupoEmpleado");
			} 
			em.persist(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO#updateDetGrupoEmpleado(com.edicsem.pe.sie.entity.DetGrupoEmpleadoSie)
	 */
	public void updateDetGrupoEmpleado(DetGrupoEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetGrupoEmpleadoSie");
			} 
			em.merge(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO#findDetGrupoEmpleado(int)
	 */
	public DetGrupoEmpleadoSie findDetGrupoEmpleado(int id) {
		DetGrupoEmpleadoSie s= new DetGrupoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Almacen"+ id);
			} 
		s=	em.find(DetGrupoEmpleadoSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO#listarDetGrupoEmpleado()
	 */
	public List listarDetGrupoEmpleado() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetGrupoEmpleadoSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista grupo --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetGrupoEmpleadoDAO#listarEmpleadosXGrupo(int)
	 */
	public List listarEmpleadosXGrupo(int idGrupo) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetGrupoEmpleadoSie p inner join  p.tbempleado.tbContratoEmpleados1 m where " +
					" p.tbGrupoVenta.idgrupo = "+idGrupo+" and m.tbCargoempleado.idcargoempleado = 16");
			lista =  q.getResultList();
		   log.info("tamaño lista grupo --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
