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
		
		
		List<Integer> puntualtmp = new ArrayList<Integer>(),regulartmp= new ArrayList<Integer>(),morosotmp= new ArrayList<Integer>(), extremotmp= new ArrayList<Integer>();
		
			/** Lógica para dividir la lista de deudores divididas por tipo de cliente
			 *  entre la cantidad de teleoperadoras */
		
		for (int i = 0; i < cobranzaList.size(); i++) {
			
			if(cobranzaList.get(i).getTbCliente().getTipocliente()==1){
				// Puntual
				puntualtmp.add(cobranzaList.get(i).getIdcontrato());
			}else if(cobranzaList.get(i).getTbCliente().getTipocliente()==2){
				// Regular
				regulartmp.add(cobranzaList.get(i).getIdcontrato());
			}else if(cobranzaList.get(i).getTbCliente().getTipocliente()==3){
				// Moroso
				morosotmp.add(cobranzaList.get(i).getIdcontrato());
			}else{
				// Extremo
				extremotmp.add(cobranzaList.get(i).getIdcontrato());
			}
		}
		
		log.info("tamano " +  puntualtmp.size()+"  "+empleadoList.size());
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
		
		for (int i = 0; i < empleadoList.size(); i++) {
			CobranzaOperadoraSie cobranzaopera= new CobranzaOperadoraSie();
			CobranzaSie cobranza = new CobranzaSie();
			cobranzaopera.setTbEmpleado(objEmpleadoDao.buscarEmpleado(Integer.parseInt(empleadoList.get(i))));
			
			cobranzaopera.setTbCobranza(cobranza);
			objCobranzaOperaDao.insertCobranzaOpera(cobranzaopera);
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
