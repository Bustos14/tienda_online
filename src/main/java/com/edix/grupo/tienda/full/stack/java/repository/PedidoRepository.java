package com.edix.grupo.tienda.full.stack.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
