package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.repository.ProductoRepository;

@Repository
public class ProductoDaoImpl implements ProductoDao{
	@Autowired
	private ProductoRepository prepo;
	@Override
	public int nuevoProducto(Producto producto) {
		
		return 0;
	}

	@Override
	public int modificarProducto(Producto producto) {
		return 0;
	}

	@Override
	public int eliminarProducto(int idProducto) {
			Producto p = prepo.findById(idProducto).orElse(null);
			if(p!=null) {
				prepo.delete(p);	
				return 1;
			}
			return 0;	
	}

	@Override
	public Producto detallesProdutos(int idProducto) {
		return prepo.findById(idProducto).orElse(null);
	}

	@Override
	public List<Producto> listadoProducto() {
		return prepo.findAll();
	}

	@Override
	public List<Producto> lProductoPorEstado(String estado) {
		return prepo.findByState(estado);
	}

}
