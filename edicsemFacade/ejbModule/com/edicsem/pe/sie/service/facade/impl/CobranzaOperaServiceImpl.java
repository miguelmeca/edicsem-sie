package com.edicsem.pe.sie.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CobranzaOperadoraSie;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.CobranzaOperaDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.impl.CobranzaDAOImpl;
import com.edicsem.pe.sie.service.facade.CobranzaOperaService;

@Stateless
public class CobranzaOperaServiceImpl implements CobranzaOperaService {
	
	private static Log log = LogFactory.getLog(CobranzaDAOImpl.class);
	
	@EJB
	private CobranzaDAO objCobranzaDao;
	@EJB
	private  CobranzaOperaDAO objCobranzaOperaDao;
	@EJB
	private  EmpleadoSieDAO objEmpleadoDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranzaOpera(List<String> empleadoList) {
		
		List<CobranzaSie> cobranzaList  = objCobranzaDao.listarCobranzas();
		for (int i = 0; i < cobranzaList.size(); i++) {
			log.info(""+cobranzaList.get(i).getTbCliente().getNombresCompletos()+" "+cobranzaList.get(i).getFechaVencimientoString());
		}
		
		List<CobranzaSie> puntualtmp = new ArrayList<CobranzaSie>(),regulartmp= new ArrayList<CobranzaSie>(),morosotmp= new ArrayList<CobranzaSie>(), extremotmp= new ArrayList<CobranzaSie>();
		
			/** Lógica para dividir la lista de deudores divididas por tipo de cliente
			 *  entre la cantidad de teleoperadoras */
		
		for (int i = 0; i < cobranzaList.size(); i++) {
			
			if(cobranzaList.get(i).getTbCliente().getTipocliente()==1){
				// Puntual
				if(!(puntualtmp.contains(cobranzaList.get(i)))){
					log.info("se agrega  "+cobranzaList.get(i).getIdcobranza()+"   "+puntualtmp.contains(cobranzaList.get(i).getIdcobranza()));
					puntualtmp.add(cobranzaList.get(i));
				}
				
			}else if(cobranzaList.get(i).getTbCliente().getTipocliente()==2){
				// Regular
				if(!(regulartmp.contains(cobranzaList.get(i)))){
				regulartmp.add(cobranzaList.get(i));}
			}else if(cobranzaList.get(i).getTbCliente().getTipocliente()==3){
				// Moroso
				if(!(morosotmp.contains(cobranzaList.get(i)))){
				morosotmp.add(cobranzaList.get(i));}
			}else if(cobranzaList.get(i).getTbCliente().getTipocliente()==4){
				// Extremo
				if(!extremotmp.contains(cobranzaList.get(i))){
				extremotmp.add(cobranzaList.get(i));}
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
		log.info(" cant contratos mororso " +  m);
		log.info(" cant contratos xtremo " +  x);
		
		for (int j = 0; j < empleadoList.size(); j++) {
			log.info(" -- empleado -- "+ empleadoList.get(j));
			for (int i = 0; i < puntualtmp.size(); i++) {
				if((i+1)<=p.intValue()){
					log.info(" -- puntuales -- "+ puntualtmp.get(i));
					CobranzaOperadoraSie cobranzaopera= new CobranzaOperadoraSie();
					CobranzaSie cobranza = objCobranzaDao.findCobranza(puntualtmp.get(i).getIdcobranza());
					cobranzaopera.setTbEmpleado(objEmpleadoDao.buscarEmpleado(Integer.parseInt(empleadoList.get(j))));
					cobranzaopera.setTbCobranza(cobranza);
					log.info("--> " +cobranzaopera.getTbEmpleado().getNombresCompletos()+", "+cobranzaopera.getTbCobranza().getTbCliente().getIdcliente());
					objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
				}
			}
		}
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
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#listarCobranzas()
	 */
	public List listarCobranzasOpera() {
		return objCobranzaOperaDao.listarCobranzasOpera();
	}
}
