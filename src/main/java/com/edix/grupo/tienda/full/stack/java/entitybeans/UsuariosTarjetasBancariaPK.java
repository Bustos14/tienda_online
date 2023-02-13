package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the usuarios_tarjetas_bancarias database table.
 * 
 */
@Embeddable
public class UsuariosTarjetasBancariaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_usuario", insertable=false, updatable=false)
	private int idUsuario;

	@Column(name="id_tarjeta_bancaria", insertable=false, updatable=false)
	private int idTarjetaBancaria;

	public UsuariosTarjetasBancariaPK() {
	}
	public int getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdTarjetaBancaria() {
		return this.idTarjetaBancaria;
	}
	public void setIdTarjetaBancaria(int idTarjetaBancaria) {
		this.idTarjetaBancaria = idTarjetaBancaria;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuariosTarjetasBancariaPK)) {
			return false;
		}
		UsuariosTarjetasBancariaPK castOther = (UsuariosTarjetasBancariaPK)other;
		return 
			(this.idUsuario == castOther.idUsuario)
			&& (this.idTarjetaBancaria == castOther.idTarjetaBancaria);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUsuario;
		hash = hash * prime + this.idTarjetaBancaria;
		
		return hash;
	}
}