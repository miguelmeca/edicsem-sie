package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.ControlKardexSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.ControlMercaderiaDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.service.facade.ControlMercaderiaService;

@Stateless
public class ControlMercaderiaServiceImpl implements ControlMercaderiaService {

	@EJB
	private  ControlMercaderiaDAO objControlDao;
	@EJB
	private  AlmacenDAO objAlmacenDao;
	@EJB
	private  EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private  EstadoGeneralDAO objEstadoGeneralDao;
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ControlMercaderiaService#insertControlKardex(com.edicsem.pe.sie.entity.ControlKardexSie)
	 */
	public void insertControlKardex(List<ControlKardexSie> lstControl, int idalmacen, int idEmpleado) {
		
		for (int i = 0; i < lstControl.size(); i++) {
			ControlKardexSie c =new ControlKardexSie();
			c.setTbPuntoVenta(objAlmacenDao.findAlmacen(idalmacen));
			c.setTbEmpleado(objEmpleadoDao.buscarEmpleado(idEmpleado));
			c.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(78));
			objControlDao.insertControlKardex(c);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ControlMercaderiaService#updateControlKardex(com.edicsem.pe.sie.entity.ControlKardexSie)
	 */
	public void updateControlKardex(ControlKardexSie c) {
		objControlDao.updateControlKardex(c);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ControlMercaderiaService#findControlKardex(int)
	 */
	public ControlKardexSie findControlKardex(int id) {
		return objControlDao.findControlKardex(id);
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.ControlMercaderiaService#listarControlKardex()
	 */
	public List listarControlKardex() {
		return objControlDao.listarControlKardex();
	}
	
}
