package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.MenuDTO;
import com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetPermisoEmpleadoDAOImpl implements DetPermisoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetPermisoEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO#insertDetPermisoEmpleado(com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie)
	 */
	public void insertDetPermisoEmpleado(DetPermisoEmpleadoSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetPermisoEmpleado");
			} 
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO#updateDetPermisoEmpleado(com.edicsem.pe.sie.entity.DetPermisoEmpleadoSie)
	 */
	public void updateDetPermisoEmpleado(DetPermisoEmpleadoSie p) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetPermisoEmpleado");
			}
			em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO#findDetPermisoEmpleado(int)
	 */
	public DetPermisoEmpleadoSie findDetPermisoEmpleado(int id) {
		DetPermisoEmpleadoSie p= new DetPermisoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetPermisoEmpleado"+ id);
			}
			p=	em.find(DetPermisoEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public DetPermisoEmpleadoSie findDetPermisoEmpleado(int idEmpleado,String nombrePermiso) {
		DetPermisoEmpleadoSie p= new DetPermisoEmpleadoSie();
		List<DetPermisoEmpleadoSie> lista ;
		try {
			Query q = em.createQuery("select p from DetPermisoEmpleadoSie p   where p.tbEmpleado.idempleado = "+ idEmpleado
					+ " and p.tbPermisos.nombrePermiso like '" +  nombrePermiso +"'" );
			 
				lista = q.getResultList();
				log.info(" tamano  "+lista.size());
				if(lista.size()>0){
					p = lista.get(0);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO#listarPermisoXUsuario(java.lang.String)
	 */
	public List<MenuDTO> listarPermisoXUsuario(String usuario) {
		List<DetPermisoEmpleadoSie>  lista = null;
		List<MenuDTO> miMenu = null;
		MenuDTO objMenu;
		log.info("  usuar "+usuario );
		try {
			Query q = em.createQuery("select p from DetPermisoEmpleadoSie p inner join  p.tbEmpleado q inner join p.tbPermisos pe where q.usuario = '"+ usuario+"' and " +
					"p.tbEstadoGeneral.idestadogeneral = "+62 +" and pe.tbEstadoGeneral.idestadogeneral = "+68);
			lista =  q.getResultList();
		   log.info("tamaño lista DetPermisoEmpleado DAOIMPL -->* " + lista.size()+"  ");
		   miMenu = new ArrayList<MenuDTO>();
		   
			for (DetPermisoEmpleadoSie p : lista) {
				log.info(" "+p.getTbPermisos().getTbModuloOpcion().getNombremodulo());
				objMenu = new MenuDTO();
				objMenu.setTipodeMenu(p.getTbPermisos().getTbModuloOpcion().getNombremodulo());
				objMenu.setNombreMenu(p.getTbPermisos().getNombrePermiso());
				objMenu.setUrlMenu(p.getTbPermisos().getDescripcion());
				if (p.getTbPermisos().getMetodoactionlistener()!=null &&
						p.getTbPermisos().getMetodoactionlistener().isEmpty() == false) {
					objMenu.setNombreActionListener(p.getTbPermisos().getMetodoactionlistener());
				}
				log.info("  ** "+objMenu.getNombreActionListener());
				miMenu.add(objMenu);
			}
			 log.info("tamaño lista  DAOIMPL --> " + miMenu.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return miMenu;
	}
	
}
