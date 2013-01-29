package com.edicsem.pe.sie.model.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.MetasDiariasDTO;
import com.edicsem.pe.sie.model.dao.MetasDiariasDTODAO;


/**
 * @author Joselo
 *
 */
@Stateless
public class MetasDiariasDTODAOImpl implements MetasDiariasDTODAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(MetasDiariasDTODAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AlmacenDAO#insertAlmacen(com.edicsem.pe.sie.entity.PuntoVentaSie)
	 */

	public void insertMetasDiariasDTO(MetasDiariasDTO p) {
		log.info("estamos en el DAOIMPL de Excel ");
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Persona ");
				
			} 
			
			log.info("cerca a persist");
			em.persist(p);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		
	}

	

	


}
