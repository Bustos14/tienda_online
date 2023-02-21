package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_realizacion")
	private Date fechaRealizacion;

	private BigDecimal precioTotal;

	//bi-directional many-to-one association to AticulosPedido
	@OneToMany(mappedBy="pedido")
	private List<AticulosPedido> aticulosPedidos;

	//uni-directional many-to-one association to Direccione
	@ManyToOne
	@JoinColumn(name="id_direccion")
	private Direccione direccione;

	//uni-directional many-to-one association to TarjetasBancaria
	@ManyToOne
	@JoinColumn(name="id_tarjeta_bancaria")
	private TarjetasBancaria tarjetasBancaria;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username")
	private Usuario usuario;

	public Pedido() {
	}

	
	public Pedido(int idPedido, String estado, Date fechaRealizacion, BigDecimal precioTotal,
			Usuario usuario, Direccione direccione, TarjetasBancaria tarjetasBancaria) {
		super();
		this.idPedido = idPedido;
		this.estado = estado;
		this.fechaRealizacion = fechaRealizacion;
		this.precioTotal = precioTotal;
		this.direccione = direccione;
		this.tarjetasBancaria = tarjetasBancaria;
		this.usuario = usuario;
	}


	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaRealizacion() {
		return this.fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public BigDecimal getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public List<AticulosPedido> getAticulosPedidos() {
		return this.aticulosPedidos;
	}

	public void setAticulosPedidos(List<AticulosPedido> aticulosPedidos) {
		this.aticulosPedidos = aticulosPedidos;
	}

	public AticulosPedido addAticulosPedido(AticulosPedido aticulosPedido) {
		getAticulosPedidos().add(aticulosPedido);
		aticulosPedido.setPedido(this);

		return aticulosPedido;
	}

	public AticulosPedido removeAticulosPedido(AticulosPedido aticulosPedido) {
		getAticulosPedidos().remove(aticulosPedido);
		aticulosPedido.setPedido(null);

		return aticulosPedido;
	}

	public Direccione getDireccione() {
		return this.direccione;
	}

	public void setDireccione(Direccione direccione) {
		this.direccione = direccione;
	}

	public TarjetasBancaria getTarjetasBancaria() {
		return this.tarjetasBancaria;
	}

	public void setTarjetasBancaria(TarjetasBancaria tarjetasBancaria) {
		this.tarjetasBancaria = tarjetasBancaria;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}