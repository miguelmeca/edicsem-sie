package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.SeguimientoContratoSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.model.dao.ContratoDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.MotivoDAO;
import com.edicsem.pe.sie.model.dao.SeguimientoContratoDAO;
import com.edicsem.pe.sie.service.facade.SeguimientoContratoService;
@Stateless
public class SeguimientoContratoServiceImpl implements SeguimientoContratoService {

	@EJB
	private  SeguimientoContratoDAO objSegContratoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private  ContratoDAO objContratoDao;
	@EJB
	private  CobranzaDAO objCobranzaDao;
	@EJB
	private  MotivoDAO objMotivoDao;
	
	private static Log log = LogFactory.getLog(SeguimientoContratoServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#insertSeguimientoContrato(com.edicsem.pe.sie.entity.SeguimientoContratoSie, int, com.edicsem.pe.sie.entity.ContratoSie, int, java.util.List)
	 */
	public void insertSeguimientoContrato(SeguimientoContratoSie s,int idMotivo, ContratoSie objContratoSie, int estadoRefinan, List<CobranzaSie> cobranzaList) {
		
		//Actualizar contrato
		objContratoSie.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(estadoRefinan));
		objContratoDao.updateContrato(objContratoSie);
		
		//buscar cobranza por contrato
		List<CobranzaSie> cobranzaantigua= objCobranzaDao.buscarCobranzaXcodigoContrato(objContratoSie.getIdcontrato());
		//Actualizar Cobranza
		for (int j = 0; j < cobranzaantigua.size(); j++) {
			for (int i = 0; i < cobranzaList.size(); i++) {
				if(cobranzaantigua.get(j).getNumletra()==cobranzaList.get(i).getNumletra() && 
					cobranzaantigua.get(j).getDiasretraso()!=cobranzaList.get(i).getDiasretraso()){
						//Actualizar Cobranza
						cobranzaantigua.get(j).setImpinicial(cobranzaList.get(i).getImpinicial());
						cobranzaantigua.get(j).setDiasretraso(cobranzaList.get(i).getDiasretraso());
						cobranzaantigua.get(j).setFecvencimiento(cobranzaList.get(i).getFecvencimiento());
						log.info("update "+cobranzaantigua.get(j).getNumletra()+" "+cobranzaantigua.get(j).getImpinicial());
						objCobranzaDao.updateCobranza(cobranzaantigua.get(j));
				}
				if(cobranzaList.get(j).getNuevo()!=null){
					if(cobranzaList.get(j).getNuevo().equalsIgnoreCase("N")){
					log.info("update "+cobranzaList.get(j).getNumletra()+" "+cobranzaList.get(j).getImpinicial());
					s.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(28));
					objCobranzaDao.insertCobranza(cobranzaList.get(j));
					}
				}
			}
		}
		
		//Insertar Seguimiento
		s.setTbContrato(objContratoSie);
		s.setTbMotivo(objMotivoDao.findMotivo(idMotivo));
		s.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(80));
		objSegContratoDao.insertSeguimientoContrato(s);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#updateSeguimientoContrato(com.edicsem.pe.sie.entity.SeguimientoContratoSie)
	 */
	public void updateSeguimientoContrato(SeguimientoContratoSie s) {
		objSegContratoDao.updateSeguimientoContrato(s);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#findSeguimientoContrato(int)
	 */
	public SeguimientoContratoSie findSeguimientoContrato(int id) {
		return objSegContratoDao.findSeguimientoContrato(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.SeguimientoContratoService#listarSeguimientoContrato(int)
	 */
	public List listarSeguimientoContrato(int codcontrato) {
		return objSegContratoDao.listarSeguimientoContrato(codcontrato);
	}
	
}
