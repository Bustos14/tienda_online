package com.edix.grupo.tienda.full.stack.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}
