package com.edix.grupo.tienda.full.stack.java.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetaBancaria;
import com.edix.grupo.tienda.full.stack.java.repository.TarjetaRepository;

@Repository
public class TarjetaDaoImpl implements TarjetaDao{

	@Autowired
	private TarjetaRepository trepo;
	
	@Override
	public int nuevaTarjeta(TarjetaBancaria tarjeta) {
		int filas = 0;
		try {
			trepo.save(tarjeta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int modificarTarjeta(TarjetaBancaria tarjeta) {
		int filas = 0;
		TarjetaBancaria mod = null;
		try {
			mod = trepo.getOne(tarjeta.getIdTarjetaBancaria());
			mod = tarjeta;
			trepo.save(mod);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int eliminarTarjeta(int idTarjeta) {
		int filas = 0;
		try {
			trepo.deleteById(idTarjeta);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

	@Override
	public TarjetaBancaria buscarUna(int idTarjeta) {
		return trepo.findById(idTarjeta).orElse(null);
	}

}
