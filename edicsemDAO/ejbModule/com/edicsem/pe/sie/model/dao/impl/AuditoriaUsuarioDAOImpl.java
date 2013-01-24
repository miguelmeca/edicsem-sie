package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.AuditoriaUsuarioSie;
import com.edicsem.pe.sie.model.dao.AuditoriaUsuarioDAO;

/**
 * @author karen
 *
 */
@Stateless
public class AuditoriaUsuarioDAOImpl implements AuditoriaUsuarioDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(AuditoriaUsuarioDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AuditoriaUsuarioDAO#insertAuditoriaUsuario(com.edicsem.pe.sie.entity.AuditoriaUsuarioSie)
	 */
	public void insertAuditoriaUsuario(AuditoriaUsuarioSie a) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar AuditoriaUsuario");
			} 
			em.persist(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.AuditoriaUsuarioDAO#listarAuditoriaUsuario(java.lang.String)
	 */
	public AuditoriaUsuarioSie listarAuditoriaUsuario(String usuario) {
		log.info("   usuario*  "+ usuario);
		AuditoriaUsuarioSie au = null;
		List  lista = null;
		try {
			Query q = em.createQuery("select p from AuditoriaUsuarioSie p where p.usuario like '"+usuario+"' order by p.idauditoria ASC");
			lista =  q.getResultList();
			if(lista.size()>0){
				au= (AuditoriaUsuarioSie) lista.get(0);
			}
		   log.info("tamaño   " + lista.size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return au;
	}
}