package com.edix.grupo.tienda.full.stack.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;

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
}
