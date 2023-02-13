package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuarios_tarjetas_bancarias database table.
 * 
 */
@Entity
@Table(name="usuarios_tarjetas_bancarias")
@NamedQuery(name="UsuariosTarjetasBancaria.findAll", query="SELECT u FROM UsuariosTarjetasBancaria u")
public class UsuariosTarjetasBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuariosTarjetasBancariaPK id;

	public UsuariosTarjetasBancaria() {
	}

	public UsuariosTarjetasBancariaPK getId() {
		return this.id;
	}

	public void setId(UsuariosTarjetasBancariaPK id) {
		this.id = id;
	}

}