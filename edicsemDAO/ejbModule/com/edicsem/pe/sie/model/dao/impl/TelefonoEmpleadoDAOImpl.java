package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.edicsem.pe.sie.entity.CargoEmpleadoSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;

import com.edicsem.pe.sie.model.dao.TelefonoEmpleadoDAO;

@Stateless
public class TelefonoEmpleadoDAOImpl implements TelefonoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TelefonoEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarTelefonoEmpleado(TelefonoPersonaSie telefonopersona) {
		//em.getTransaction().begin();
		try {
						
                                        

			em.persist(telefonopersona);
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
	public void actualizarTelefonoEmpleado(TelefonoPersonaSie telefonopersona) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
			em.merge(telefonopersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarTelefonoEmpleado(int id) {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public TelefonoPersonaSie buscarTelefonoEmpleado(int id) {
		// TODO Auto-generated method stub
				TelefonoPersonaSie telefonopersona= new TelefonoPersonaSie();
				try {
				if (log.isInfoEnabled()) {
				log.info("buscar TelefonoPersona");
				} 
				telefonopersona=em.find(TelefonoPersonaSie.class, id);
				log.info(" TelefonoPersona " +telefonopersona);
				} catch (Exception e) {
				e.printStackTrace();
				}
				return telefonopersona;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarTelefonoEmpleados() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
