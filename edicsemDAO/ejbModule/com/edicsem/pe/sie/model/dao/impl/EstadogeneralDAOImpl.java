package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;


@Stateless
public class EstadogeneralDAOImpl implements EstadoGeneralDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(EstadogeneralDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#insertEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void insertEstadogeneral(EstadoGeneralSie estadogeneral) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar estado general");
			}
			em.persist(estadogeneral);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public void updateEstadogeneral(EstadoGeneralSie estadogeneral) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar estado general");
			}
			em.merge(estadogeneral);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#findProducto(java.lang.String)
	 */
	 
	public EstadoGeneralSie findEstadoGeneral(String id) {
		EstadoGeneralSie estadogeneral = new EstadoGeneralSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar cargo de empleado");
			}
			estadogeneral = em.find(EstadoGeneralSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estadogeneral;
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#listarEmpresas()
	 */
	 
	



 
	public EstadoGeneralSie findEstadoGeneral(int id) {
		EstadoGeneralSie e= new EstadoGeneralSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Estado General");
			} 
		e=	em.find(EstadoGeneralSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

 
	public List listarEstados(String codigo) {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from EstadoGeneralSie p where p.codestadogeneral  like  '%"+ codigo + "%'");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista EstadoGeneral  --> " + lista.size()+"  "+codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void eliminarEstadogeneral(EstadoGeneralSie estadogeneral) {
		// TODO Auto-generated method stub
		
	}

   
	public void eliminarEstadogeneral(int id) {
		// TODO Auto-generated method stub
		
	}
  
}
