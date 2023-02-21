package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.repository.DireccionRepository;

@Repository
public class DireccionDaoImpl implements DireccionDao {

	@Autowired
	private DireccionRepository drepo;
	
	@Override
	public int nuevaDireccion(Direccione direccion) {
		int filas = 0;
		try {
			drepo.save(direccion);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int modificarDireccion(Direccione direccion) {
		int filas = 0;
		Direccione mod = null;
		try {
			mod = drepo.getOne(direccion.getIdDireccion());
			mod = direccion;
			drepo.save(mod);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int eliminarDireccion(int idDireccion) {
		Direccione d = drepo.findById(idDireccion).orElse(null);
		
		if(d != null) {
			drepo.delete(d);
			return 1;
		}
		return 0;
	}

	@Override
	public Direccione buscarUna(int idDireccion) {
		return drepo.findById(idDireccion).orElse(null);
	}

	@Override
	public List<Direccione> todas() {
		return drepo.findAll();
	}

	@Override
	public List<Direccione> findByLocalidad(String localidad) {
		return drepo.findByLocalidad(localidad);
	}


}
