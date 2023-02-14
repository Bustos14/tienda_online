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

import com.edix.grupo.tienda.full.stack.java.dao.TarjetaDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetaBancaria;

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
		TarjetaBancaria tarjetaEditar = tdao.buscarUna(id);
		
		model.addAttribute("tarjetaBancaria", tarjetaEditar);
		
		return "editarTarjeta";
	}
	
	
	@PostMapping("/alta")
	public String altaTarjeta(@ModelAttribute TarjetaBancaria tarjeta, RedirectAttributes attr) {
		
		tdao.nuevaTarjeta(tarjeta);
		attr.addFlashAttribute("mensaje", "Tarjeta bancaria dada de alta");
		
		
		return "redirect:/tarjeta/alta";
	}
	
	
	
	@PostMapping("/editar")
	public String editarTarjeta(@ModelAttribute TarjetaBancaria tarjeta, RedirectAttributes attr) {
		
		//Obtenemos la tarjeta existente
		TarjetaBancaria tarjetaExistente = tdao.buscarUna(tarjeta.getIdTarjetaBancaria());
		
		//Actualizamos los campos necesarios
		tarjetaExistente.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
		tarjetaExistente.setNombreTitular(tarjeta.getNombreTitular());
		tarjetaExistente.setFechaCaducidad(tarjeta.getFechaCaducidad());
		tarjetaExistente.setCvv(tarjeta.getCvv());
		tarjetaExistente.setUsuario(tarjeta.getUsuario());
		
		tdao.modificarTarjeta(tarjetaExistente);
		
				
		attr.addFlashAttribute("mensaje", "Tarjeta modificada con éxito");
		/*
		 * Pendiente ver donde redirigir, de momento va a alta
		 */
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model,@PathVariable("id") int idTarjeta) {
		int i = tdao.eliminarTarjeta(idTarjeta);
		if(i == 0)
			model.addAttribute("mensaje","producto no eliminado");
		else {
			tdao.eliminarTarjeta(idTarjeta);
			model.addAttribute("mensaje","producto eliminado");
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
