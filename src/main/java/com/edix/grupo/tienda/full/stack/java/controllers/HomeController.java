package com.edix.grupo.tienda.full.stack.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;

@Controller
public class HomeController {

	
	@Autowired
	private ProductoDao pdao;
	@GetMapping("/")
	public String inicio(Model model) {
		List<Producto> listproductos= pdao.listadoProducto();
		model.addAttribute("productos", listproductos);
		return "index";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "/registro";
	}
	//@PostMapping("/registro")
/*public String proregistrar(Model model, Usuario usuario, RedirectAttributes ratt) {
		
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
	 	usuario.addPerfil(pfdao.findById(3));
	 	usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
	 	
	 	if (udao.registro(usuario)) {
	 		ratt.addFlashAttribute("mensaje", "alta realizada");
	 		return "redirect:/login";
	 	}
	 	else {
	 		model.addAttribute("mensaje", "ya existe como usuario");
	 		return "/registro";
	 		
	 	}
		
	}
	@GetMapping("/login")
	public String logIn() {
		return null;
	}
	@PostMapping("/login")
	public String procLogIn() {
		return null;
	}*/
}
