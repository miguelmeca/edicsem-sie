package com.edicsem.pe.sie.service.facade;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.MetasDiariasDTO;
import com.edicsem.pe.sie.entity.Personalead;
@Local
public interface MetasDiariasDTOService {
	
	public abstract void insertMetasDiariasDTO(MetasDiariasDTO p);
	
}
