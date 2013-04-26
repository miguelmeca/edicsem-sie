package com.edicsem.pe.sie.client.dataModel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.edicsem.pe.sie.entity.CobranzaSie;

public class CobranzaDataModel extends ListDataModel<CobranzaSie> implements SelectableDataModel<CobranzaSie> {

	public CobranzaDataModel(List<CobranzaSie> data) {
		super(data);  
	}
	
	public CobranzaSie getRowData(String rowKey) {
        List<CobranzaSie> cobs = (List<CobranzaSie>) getWrappedData();  
        for(CobranzaSie cob : cobs) {  
            if(cob.getModel().equals(rowKey))  
                return cob;  
        }
        return null;  
	}
	
	public Object getRowKey(CobranzaSie cob) {
		return cob.getModel();
	}
	
}
