package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.model.dao.ClienteDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ClienteDAOImpl implements ClienteDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ClienteDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ClienteDAO#insertCliente(com.edicsem.pe.sie.entity.ClienteSie)
	 */
	public void insertCliente(ClienteSie Cliente) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Cliente");
			} 
			em.persist(Cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ClienteDAO#updateCliente(com.edicsem.pe.sie.entity.ClienteSie)
	 */
	public void updateCliente(ClienteSie Cliente) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar Cliente");
			} 
			em.merge(Cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ClienteDAO#findCliente(int)
	 */
	public ClienteSie findCliente(int id) {
		ClienteSie obj= new ClienteSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Contrato");
			} 
		obj=	em.find(ClienteSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ClienteDAO#listarClientes()
	 */
	public List listarClientes() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ClienteSie p where p.tbEstadoGeneral.idestadogeneral = "+ 23);
			lista =  q.getResultList(); 
			log.info("tamaño lista Cliente --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ClienteDAO#listarClientesXTipo(int)
	 */
	public List listarClientesXTipo(int tipoCliente) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ClienteSie p where p.tipocliente = "+ tipoCliente);
			lista =  q.getResultList(); 
			log.info("tamaño lista Cliente --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
