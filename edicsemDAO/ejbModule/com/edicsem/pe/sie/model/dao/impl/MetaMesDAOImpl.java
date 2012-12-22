package com.edicsem.pe.sie.model.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.MetaMesSie;
import com.edicsem.pe.sie.model.dao.MetaMesDAO;
import com.edicsem.pe.sie.util.constants.DateUtil;

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
				log.info("buscar MetaMes + id");
			}
			metames = em.find(MetaMesSie.class, id);
			
			log.info(" Meta Mes DAOIMPL +  id" );
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
			Query q = em.createQuery("select m from MetaMesSie m");
			lista = q.getResultList();
			log.info("tamaño de lista de Metames--->" + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MetaMesDAO#fechasEfectividad()
	 */
	public MetaMesSie fechasEfectividad() {
		List<MetaMesSie> lista = null;
		MetaMesSie objMetaMes = new MetaMesSie();
		try {
			Calendar cal =	DateUtil.getToday();
			int MesInicial =0, Mesfinal=0;
			log.info(" "+cal.get(Calendar.MONTH)+"  ");
			MesInicial= cal.get(Calendar.MONTH)-6;
			if(MesInicial<0){
				MesInicial= MesInicial*-1;
			}
			Mesfinal =MesInicial+6;
			log.info("mes inicial "+ MesInicial +"  mes final "+ Mesfinal);
			Query q = em.createQuery("select m from MetaMesSie m where m.codmes =" + MesInicial );
			lista = q.getResultList();
			objMetaMes.setFechainicio(lista.get(0).getFechainicio()+"/2012");
			Query q2 = em.createQuery("select m from MetaMesSie m where  m.codmes = "+ Mesfinal);
			lista = q2.getResultList();
			objMetaMes.setFechafin(lista.get(0).getFechafin()+"/2012");
			log.info("f inicial "+ objMetaMes.getFechainicio() +"  f final "+ objMetaMes.getFechafin());
			log.info("tamaño de fechasEfectividad--->" + lista.size());
			}catch (Exception e) {
				e.printStackTrace();
			}
			return objMetaMes;
		}
	}
