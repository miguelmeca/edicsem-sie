package com.edicsem.pe.sie.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ConfigCobranzaOperaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.CobranzaOperaDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.impl.CobranzaDAOImpl;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;

@Stateless
public class CobranzaOperaServiceImpl implements CobranzaOperaService {
	
	private static Log log = LogFactory.getLog(CobranzaDAOImpl.class);
	
	@EJB
	private CobranzaDAO objCobranzaDao;
	@EJB
	private CobranzaOperaDAO objCobranzaOperaDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaOperaService#insertCobranzaOpera(com.edicsem.pe.sie.entity.CobranzaOperadoraSie, java.util.List)
	 */
	public void insertCobranzaOpera(CobranzaOperadoraSie cobranzaopera, List<ConfigCobranzaOperaSie> configList) {
		cobranzaopera.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(108));
		Calendar hoy = new GregorianCalendar();
		
		for (int i = 0; i < configList.size(); i++) {
			if(configList.get(i).getTbTipoCliente().getIdtipocliente()==cobranzaopera.getTbCobranza().getTbCliente().getTbTipoCliente().getIdtipocliente()){
				hoy = new GregorianCalendar();
				hoy.add(Calendar.DAY_OF_MONTH, configList.get(i).getDiasProgramados());
				cobranzaopera.setFechaexpira(hoy.getTime());
				objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaOperaService#insertCobranzaOpera(java.util.List, java.util.List)
	 */
	public List<CobranzaSie> insertCobranzaOpera(List<String> empleadoList,List<ConfigCobranzaOperaSie> configList) {
		List<CobranzaSie> cobranzaSinAsignarTmp = new ArrayList<CobranzaSie>();
		List<CobranzaSie> cobranzaList  = objCobranzaDao.listarCobranzas();
		Calendar hoy = new GregorianCalendar();
		boolean isvalidate=true;
		List<CobranzaSie> puntualtmp = new ArrayList<CobranzaSie>(),regulartmp= new ArrayList<CobranzaSie>(),morosotmp= new ArrayList<CobranzaSie>(), extremotmp= new ArrayList<CobranzaSie>();
		List<Integer> arrTipoCliente= new ArrayList<Integer>();
		//Buscar s�lo los tipo de clientes admitidos para �sta fecha
		for (int i = 0; i < configList.size(); i++) {
			if(!arrTipoCliente.contains(configList.get(i).getTbTipoCliente().getIdtipocliente()))
			arrTipoCliente.add(configList.get(i).getTbTipoCliente().getIdtipocliente());
		}
		log.info("Arreglo de Tipo De Cliente "+arrTipoCliente+" tam "+empleadoList.size() );
		/** L�gica para dividir la lista de deudores divididas por tipo de cliente
		 *  entre la cantidad de teleoperadoras */
		//Listas nuevas: lunes, mi�rcoles y viernes
		//Extremos lo trabajamos 15 d�as porque se refinancian(quincena, fin de mes)
		//moroso una vez cada semana
		List<Integer> listaContrato= new ArrayList<Integer>();
		for (int i = 0; i < cobranzaList.size(); i++) {
			if(arrTipoCliente.contains(1) && cobranzaList.get(i).getTbCliente().getTbTipoCliente().getIdtipocliente()==1){
				// Puntual
				if(puntualtmp.size()>0){
					for (int j = 0; j < puntualtmp.size(); j++) {
						isvalidate=false;
						if(listaContrato.contains(cobranzaList.get(i).getIdcontrato())){
							isvalidate=false;
						}else{
							isvalidate=true;
						}
						 if(isvalidate){
							listaContrato.add(cobranzaList.get(i).getIdcontrato());
							puntualtmp.add(cobranzaList.get(i));
						}
					}
				}else {
					listaContrato.add(cobranzaList.get(i).getIdcontrato());
					puntualtmp.add(cobranzaList.get(i));
				}
			}else if(arrTipoCliente.contains(2) && cobranzaList.get(i).getTbCliente().getTbTipoCliente().getIdtipocliente()==2){
				// Regular
				if(regulartmp.size()>0){
					for (int j = 0; j < regulartmp.size(); j++) {
						isvalidate=false;
						if(listaContrato.contains(cobranzaList.get(i).getIdcontrato())){
							isvalidate=false;
						}else{
							isvalidate=true;
						}
						 if(isvalidate){
							listaContrato.add(cobranzaList.get(i).getIdcontrato());
							regulartmp.add(cobranzaList.get(i));
						}
					}
				}else {
					listaContrato.add(cobranzaList.get(i).getIdcontrato());
					regulartmp.add(cobranzaList.get(i));
				}
			}else if(arrTipoCliente.contains(3) && cobranzaList.get(i).getTbCliente().getTbTipoCliente().getIdtipocliente()==3){
				// Moroso
				if(morosotmp.size()>0){
					for (int j = 0; j < morosotmp.size(); j++) {
						isvalidate=false;
						if(listaContrato.contains(cobranzaList.get(i).getIdcontrato())){
							isvalidate=false;
						}else{
							isvalidate=true;
						}
						 if(isvalidate){
							listaContrato.add(cobranzaList.get(i).getIdcontrato());
							morosotmp.add(cobranzaList.get(i));
						}
					}
				}else {
					listaContrato.add(cobranzaList.get(i).getIdcontrato());
					morosotmp.add(cobranzaList.get(i));
				}
			}else if(arrTipoCliente.contains(4) && cobranzaList.get(i).getTbCliente().getTbTipoCliente().getIdtipocliente()==4){
				// Extremo
				if(extremotmp.size()>0){
					for (int j = 0; j < extremotmp.size(); j++) {
						isvalidate=false;
						if(listaContrato.contains(cobranzaList.get(i).getIdcontrato())){
							isvalidate=false;
						}else{
							isvalidate=true;
						}
						 if(isvalidate){
							listaContrato.add(cobranzaList.get(i).getIdcontrato());
							extremotmp.add(cobranzaList.get(i));
						}
					}
				}else {
					listaContrato.add(cobranzaList.get(i).getIdcontrato());
					extremotmp.add(cobranzaList.get(i));
				}
			}
		}
		log.info("tamano " +  puntualtmp.size()+" - "+regulartmp.size()+" - "+morosotmp.size()+" - "+extremotmp.size());
		BigDecimal p = new BigDecimal(puntualtmp.size()/empleadoList.size());
		p.setScale(0);
		BigDecimal r = new BigDecimal(regulartmp.size()/empleadoList.size());
		r.setScale(0);
		BigDecimal m = new BigDecimal(morosotmp.size()/empleadoList.size());
		m.setScale(0);
		BigDecimal x = new BigDecimal(extremotmp.size()/empleadoList.size());
		x.setScale(0);
		log.info(" cant contratos puntual " +  p);
		log.info(" cant contratos regular " +  r);
		log.info(" cant contratos moroso " +  m);
		log.info(" cant contratos xtremo " +  x);
		
		int valor=0;
		int valori=0;
		for (int i = 0; i < configList.size(); i++) {
			hoy = new GregorianCalendar();
			hoy.add(Calendar.DAY_OF_MONTH, configList.get(i).getDiasProgramados());
			if(configList.get(i).getTbTipoCliente().getIdtipocliente()==1){
				for (int j = 0; j < empleadoList.size(); j++) {
					valor=0;
					for (int k = valori; k< puntualtmp.size(); k++) {
						
						if(valor<p.intValue()){
							CobranzaOperadoraSie cobranzaopera= new CobranzaOperadoraSie();
							CobranzaSie cobranza = objCobranzaDao.findCobranza(puntualtmp.get(k).getIdcobranza());
							cobranzaopera.setTbEmpleado(objEmpleadoDao.buscarEmpleado(empleadoList.get(j)));
							cobranzaopera.setTbCobranza(cobranza);
							//hoy + los d�as programados
							cobranzaopera.setFechaexpira(hoy.getTime());
							cobranzaopera.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(108));
							objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
							valor=valor + 1;
						}
						if(valor>=p.intValue()){
							valori=k+1;
							break;
						}
					}
				}
				for (int k = valori; k< puntualtmp.size(); k++) {
					cobranzaSinAsignarTmp.add(puntualtmp.get(k));
				}
				if(p.intValue()==0)
					for (int n = 0; n< puntualtmp.size(); n++) {
						cobranzaSinAsignarTmp.add(puntualtmp.get(n));
					}
			}
		}
		valori=0;
		for (int i = 0; i < configList.size(); i++) {
			hoy = new GregorianCalendar();
			hoy.add(Calendar.DAY_OF_MONTH, configList.get(i).getDiasProgramados());
			if(configList.get(i).getTbTipoCliente().getIdtipocliente()==2){
			for (int j = 0; j < empleadoList.size(); j++) {
				valor=0;
				for (int k = valori; k < regulartmp.size(); k++) {
					if(valor<r.intValue()){
						CobranzaOperadoraSie cobranzaopera= new CobranzaOperadoraSie();
						CobranzaSie cobranza = objCobranzaDao.findCobranza(regulartmp.get(k).getIdcobranza());
						cobranzaopera.setTbEmpleado(objEmpleadoDao.buscarEmpleado(empleadoList.get(j)));
						cobranzaopera.setTbCobranza(cobranza);
						cobranzaopera.setFechaexpira(hoy.getTime());
						cobranzaopera.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(108));
						objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
						valor=valor + 1;
					}
					if(valor>=r.intValue()){
					valori=k+1;
					break;
					}
				}
			}
			for (int k = valori; k< regulartmp.size(); k++) {
				cobranzaSinAsignarTmp.add(regulartmp.get(k));
			}
			//Los que restan, ser�n asignados 
			if(r.intValue()==0)
				for (int n = 0; n< regulartmp.size(); n++) {
					cobranzaSinAsignarTmp.add(regulartmp.get(n));
				}
			}
		}
		valori=0;
		for (int i = 0; i < configList.size(); i++) {
			hoy = new GregorianCalendar();
			hoy.add(Calendar.DAY_OF_MONTH, configList.get(i).getDiasProgramados());
			if(configList.get(i).getTbTipoCliente().getIdtipocliente()==3){
				for (int j = 0; j < empleadoList.size(); j++) {
					valor=0;
					for (int k = valori; k < morosotmp.size(); k++) {
						
						if(valor<m.intValue()){
							CobranzaOperadoraSie cobranzaopera= new CobranzaOperadoraSie();
							CobranzaSie cobranza = objCobranzaDao.findCobranza(morosotmp.get(k).getIdcobranza());
							cobranzaopera.setTbEmpleado(objEmpleadoDao.buscarEmpleado(empleadoList.get(j)));
							cobranzaopera.setTbCobranza(cobranza);
							cobranzaopera.setFechaexpira(hoy.getTime());
							cobranzaopera.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(108));
							objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
							valor=valor + 1;
						}if(valor>=m.intValue()){
						valori=k+1;
						break;
						}
					}
				}
				for (int k = valori; k< morosotmp.size(); k++) {
					cobranzaSinAsignarTmp.add(morosotmp.get(k));
				}
				if(m.intValue()==0)
					for (int n = 0; n< morosotmp.size(); n++) {
						cobranzaSinAsignarTmp.add(morosotmp.get(n));
					}
			}
		}
		valori=0;
		for (int i = 0; i < configList.size(); i++) {
			hoy = new GregorianCalendar();
			hoy.add(Calendar.DAY_OF_MONTH, configList.get(i).getDiasProgramados());
			if(configList.get(i).getTbTipoCliente().getIdtipocliente()==4){
				for (int j = 0; j < empleadoList.size(); j++) {
					valor=0;
					for (int k = valori; k < extremotmp.size(); k++) {
						
						if(valor<x.intValue()){
							CobranzaOperadoraSie cobranzaopera= new CobranzaOperadoraSie();
							CobranzaSie cobranza = objCobranzaDao.findCobranza(extremotmp.get(k).getIdcobranza());
							cobranzaopera.setTbEmpleado(objEmpleadoDao.buscarEmpleado(empleadoList.get(j)));
							cobranzaopera.setFechaexpira(hoy.getTime());
							cobranzaopera.setTbCobranza(cobranza);
							cobranzaopera.setTbEstadoGeneral(objEstadoDao.findEstadoGeneral(108));
							objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
							valor=valor + 1;
						}if(valor>=x.intValue()){
						valori=k+1;
						break;
						}
					}
				}
				for (int n = valori; n< extremotmp.size(); n++) {
					cobranzaSinAsignarTmp.add(extremotmp.get(n));
				}
				if(x.intValue()==0)
					for (int n = 0; n< extremotmp.size(); n++) {
						cobranzaSinAsignarTmp.add(extremotmp.get(n));
					}
			}
		}
		log.info(" -->>> "+cobranzaSinAsignarTmp.size());
		return cobranzaSinAsignarTmp;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranzaOpera(CobranzaOperadoraSie cobranzaopera) {
		objCobranzaOperaDao.updateCobranzaOpera(cobranzaopera);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#findCobranza(int)
	 */
	public CobranzaOperadoraSie findCobranzaOpera(int id) {
		return objCobranzaOperaDao.findCobranzaOpera(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaOperaService#listarCobranzasOpera(java.lang.String)
	 */
	public List listarCobranzasOpera(String usuario) {
		return objCobranzaOperaDao.listarCobranzasOpera(usuario);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaOperaService#verificargeneracionDiaria()
	 */
	public int verificargeneracionDiaria() {
		return objCobranzaOperaDao.verificargeneracionDiaria();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaOperaService#listarCobranzasOperaPagada(java.lang.String)
	 */
	public List listarCobranzasOperaPagada(String usuario) {
		return objCobranzaOperaDao.listarCobranzasOperaPagada(usuario);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaOperaService#listarCobranzasOperaFechaActual(java.util.Date)
	 */
	public List listarCobranzasOperaFechaActual(Date dhoy) {
		return objCobranzaOperaDao.listarCobranzasOperaFechaActual(dhoy);
	}
}
