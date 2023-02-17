package com.edix.grupo.tienda.full.stack.java.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDaoImpl udao;
	
	@GetMapping("usuarios")
	public String todosUsuarios(Model model) {
		
		model.addAttribute("todosUsuarios", udao.todos());
		
		return "usuarios";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarUsuario(Authentication auth, HttpSession sesion,Model model,@PathVariable("id") String username) {
		
		for(Usuario ele : udao.todos()) {
			if(ele.getUsername().equals(username)) {
				udao.eliminarUsuario(username);
			}
		}
		
		return "redirect:/usuario/usuarios";
	}
}
