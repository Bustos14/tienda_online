package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;

public interface ProductoDao {

	int nuevoProducto(Producto producto);
	int modificarProducto(Producto producto);
	int eliminarProducto(int idProducto);
	Producto detallesProdutos(int idProducto);
	List<Producto> listadoProducto();
	List<Producto> lProductoPorEstado(String estado);
	List<Producto> lBusquedaProduc(String nombre);
	double findPrecioById(int idProducto);
	List<Producto> findByTipo(String tipo);
	Producto findById(int idProducto);
}
