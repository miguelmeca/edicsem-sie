package com.edicsem.pe.sie.model.dao.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;

@Stateless
public class EmpleadoSieDAOImpl implements EmpleadoSieDAO{
    @PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(EmpleadoSieDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#insertarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie)
	 */
	public void insertarEmpleado(EmpleadoSie empleado) {
		try {                         
			em.persist(empleado);
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#actualizarEmpleado(com.edicsem.pe.sie.entity.EmpleadoSie)
	 */
	public void actualizarEmpleado(EmpleadoSie empleado) {
		try {
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
			em.merge(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#eliminarEmpleado(int)
	 */
	public void eliminarEmpleado(int id) {
		try {
			em.remove(id);
			if (log.isInfoEnabled()) {
				log.info("apunto de insertar Empleado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#buscarEmpleado(int)
	 */
	public EmpleadoSie buscarEmpleado(int id) {
		EmpleadoSie empleado= new EmpleadoSie();
		try {
		if (log.isInfoEnabled()) {
		log.info("buscar empleado");
		} 
		empleado=	em.find(EmpleadoSie.class, id);
		log.info(" empleado " +empleado);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return empleado;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarEmpleados()
	 */
	public List listarEmpleados() {
		List lista = null;
		try {
			Query q = em.createQuery("select p from EmpleadoSie p where p.tbEstadoGeneral.idestadogeneral = "+3);
			lista = q.getResultList();
			log.info("tamaño lista Empleados --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarEmpleadosXCargo(int)
	 */
	public List listarEmpleadosXCargo(int idCargo) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from EmpleadoSie p inner join p.tbContratoEmpleados1 q  " +
					"inner join  q.tbCargoempleado r where r.idcargoempleado = "+idCargo);
			lista = q.getResultList();
			log.info("tamaño lista Empleados X Cargo --> " + lista.size()+" idcargo "+idCargo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarEmpleadoxEmpresas(int)
	 */
	public List listarEmpleadoxEmpresas(int parametroObtenido) {
		List lista = null;
		log.info("por entrar al QUERY  "+ parametroObtenido);
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e inner join e.tbContratoEmpleados1 f  " +
					"inner join f.tbEmpresa g  where g.idempresa = " + parametroObtenido);
			lista = q.getResultList();
			log.info("despues del QUERY tamaño lista Empleados X  parametroObtenido--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarDni()
	 */
	public List listarDni() {
		List lista = null;
		try {
			Query q = em.createQuery("SELECT e.numdocumento FROM EmpleadoSie e ");
			lista = q.getResultList();
			log.info("tamano --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarUsuario()
	 */
	public List listarUsuario() {
		List lista = null;
		try {
			Query q = em.createQuery("SELECT e.usuario FROM EmpleadoSie e ");
			lista = q.getResultList();
			log.info("tamano --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#buscarEmpleado(java.lang.String)
	 */
	public EmpleadoSie buscarEmpleado(String nombreCompleto) {
		List lista = null;
		EmpleadoSie obj = null;
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e " +
					"where  e.nombreemp||' '||e.apepatemp||' '||e.apematemp like '" +nombreCompleto+"'");
			lista = q.getResultList();
			if(lista.size()==1){
				obj= (EmpleadoSie) lista.get(0);
			}
			
			log.info("tamano --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#buscarEmpleadoPorNombreCompleto(java.lang.String)
	 */
	public EmpleadoSie buscarEmpleadoPorNombreCompleto(String nombreCompleto) {
		List lista = null;
		EmpleadoSie obj = null;
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e " +
					"where  UPPER(e.nombreemp)||' '||UPPER(e.apepatemp)||' '||UPPER(e.apematemp) like '" +nombreCompleto.toUpperCase().trim()+"'");
			lista = q.getResultList();
			if(lista.size()==1){
				obj= (EmpleadoSie) lista.get(0);
			}
			
			log.info("BUSQUEDA DE EMPLEADO en el DAOIMPLE --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#buscarEmpleadosPorUsuario(java.lang.String)
	 */
	public EmpleadoSie buscarEmpleadosPorUsuario(String usuario) {
		List lista = null;
		EmpleadoSie obj = null;
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e " +
					" where UPPER(e.usuario) like '" +usuario.toUpperCase().trim()+"'");
			lista = q.getResultList();
			if(lista.size()==1){
				 obj = (EmpleadoSie) lista.get(0);
			}
			
			log.info("BUSQUEDA DE EMPLEADO en el DAOIMPLE --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
