package com.edix.grupo.tienda.full.stack.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;

public interface TarjetaRepository extends JpaRepository<TarjetasBancaria, Integer>{
	 List<TarjetasBancaria> findByUsuario_Username(String username);
}
