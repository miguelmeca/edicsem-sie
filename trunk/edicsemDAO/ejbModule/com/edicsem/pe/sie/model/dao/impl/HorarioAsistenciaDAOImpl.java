package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HorarioAsistenciaSie;
import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.model.dao.HorarioAsistenciaDAO;
import com.edicsem.pe.sie.model.dao.HorarioPersonalDAO;;


@Stateless
public class HorarioAsistenciaDAOImpl implements HorarioAsistenciaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(HorarioAsistenciaDAOImpl.class);
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#insertHorarioPersonal(com.edicsem.pe.sie.entity.HorarioPersonalSie)
	 */
	public void insertHorarioAsistencia(HorarioAsistenciaSie horarioasistencia) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar el horarioasistencia");
			}
			em.persist(horarioasistencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#updateHorarioPersonal(com.edicsem.pe.sie.entity.HorarioPersonalSie)
	 */
	public void updateHorarioAsistencia(HorarioAsistenciaSie horarioasistencia) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el horarioasistencia");
			}
			em.merge(horarioasistencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#eliminarHorarioPersonal(int)
	 */
	public void eliminarHorarioAsistencia(int id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#findHorarioPersonal(int)
	 */
	public HorarioAsistenciaSie findHorarioAsistencia(int id) {
		HorarioAsistenciaSie e= new HorarioAsistenciaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar horarioasistencia");
			} 
		e=	em.find(HorarioAsistenciaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#listarHorarioPersonal()
	 */
	public List listarHorarioAsistencia() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HorarioAsistenciaSie p");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista horarioasistencia  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#listarHorarioPersonalXempleado(int)
	 */
	public List listarHorarioAsistenciaXempleado(int id) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HorarioAsistenciaSie p where p.tbEmpleado.idempleado = "+id);
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista horarioasistencia  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List obtenerFechaFinalXempleado(int id) {
		List  lista = null;
		try {
			Query q = em.createQuery("select MAX(p.fecha) from HorarioAsistenciaSie p where p.tbEmpleado.idempleado = "+id);
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista horarioasistencia  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
