package com.edix.grupo.tienda.full.stack.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
/**
 * @author Raul - Alvaro
 * 
 * Interfaz que hereda de JpaRepository para proporcionarnos todos los métodos
 * para realizar el CRUD
 *
 */
public interface TarjetaRepository extends JpaRepository<TarjetasBancaria, Integer>{
	 List<TarjetasBancaria> findByUsuario_Username(String username);
}
