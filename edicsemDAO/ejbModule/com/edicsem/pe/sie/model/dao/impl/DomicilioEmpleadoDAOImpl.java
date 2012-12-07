package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;

@Stateless
public class DomicilioEmpleadoDAOImpl implements DomicilioEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DomicilioEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarDomicilioEmpleado(DomicilioPersonaSie domiciliopersona) {
		log.info("apunto de insertar domicilio"+ domiciliopersona.getDomicilio()+
				" - " + domiciliopersona.getReferencia());
		try {
			
			
			log.info("antes del Persist");
			em.persist(domiciliopersona);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarDomicilioEmpleado(DomicilioPersonaSie domiciliopersona) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de actualizar domicilio del Empleado");
			}
			em.merge(domiciliopersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarDomicilioEmpleado(int id) {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public DomicilioPersonaSie buscarDomicilioEmpleado(int id) {
		// TODO Auto-generated method stub
				DomicilioPersonaSie domiciliopersona= new DomicilioPersonaSie();
				try {
				if (log.isInfoEnabled()) {
				log.info("buscar DomicilioPersona");
				} 
				domiciliopersona=	em.find(DomicilioPersonaSie.class, id);
				log.info(" DomicilioPersona " +domiciliopersona);
				} catch (Exception e) {
				e.printStackTrace();
				}
				return domiciliopersona;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	
	
	/*buscar domicilio por idempleado*/
	public DomicilioPersonaSie buscarDomicilioXIdempleado(int id) {
		log.info(" idempleado "+ id);
		DomicilioPersonaSie domicilio =new DomicilioPersonaSie();
		try {
			Query q = em.createQuery("select p from DomicilioPersonaSie p where p.idempleado = "+ id);
			domicilio = (DomicilioPersonaSie) q.getResultList().get(0);
			//log.info("Domicilio x idempleado  --> " + domicilio.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domicilio;
	}
	
	
		
	public List listarDomicilioCliente(int id) {
		log.info(" idcliente "+ id);	
	List<DomicilioPersonaSie> domiciliocliente =new ArrayList<DomicilioPersonaSie>();
		try {
			Query q = em.createQuery("select d from DomicilioPersonaSie d where d.idcliente = "+ id);
			domiciliocliente = q.getResultList();
			log.info("Domicilio x idcliente  --> " + domiciliocliente.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domiciliocliente;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO#listarDomicilioEmpleados()
	 */
	
	public List listarDomicilioEmpleados() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
