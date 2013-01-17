package com.edicsem.pe.sie.model.dao.impl;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.model.dao.LoginDAO;

@Stateless
public class LoginDAOImpl implements LoginDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(LoginDAOImpl.class);

	public EmpleadoSie validacionLogin(String usuario, String contrasena) {
		EmpleadoSie objEmpleado =null;
		try{
			log.info(" u "+usuario+" c "+ contrasena);
			Query q = em.createQuery("SELECT u FROM EmpleadoSie u WHERE u.usuario = :username AND u.contrasena = :password " );
			q.setParameter("username",usuario);
			q.setParameter("password",contrasena);
			log.info(" tamano  "+q.getResultList().size());
			if(q.getResultList().size()>0){
				log.info(" positivo!!  ");
				objEmpleado = (EmpleadoSie) q.getSingleResult();
			}
		}
		catch(Exception e){
			log.info(" exception !!  ");
			log.info(e.getMessage());
		}
		return objEmpleado;
	}		
}
