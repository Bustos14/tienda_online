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
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

//La anotación @CrossOrigins(origins=”*”), es para permitir accesos desde aplicaciones cliente web
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioDaoImpl udao;
	
	@Autowired
	private DireccionDaoImpl ddao;
	@GetMapping("/usuarios/{localidad}")
	public ResponseEntity<List<String>> findByLocalidad(@PathVariable("localidad") String localidad) {
		
	    List<Usuario> milista = udao.buscarPorLocalidad(localidad);
	    
	    if (milista == null || milista.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    List<String> aMostrar = new ArrayList<>();

	    for (Usuario user : milista) {
	        String cadenaGuardar = "Nombre usuario : " + user.getNombre() + " || con apellidos : " + user.getApellidos() +
	                " || con fecha de nacimiento : " + user.getFechaNacimiento() + " || y su username es : " + user.getUsername();
	        aMostrar.add(cadenaGuardar);
	    }

	    return ResponseEntity.ok(aMostrar);
	}

}
