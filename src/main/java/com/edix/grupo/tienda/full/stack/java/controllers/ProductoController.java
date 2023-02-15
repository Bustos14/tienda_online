package com.edix.grupo.tienda.full.stack.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String detalles(@PathVariable("id") int idProd, Model model) {
		Producto p = pdao.detallesProdutos(idProd);
		model.addAttribute("producto", p);
		System.out.println(p.getNombre());
		return "detallesProducto";
	}
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable("id") int idProd, Model model) {
		if(pdao.eliminarProducto(idProd)==1) {
			model.addAttribute("mensaje", "Eliminado, bien hecho");
			return "redirect:/";
		}else {
			model.addAttribute("mensaje", "No eliminado, problemitas.");
			return "redirect:/";
		}
	}
	@GetMapping("/modificarProducto/{id}")
	public String modProducto(@PathVariable ("id") int idProd, Model model) {
		Producto p = pdao.detallesProdutos(idProd);
		model.addAttribute("productoEditable", p);
		return "editarProducto";
	}
	@PostMapping("/modificarProducto")
	public String guardarModificado( @ModelAttribute Producto productoEditable, RedirectAttributes attr) {
		//Obtenemos la tarjeta existente
				Producto p = pdao.detallesProdutos(productoEditable.getIdProducto());
				
				//Actualizamos los campos necesarios
				p.setNombre(productoEditable.getNombre());
				p.setDescripcion(productoEditable.getDescripcion());
				p.setPrice(productoEditable.getPrice());
				p.setStock(productoEditable.getStock());
				p.setEstado(productoEditable.getEstado());
				if(pdao.modificarProducto(p)==1) {
					attr.addFlashAttribute("mensaje", "Producto modificado con exito");
					return "redirect:/";
				}
				attr.addFlashAttribute("mensaje", "Producto modificado sin exito");
				/*
				 * Pendiente ver donde redirigir, de momento va a alta
				 */
				return "redirect:/";
				
	}
	@GetMapping("/altaProducto")
	public String altaProducto() {
		return "altaProducto";
	}
	@PostMapping("/altaProducto")
	public String altaProducto(@ModelAttribute Producto p, RedirectAttributes attr) {
		if(pdao.nuevoProducto(p)==1) {
			attr.addFlashAttribute("mensaje", "Producto añadido correctamente");
			return "redirect:/";
		}
		return "redirect:/altaProducto";
		
	}
	
}
