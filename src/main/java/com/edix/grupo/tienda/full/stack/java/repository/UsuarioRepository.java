package com.edix.grupo.tienda.full.stack.java.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;
/**
 * @author Raul - Alvaro
 * 
 * Interfaz que hereda de JpaRepository para proporcionarnos todos los métodos
 * para realizar el CRUD
 *
 */

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	@Query("SELECT u FROM Usuario u JOIN u.direcciones d WHERE d.localidad = :localidad")
    List<Usuario> findByLocalidad(@Param("localidad") String localidad);
}
