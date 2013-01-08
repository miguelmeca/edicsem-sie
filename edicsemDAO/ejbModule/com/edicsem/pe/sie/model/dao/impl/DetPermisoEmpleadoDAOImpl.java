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

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetPermisoEmpleadoDAO#listarPermisoXUsuario(java.lang.String)
	 */
	public List<MenuDTO> listarPermisoXUsuario(String usuario) {
		List<DetPermisoEmpleadoSie>  lista = null;
		List<MenuDTO> miMenu = null;
		MenuDTO objMenu;
		log.info("  usuar "+usuario );
		try {
			Query q = em.createQuery("select p from DetPermisoEmpleadoSie p inner join  p.tbEmpleado q where q.usuario = '"+ usuario+"'");
			lista =  q.getResultList(); 						
		   log.info("tamaño lista DetPermisoEmpleado DAOIMPL --> " + lista.size()+"  ");
		   miMenu = new ArrayList<MenuDTO>();
		   
			for (DetPermisoEmpleadoSie p : lista) {
				objMenu = new MenuDTO();
				objMenu.setTipodeMenu(p.getTbPermisos().getTbModuloOpcion().getTbTipoModulo().getDescripcion());
				objMenu.setNombreMenu(p.getTbPermisos().getTbModuloOpcion().getNombremodulo());
				objMenu.setUrlMenu(p.getTbPermisos().getTbModuloOpcion().getDescripcion());
				if (p.getTbPermisos().getMetodoactionlistener()!=null &&
						p.getTbPermisos().getMetodoactionlistener().isEmpty() == false) {
					objMenu.setNombreActionListener(p.getTbPermisos().getMetodoactionlistener());
				}
				miMenu.add(objMenu);
			}
			 log.info("tamaño lista  DAOIMPL --> " + miMenu.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return miMenu;
	}
	
}
