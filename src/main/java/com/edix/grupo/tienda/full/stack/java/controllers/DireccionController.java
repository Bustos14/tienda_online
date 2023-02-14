package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.DireccionDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetaBancaria;

@Controller
@RequestMapping("/direccion")
public class DireccionController {
	
	@Autowired
	private DireccionDaoImpl ddao;
	
	@GetMapping("/alta")
	public String irAltaDireccion() {
		
		return "altaDireccion";
	}
	
	@GetMapping("/editar/{id}")
	public String irEditarDirecceion(@PathVariable("id") int id, Model model) {
		Direccione direccionEditar = ddao.buscarUna(id);
		
		model.addAttribute("direccion", direccionEditar);
		
		return "editarDireccion";
	}
	
	@PostMapping("/alta")
	public String altaDireccion(@ModelAttribute Direccione direccion, RedirectAttributes attr) {
		
		ddao.nuevaDireccion(direccion);
		attr.addFlashAttribute("mensaje", "Dirección dada de alta");
		
		return "redirect:/direccion/alta";
	}

	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
}
