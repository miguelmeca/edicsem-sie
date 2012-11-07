package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.HorarioPersonalSie;
import com.edicsem.pe.sie.model.dao.HorarioPersonalDAO;;


@Stateless
public class HorarioPersonalDAOImpl implements HorarioPersonalDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(HorarioPersonalDAOImpl.class);
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#insertHorarioPersonal(com.edicsem.pe.sie.entity.HorarioPersonalSie)
	 */
	public void insertHorarioPersonal(HorarioPersonalSie horariopersonal) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar el horariopersonal");
			}
			em.persist(horariopersonal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#updateHorarioPersonal(com.edicsem.pe.sie.entity.HorarioPersonalSie)
	 */
	public void updateHorarioPersonal(HorarioPersonalSie horariopersonal) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el horariopersonal");
			}
			em.merge(horariopersonal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#eliminarHorarioPersonal(int)
	 */
	public void eliminarHorarioPersonal(int id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#findHorarioPersonal(int)
	 */
	public HorarioPersonalSie findHorarioPersonal(int id) {
		HorarioPersonalSie e= new HorarioPersonalSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar horariopersonal " +id);
			} 
		e=	em.find(HorarioPersonalSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#listarHorarioPersonal()
	 */
	public List listarHorarioPersonal() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HorarioPersonalSie p");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista horario personal  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.HorarioPersonalDAO#listarHorarioPersonalXempleado(int)
	 */
	public List listarHorarioPersonalXempleado(int id) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from HorarioPersonalSie p where p.tbEmpleado.idempleado = "+id);
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista horario personal  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
