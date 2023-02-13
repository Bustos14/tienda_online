package com.edix.grupo.tienda.full.stack.java.dao;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;

public interface DireccionDao {
	
	int nuevaDireccion(Direccione direccion);
	int modificarDireccion(Direccione direccion);
	int eliminarDireccion(Direccione direccion);
	Direccione buscarUna(int idDireccion);

}
