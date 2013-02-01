package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
import com.edicsem.pe.sie.util.constants.DateUtil;

/**
 * @author karen
 *
 */
@Stateless
public class ContratoDAOImpl implements ContratoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ContratoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#insertContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	
	public void insertContrato(ContratoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar contrato");
			} 
			em.persist(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#updateContrato(com.edicsem.pe.sie.entity.ContratoSie)
	 */
	
	public void updateContrato(ContratoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar contrato");
			} 
			em.merge(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#findContrato(int)
	 */
	
	public ContratoSie findContrato(int id) {
		ContratoSie contrato= new ContratoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Contrato");
			} 
		contrato=	em.find(ContratoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contrato;
	}

	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#listarContratos()
	 */
	public List listarContratos() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ContratoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Contrato --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#listarContratosDeudores()
	 */
	public List listarContratosDeudores() {
		List  lista = null;
		try {
			//cobranzas que estan vencidas o por vencer en dos días (como recordatorio)
			Query q = em.createQuery("select c from ContratoSie c inner join  c.tbCobranzas p where p.fecpago IS null and p.diasretraso > 0  or  " +
			"  DATE(p.fecvencimiento) - DATE('"+ DateUtil.getDate(DateUtil.getToday().getTime())  + "')  <= 2 and p.fecpago IS null ");
			
			lista =  q.getResultList();
			log.info("tamaño lista Cobranza --> " + lista.size()+"  ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#listarClientePorParametro(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List listarClientePorParametro(String numDocumento,String codigoContrato,String nombreCliente, String apePat,String apeMat ){
		List  lista = null;
		log.info( numDocumento+ " c "+codigoContrato+ " n "+nombreCliente+ " ap  "+apePat+ " am "+ apeMat);
		
		try {
			String sql = "select c from ContratoSie c inner join  c.tbCliente p  where  " ;
					if(numDocumento!=null)
						sql+= " p.numdocumento like '"+ numDocumento +"'";
					else if(codigoContrato!=null){
						sql+= "  c.codcontrato like '"+ codigoContrato +"'";
					}else{
						if(nombreCliente!=null)
							sql+= "  p.nombrecliente like '%"+ nombreCliente +"%'";
						if(apePat!=null)
							if(nombreCliente!=null){
								sql+= " and ";
							}
							sql+= "  p.apepatcliente like '%"+ apePat +"%'";
						if(apeMat!=null)
							if(apePat!=null){
								sql+= " and ";
							}else if(apePat==null && nombreCliente!=null){
								sql+= " and ";
							}
							sql+= "  p.apematcliente like '%"+ apeMat +"%'";
					}
					log.info("SQL "+sql);
			Query q = em.createQuery(sql);
			lista =  q.getResultList();
			log.info("tamaño lista contrato --> " + lista.size()+"  ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#obtenerCodigo()
	 */
	public int obtenerCodigo() {
		int codigo=0;
		try {
			Query q = em.createNativeQuery("SELECT nextval('SIE.TB_CONTRATO_IDCONTRATO_SEQ') ");
			codigo =  Integer.parseInt(q.getResultList().get(0).toString()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codigo;
	}
}
