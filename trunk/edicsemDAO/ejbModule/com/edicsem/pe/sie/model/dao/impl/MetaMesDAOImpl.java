package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.MetaMesDAO;

/**
 * @author joselo
 * 
 */
@Stateless
public  class MetaMesDAOImpl implements MetaMesDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(MetaMesDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#insertEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void insertMetaMes(MetaMesSie metames) {
		 
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar Meta Mes"+ metames.getIdmetames());
			} 
			em.persist(metames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#updateEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void updateMetaMes(MetaMesSie metames) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar Meta Mes");
			}

			log.info("BeanMetaMesDaoImpl");
			em.merge(metames);
			log.info("---despues del merge ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#findProducto(java.lang.String)
	 */
	 
	public MetaMesSie findMetaMes(int id) {
		MetaMesSie metames = new MetaMesSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar MetaMes");
			}
			metames = em.find(MetaMesSie.class, id);
			
			log.info(" Meta Mes DAOIMPL " );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metames;
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#listarEmpresas()
	 */
	 
	public List<MetaMesSie> listarMetaMeses() {
		List<MetaMesSie> lista = null;
		try {
			Query q = em.createQuery("select m from MetaMesSie m  ");
			lista = q.getResultList();
			log.info("DAOIMPL tamaño de lista de Metames--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	}
