package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_direccion database table.
 * 
 */
@Entity
@Table(name="usuario_direccion")
@NamedQuery(name="UsuarioDireccion.findAll", query="SELECT u FROM UsuarioDireccion u")
public class UsuarioDireccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioDireccionPK id;

	public UsuarioDireccion() {
	}

	public UsuarioDireccionPK getId() {
		return this.id;
	}

	public void setId(UsuarioDireccionPK id) {
		this.id = id;
	}

}