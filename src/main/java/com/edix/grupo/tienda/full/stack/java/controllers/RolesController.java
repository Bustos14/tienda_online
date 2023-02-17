package com.edix.grupo.tienda.full.stack.java.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.RolDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
@RequestMapping("/rol")
public class RolesController {
	
	@Autowired
	private RolDaoImpl rdao;
	
	@Autowired 
	private UsuarioDaoImpl udao;
	
	@GetMapping("/alta")
	public String irAlta(Authentication auth, HttpSession sesion, Model model){
		
		
		return "altaRol";
	}
	
	@GetMapping("/roles")
	public String todosRoles(Model model) {
		
		System.out.println(rdao.todos());
		model.addAttribute("todosRoles", rdao.todos());
		
		return "roles";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Authentication auth, HttpSession sesion, Model model, @PathVariable("id") int idRol) {
		
		for(Role ele : rdao.todos()) {
			if(ele.getIdRol() == idRol) {
				rdao.eliminarRol(idRol);
			}
		}
		
		return "redirect:/rol/roles";
	}
	
	@PostMapping("/alta")
	public String altaRol(Authentication auth, HttpSession sesion,Role rol, RedirectAttributes attr) {
		
		if(rdao.nuevoRol(rol) != 0) {
			attr.addFlashAttribute("mensaje", "Rol dado de alta");
		} else {
			attr.addFlashAttribute("mensaje", "Rola imposible dar de alta");
		}
		
		
		return "redirect:/rol/alta";
	}
	

}
