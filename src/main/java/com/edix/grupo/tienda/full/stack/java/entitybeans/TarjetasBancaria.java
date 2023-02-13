package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tarjetas_bancarias database table.
 * 
 */
@Entity
@Table(name="tarjetas_bancarias")
@NamedQuery(name="TarjetasBancaria.findAll", query="SELECT t FROM TarjetasBancaria t")
public class TarjetasBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tarjeta_bancaria")
	private int idTarjetaBancaria;

	private int cvv;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_caducidad")
	private Date fechaCaducidad;

	@Column(name="nombre_titular")
	private String nombreTitular;

	@Column(name="numero_tarjeta")
	private String numeroTarjeta;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public TarjetasBancaria() {
	}

	public int getIdTarjetaBancaria() {
		return this.idTarjetaBancaria;
	}

	public void setIdTarjetaBancaria(int idTarjetaBancaria) {
		this.idTarjetaBancaria = idTarjetaBancaria;
	}

	public int getCvv() {
		return this.cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNombreTitular() {
		return this.nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getNumeroTarjeta() {
		return this.numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}