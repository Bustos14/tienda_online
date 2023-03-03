package com.edix.grupo.tienda.full.stack.java.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	@Query("SELECT p FROM Pedido p WHERE p.usuario.username = :username")
	public List<Pedido> findByUsername(@Param("username") String username);
	@Query("SELECT p FROM Pedido p WHERE p.usuario.username = :username AND p.estado = 'En el carrito'")
	Pedido findCard(@Param("username") String username);
	@Query("SELECT p FROM Pedido p WHERE p.usuario.username = :username AND p.estado = 'Comprado'")
	List<Pedido> pedidoRealizado(@Param("username") String username);
	@Query("SELECT p FROM Pedido p WHERE p.fechaRealizacion = :fechaRealizacion AND p.estado ='Comprado'")
    List<Pedido> findByFechaRealizacion(@Param("fechaRealizacion") Date fechaRealizacion);
}
