package com.edicsem.pe.sie.service.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.MetasDiariasDTO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.MetasDiariasDTODAO;
import com.edicsem.pe.sie.service.facade.MetasDiariasDTOService;

@Stateless
public class MetasDiariasDTOServiceImpl implements MetasDiariasDTOService {

	@EJB
	private MetasDiariasDTODAO objMetasDiariasDTODAO;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	private static Log log = LogFactory.getLog(MetasDiariasDTOServiceImpl.class);

	
	
	
	public void insertMetasDiariasDTO(MetasDiariasDTO p) {
		log.info("estamos en el servicio  de Excel ");
		
		objMetasDiariasDTODAO.insertMetasDiariasDTO(p);
		
	}




	




	





	
	

}
