package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
/**
 * @author Raul-Alvaro
 * 
 * La interfaz AticulosPedidoDao, nos sirve para definir los métodos que vamos a utilizar
 *
 */
public interface ArticuloPedidoDao {

	List<AticulosPedido> listadoPedidos(int idPedido);
	boolean guardarArPe (AticulosPedido ap);
	boolean modArPe(AticulosPedido ap);
	boolean delArPe (AticulosPedido ap);
	List<AticulosPedido> findByPedido(int idPedido);
	AticulosPedido findByPedidoProdcuto(int idPedido, int idProducto);
}
