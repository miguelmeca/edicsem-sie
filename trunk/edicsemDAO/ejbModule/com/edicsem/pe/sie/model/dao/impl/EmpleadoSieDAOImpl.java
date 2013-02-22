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
			log.info("tama�o lista Empleados --> " + lista.size());
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
			log.info("tama�o lista Empleados X Cargo --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	public List listarEmpleadoxEmpresas(int parametroObtenido) {
		List lista = null;
		log.info("por entrar al QUERY  "+ parametroObtenido);
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e inner join e.tbDetEmpresaEmpleados f  " +
					"inner join f.tbEmpresa g  where g.idempresa = " + parametroObtenido);
			lista = q.getResultList();
			log.info("despues del QUERY tama�o lista Empleados X  parametroObtenido--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	public List listarEmpleadoxCargo(int parametroObtenido) {
		List lista = null;
		log.info("por entrar al QUERY listarEmpleadoxCargo "+ parametroObtenido);
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e inner join e.tbContratoEmpleados1 f  " +
					"inner join f.tbCargoempleado g  where g.idcargoempleado = " + parametroObtenido);
			lista = q.getResultList();
			log.info("despues del QUERY tama�o lista cargo X  parametroObtenido--> " + lista.size());
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.EmpleadoSieDAO#listarExpositor(int)
	 */
	public List listarExpositor(int idEmpresa) {
		List lista = null;
		log.info("listarExpositor "+ idEmpresa);
		try {
			Query q = em.createQuery("SELECT e FROM EmpleadoSie e inner join e.tbDetEmpresaEmpleados f  " +
					" inner join f.tbEmpresa g inner join e.tbContratoEmpleados1 h inner join h.tbCargoempleado i " +
					" where  i.idcargoempleado = 3 and g.idempresa = " + idEmpresa  );
			lista = q.getResultList();
			log.info("tamano --> " + lista.size());
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
}
