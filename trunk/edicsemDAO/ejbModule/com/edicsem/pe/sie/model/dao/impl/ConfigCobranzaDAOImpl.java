package com.edicsem.pe.sie.model.dao.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ConfigCobranzaDAOImpl implements ConfigCobranzaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ConfigCobranzaDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO#insertConfigCobranza(com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie)
	 */
	public void insertConfigCobranza(ConfigCobranzaOperaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar config cobranza");
			} 
			em.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO#updateConfigCobranza(com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie)
	 */
	public void updateConfigCobranza(ConfigCobranzaOperaSie c) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar config cobranza");
			} 
			em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO#findConfigCobranza(int)
	 */
	public ConfigCobranzaOperaSie findConfigCobranza(int id) {
		ConfigCobranzaOperaSie c= new ConfigCobranzaOperaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar config cobranza "+ id);
			}
			c =	em.find(ConfigCobranzaOperaSie.class, id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO#listarConfigCobranza()
	 */
	public List listarConfigCobranza() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ConfigCobranzaOperaSie p ");
			lista =  q.getResultList(); 
		   log.info("tamaño lista config cobranza  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ConfigCobranzaDAO#buscarConfigCobranza(int)
	 */
	public List buscarConfigCobranza(int tipoCobranza) {
		List  lista = null;
		Calendar cal = new GregorianCalendar();
		try {
			Query q = em.createQuery("select p from ConfigCobranzaOperaSie p " +
					" where p.dias like '%"+ cal.get(Calendar.DAY_OF_WEEK) +"%' " +
					" and p.tbTipoCobranza.idtipocobranza = "+tipoCobranza+
					" and p.tbEstadoGeneral.idestadogeneral = 104");
			lista =  q.getResultList();
			log.info("tamaño lista config cobranza  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
