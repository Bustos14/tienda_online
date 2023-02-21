package com.edix.grupo.tienda.full.stack.java.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.grupo.tienda.full.stack.java.dao.ProductoDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;

//La anotación @CrossOrigins(origins=”*”), es para permitir accesos desde aplicaciones cliente web
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/producto")
public class ProductoRestController {

	@Autowired
	private ProductoDaoImpl pdao;
	
	@GetMapping("/precio/{idProducto}")
	public ResponseEntity<String> findPrecioById(@PathVariable("idProducto") int idProducto) {
		double precio = pdao.findPrecioById(idProducto);
		String mensaje = "El precio del producto con ID " + idProducto + " es: €" + precio;
	    return ResponseEntity.ok(mensaje);
	}
	
	@GetMapping("/productos/{tipo}")
	public List<Producto> findByTipo(@PathVariable("tipo")String tipo){
		
		return pdao.findByTipo(tipo);
	}
	
}
