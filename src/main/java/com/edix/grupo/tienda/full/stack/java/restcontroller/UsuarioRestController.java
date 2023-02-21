package com.edix.grupo.tienda.full.stack.java.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.grupo.tienda.full.stack.java.dao.DireccionDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
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
	public List<Usuario> findByLocalidad(@PathVariable("localidad")String localidad){

	
		List<Direccione> dir = ddao.findByLocalidad(localidad);
		
		List<Usuario> mostrar = new ArrayList<>();
		for(Direccione direcc : dir) {
			if(direcc.getLocalidad().equals(localidad)) {
				for(Usuario us : udao.todos()) {	
					if(us.getDirecciones().contains(direcc)) {
						System.out.println("Estamos aqui");
					}
					System.out.println("Aui no deberiamos estar");
				} 
			}
		}
		
		return mostrar;
	}
	
}
