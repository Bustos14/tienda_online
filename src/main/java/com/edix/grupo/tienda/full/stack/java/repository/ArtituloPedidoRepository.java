package com.edix.grupo.tienda.full.stack.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;

public interface ArtituloPedidoRepository extends JpaRepository<AticulosPedido, Integer>{
	@Query("SELECT ap FROM AticulosPedido ap WHERE ap.pedido.idPedido = :idPedido")
	public List<AticulosPedido> findByPedido(@Param("idPedido") int idPedido);
	@Query("SELECT ap FROM AticulosPedido ap WHERE ap.pedido.idPedido = :idPedido AND ap.producto.idProducto = :idProducto")
	public AticulosPedido findByPedidoIdAndProductoId(@Param("idPedido") int idPedido, @Param("idProducto") int idProducto);

}
