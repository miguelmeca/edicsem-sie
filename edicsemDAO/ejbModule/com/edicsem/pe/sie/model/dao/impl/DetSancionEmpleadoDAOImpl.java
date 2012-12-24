package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetSancionEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetSancionEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetSancionEmpleadoDAOImpl implements DetSancionEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetSancionEmpleadoDAOImpl.class);
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionEmpleadoDAO#insertDetSancionEmpleado(com.edicsem.pe.sie.entity.DetSancionEmpleadoSie)
	 */
	public void insertDetSancionEmpleado(DetSancionEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetSancionEmpleadoSie ");
			} 
			em.persist(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionEmpleadoDAO#updateDetSancionEmpleado(com.edicsem.pe.sie.entity.DetSancionEmpleadoSie)
	 */
	public void updateDetSancionEmpleado(DetSancionEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetSancionEmpleadoSie ");
			}
			em.merge(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionEmpleadoDAO#findContrato(int)
	 */
	public DetSancionEmpleadoSie findContrato(int id) {
		DetSancionEmpleadoSie d= new DetSancionEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetSancionEmpleadoSie");
			} 
		d=	em.find(DetSancionEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetSancionEmpleadoDAO#listarDetSancionEmpleado(int, java.lang.String, java.lang.String)
	 */
	public List listarDetSancionEmpleado(int idEmpleado,String fechaInicio,String fechaFin) {
		log.info("empleado :D :D "+ idEmpleado );
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetSancionEmpleadoSie p where p.tbEmpleado.idempleado = "+ idEmpleado
						+" and  DATE(p.fechacreacion ) between DATE('" + fechaInicio +"') and  DATE('" + fechaFin +"')  ");
			lista =  q.getResultList(); 
			log.info("tamaño  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
