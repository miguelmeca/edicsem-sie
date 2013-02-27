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
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#insertarCargoEmpleado(com.edicsem.pe.sie.entity.CargoEmpleadoSie)
	 */
	public void insertarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		log.info("apunto de insertar cargo empleado Empleado"+ cargoempleado.getDescripcion()+
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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#actualizarCargoEmpleado(com.edicsem.pe.sie.entity.CargoEmpleadoSie)
	 */
	public void actualizarCargoEmpleado(CargoEmpleadoSie cargoempleado) {
		
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Cargo empleado");
			}
			//CargoEmpleadoSie bean= buscarCargoEmpleado(cargoempleado.getIdcargoempleado());
			log.info("bean" + cargoempleado.getDescripcion() + " " + cargoempleado.getIdcargoempleado());
			
			em.merge(cargoempleado);
			log.info("--- ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#eliminarCargoEmpleado(int)
	 */
	public void eliminarCargoEmpleado(int id) {
		try {
			CargoEmpleadoSie bean= buscarCargoEmpleado(id);
			em.remove(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#buscarCargoEmpleado(int)
	 */
	public CargoEmpleadoSie buscarCargoEmpleado(int id) {
		log.info("buscar CargoEmpleado " +id);
		CargoEmpleadoSie cargoempleado = new CargoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar CargoEmpleado en el DAOIMPL" +id);
			}
			cargoempleado = em.find(CargoEmpleadoSie.class, id);
			log.info(" CargoEmpleado descripcion " + cargoempleado.getDescripcion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cargoempleado;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#listarCargoEmpleado()
	 */
	public List listarCargoEmpleado() {
		log.info("***************** listar cargoEmpleado");
		List lista = null;
		try {
			Query q = em.createQuery("select c from CargoEmpleadoSie c where c.tbEstadoGeneral.idestadogeneral = "+ 1);
			lista = q.getResultList();
			log.info("DAOIMPL tamaño de lista de cargo empleado--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#listarCargosXEmpleado(int)
	 */
	public List listarCargosXEmpleado(int idEmpleado){
		log.info("listar cargos X empleado");
		List lista = null;
		try {
			Query q = em.createQuery("select c from CargoEmpleadoSie c inner join c.tbContratoEmpleado d   " +
					" where d.tbEmpleado1.idempleado= "+ idEmpleado +" and c.tbEstadoGeneral.idestadogeneral = "+ 1+
					" and d.tbEstadoGeneral.idestadogeneral = "+17);
			lista = q.getResultList();
			log.info(" tamaño de lista de cargo X empleado--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
