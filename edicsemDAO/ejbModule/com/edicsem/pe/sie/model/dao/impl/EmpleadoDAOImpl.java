package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.model.dao.EmpleadoDAO;

@Stateless
public class EmpleadoDAOImpl implements EmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(EmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarEmpleado(EmpleadoSie empleado) {
		//em.getTransaction().begin();
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
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
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
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarEmpleado(int id) {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public EmpleadoSie buscarEmpleado(int id) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarEmpleados() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
