package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.EmpresaSie;
import com.edicsem.pe.sie.entity.EstadoGeneralSie;
import com.edicsem.pe.sie.entity.ProductoSie;
import com.edicsem.pe.sie.entity.PuntoVentaSie;
import com.edicsem.pe.sie.entity.TipoCasaSie;
import com.edicsem.pe.sie.model.dao.EmpresaDAO;
import com.edicsem.pe.sie.model.dao.TipoCasaDAO;
import com.edicsem.pe.sie.model.dao.ProductoDAO;


@Stateless
public class TipoCasaDAOImpl implements TipoCasaDAO {

	@PersistenceContext(name = "edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(TipoCasaDAOImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#insertEmpresa(com.edicsem.pe.sie.entity.EmpresaSie)
	 */
	 
	public void insertTipoCasa(TipoCasaSie tipocasa) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar el tipo de casa");
			}
			em.persist(tipocasa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public void updateTipoCasa(TipoCasaSie tipocasa) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Actualizar el tipo de casa");
			}
			em.merge(tipocasa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#findProducto(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpresaDAO#listarEmpresas()
	 */
   
	public void eliminarTipoCasa(int id) {
		// TODO Auto-generated method stub
		
	}

 
	public TipoCasaSie findTipoCasa(int id) {
		TipoCasaSie e= new TipoCasaSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar TipoCasa");
			} 
		e=	em.find(TipoCasaSie.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

 
	public List listarTipoCasa() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from TipoCasaSie p");
			lista =  q.getResultList(); 
		   System.out.println("tamaño lista TipoCasa  --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
 
	
}
