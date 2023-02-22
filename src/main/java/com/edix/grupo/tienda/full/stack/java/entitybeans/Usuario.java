package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String apellidos;

	private String contrasena;

	private boolean enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	private String nombre;

	//uni-directional many-to-many association to Direccione
	@ManyToMany
	@JoinTable(
		name="usuario_direccion"
		, joinColumns={
			@JoinColumn(name="username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_direccion")
			}
		)
	private List<Direccione> direcciones;

	//uni-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="usuario_rol"
		, joinColumns={
			@JoinColumn(name="username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_rol")
			}
		)
	private List<Role> roles;

	//uni-directional many-to-many association to TarjetasBancaria
	@ManyToMany
	@JoinTable(
		name="usuarios_tarjetas_bancarias"
		, joinColumns={
			@JoinColumn(name="username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_tarjeta_bancaria")
			}
		)
	private List<TarjetasBancaria> tarjetasBancarias;

	public Usuario() {
	}

	

	public Usuario(String username, String apellidos, String contrasena, boolean enabled, Date fechaNacimiento,
			Date fechaRegistro, String nombre) {
		super();
		this.username = username;
		this.apellidos = apellidos;
		this.contrasena = contrasena;
		this.enabled = enabled;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
	}



	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Direccione> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<Direccione> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Role> getRoles() {
		return this.roles;
	}
	public void addRol(Role rol) {
		if (roles == null)
			roles = new ArrayList<>();
		roles.add(rol);
	}
	
	public void addTarjetA(TarjetasBancaria tarjeta) {
		if(tarjetasBancarias == null)
			tarjetasBancarias = new ArrayList<>();
		tarjetasBancarias.add(tarjeta);
	}
	
	public void removeTarjeta(int idTarjeta) {
	    List<TarjetasBancaria> nuevasTarjetas = new ArrayList<TarjetasBancaria>();
	    for (TarjetasBancaria tarjeta : tarjetasBancarias) {
	        if (tarjeta.getIdTarjetaBancaria() != idTarjeta) {
	            nuevasTarjetas.add(tarjeta);
	        }
	    }
	    if (nuevasTarjetas.size() < tarjetasBancarias.size()) {
	        tarjetasBancarias = nuevasTarjetas;
	    } else {
	        System.out.println("No existe esa tarjeta");
	    }
	}
	
	public void addDireccion(Direccione direccion) {
		if(direcciones == null)
			direcciones = new ArrayList<>();
		direcciones.add(direccion);
	}

	public void removeDireccion(int idDireccion) {
	    List<Direccione> nuevasDirecciones = new ArrayList<Direccione>();
	    for (Direccione direccion : direcciones) {
	        if (direccion.getIdDireccion() != idDireccion) {
	            nuevasDirecciones.add(direccion);
	        }
	    }
	    if (nuevasDirecciones.size() < direcciones.size()) {
	        direcciones = nuevasDirecciones;
	    } else {
	        System.out.println("No existe la dirección");
	    }
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<TarjetasBancaria> getTarjetasBancarias() {
		return this.tarjetasBancarias;
	}

	public void setTarjetasBancarias(List<TarjetasBancaria> tarjetasBancarias) {
		this.tarjetasBancarias = tarjetasBancarias;
	}
	
	

}