package com.edix.grupo.tienda.full.stack.java.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetaBancaria;
import com.edix.grupo.tienda.full.stack.java.repository.TarjetaRepository;

public class TarjetaDaoImpl implements TarjetaDao{

	@Autowired
	private TarjetaRepository trepo;
	
	@Override
	public int nuevaTarjeta(TarjetaBancaria tarjeta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modificarTarjeta(TarjetaBancaria tarjeta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarTarjeta(TarjetaBancaria tarjeta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TarjetaBancaria buscarUna(int idTarjeta) {
		// TODO Auto-generated method stub
		return null;
	}

}
