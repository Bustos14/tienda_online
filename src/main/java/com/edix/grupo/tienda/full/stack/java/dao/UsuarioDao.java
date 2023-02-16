package com.edix.grupo.tienda.full.stack.java.dao;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

public interface UsuarioDao {

	Usuario findById(String username);
	boolean registro(Usuario usuario);
}
