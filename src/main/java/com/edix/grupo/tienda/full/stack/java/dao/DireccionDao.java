package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;

public interface DireccionDao {
	
	int nuevaDireccion(Direccione direccion);
	int modificarDireccion(Direccione direccion);
	int eliminarDireccion(int idDireccion);
	Direccione buscarUna(int idDireccion);
	List<Direccione> todas();
	public List<Direccione> findByLocalidad(String localidad);
}
