package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;
import com.edix.grupo.tienda.full.stack.java.repository.PedidoRepository;

@Repository
public class PedidoDaoImpl implements PedidoDao{

	@Autowired
	PedidoRepository perepo;
	
	@Override
	public List<Pedido> obtenerPedidos() {

		return perepo.findAll();
	}

	@Override
	public List<Pedido> obtenerPorUsername(String username) {
		return perepo.findByUsername(username);
	}

	@Override
	public boolean guardarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean efectuarCompra(Pedido pedido) {
		// TODO Auto-generated method stub
		return false;
	}

}
