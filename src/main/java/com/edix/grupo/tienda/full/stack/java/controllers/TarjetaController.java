package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/alta")
	public String altaTarjeta(TarjetaBancaria tarjeta, RedirectAttributes attr) {
		
		/*
		 * Crear logica
		 */
		
		return "/tarjeta/alta";
	}
	
	
	//Método necesario para formatear fechas
	@InitBinder
	public void initBinder(WebDataBinder webdataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webdataBinder
		.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
	
	
}
