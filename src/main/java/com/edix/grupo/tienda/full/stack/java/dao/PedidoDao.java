package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;

public interface PedidoDao {
	List<Pedido> obtenerPedidos();
	List<Pedido> obtenerPorUsername(String username);
	List<Pedido> pedidoRealizado(String username);
	Pedido obtenerCarrito(String username);
	boolean guardarPedido(Pedido pedido);
	boolean efectuarCompra (Pedido pedido);
	boolean elminarPedido(int idPedido);
	List<Pedido> pedidoRealizado(String username);
	Pedido buscarUno(int idPedido);
}
