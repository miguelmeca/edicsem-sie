package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.MetaMesDAO;
import com.edicsem.pe.sie.util.constants.DateUtil;

/**
 * @author karen
 *
 */
@Stateless
public class CobranzaDAOImpl implements CobranzaDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(CobranzaDAOImpl.class);
	
	@EJB
	private MetaMesDAO metaMesDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranza(CobranzaSie Cobranza) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Cobranza");
			} 
			em.persist(Cobranza);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranza(CobranzaSie Cobranza) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Cobranza");
			} 
			em.merge(Cobranza);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#findCobranza(int)
	 */
	public CobranzaSie findCobranza(int id) {
		CobranzaSie obj= new CobranzaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Contrato");
			} 
		obj=	em.find(CobranzaSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#listarCobranzas()
	 */
	public List listarCobranzas() {
		List  lista = null;
		try {
			//cobranzas que estan vencidas o por vencer en dos días como máximo para realizar recordatorio
			
			Query q = em.createQuery("select p from CobranzaSie p where " +
					" DATE(p.fecpago) - DATE('"+ DateUtil.getDatePattern() + "')  <= 2 ");
			lista =  q.getResultList();
			log.info("tamaño lista Cobranza --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#listarCobranzasXidcontrato(int)
	 */
	public List listarCobranzasXidcontrato(int idcontrato) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from CobranzaSie p where p.tbContrato.idcontrato = "+ idcontrato);
			lista =  q.getResultList(); 
			log.info("tamaño lista Cobranza --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List calcularEfectividad(int idEmpleado) {
		List  lista = null;
		try {
			/* cantidad de contratos pagados de un personal como expositor entre fechas correspondientes 
			 * que son los 6 meses anteriores*/
			MetaMesSie objMetaMes = metaMesDao.fechasEfectividad();
			
			Query q = em.createQuery("select a from CobranzaSie a inner join a.tbContrato b " +
					" inner join b.tbDetContratoEmpleados c where c.tbEmpleado.idempleado = "+ idEmpleado+ 
					" and DATE(c.tbContrato.fechaentrega) between DATE('" + objMetaMes.getFechainicio() +
					"') and  DATE('" + objMetaMes.getFechafin() +"') and a.tbEstadoGeneral.idestadogeneral = 27 ");
			lista =  q.getResultList();
			log.info("tamaño lista Cobranza --> " + lista.size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
