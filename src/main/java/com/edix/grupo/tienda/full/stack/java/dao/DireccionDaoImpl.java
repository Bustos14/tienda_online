package com.edix.grupo.tienda.full.stack.java.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.repository.DireccionRepository;

public class DireccionDaoImpl implements DireccionDao {

	@Autowired
	private DireccionRepository drepo;
	
	@Override
	public int nuevaDireccion(Direccione direccion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modificarDireccion(Direccione direccion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarDireccion(Direccione direccion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Direccione buscarUna(int idDireccion) {
		// TODO Auto-generated method stub
		return null;
	}

}
