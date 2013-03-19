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
	 * @see com.edicsem.pe.sie.model.dao.MetaMesDAO#findMetaMes(int)
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
			Query q = em.createQuery("select m from MetaMesSie m order by m.idmetames ASC ");
			lista = q.getResultList();
			log.info("tamaño de lista de Metames--->" + lista.size());
			for (int i = 0; i < lista.size(); i++) {
				log.info(" Metames--->" + i+""+lista.get(i).getMes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.MetaMesDAO#fechasEfectividad(int)
	 */
	public MetaMesSie fechasEfectividad(int idMes) {
		List<MetaMesSie> lista = null;
		MetaMesSie objMetaMes = new MetaMesSie();
		try {
			Calendar cal =	DateUtil.getToday();
			Calendar cal2 =	DateUtil.getToday();
			int MesInicial =0, Mesfinal=0;
			
			MetaMesSie obj = findMetaMes(idMes);
			log.info(" FECHA FIN!  "+obj.getFechafin());
			cal2.setTime(DateUtil.convertStringToDate(obj.getFechafin()+"/"+cal2.get(Calendar.YEAR)));
			log.info(" mess! "+cal2.get(Calendar.MONTH));
			Mesfinal = cal2.get(Calendar.MONTH);
			if(Mesfinal==0)Mesfinal=12;
			log.info("messs  "+cal2.getTime());
			cal2.add(Calendar.MONTH, -6);
			log.info("messs  "+cal2.getTime());
			MesInicial =cal2.get(Calendar.MONTH)+1;
			log.info("mes inicial "+ MesInicial +"  mes final "+ Mesfinal);
			
			Query q = em.createQuery("select m from MetaMesSie m where m.codmes =" + Mesfinal );
			lista = q.getResultList();
			objMetaMes.setFechafin(lista.get(0).getFechafin()+"/"+cal.get(Calendar.YEAR));
			
			Query q2 = em.createQuery("select m from MetaMesSie m where  m.codmes = "+ MesInicial);
			lista = q2.getResultList();
			
			if(Mesfinal<=4){
				int anio =cal.get(Calendar.YEAR)-1;
				objMetaMes.setFechainicio(lista.get(0).getFechainicio()+"/"+anio);
			}else{
				objMetaMes.setFechainicio(lista.get(0).getFechainicio()+"/"+cal.get(Calendar.YEAR));
			}
			log.info("f inicial "+ objMetaMes.getFechainicio() +"  f final "+ objMetaMes.getFechafin());
			log.info("tamaño de fechasEfectividad--->" + lista.size());
			}catch (Exception e) {
				e.printStackTrace();
			}
			return objMetaMes;
		}
	}
