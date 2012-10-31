package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetCargoEmpleadoSie;
import com.edicsem.pe.sie.entity.ProveedorSie;
import com.edicsem.pe.sie.entity.TelefonoPersonaSie;
import com.edicsem.pe.sie.entity.TipoDocumentoIdentidadSie;
import com.edicsem.pe.sie.model.dao.DetalleCarEmpDAO;

@Stateless
public class DetalleCarEmpDAOImpl implements DetalleCarEmpDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetalleCarEmpDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#insertDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void insertarDetalleCarEmp(DetCargoEmpleadoSie detallecaremp) {
		//em.getTransaction().begin();
		try {
                              
			em.persist(detallecaremp);
			//em.flush();
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar detallecaremp");
			}
			//em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#updateDemo(com.edicsem.pe.sie.entity.Usuario)
	 */
	public void actualizarDetalleCarEmp(DetCargoEmpleadoSie detallecaremp) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar detallecaremp");
			}
			em.merge(detallecaremp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#deleteDemo(java.lang.String)
	 */
	public void eliminarDetalleCarEmp(int id) {
		try {
            //falta buscar
			em.remove(id);
			//em.flush();
			if (log.isInfoEnabled()) {
				log.info("apunto de eliminar detallecaremp");
			}
			//em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#findDemo(java.lang.String)
	 */
	public DetCargoEmpleadoSie findDetalleCarEmp(int id) {
		// TODO Auto-generated method stub
		DetCargoEmpleadoSie detallecaremp= new DetCargoEmpleadoSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar detallecaremp");
		} 
		detallecaremp=	em.find(DetCargoEmpleadoSie.class, id);
		log.info(" detallecaremp " +detallecaremp);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return detallecaremp;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DemoDAO#listarUsuarios(com.edicsem.pe.sie.entity.Usuario)
	 */
	public List listarDetalleCarEmp() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from DetCargoEmpleadoSie");
			lista = q.getResultList();
			System.out.println("tama�o lista detalleCargo --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List listarxCargo(int cargo) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from DetCargoEmpleadoSie p where p.tbCargoEmpleado.idcargoempleado = "+cargo);
			lista = q.getResultList();
			System.out.println("tama�o lista detalleCargo --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
