package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;

public interface RolDao {

	Role buscarRol (int idRol);
	List<Role> todos();
	int nuevoRol(Role rol);
	int eliminarRol(int idRol);
}
