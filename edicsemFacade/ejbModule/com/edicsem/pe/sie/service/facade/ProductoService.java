package com.edicsem.pe.sie.service.facade;

import java.util.List;
import javax.ejb.Local; 

import com.edicsem.pe.sie.entity.ProductoSie;
@Local
public interface ProductoService {

	public abstract void insertProducto (ProductoSie producto,int TipoProducto);
	public abstract void updateProducto(ProductoSie producto,int TipoProducto);
	public abstract ProductoSie findProducto (int id);
	public abstract List  listarProductos();
	public abstract List  listarProductosXTipo(int tipoProducto);
	//MANTENIMIENTO EMPRESA VALIDACIONES PRODUCTO Y EMPLEADO
	public abstract List listarProductoxEmpresas(int parametroObtenido);	
	public abstract boolean verificarTipoProducto(int tipoProducto);
	public abstract List  listarCodigosProductos();
	
}
