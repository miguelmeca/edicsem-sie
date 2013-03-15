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
							sql+= "  UPPER(p.nombrecliente) like UPPER('"+ nombreCliente.trim() +"%')";
						if(apePat!=null)
							if(nombreCliente!=null){
								sql+= " and ";
							}
							sql+= "  UPPER(p.apepatcliente) like UPPER('"+ apePat.trim() +"%')";
						if(apeMat!=null)
							if(apePat!=null){
								sql+= " and ";
							}else if(apePat==null && nombreCliente!=null){
								sql+= " and ";
							}
							sql+= "  UPPER(p.apematcliente) like UPPER('"+ apeMat.trim() +"%')";
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
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#listarContratoEntregaLetraObsequio(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List listarContratoEntregaLetraObsequio(String numDocumento,String codigoContrato, String nombreCliente, String apePat,String apeMat) {
		List  lista = null;
		log.info( numDocumento+ " c "+codigoContrato+ " n "+nombreCliente+ " ap  "+apePat+ " am "+ apeMat);
		
		try {
			String sql = "select c from ContratoSie c where  c.idcontrato in (select p.tbContrato.idcontrato " +
					" from CobranzaSie p  where p.fecpago is not null and p.tbContrato.idcontrato = c.idcontrato )" ;
					
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

	
	public ContratoSie buscarXcodigoContrato(int id) {
		
		return null;
	}
	
	//verifico si este numero de contrato ya se encuentra en la BD de sistemas integrados
	public boolean verificarNumContrato(int numContrato) {
		boolean bandera = true;	
		List lista = null;
		try {
			Query q = em.createQuery("select p from ContratoSie p where p.tbEstadoGeneral.idestadogeneral = 25 AND p.codcontrato like  '"+ numContrato + "'");
			lista = q.getResultList();											
			log.info("tamaño lista Numero Contrato --> " + lista.size());
			if(lista.size()>0){ //hay uno o mas contratos retornados.
				bandera=false;
			}else{//no hay contrato, entonces puede proseguir
				bandera=true;
			}
			
		} catch (Exception e) {
			bandera=false;
			e.printStackTrace();
		}
				
		return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#buscarXcodigoContrato(java.lang.String)
	 */
	public ContratoSie buscarXcodigoContrato(String codContrato) {

		ContratoSie p = null;
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Numero de Contrato" +"  "+ codContrato );
			}
			Query q = em.createQuery("select p from ContratoSie p where p.tbEstadoGeneral.idestadogeneral = 25 AND p.codcontrato like  '"+ codContrato + "'");
			
			if (q.getResultList().size() == 1) {
				p = (ContratoSie) q.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoDAO#findcantContratoFacturadoEntregado(int)
	 */
	public Integer findcantContratoFacturadoEntregado(int idEmpleado, int cargo) {
		
		int cantidadFact=0;
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar facturados "+ idEmpleado );
			}
			//maximo la primera letra: se divide en dos cuotas (Cuota Inicial : 0 y -1 ) las dos pagadas
			//solo con cargo de EXPOSITOR
			
			Query q = em.createQuery("select p from ContratoSie p where p.tbEstadoGeneral.idestadogeneral = 25 " +
					" and p.tbCobranzas.numletra <= 0  and  p.tbCobranzas.fecpago != empty " +
					" and p.tbDetContratoEmpleados.tbEmpleado.idempleado = "+idEmpleado+
					" and p.tbDetContratoEmpleados.tbCargoempleado = "+cargo);
			
			if (q.getResultList().size() > 1) {
				cantidadFact = q.getResultList().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cantidadFact;
	}
	
}
