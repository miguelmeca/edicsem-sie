//package com.edicsem.pe.sie.service.facade.impl;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//
//import com.edicsem.pe.sie.entity.Personalead;
//import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
//import com.edicsem.pe.sie.model.dao.PersonaDAO;
//import com.edicsem.pe.sie.service.facade.PersonaService;
//
//@Stateless
//public class PersonaServiceImpl implements PersonaService {
//
//	@EJB
//	private PersonaDAO objPersonaDao;
//	@EJB
//	private  EstadoGeneralDAO objEstadoGeneralDao;
//	
//	private static Log log = LogFactory.getLog(PersonaServiceImpl.class);
//
//	
//	
//	
//	public void insertPersona(Personalead p) {
//		log.info("estamos en el servicio  de Excel ");
//		
//		objPersonaDao.insertPersona(p);
//		
//	}
//
//
//
//
//
//	
//	
//
//}
