package com.edicsem.pe.sie.model.dao;

import java.util.List;

import javax.ejb.Local;

import com.edicsem.pe.sie.entity.ProductoSie;
 

@Local
public interface ProductoDAO {
	
	public abstract void insertProducto (ProductoSie producto);
	public abstract void updateProducto(ProductoSie producto);
	public abstract ProductoSie findProducto (int id);
	public abstract List  listarProductosXTipo(int tipoProducto);
	public abstract List  listarProductos();
	public abstract List listarProductoxEmpresas(int parametroObtenido);	
	public abstract boolean verificarTipoProducto(int tipoProducto);
	public abstract List  listarCodigosProductos();
}
