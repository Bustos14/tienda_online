package com.edix.grupo.tienda.full.stack.java.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{

//	@Query("SELECT u FROM Usuario u WHERE u.direcciones.localidad = ?1 ")
//	List<Usuario> findByDireccion(String direccione);
}
