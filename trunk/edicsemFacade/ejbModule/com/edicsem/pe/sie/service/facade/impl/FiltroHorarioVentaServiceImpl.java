package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.edicsem.pe.sie.entity.FiltroHorarioVentaSie;
import com.edicsem.pe.sie.model.dao.AlmacenDAO;
import com.edicsem.pe.sie.model.dao.EmpleadoSieDAO;
import com.edicsem.pe.sie.model.dao.EstadoGeneralDAO;
import com.edicsem.pe.sie.model.dao.FechaDAO;
import com.edicsem.pe.sie.model.dao.FiltroHorarioVentaDAO;
import com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService;
import com.edicsem.pe.sie.service.facade.TipoFiltroService;

@Stateless
public class FiltroHorarioVentaServiceImpl implements FiltroHorarioVentaService {

	@EJB
	private  FiltroHorarioVentaDAO objFiltroHorarioVentaDao;
	@EJB
	private AlmacenDAO objPuntoVentaDao;
	@EJB
	private EmpleadoSieDAO objEmpleadoDao;
	@EJB
	private EstadoGeneralDAO objEstadoGeneralDao;
	@EJB
	private FechaDAO objFechaDao;
	@EJB
	private TipoFiltroService objTipoFiltroDao;

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService#insertFiltroHorarioVenta(com.edicsem.pe.sie.entity.FiltroHorarioVentaSie, int, int, int, java.util.List)
	 */
	public void insertFiltroHorarioVenta(FiltroHorarioVentaSie objFiltroHorario,int idPuntoVenta,int idvendedor,int idtipoFiltro,List<String> diaList) {
		
		for (int j = 0; j < diaList.size(); j++) {
			if(idPuntoVenta!=0){
			objFiltroHorario.setTbPuntoventa(objPuntoVentaDao.findAlmacen(idPuntoVenta));
			}
			objFiltroHorario.setTbEmpleado(objEmpleadoDao.buscarEmpleado(idvendedor));
			objFiltroHorario.setTbEstadoGeneral(objEstadoGeneralDao.findEstadoGeneral(22));
			objFiltroHorario.setTbFecha(objFechaDao.findFecha(Integer.parseInt(diaList.get(j))));
			objFiltroHorario.setTbTipoFiltro(objTipoFiltroDao.findTipoFiltro(idtipoFiltro));
			objFiltroHorarioVentaDao.insertFiltroHorarioVenta(objFiltroHorario);
		}
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService#updateFiltroHorarioVenta(com.edicsem.pe.sie.entity.FiltroHorarioVentaSie)
	 */
	public void updateFiltroHorarioVenta(FiltroHorarioVentaSie f) {
		objFiltroHorarioVentaDao.updateFiltroHorarioVenta(f);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService#findFiltroHorarioVenta(int)
	 */
	public FiltroHorarioVentaSie findFiltroHorarioVenta(int id) {
		return objFiltroHorarioVentaDao.findFiltroHorarioVenta(id);
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService#listarFiltroHorarioVenta()
	 */
	public List listarFiltroHorarioVenta() {
		return objFiltroHorarioVentaDao.listarFiltroHorarioVenta();
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.service.facade.FiltroHorarioVentaService#listarFiltroHorarioVentaVigentes()
	 */
	public List listarFiltroHorarioVentaVigentes() {
		return objFiltroHorarioVentaDao.listarFiltroHorarioVentaVigentes();
	}
 
}
