package com.edicsem.pe.sie.client.action.mantenimiento;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import java.util.List;

import com.edicsem.pe.sie.entity.ProductoSie;
  
public class MantenimientoEmpresaDataModelFormAction extends ListDataModel<ProductoSie> 
implements SelectableDataModel<ProductoSie> {    
  
    public MantenimientoEmpresaDataModelFormAction() {  
    }  
  
    public MantenimientoEmpresaDataModelFormAction(List<ProductoSie> data) {  
        super(data);  
    }  
      
    @Override  
    public ProductoSie getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<ProductoSie> cars = (List<ProductoSie>) getWrappedData();  
          
        for(ProductoSie car : cars) {  
            if(car.getIdproducto().equals(rowKey))  
                return car;  
        }  
          
        return null;  
    }  
  
 

	@Override
	public Object getRowKey(ProductoSie car) {
		 return car.getIdproducto(); 
	} 
}