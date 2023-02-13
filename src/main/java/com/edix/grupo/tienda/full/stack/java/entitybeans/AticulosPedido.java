package com.edix.grupo.tienda.full.stack.java.entitybeans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aticulos_pedidos database table.
 * 
 */
@Entity
@Table(name="aticulos_pedidos")
@NamedQuery(name="AticulosPedido.findAll", query="SELECT a FROM AticulosPedido a")
public class AticulosPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_pedArticulo;

	private int cantidad;

	//uni-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public AticulosPedido() {
	}

	public int getId_pedArticulo() {
		return this.id_pedArticulo;
	}

	public void setId_pedArticulo(int id_pedArticulo) {
		this.id_pedArticulo = id_pedArticulo;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}