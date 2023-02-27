package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
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
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;


@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {
	
	
	@Autowired
	private TarjetaDaoImpl tdao;
	
	@Autowired
	private UsuarioDaoImpl udao;
	
	@GetMapping("/alta")
	public String irAltaTarjeta(Authentication auth, HttpSession sesion, Model model) {
		
		
		return "altaTarjeta";
	}
	
	
	@GetMapping("/tarjetas")
	public String todasTarjetas(Model model) {
		
		model.addAttribute("todasTarjetas", tdao.todas());
		
		return "tarjetas";
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
	
	

	@GetMapping("/eliminar/{id}")
	public String eliminar(Authentication auth, HttpSession sesion,Model model,@PathVariable("id") int idTarjeta) {
		
		Usuario usuario = udao.findById(auth.getName());
		String userName = usuario.getUsername();
		
		System.out.println(userName);
		System.out.println(usuario);
		
		for(TarjetasBancaria ele : tdao.todas()) {
			if(ele.getIdTarjetaBancaria() == idTarjeta) {
				usuario.removeTarjeta(idTarjeta);
				udao.modUsuario(usuario);
				tdao.eliminarTarjeta(idTarjeta);
			}
		}
		
		return "redirect:/tarjeta/tarjetas";
	}
	
	@PostMapping("/alta")
	public String altaTarjeta(Authentication auth, HttpSession sesion,TarjetasBancaria tarjeta, RedirectAttributes attr) {
		
		Usuario usuario = udao.findById(auth.getName());	
		
		
		if(tdao.nuevaTarjeta(tarjeta) != 0) {
			tarjeta.setUsuario(usuario);
			usuario.addTarjetA(tarjeta);
			udao.modUsuario(usuario);
			attr.addFlashAttribute("mensaje", "Tarjeta bancaria dada de alta");
		} else {

			attr.addFlashAttribute("mensaje", "Error al crear la tarjeta");
		}		
		
		return "redirect:/usuario/misTarjetas/"+auth.getName();
	}
	
	
	
	@PostMapping("/editar")
	public String editarTarjeta(@ModelAttribute TarjetasBancaria tarjeta, RedirectAttributes attr, Authentication auth) {
				
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
	
		return "redirect:/usuario/misTarjetas/"+auth.getName();
	}
	
	
	
	
	//Método necesario para formatear fechas
	@InitBinder
	public void initBinder(WebDataBinder webdataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webdataBinder
		.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
	
	
}
