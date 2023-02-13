package com.edix.grupo.tienda.full.stack.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoDao pdao;
	@GetMapping("/destacados")
	public String destacados(Model model) {
		List<Producto> listproductos= pdao.lProductoPorEstado("Destacado");
		model.addAttribute("productos", listproductos);
		return "index";
	}
	@GetMapping("/oferta")
	public String ofertas(Model model) {
		List<Producto> listproductos= pdao.lProductoPorEstado("Oferta");
		model.addAttribute("productos", listproductos);
		return "index";
	}
	@GetMapping("/detallesProducto/{id}")
	public String detalles(@PathVariable("id") int idDepar, Model model) {
		Producto p = pdao.detallesProdutos(idDepar);
		model.addAttribute("producto", p);
		System.out.println(p.getNombre());
		return "detallesProducto";
	}
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable("id") int idDepar, Model model) {
		if(pdao.eliminarProducto(idDepar)==1) {
			model.addAttribute("mensaje", "Eliminado, bien hecho");
			return "redirect:index";
		}else {
			model.addAttribute("mensaje", "No eliminado, problemitas.");
			return "redirect:/";
		}
	}
}
