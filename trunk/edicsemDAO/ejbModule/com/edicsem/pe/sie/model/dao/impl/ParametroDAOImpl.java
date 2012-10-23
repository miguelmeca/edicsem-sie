package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ParametroSistemaSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.model.dao.ParametroDAO;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;

@Stateless
public class ParametroDAOImpl implements ParametroDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ParametroDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarParametro(ParametroSistemaSie parametro) {
		//em.getTransaction().begin();
		try {
                              
			em.persist(parametro);
			//em.flush();
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar parametro");
			}
			//em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarParametro(ParametroSistemaSie parametro) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar parametro");
			}
			em.merge(parametro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarParametro(int id) {
		try {
            //falta buscar
			em.remove(id);
			//em.flush();
			if (log.isInfoEnabled()) {
				log.info("apunto de eliminar parametro");
			}
			//em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public ParametroSistemaSie findParametro(int id) {
		// TODO Auto-generated method stub
		ParametroSistemaSie parametro= new ParametroSistemaSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar proveedor");
		} 
		parametro=	em.find(ParametroSistemaSie.class, id);
		log.info(" parametro " +parametro);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return parametro;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarParametros() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ParametroSistemaSie p where p.tbEstadoGeneral.idestadogeneral = "+29);
			lista = q.getResultList();
			System.out.println("tamaño lista parametro --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
