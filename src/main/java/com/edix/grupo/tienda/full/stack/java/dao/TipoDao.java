package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Tipo;
/**
 * @author Raul-Alvaro
 * 
 * La interfaz TipoDao, nos sirve para definir los métodos que vamos a utilizar
 *
 */
public interface TipoDao {

	List<Tipo> todoTipo();
}
