package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;

public interface TarjetaDao {
	
	int nuevaTarjeta(TarjetasBancaria tarjeta);
	int modificarTarjeta(TarjetasBancaria tarjeta);
	int eliminarTarjeta(int idTarjeta);
	TarjetasBancaria buscarUna(int idTarjeta);
	List<TarjetasBancaria> todas();
	List<TarjetasBancaria> findByUsername(String username);
	

}
