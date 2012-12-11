package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ContratoEmpleadoDAOImpl implements ContratoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ContratoEmpleadoDAOImpl.class);
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#insertContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	@Override
	public void insertContratoEmpleado(ContratoEmpleadoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar contrato empleado");
			} 
			em.persist(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#updateContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	public void updateContratoEmpleado(ContratoEmpleadoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar contrato empleado");
			} 
			em.merge(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#findContratoEmpleado(int)
	 */
	public ContratoEmpleadoSie findContratoEmpleado(int id) {
		ContratoEmpleadoSie contrato= new ContratoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar ContratoEmpleado");
			} 
		contrato=	em.find(ContratoEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contrato;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarContrato()
	 */
	public List listarContrato() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Contrato --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarPatrocinados(int)
	 */
	public List listarPatrocinados(int idEmpleado) {
		log.info("listarPatrocinados "+idEmpleado);
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p where p.tbEmpleado2.idempleado = "+  idEmpleado);
			lista =  q.getResultList();
			log.info("tamaño listarPatrocinados--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
