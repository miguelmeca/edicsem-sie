package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class DetContratoEmpleadoDAOImpl implements DetContratoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(DetContratoEmpleadoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#insertDetContratoEmpleado(com.edicsem.pe.sie.entity.DetContratoEmpleadoSie)
	 */
	public void insertDetContratoEmpleado(DetContratoEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar DetContratoEmpleadoSie");
			} 
			em.persist(d);
		} catch (Exception e) {
			log.info(" msg  -->  " + e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#updateDetContratoEmpleado(com.edicsem.pe.sie.entity.DetContratoEmpleadoSie)
	 */
	public void updateDetContratoEmpleado(DetContratoEmpleadoSie d) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar DetContratoEmpleadoSie");
			} 
			em.merge(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#findDetContratoEmpleado(int)
	 */
	public DetContratoEmpleadoSie findDetContratoEmpleado(int id) {
		DetContratoEmpleadoSie d= new DetContratoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar DetContratoEmpleadoSie");
			}
		d=	em.find(DetContratoEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#listarDetContratoEmpleado()
	 */
	public List listarDetContratoEmpleado() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from DetContratoEmpleadoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista DetContratoEmpleadoSie --> " + lista.size()+"  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO#listarContratoXEmpleado(int, java.lang.String, java.lang.String, int)
	 */
	public List listarContratoXEmpleado(int idempleado,String fechaInicio,String fechaFin, int idCargoContrato) {
		List<DetContratoEmpleadoSie>  lista = null;
		List<DetContratoEmpleadoSie>  lista2 = new ArrayList<DetContratoEmpleadoSie>();
		DetContratoEmpleadoSie objExpositor = new DetContratoEmpleadoSie();
		DetContratoEmpleadoSie objVendedor = new DetContratoEmpleadoSie();
		DetContratoEmpleadoSie objColaborador = new DetContratoEmpleadoSie();
		
		try {
			String query= "select p from DetContratoEmpleadoSie p where p.tbEmpleado.idempleado = "+ idempleado
					+" and DATE(p.tbContrato.fechaentrega) between DATE('" + fechaInicio + "') and  DATE('" + fechaFin +"') ";
					if(idCargoContrato!=0){
						query=query + " and p.idCargoContrato = "+ idCargoContrato;
					}
			Query q = em.createQuery(query);
			lista =  q.getResultList();
			
			log.info("tamaño lista Contrato X Empleado  --> " + lista.size());
			int cantidad =0, cantidad2=0, cantidad3=0;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if(lista.get(i).getIdCargoContrato()==1 ){
					//expositor
					cantidad+=1;
					objExpositor=lista.get(i);
					objExpositor.setCantContratosXCargo(cantidad);
					log.info(" objExpositor "+objExpositor.getCantContratosXCargo());
				}
				else if(lista.get(i).getIdCargoContrato()==2 ){
					//vendedor
					cantidad2+=1;
					objVendedor=lista.get(i);
					objVendedor.setCantContratosXCargo(cantidad2);
					log.info(" objVendedor "+objVendedor.getCantContratosXCargo());
				}
				else if(lista.get(i).getIdCargoContrato()==3 ){
					//colaborador
					cantidad3+=1;
					objColaborador=lista.get(i);
					objColaborador.setCantContratosXCargo(cantidad3);
					log.info(" objColaborador "+objColaborador.getCantContratosXCargo());
				}
			}
			
			if(objExpositor.getCantContratosXCargo()!=null)lista2.add(objExpositor);
			if(objVendedor.getCantContratosXCargo()!=null)lista2.add(objVendedor);
			if(objColaborador.getCantContratosXCargo()!=null)lista2.add(objColaborador);
			log.info("tamaño lista Contrato X Empleado --> " + lista2.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista2;
	}
}
