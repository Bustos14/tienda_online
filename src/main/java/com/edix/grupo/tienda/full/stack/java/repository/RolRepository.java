package com.edix.grupo.tienda.full.stack.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;
/**
 * @author Raul - Alvaro
 * 
 * Interfaz que hereda de JpaRepository para proporcionarnos todos los métodos
 * para realizar el CRUD
 *
 */
public interface RolRepository extends  JpaRepository<Role, Integer> {

}
