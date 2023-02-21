package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

public interface UsuarioDao {

	Usuario findById(String username);
	boolean registro(Usuario usuario);
	boolean modUsuario(Usuario usuario);
	List<Usuario> todos();
	int eliminarUsuario(String username);
	int modificarUsuario(Usuario usuario);
	List<Usuario> buscarPorLocalidad(String localidad);
}
