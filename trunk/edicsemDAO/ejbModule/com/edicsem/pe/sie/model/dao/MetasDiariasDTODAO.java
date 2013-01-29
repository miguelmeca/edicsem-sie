package com.edicsem.pe.sie.model.dao;

import javax.ejb.Local;

import com.edicsem.pe.sie.beans.MetasDiariasDTO;
 

@Local
public interface MetasDiariasDTODAO {
	public abstract void insertMetasDiariasDTO(MetasDiariasDTO p);


}
