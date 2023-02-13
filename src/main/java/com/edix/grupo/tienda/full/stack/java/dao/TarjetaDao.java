package com.edix.grupo.tienda.full.stack.java.dao;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetaBancaria;

public interface TarjetaDao {
	
	int nuevaTarjeta(TarjetaBancaria tarjeta);
	int modificarTarjeta(TarjetaBancaria tarjeta);
	int eliminarTarjeta(int idTarjeta);
	TarjetaBancaria buscarUna(int idTarjeta);
	

}
