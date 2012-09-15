package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO;

@Stateless
public class CargoEmpleadoDAOImpl implements CargoEmpleadoDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;

	private static Log log = LogFactory.getLog(CargoEmpleadoDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity
	 * .Usuario)
	 */
	public void insertarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		log.info("apunto de insertar cargo empleado Empleado"+ cargoempleado.getDescipcion()+
				" - ");
		try {
			em.persist(cargoempleado);

			if (log.isInfoEnabled()) {
				log.info("apunto de insertar cargo empleado Empleado");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actualizarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		em.getTransaction().begin();
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Cargo empleado");
			}
			em.merge(cargoempleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarCargoEmpleado(int id) {
		try {
			CargoEmpleadoSie bean= buscarCargoEmpleado(id);
			em.remove(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public CargoEmpleadoSie buscarCargoEmpleado(int id) {
		// TODO Auto-generated method stub
		CargoEmpleadoSie cargoempleado = new CargoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar CargoEmpleado");
			}
			cargoempleado = em.find(CargoEmpleadoSie.class, id);
			log.info(" CargoEmpleado " + cargoempleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cargoempleado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie
	 * .entity.Usuario)
	 * 
	 * public List listarCargoEmpleado(String descripcion) {
	 * log.info("***************** listar cargoEmpleado"); List lista= null; try
	 * { if (log.isInfoEnabled()) {
	 * log.info("apunto de insertar cargoEmpleado"); } Query q=
	 * em.createQuery("select p from CargoEmpleadoSie p"); lista=
	 * q.getResultList(); log.info(" tama�o " + lista.size()); } catch
	 * (Exception e) { e.printStackTrace(); } return lista; } }
	 */
	public List listarCargoEmpleado(String descripcion) {
		log.info("***************** listar cargoEmpleado");
		List lista = null;
		try {
			Query q = em.createQuery("select p from CargoEmpleadoSie p");
			lista = q.getResultList();
			log.info("tama�o de lista de cargo empleado--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
