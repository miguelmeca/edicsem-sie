package com.edicsem.pe.sie.client.dataModel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.SelectableDataModel;

import com.edicsem.pe.sie.entity.CobranzaSie;

public class CobranzaDataModel extends ListDataModel<CobranzaSie> implements SelectableDataModel<CobranzaSie> {
	private Log log = LogFactory.getLog(CobranzaDataModel.class);
	
	public CobranzaDataModel(List<CobranzaSie> data) {
		super(data);  
	}
	
	public CobranzaSie getRowData(String rowKey) {
		log.info("CobranzaDataModel - getRowData "+rowKey);
        List<CobranzaSie> cobs = (List<CobranzaSie>) getWrappedData();  
        for(CobranzaSie cob : cobs) {  
            if(cob.getModel().equals(rowKey))  
                return cob;  
        }
        return null;
	}
	
	public Object getRowKey(CobranzaSie cob) {
		log.info("CobranzaDataModel - getRowKey ");
		return cob.getModel();
	}
	
}
