package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
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
	@EJB
	private ContratoDAO objContratoDao;

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
				log.info("buscar Cobranza");
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
	public List<CobranzaSie> listarCobranzas() {
		List<CobranzaSie>  lista = null;
		try {
			
			//cobranzas que estan vencidas o por vencer en dos días (como recordatorio)
			log.info("tamaño lista Cobranza --> "+ DateUtil.getDate(DateUtil.getToday().getTime()));
			Query q = em.createQuery("select p from CobranzaSie p  where p.fecpago IS null and " +
			" p.diasretraso > 0  or  DATE(p.fecvencimiento) - DATE('"+ DateUtil.getDate(DateUtil.getToday().getTime())
			+ "')  <= 2 and p.fecpago IS null ");
			
			lista = new ArrayList<CobranzaSie>(q.getResultList());
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
		List<CobranzaSie> cob= new ArrayList<CobranzaSie>();
		try {
			Query q = em.createQuery("select p from CobranzaSie p where p.tbContrato.idcontrato = "+ idcontrato+" order by p.fecvencimiento ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Cobranza --> " + lista.size()+"  ");
			cob=lista;
			for (int i = 0; i < lista.size(); i++) {
				log.info(" fecha :   "+cob.get(i).getFechacreacion()+" fec ven: "+cob.get(i).getFecvencimiento());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#calcularEfectividad(int)
	 */
	public List calcularEfectividad(int idEmpleado,String fechaInicio, String fechaFin) {
		List  lista = null;
		try {
			/* cantidad de contratos pagados de un personal como expositor entre fechas correspondientes 
			 * que son los 6 meses anteriores*/
			
			Query q = em.createQuery("select a from CobranzaSie a inner join a.tbContrato b " +
					" inner join b.tbDetContratoEmpleados c where c.tbEmpleado.idempleado = "+ idEmpleado+ 
					" and DATE(c.tbContrato.fechaentrega) between DATE('" + fechaInicio +"') " +
					" and DATE('" + fechaFin +"')  and a.tbEstadoGeneral.idestadogeneral = 27 "+
					" and DATE(a.fecvencimiento) between DATE('" + fechaInicio +"') " +
					" and DATE('" + fechaFin +"') ");
			lista =  q.getResultList();
			log.info("tamaño lista Cobranza --> " + lista.size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#buscarCobranzaXcodigo(java.lang.String, java.util.Date, double)
	 */
	public CobranzaSie buscarCobranzaXcodigo(String codigo, Date fechaVencimiento,double montototalpagado){
		CobranzaSie cobranza=null;
		try {
			Query q = em.createQuery("select p from CobranzaSie p  where " +
					" p.tbCliente.numdocumento = '" +codigo+"' and  DATE(p.fecvencimiento) =  DATE('" +fechaVencimiento+"') "+
					" and p.importemasmora = "+ montototalpagado);
			if(q.getResultList().size()>0){
			cobranza = (CobranzaSie) q.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cobranza;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.CobranzaDAO#buscarCobranzaXcodigoContrato(java.lang.Integer)
	 */
	public List buscarCobranzaXcodigoContrato(Integer idcontrato) {
		List cobranza=null;
		try {
			Query q = em.createQuery("select p from CobranzaSie p  where " +
					" p.tbContrato.idcontrato  = '" +idcontrato+"'");
			if(q.getResultList().size()>0){
			cobranza = q.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cobranza;
	}
}
