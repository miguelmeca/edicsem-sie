package com.edicsem.pe.sie.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.ContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.DetContratoEmpleadoSie;
import com.edicsem.pe.sie.entity.MetaEmpleadoSie;
import com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.DetContratoEmpleadoDAO;
import com.edicsem.pe.sie.model.dao.MetaEmpleadoDAO;

/**
 * @author karen
 *
 */
@Stateless
public class ContratoEmpleadoDAOImpl implements ContratoEmpleadoDAO{

	@PersistenceContext(name="edicsemJPASie")
	private EntityManager em;
	private static Log log = LogFactory.getLog(ContratoEmpleadoDAOImpl.class);
	@EJB
	private DetContratoEmpleadoDAO objDetContratoEmpleadoDao;
	@EJB 
	private MetaEmpleadoDAO objMetaEmpleadoDao;
	@EJB 
	private ContratoEmpleadoDAO objContratoEmpleadoDao;
 
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#insertContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	@Override
	public void insertContratoEmpleado(ContratoEmpleadoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("insertar contrato empleado");
			} 
			em.persist(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#updateContratoEmpleado(com.edicsem.pe.sie.entity.ContratoEmpleadoSie)
	 */
	public void updateContratoEmpleado(ContratoEmpleadoSie contrato) {
		try {
			if (log.isInfoEnabled()) {
				log.info("modificar contrato empleado");
			} 
			em.merge(contrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#findContratoEmpleado(int)
	 */
	public ContratoEmpleadoSie findContratoEmpleado(int id) {
		ContratoEmpleadoSie contrato= new ContratoEmpleadoSie();
		try {
			if (log.isInfoEnabled()) {
				log.info("buscar ContratoEmpleado"+id);
			} 
		contrato=	em.find(ContratoEmpleadoSie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contrato;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarContrato()
	 */
	public List listarContrato() {
		List  lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p ");
			lista =  q.getResultList(); 
			log.info("tamaño lista Contrato --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarPatrocinados(int, int)
	 */
	public List listarPatrocinados(int idEmpleado,String fechaInicio,String fechaFin ) {
		log.info("listarPatrocinados "+idEmpleado);
		List<ContratoEmpleadoSie> lista = null;
		List<DetContratoEmpleadoSie> listaDetContrato= new ArrayList<DetContratoEmpleadoSie>();
		ContratoEmpleadoSie c ;
		int valormeta=0;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p where p.tbEmpleado2.idempleado = "+  idEmpleado);
			lista =  q.getResultList();
			
			log.info(" aki  -----  ");
			
			//si las ventas llegan a la meta esperada, se cuenta sus contratos pagados (facturados), el expositor podrá cobrar las comisiones por sus patrocinados
			MetaEmpleadoSie metaEmpl = objMetaEmpleadoDao.findMetaEmpleado(idEmpleado);
			
			//cantidad de  ventas del empleado , dependiendo de cargo
			c = (ContratoEmpleadoSie) objContratoEmpleadoDao.listarCargoXEmp(idEmpleado).get(0);
			listaDetContrato = objDetContratoEmpleadoDao.listarContratoXEmpleado(idEmpleado, fechaInicio, fechaFin, c.getTbCargoempleado().getIdcargoempleado() );
			if(listaDetContrato==null){
				listaDetContrato = new ArrayList<DetContratoEmpleadoSie>();
			}
			if(metaEmpl!=null){
				valormeta = metaEmpl.getValormeta();
			}
			//SOLO SI SUPERA LA META SE LE PAGA POR SUS PATROCINADOS
			if(valormeta > listaDetContrato.size()){
				
				for (int i = 0; i < lista.size(); i++) {
					//averiguar cantidad de contratos por cada patrociando (contratos en los que el patrocinado es expositor = 1)
					List<DetContratoEmpleadoSie> lista2 = objDetContratoEmpleadoDao.listarContratoXEmpleado(lista.get(i).getIdempleado(), fechaInicio, fechaFin, 1 );
					
					lista.get(i).setCantContratoXPatrocinado(lista2.size());
				}
				for (int i = 0; i < lista.size(); i++) {
					log.info("--> in DAO   "+lista.get(i).getTbEmpleado1().getNombresCompletos()+" "+  lista.get(i).getCantContratoXPatrocinado());
				}
			}
			log.info("tamaño listarPatrocinados--> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#verificarEmpleadoConCargo(int)
	 */
	public boolean verificarEmpleadoConCargo(int idcargo) {
		boolean bandera = true;
		List lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p where p.tbCargoempleado.idcargoempleado = "+ idcargo);
			lista = q.getResultList();											
			log.info("tamaño lista detalleCargoJK --> " + lista.size());
			if(lista.size()>0){ //hay uno o mas empleados retornados.
				bandera=false;
			}else{//no hay empleados, entonces puede proseguir
				bandera=true;
			}
			
		} catch (Exception e) {
			bandera=false;
			e.printStackTrace();
		}
		return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarxCargo(int)
	 */
	public List listarxCargo(int cargo) {
		log.info("cargo--> " +cargo);
		List lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p where p.tbCargoempleado.idcargoempleado = "+cargo);
			lista = q.getResultList();
			log.info("tamaño lista x Cargo --> " + lista.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarCargoXEmp(int)
	 */
	public List listarCargoXEmp(int idEmpleado) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p where p.tbEmpleado1.idempleado = "+ idEmpleado);
			lista = q.getResultList();
			log.info("tamaño lista listar Cargo X Emp --> " + lista.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.model.dao.ContratoEmpleadoDAO#listarxCargoxgrupo(int, int)
	 */
	public List listarxCargoxgrupo(int cargoEmpleado, int idGrupo) {
		List lista = null;
		try {
			Query q = em.createQuery("select p from ContratoEmpleadoSie p  inner join p.tbEmpleado1.tbDetGrupoEmpleado d " +
					"   where p.tbCargoempleado.idcargoempleado  = "+cargoEmpleado +"  and  d.tbGrupoVenta.idgrupo = "+idGrupo );
			lista = q.getResultList();
			log.info("tamaño lista listarx Cargox grupo --> " + lista.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
