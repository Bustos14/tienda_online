package com.edix.grupo.tienda.full.stack.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
}
