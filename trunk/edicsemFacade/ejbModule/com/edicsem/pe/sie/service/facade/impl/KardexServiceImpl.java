package com.edicsem.pe.sie.service.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless; 

import com.edicsem.pe.sie.entity.KardexSie;
import com.edicsem.pe.sie.model.dao.KardexDAO;
import com.edicsem.pe.sie.service.facade.KardexService;
 
@Stateless
public class KardexServiceImpl implements KardexService {

	@EJB
	private  KardexDAO objKardexDao;
 
 
	public List ConsultaProductos(int idproducto, int idalmacen,
			String fechaDesde, String fechaHasta) { 
		
		return objKardexDao.ConsultaProductos(idproducto, idalmacen, fechaDesde, fechaHasta); 
	}

 
	public void insertMovimiento(KardexSie kardex) {
		objKardexDao.insertMovimiento(kardex);
	}
}
