package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local; 

import com.edicsem.pe.sie.entity.ProductoSie;
@Local
public interface ProductoService {

	public abstract void insertProducto (ProductoSie producto,int TipoProducto,int estadoProducto);
	public abstract void updateProducto(ProductoSie producto,int TipoProducto,int estadoProducto);
	public abstract ProductoSie findProducto (int id);
	public abstract List  listarProductos();
	public abstract List  listarProductosXTipo(int tipoProducto);
}
