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
			EmpresaSie bean = findProducto(empresa.getIdempresa());
			
			em.merge(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#findProducto(java.lang.String)
	 */
	 
	public EmpresaSie findProducto(int id) {
		EmpresaSie empresa = new EmpresaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar Producto");
			}
			empresa = em.find(EmpresaSie.class, id);
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
			Query q = em.createQuery("select p from EmpresaSie p");
			lista = q.getResultList();
			System.out.println("tamaño lista Empresas --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
