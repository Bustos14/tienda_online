package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
public interface ArticuloPedidoDao {

	List<AticulosPedido> listadoPedidos(int idPedido);
	boolean guardarArPe (AticulosPedido ap);
	boolean modArPe(AticulosPedido ap);
	boolean delArPe (AticulosPedido ap);
	List<AticulosPedido> findByPedido(int idPedido);
	AticulosPedido findByPedidoProdcuto(int idPedido, int idProducto);
}
