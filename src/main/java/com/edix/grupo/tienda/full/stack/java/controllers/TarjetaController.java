package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

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

import com.edix.grupo.tienda.full.stack.java.dao.TarjetaDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {
	
	@Autowired
	private TarjetaDaoImpl tdao;
	
	@GetMapping("/alta")
	public String irAltaTarjeta() {
		
		return "altaTarjeta";
	}
	
	@GetMapping("/editar/{id}")
	public String irEditarTarjeta(@PathVariable("id") int id, Model model) {
		TarjetasBancaria tarjetaEditar = tdao.buscarUna(id);
		
		model.addAttribute("tarjetaBancaria", tarjetaEditar);
		
		return "editarTarjeta";
	}
	
	@GetMapping("/verTarjeta/{id}")
	public String irDetalleTarjeta(@PathVariable("id") int id, Model model) {
		
		TarjetasBancaria detalleTarjeta = tdao.buscarUna(id);
		
		model.addAttribute("tarjetaBancaria", detalleTarjeta);
		
		return "detalleTarjeta";
	}
	
	
	@PostMapping("/alta")
	public String altaTarjeta(@ModelAttribute TarjetasBancaria tarjeta, RedirectAttributes attr, HttpSession sesion) {
		
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		tarjeta.setUsuario(usuario);
		
		if(tdao.nuevaTarjeta(tarjeta) != 0) {
			attr.addFlashAttribute("mensaje", "Tarjeta bancaria dada de alta");
		} else {

			attr.addFlashAttribute("mensaje", "Error al crear la tarjeta");
		}		
		
		return "redirect:/tarjeta/alta";
	}
	
	
	
	@PostMapping("/editar")
	public String editarTarjeta(@ModelAttribute TarjetasBancaria tarjeta, RedirectAttributes attr) {
				
		//Obtenemos la tarjeta existente
		TarjetasBancaria tarjetaExistente = tdao.buscarUna(tarjeta.getIdTarjetaBancaria());
		
		if(tarjetaExistente == null) {
			attr.addFlashAttribute("mensaje", "tarjeta no encontrada");
		} else {			
			//Actualizamos los campos necesarios
			tarjetaExistente.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
			tarjetaExistente.setNombreTitular(tarjeta.getNombreTitular());
			tarjetaExistente.setFechaCaducidad(tarjeta.getFechaCaducidad());
			tarjetaExistente.setCvv(tarjeta.getCvv());
			tarjetaExistente.setUsuario(tarjeta.getUsuario());
			
			tdao.modificarTarjeta(tarjetaExistente);
			
					
			attr.addFlashAttribute("mensaje", "Tarjeta modificada con éxito");
		
		}
	
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model,@PathVariable("id") int idTarjeta) {
		int i = tdao.eliminarTarjeta(idTarjeta);
		if(i == 0)
			model.addAttribute("mensaje","tarjeta no eliminado");
		else {
			model.addAttribute("mensaje","tarjeta eliminado");
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
