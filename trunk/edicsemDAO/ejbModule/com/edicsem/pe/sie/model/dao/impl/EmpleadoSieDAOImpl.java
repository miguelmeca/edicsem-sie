package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;

@Stateless
public class EmpleadoSieDAOImpl implements EmpleadoSieDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(EmpleadoSieDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#insertarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie)
	 */
	public void insertarEmpleado(EmpleadoSie empleado) {
		try {
                              
			em.persist(empleado);
			//em.flush();
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
			//em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#actualizarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie)
	 */
	public void actualizarEmpleado(EmpleadoSie empleado) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
			em.merge(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#eliminarEmpleado(int)
	 */
	public void eliminarEmpleado(int id) {
		try {
			em.remove(id);
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#buscarEmpleado(int)
	 */
	public EmpleadoSie buscarEmpleado(int id) {
		
		EmpleadoSie empleado= new EmpleadoSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar empleado");
		} 
		empleado=	em.find(EmpleadoSie.class, id);
		log.info(" empleado " +empleado);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return empleado;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarEmpleados()
	 */
	public List listarEmpleados() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from EmpleadoSie p where p.tbEstadoGeneral.idestadogeneral = "+3);
			lista = q.getResultList();
			System.out.println("tamaño lista Empleados --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarEmpleadosXCargo(int)
	 */
	public List listarEmpleadosXCargo(int idCargo) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from EmpleadoSie p inner join p.tbDetCargoEmpleados q  " +
					"inner join  q.tbCargoEmpleado r where r.idcargoempleado = "+idCargo);
			lista = q.getResultList();
			System.out.println("tamaño lista Empleados X Cargo --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
