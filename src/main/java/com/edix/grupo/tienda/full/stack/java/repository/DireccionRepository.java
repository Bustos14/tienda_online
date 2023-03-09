package com.edix.grupo.tienda.full.stack.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
/**
 * @author Raul - Alvaro
 * 
 * Interfaz que hereda de JpaRepository para proporcionarnos todos los métodos
 * para realizar el CRUD
 *
 */
public interface DireccionRepository extends JpaRepository<Direccione, Integer>{
	
	@Query("SELECT d FROM Direccione d WHERE d.localidad = ?1")
	public List<Direccione> findByLocalidad(String localidad);
	
}
