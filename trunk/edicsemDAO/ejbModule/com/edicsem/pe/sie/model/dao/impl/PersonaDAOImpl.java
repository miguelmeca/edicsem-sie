package com.edicsem.pe.sie.model.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.Personalead;
import com.edicsem.pe.sie.model.dao.PersonaDAO;

/**
 * @author Joselo
 *
 */
@Stateless
public class PersonaDAOImpl implements PersonaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(PersonaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#insertAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */

	public void insertPersona(Personalead p) {
		log.info("estamos en el DAOIMPL de Excel "+ p.getId()+ p.getNombre());
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Persona "+ p.getId() + p.getNombre());
				
			} 
			
			log.info("cerca a persist");
			em.persist(p);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		
	}

	


}
