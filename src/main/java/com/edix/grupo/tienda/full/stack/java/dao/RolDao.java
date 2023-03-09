package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;
/**
 * @author Raul-Alvaro
 * 
 * La interfaz RolDao, nos sirve para definir los métodos que vamos a utilizar
 *
 */
public interface RolDao {

	Role buscarRol (int idRol);
	List<Role> todos();
	int nuevoRol(Role rol);
	int eliminarRol(int idRol);
}
