package com.edix.grupo.tienda.full.stack.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
