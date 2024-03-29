package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.model.dao.ProveedorDAO;

@Stateless
public class ProveedorDAOImpl implements ProveedorDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ProveedorDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarProveedor(ProveedorSie proveedor) {
		try {                           
			em.persist(proveedor);		
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar proveedor");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarProveedor(ProveedorSie proveedor) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar proveedor");
			}
			em.merge(proveedor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarProveedor(int id) {
		try {
			em.remove(id);
			if (log.isInfoEnabled()) {
				log.info("apunto de eliminar Proveedor");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public ProveedorSie findProveedor(int id) {
		ProveedorSie proveedor= new ProveedorSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar proveedor");
		} 
		proveedor=	em.find(ProveedorSie.class, id);
		log.info(" proveedor " +proveedor);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return proveedor;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarProveedores() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ProveedorSie p where p.tbEstadoGeneral.idestadogeneral = "+9);
			lista = q.getResultList();
			log.info("tama�o lista proveedor --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
