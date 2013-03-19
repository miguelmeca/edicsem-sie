package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edicsem.pe.sie.beans.RecaudacionDTO;
import com.edicsem.pe.sie.entity.CobranzaSie;
import com.edicsem.pe.sie.model.dao.CobranzaDAO;
import com.edicsem.pe.sie.service.facade.CobranzaService;

@Stateless
public class CobranzaServiceImpl implements CobranzaService {
	public static Log log = LogFactory.getLog(CobranzaServiceImpl.class);
	@EJB
	private  CobranzaDAO objCobranzaDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#insertCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void insertCobranza(CobranzaSie Cobranza) {
		objCobranzaDao.insertCobranza(Cobranza);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#updateCobranza(com.edicsem.pe.sie.entity.CobranzaSie)
	 */
	public void updateCobranza(CobranzaSie Cobranza) {
		objCobranzaDao.updateCobranza(Cobranza);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#findCobranza(int)
	 */
	public CobranzaSie findCobranza(int id) {
		return objCobranzaDao.findCobranza(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#listarCobranzas()
	 */
	public List<CobranzaSie> listarCobranzas() {
		return objCobranzaDao.listarCobranzas();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#listarCobranzasXidcontrato(int)
	 */
	public List listarCobranzasXidcontrato(int idcontrato) {
		return objCobranzaDao.listarCobranzasXidcontrato(idcontrato);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#calcularEfectividad(int, java.lang.String, java.lang.String)
	 */
	public List calcularEfectividad(int idEmpleado,String fechaInicio, String fechaFin) {
		return objCobranzaDao.calcularEfectividad(idEmpleado, fechaInicio, fechaFin);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.CobranzaService#MigrarRecaudacion(java.util.List)
	 */
	public String MigrarRecaudacion(List<RecaudacionDTO> lst) {
		String mensaje=null;
		CobranzaSie cobranza = null;
		RecaudacionDTO s =null;
		try {
			
			for (int i = 0; i < lst.size(); i++) {
				
				s = lst.get(i);
				//buscar la cobranza
				String numDoc =s.getCodigoDepositante();
				numDoc =numDoc.substring(numDoc.length()-8, numDoc.length());
				log.info("num docu  "+numDoc);
				
				cobranza = objCobranzaDao.buscarCobranzaXcodigo(numDoc,s.getFechaVencimiento(), s.getMontoTotalPagado());
				if(cobranza==null){
					mensaje="Solucionar cobranza del número de Documento "+numDoc+" el pago de "+ s.getMontoTotalPagado()+" que venció el " +s.getFechaVencimientoString();
				break;
				}else{
					log.info(" cobranza "+cobranza.getTbCliente().getNombresCompletos());
					cobranza.setFecpago(s.getFecpago());
					cobranza.setNrooperacion(s.getNumOperacion());
					if(s.getHoraAtencion()!=null)
					cobranza.setHorapago(s.getHoraAtencion());
					cobranza.setLugarpago("B");
					//Definir si en Banco, oficina
					objCobranzaDao.updateCobranza(cobranza);
				}
			}
		}catch (Exception e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	}
}
