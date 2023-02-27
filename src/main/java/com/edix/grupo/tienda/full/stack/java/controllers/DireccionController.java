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

import com.edix.grupo.tienda.full.stack.java.dao.DireccionDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
@RequestMapping("/direccion")
public class DireccionController {
	
	@Autowired
	private DireccionDaoImpl ddao;
	
	@Autowired
	private UsuarioDaoImpl udao;
	
	@GetMapping("/alta")
	public String irAltaDireccion(Authentication auth, HttpSession sesion, Model model) {
		
		return "altaDireccion";
	}
	
	@GetMapping("/direcciones")
	public String todasDirecciones(Model model) {
		
		model.addAttribute("todasDirecciones", ddao.todas());
		
		return "direcciones";
	}
	
	@GetMapping("/editar/{id}")
	public String irEditarDirecceion(@PathVariable("id") int id, Model model) {
		Direccione direccionEditar = ddao.buscarUna(id);
		
		if(direccionEditar == null) {
			model.addAttribute("mensaje", "La tarjeta que deseaba editar no existe");
			return "redirect:/";
		}
		
		model.addAttribute("direccion", direccionEditar);
		
		return "editarDireccion";
	}
	
	@GetMapping("/verDireccion/{id}")
	public String irDetalleTarjeta(@PathVariable("id") int id, Model model) {
		
		Direccione direccionDetalle = ddao.buscarUna(id);
		
		System.out.println(direccionDetalle);
		
		model.addAttribute("direccion", direccionDetalle);
		
		return "detalleDireccion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Authentication auth, HttpSession sesion,Model model,@PathVariable("id") int idDireccion) {
		
		Usuario usuario = udao.findById(auth.getName());
		String userName = usuario.getUsername();
		
		System.out.println(userName);
		System.out.println(usuario);
		
		for(Direccione ele : ddao.todas()){
			if(ele.getIdDireccion() == idDireccion) {
				usuario.removeDireccion(idDireccion);
				udao.modUsuario(usuario);
				ddao.eliminarDireccion(idDireccion);
			}
		}
			
				
		
		return "redirect:/direccion/direcciones";
	}
	
	
	@PostMapping("/alta")
	public String altaDireccion(Authentication auth, HttpSession sesion, Direccione direccion, RedirectAttributes attr) {
		
		Usuario usuario = udao.findById(auth.getName());
	
		if(ddao.nuevaDireccion(direccion) != 0) {
			usuario.addDireccion(direccion);
			udao.modUsuario(usuario);
			
			attr.addFlashAttribute("mensaje", "Dirección dada de alta");						
		} else {
			attr.addFlashAttribute("mensaje", "Error al dar de alta direcicón");
		}
		
		 return "redirect:/usuario/misDirecciones/"+auth.getName();
	}
	
	@PostMapping("/editar")
	public String editarDireccion(@ModelAttribute Direccione direccion, RedirectAttributes attr, Authentication auth) {
		//Obtenemos la direcció existente
		Direccione direccionExistente = ddao.buscarUna(direccion.getIdDireccion());
		
			//Actualizamos los campos necesarios
			direccionExistente.setCodigoPostal(direccion.getCodigoPostal());
			direccionExistente.setLetra(direccion.getLetra());
			direccionExistente.setLocalidad(direccion.getLocalidad());
			direccionExistente.setNumero(direccion.getNumero());
			direccionExistente.setPiso(direccion.getPiso());
			
			
			if(ddao.modificarDireccion(direccionExistente) == 1) {
			
			attr.addFlashAttribute("mensaje", "Direccion actualizada con éxito");
			return "redirect:/usuario/misDirecciones/"+auth.getName();
			}
			
			attr.addFlashAttribute("mensaje", "Dirección imposible de actualizar");
		
			return "redirect:/usuario/misDirecciones/"+auth.getName();
	}
	
	
	
	

	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
}
