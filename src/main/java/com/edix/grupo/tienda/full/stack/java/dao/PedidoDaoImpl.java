package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.Date;
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
		if(perepo.save(pedido)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean efectuarCompra(Pedido pedido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pedido obtenerCarrito(String username) {
		// TODO Auto-generated method stub
		return perepo.findCard(username);
	}
	
	@Override
	public List<Pedido> pedidoRealizado(String username){
		return perepo.pedidoRealizado(username);
	}

	@Override
	public boolean elminarPedido(int idPedido) {
		Pedido p = perepo.findById(idPedido).orElse(null);
		if(p!=null) {
			perepo.delete(p);
			return true;
		}
		return false;
		
	}

	@Override
	public Pedido buscarUno(int idPedido) {
		return perepo.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> porFechaRealizacion(Date fechaRealizacion) {
		return perepo.findByFechaRealizacion(fechaRealizacion);
	}


}
