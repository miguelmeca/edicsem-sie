package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local; 

import com.edicsem.pe.sie.entity.ProductoSie;
@Local
public interface ProductoService {

	public abstract void insertProducto (ProductoSie producto);
	public abstract void updateProducto(ProductoSie producto);
	public abstract ProductoSie findProducto (String id);
	public abstract List  listarProductos();
	public abstract List  listarProductosXTipo(int tipoProducto);
}
