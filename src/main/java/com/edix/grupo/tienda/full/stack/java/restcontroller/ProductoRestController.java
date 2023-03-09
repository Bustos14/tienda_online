package com.edix.grupo.tienda.full.stack.java.restcontroller;

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

//La anotación @CrossOrigins(origins), es para permitir accesos desde aplicaciones cliente web
/**
 * @author Raul - Alvaro
 * 
 * Esta clase RestController nos permite mostrar información a cualquiera que la quiera consumir
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/producto")
public class ProductoRestController {

	@Autowired
	private ProductoDaoImpl pdao;
	
	/**
	 * Método que nos proporciona el precio de un producto, buscado por ID.
	 * 
	 * @param idProducto
	 * @return
	 */
	@GetMapping("/precio/{idProducto}")
	public ResponseEntity<String> findPrecioById(@PathVariable("idProducto") Integer idProducto) {
	    if (idProducto == null) {
	        return ResponseEntity.badRequest().body("El ID del producto no puede ser nulo.");
	    }
	    try {
	        double precio = pdao.findPrecioById(idProducto);
	        String mensaje = "El precio del producto con ID " + idProducto + " es: €" + precio;
	        return ResponseEntity.ok(mensaje);
	    } catch (Exception e) {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	/**
	 * Método que nos proporciona los productos filtrados por tipo.
	 * 
	 * @param tipo
	 * @return
	 */
	@GetMapping("/productos/{tipo}")
	public List<Producto> findByTipo(@PathVariable("tipo")String tipo){		
		return pdao.findByTipo(tipo);
	}
	
}
