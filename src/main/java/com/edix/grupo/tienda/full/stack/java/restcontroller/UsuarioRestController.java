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
/*	
	@GetMapping("/usuarios/{localidad}")
	public List<Usuario> findByLocalidad(@PathVariable("localidad")String localidad){

		List<Usuario> milista = udao.buscarUsuariosPorLocalidad(localidad);
		
		List<Usuario> aMostrar = new ArrayList<>();
		
		for(Usuario ele : milista) {
			System.out.println(ele.getDirecciones());
			for(Direccione dir : ele.getDirecciones()) {
				System.out.println(dir.getLocalidad());
				var comparar = dir.getLocalidad();
				if(comparar.equals(localidad)) {
					aMostrar.add(ele);
					System.out.println(aMostrar.toString());
				}
			}
		}
		return null;
	}
*/
}
