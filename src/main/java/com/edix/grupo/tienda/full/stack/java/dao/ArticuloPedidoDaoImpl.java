package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
import com.edix.grupo.tienda.full.stack.java.repository.ArtituloPedidoRepository;

@Repository
public class ArticuloPedidoDaoImpl implements ArticuloPedidoDao {

	@Autowired
	ArtituloPedidoRepository aprepo;
	@Override
	public List<AticulosPedido> listadoPedidos(int idPedido) {
		return aprepo.findByPedido(idPedido);
	}
	@Override
	public boolean guardarArPe(AticulosPedido ap) {
		try {
			if(aprepo.save(ap)!=null) {
				return true;
			}
		}catch(Exception e) {
			return false;
		}
		
		return false;
	}
	@Override
	public boolean modArPe(AticulosPedido ap) {
		if(ap!=null) {
			aprepo.save(ap);
			return true;
		}
		return false;
	}
	@Override
	public boolean delArPe(AticulosPedido ap) {
		if(ap!=null) {
			aprepo.delete(ap);
			return true;
		}
		return false;
	}
	@Override
	public List<AticulosPedido> findByPedido(int idPedido) {
		return aprepo.findByPedido(idPedido);
	}
	@Override
	public AticulosPedido findByPedidoProdcuto(int idPedido, int idProducto) {
		// TODO Auto-generated method stub
		return aprepo.findByPedidoIdAndProductoId(idPedido, idProducto);
	}

}
