package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the usuario_direccion database table.
 * 
 */
@Embeddable
public class UsuarioDireccionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_usuario", insertable=false, updatable=false)
	private int idUsuario;

	@Column(name="id_direccion", insertable=false, updatable=false)
	private int idDireccion;

	public UsuarioDireccionPK() {
	}
	public int getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdDireccion() {
		return this.idDireccion;
	}
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioDireccionPK)) {
			return false;
		}
		UsuarioDireccionPK castOther = (UsuarioDireccionPK)other;
		return 
			(this.idUsuario == castOther.idUsuario)
			&& (this.idDireccion == castOther.idDireccion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUsuario;
		hash = hash * prime + this.idDireccion;
		
		return hash;
	}
}