package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;
import com.edix.grupo.tienda.full.stack.java.repository.ProductoRepository;

@Repository
public class ProductoDaoImpl implements ProductoDao{
	@Autowired
	private ProductoRepository prepo;
	@Override
	public int nuevoProducto(Producto producto) {
		try {
				prepo.save(producto);
				return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int modificarProducto(Producto producto) {
		try {
		if(prepo.findById(producto.getIdProducto())!=null) {
			prepo.save(producto);
			return 1;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Override
	public List<Producto> lBusquedaProduc(String nombre) {
		return prepo.findLikeName(nombre);
	}
	
	@Override
	public double findPrecioById(int idProducto) {		
		Optional<Producto> prod = prepo.findById(idProducto);	
			
		return prod.get().getPrice();
	}

	@Override
	public List<Producto> findByTipo(String tipo) {
		return prepo.findByTipo(tipo);
	}

	@Override
	public Producto findById(int idProducto) {
		return prepo.findById(idProducto).orElse(null);
	}

}
