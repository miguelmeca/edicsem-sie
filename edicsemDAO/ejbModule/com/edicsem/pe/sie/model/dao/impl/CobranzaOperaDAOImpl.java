package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.model.dao.CobranzaOperaDAO;
import com.edicsem.pe.sie.util.constants.DateUtil;

/**
 * @author karen
 *
 */
@Stateless
public class CobranzaOperaDAOImpl implements CobranzaOperaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CobranzaOperaDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranzaOpera(CobranzaOperadoraSie cobranzaopera) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar cobranzaopera");
			} 
			em.persist(cobranzaopera);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar cobranzaopera");
			} 
			em.merge(cobranzaopera);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#findCobranza(int)
	 */
	public CobranzaOperadoraSie findCobranzaOpera(int id) {
		CobranzaOperadoraSie obj= new CobranzaOperadoraSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar cobranzaopera");
			} 
		obj=em.find(CobranzaOperadoraSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#listarCobranzas()
	 */
	public List listarCobranzasOpera(String usuario) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CobranzaOperadoraSie p where  DATE(p.fechacreacion) " +
					"<= DATE('"+DateUtil.getToday().getTime()+"')  and " +
					" DATE(p.fechaexpira) >= DATE('"+DateUtil.getToday().getTime()+"')  " +
					" and p.tbEmpleado.usuario like '"+usuario+"' and p.tbEstadoGeneral.idestadogeneral = 108 " +
					" or p.tbEstadoGeneral.idestadogeneral = 109 order by p.fechamodifica");
			lista =  q.getResultList();
			log.info("tamaño lista CobranzaOperadora --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaOperaDAO#verificargeneracionDiaria()
	 */
	public int verificargeneracionDiaria() {
		int tamano=0;
		List  lista = null;
		try {
			//Las listas son repartidas en dos turnos
			//Verificar si ya se generó lista anteriormente
			Query q = em.createQuery("select p from CobranzaOperadoraSie p where " +
					" DATE(p.fechacreacion) = DATE('"+DateUtil.getToday().getTime()+"') and p.tbEstadoGeneral.idestadogeneral = 108");
			lista =  q.getResultList();
			tamano=lista.size();
			log.info("tamaño lista CobranzaOperadora --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tamano;
	}

}
