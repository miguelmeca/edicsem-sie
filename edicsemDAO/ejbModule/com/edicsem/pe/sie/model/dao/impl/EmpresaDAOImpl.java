package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;

/**
 * @author karen
 * 
 */
@Stateless
public class EmpresaDAOImpl implements EmpresaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(EmpresaDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#insertEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void insertEmpresa(EmpresaSie empresa) {
		 
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Empresa"+ empresa.getIdempresa());
			} 
			em.persist(empresa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#updateEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void updateEmpresa(EmpresaSie empresa) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar Empresa");
			}

			log.info("Dentro del updateEmpresa --->");
			em.merge(empresa);
			log.info("---despues del merge ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#findProducto(java.lang.String)
	 */
	 
	public EmpresaSie findEmpresa(int id) {
		EmpresaSie empresa = new EmpresaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Empresa");
			}
			empresa = em.find(EmpresaSie.class, id);
			
			log.info(" Epresa DAOIMPL-> " + empresa.getIdempresa() + "-"+ empresa.getDescripcion() +" - "+ empresa.getNumruc() +" - "+empresa.getNumtelefono() +" - "+ empresa.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresa;
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#listarEmpresas()
	 */
	 
	public List<EmpresaSie> listarEmpresas() {
		List<EmpresaSie> lista = null;
		try {
			Query q = em.createQuery("select p from EmpresaSie p  where p.tbEstadoGeneral.idestadogeneral = "+ 7);
			lista = q.getResultList();
			log.info("DAOIMPL tamaño de lista de empresa--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#findEmpresaXdescripcion(java.lang.String)
	 */
	
	public EmpresaSie findEmpresaXdescripcion(String razonSocial) {
		EmpresaSie obj=null;
		List<EmpresaSie> lista = null;
		try {
			Query q = em.createQuery("select p from EmpresaSie p  where trim(p.razonsocial)  like  '"+ razonSocial+"'");
			lista = q.getResultList();
			log.info("  tamaño de lista de empresa String --->" + lista.size());
			if(lista.size()==1){
				obj= lista.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


	public EmpresaSie buscarIdEmpresa(String razonSocial) {

		EmpresaSie p = new EmpresaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar razonSocial en la IMPLEMENTACION DAO" + razonSocial );
			}

Query q = em.createQuery("select p from  EmpresaSie p where p.tbEstadoGeneral.idestadogeneral = 7 AND p.razonsocial like  '"+ razonSocial + "'");
			if (q.getResultList().size() == 1) {

				p = (EmpresaSie) q.getResultList().get(0);
				// casteado tiene columnas pero no se ah mencionado cuales son p=(ContratoSie) q.getResultList().get(0);
				
			}
			log.info("Aquita Empresa-->"+ p.getRazonsocial()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	
	
	
	
	
	
	
}
