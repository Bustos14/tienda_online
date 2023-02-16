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
	
	@GetMapping("/verDireccion/{id}")
	public String irDetalleTarjeta(@PathVariable("id") int id, Model model) {
		
		Direccione direccionDetalle = ddao.buscarUna(id);
		
		model.addAttribute("direccion", direccionDetalle);
		
		return "detalleDireccion";
	}
	
	@PostMapping("/alta")
	public String altaDireccion(@ModelAttribute Direccione direccion, RedirectAttributes attr) {
	
		if(ddao.nuevaDireccion(direccion) != 0) {
			attr.addFlashAttribute("mensaje", "Error al dar de alta direcicón");			
		} else {
			attr.addFlashAttribute("mensaje", "Dirección dada de alta");
		}
		
		return "redirect:/direccion/alta";
	}
	
	@PostMapping("/editar")
	public String editarDireccion(@ModelAttribute Direccione direccion, RedirectAttributes attr) {
		//Obtenemos la direcció existente
		Direccione direccionExistente = ddao.buscarUna(direccion.getIdDireccion());
		
		if(direccionExistente == null) {
			attr.addFlashAttribute("mensaje", "Dirección no encontrada");
		} else {

			//Avtualizamos los campos necesarios
			direccionExistente.setCodigoPostal(direccion.getCodigoPostal());
			direccionExistente.setLetra(direccion.getLetra());
			direccionExistente.setLocalidad(direccion.getLocalidad());
			direccionExistente.setNumero(direccion.getNumero());
			direccionExistente.setPiso(direccion.getPiso());
			
			ddao.modificarDireccion(direccionExistente);
			
			attr.addFlashAttribute("mensaje", "Direccion actualizada con éxito");
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model,@PathVariable("id") int idDireccion) {
		int i = ddao.eliminarDireccion(idDireccion);
		if(i == 0)
			model.addAttribute("mensaje","Direccion no eliminado");
		else {
			model.addAttribute("mensaje","Direccion eliminado");
		}
		return "redirect:/";
	}
	

	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
}
