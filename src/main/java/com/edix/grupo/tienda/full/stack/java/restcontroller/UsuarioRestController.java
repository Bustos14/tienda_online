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

import com.edix.grupo.tienda.full.stack.java.dao.DireccionDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

//La anotación @CrossOrigins(origins), es para permitir accesos desde aplicaciones cliente web
/**
 * @author Raul - Alvaro
 * 
 * Esta clase RestController nos permite mostrar información al exterior sobre los usuarios
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

	
	@Autowired
	private DireccionDaoImpl ddao;
	
	/**
	 * Método que muestra las direcciones segun la localidad en la que queramos buscar
	 * 
	 * @param localidad
	 * @return
	 */
	@GetMapping("/usuarios/{localidad}")
	public List<Direccione> findByLocalidad(@PathVariable("localidad") String localidad) {
		
		
	    return ddao.findByLocalidad(localidad); 
	    
	}

}
