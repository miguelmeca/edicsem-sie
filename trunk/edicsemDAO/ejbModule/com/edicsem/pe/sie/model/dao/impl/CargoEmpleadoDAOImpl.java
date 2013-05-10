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
		try {
			if (log.isInfoEnabled()) {
				log.info("insertarCargoEmpleado");
			}
			
			em.persist(cargoempleado);

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
				log.info("modificar Cargo empleado "+cargoempleado.getDescripcion());
			}
			em.merge(cargoempleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#eliminarCargoEmpleado(int)
	 */
	public void eliminarCargoEmpleado(int id) {
		try {
			CargoEmpleadoSie bean= findCargoEmpleado(id);
			em.remove(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#buscarCargoEmpleado(int)
	 */
	public CargoEmpleadoSie findCargoEmpleado(int id) {
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
		log.info("listarCargoEmpleado()");
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
		log.info("listarCargosXEmpleado()");
		List lista = null;
		try {
			Query q = em.createQuery("select c from CargoEmpleadoSie c inner join c.tbContratoEmpleado d   " +
					" where d.tbEmpleado1.idempleado= "+ idEmpleado +" and c.tbEstadoGeneral.idestadogeneral = "+ 1+
					" and d.tbEstadoGeneral.idestadogeneral = "+19);
			lista = q.getResultList();
			log.info(" tamaño de lista de cargo X empleado--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CargoEmpleadoDAO#buscarCargoEmpleado(java.lang.String)
	 */
	public CargoEmpleadoSie buscarCargoEmpleado(String cargo) {
		log.info("buscarCargoEmpleado()");
		List lista = null;
		CargoEmpleadoSie ca=null;
		try {
			Query q = em.createQuery("select c from CargoEmpleadoSie c where UPPER(c.descripcion) like UPPER('"+ cargo+"')");
			lista = q.getResultList();
			if(lista.size()>0)
			ca=(CargoEmpleadoSie) lista.get(0);
			log.info(" tamaño de lista de cargo empleado x string --->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ca;
	}
	
}
