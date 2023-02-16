package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.dao.RolDao;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
public class HomeController {

	
	@Autowired
	private ProductoDao pdao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private RolDao rdao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping("/")
	public String inicio(Model model, HttpSession misesion, Authentication aut) {
		System.out.println ("usuario : " + aut.getName());
		Usuario usuario = udao.findById(aut.getName());
		if (misesion.getAttribute ("usuario") == null) {
			misesion.setAttribute("usuario",usuario);
		}
		List<Producto> listproductos= pdao.listadoProducto();
		model.addAttribute("productos", listproductos);
		return "index";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "/registroUsuario";
	}
	@PostMapping("/registro")
	public String proregistrar(Model model, Usuario usuario, RedirectAttributes ratt) {
		usuario.setEnabled(true);
		usuario.setFechaRegistro(new Date());
		usuario.addRol(rdao.buscarRol(1));
		usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
	 	if (udao.registro(usuario)) {
	 		return "redirect:/login";
	 	}
	 	else {
	 		model.addAttribute("mensaje", "ya existe como usuario");
	 		return "/registroUsuario";
	 		
	 	}
		
	}
	@GetMapping("/index")
	public void procesarLogin(Authentication aut , HttpSession misesion ){
        System.out.println ("usuario : " + aut.getName());
        Usuario usuario = udao.findById(aut.getName());
        if (misesion.getAttribute ("usuario") == null) {
            misesion.setAttribute("usuario",usuario);
            misesion.setAttribute("username", usuario.getUsername());
        }else {
        	
            System.out.println("La sesion actual es de" + aut.getName());
        }

    }
	
	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
		
		
}
