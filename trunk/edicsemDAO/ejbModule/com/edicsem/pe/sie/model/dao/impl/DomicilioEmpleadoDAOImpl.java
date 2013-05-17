package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DomicilioPersonaSie;
import com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO;
import com.edicsem.pe.sie.util.constants.StringUtil;

@Stateless
public class DomicilioEmpleadoDAOImpl implements DomicilioEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DomicilioEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarDomicilioEmpleado(DomicilioPersonaSie domiciliopersona) {
		try {
			log.info("insert domicilio");
			em.persist(domiciliopersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarDomicilioEmpleado(DomicilioPersonaSie domiciliopersona) {
		try {
			if (log.isInfoEnabled()) {
				log.info("actualizar domicilio");
			}
			em.merge(domiciliopersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarDomicilioEmpleado(int id) {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public DomicilioPersonaSie buscarDomicilioEmpleado(int id) {
		DomicilioPersonaSie domiciliopersona= new DomicilioPersonaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DomicilioPersona"+id);
			} 
			domiciliopersona=	em.find(DomicilioPersonaSie.class, id);
			log.info(" DomicilioPersona " +domiciliopersona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domiciliopersona;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO#listarDomicilioEmpleados()
	 */
	public List listarDomicilioEmpleados() {
		return null;
	}	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO#buscarDomicilioXIdempleado(int)
	 */
	public DomicilioPersonaSie buscarDomicilioXIdempleado(int id) {
		log.info(" buscarDomicilioXIdempleado "+ id);
		DomicilioPersonaSie domicilio =new DomicilioPersonaSie();
		try {
			Query q = em.createQuery("select d from DomicilioPersonaSie d where d.idempleado.idempleado = "+ id);
			domicilio = (DomicilioPersonaSie) q.getResultList().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domicilio;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO#listarDomicilioCliente(int)
	 */
	public List listarDomicilioCliente(int id) {
		log.info(" idcliente "+ id);
		List<DomicilioPersonaSie> domiciliocliente =new ArrayList<DomicilioPersonaSie>();
		try {
			Query q = em.createQuery("select d from DomicilioPersonaSie d where d.idcliente = "+ id + " AND d.tbEstadoGeneral.idestadogeneral = 15" );
			domiciliocliente = q.getResultList();
			log.info("Domicilio x idcliente  --> " + domiciliocliente.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domiciliocliente;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO#buscarDomicilioXIdcliente(int)
	 */
	public DomicilioPersonaSie buscarDomicilioXIdcliente(int id) {
		log.info(" buscarDomicilioXIdcliente "+ id);
		DomicilioPersonaSie domicilio =new DomicilioPersonaSie();
		try {
			Query q = em.createQuery("select d from DomicilioPersonaSie d where d.idcliente.idcliente = "+ id);
			domicilio = (DomicilioPersonaSie) q.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domicilio;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DomicilioEmpleadoDAO#listarClientesXZonificacion(java.lang.String, java.util.List, java.util.List, java.util.List)
	 */
	public List listarClientesXZonificacion(String idUbigeo,List<String> planoList, List<String> letraList,List<String> sectorList)  {
		List  lista = null;
		String letra="", plano="", sector="";
		letra = StringUtil.stringtoList(letraList);
		plano = StringUtil.stringtoList(planoList);
		sector = StringUtil.stringtoList(sectorList);
		log.info("Plano "+ plano+" Letra "+letra+"  sector "+sector);
		try {
			Query q = em.createQuery("select p from DomicilioPersonaSie p where p.tbUbigeo.idubigeo = "+idUbigeo+" and " +
					" p.planoDomicilio in ("+plano+") and p.letraDomicilio in ("+letra+") " +
					" and p.sectorDomicilio in ("+sector+")");
			lista =  q.getResultList();
			log.info("tamaño lista domicilio  X Zonificacion --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
