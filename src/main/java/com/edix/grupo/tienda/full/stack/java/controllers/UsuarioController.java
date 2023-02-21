package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.DireccionDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.PedidoDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.TarjetaDaoImpl;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDaoImpl;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDaoImpl udao;
	
	@Autowired
	private TarjetaDaoImpl tdao;
	
	@Autowired
	private DireccionDaoImpl ddao;
	
	@Autowired
	private PedidoDaoImpl pdao;
	
	@GetMapping("usuarios")
	public String todosUsuarios(Model model) {
		
		model.addAttribute("todosUsuarios", udao.todos());
		
		return "usuarios";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarUsuario(Authentication auth, HttpSession sesion,Model model,@PathVariable("id") String username) {
		
		for(Usuario ele : udao.todos()) {
			if(ele.getUsername().equals(username)) {
				udao.eliminarUsuario(username);
			}
		}
		
		return "redirect:/usuario/usuarios";
	}
	
	@GetMapping("/perfil")
	public String irPerfil(Authentication auth, Model model) {
		
		Usuario usuario = udao.findById(auth.getName());
		String userName = usuario.getUsername();
		String nombre = usuario.getNombre();
		String apellidos = usuario.getApellidos();
		Date fechaNacimiento = usuario.getFechaNacimiento();
		
		model.addAttribute("userName", userName);
		model.addAttribute("nombre", nombre);
		model.addAttribute("apellidos", apellidos);
		model.addAttribute("fechaNacimiento", fechaNacimiento);

		
		return "irPerfil";
	}
	
	@PostMapping("/editar")
	public String guardarUsuario(Usuario usuario, RedirectAttributes attr) {
		
		Usuario usuarioExistente = udao.findById(usuario.getUsername());
		
		usuarioExistente.setUsername(usuarioExistente.getUsername());
		usuarioExistente.setNombre(usuario.getNombre());
		usuarioExistente.setApellidos(usuario.getApellidos());
		usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
		
		
		if(udao.modificarUsuario(usuario) == 1) {
			attr.addFlashAttribute("mensaje", "Usuario actualizado");
		} else {
			attr.addFlashAttribute("mensaje", "No se ha podido actualizar usuario");
		}
		
	    return "redirect:/usuario/perfil";
	}
	
	@GetMapping("/misTarjetas/{username}")
	public String misTarjetas(Authentication auth, Model model, @PathVariable("username") String username) {
		
		List<TarjetasBancaria> tarjetas = tdao.findByUsername(username);
		
		model.addAttribute("todasTarjetas", tarjetas);
		
		return "tarjetas";
	}
	
	@GetMapping("/misDirecciones/{username}")
	public String misDirecciones(Authentication auth, Model model, @PathVariable("username") String username) {
		
		Usuario user = udao.findById(username);
		String userName = user.getUsername();
		
		List<Direccione> misDirecciones = user.getDirecciones();
		
		model.addAttribute("todasDirecciones", misDirecciones);
		
		
		
		return "direcciones";
	}
	
	@GetMapping("/realizados/{username}")
	public String pedidoRealizado(Authentication auth, Model model, @PathVariable("username") String username) {
		
		Usuario user = udao.findById(username);
		String userName = user.getUsername();
		
		List<Pedido> misPedidos = pdao.pedidoRealizado(userName);
		
		model.addAttribute("todosPedidos", misPedidos);
		
		return "pedidos";
	}
	
	//Método necesario para formatear fechas
			@InitBinder
			public void initBinder(WebDataBinder webdataBinder) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				webdataBinder
				.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
			}
	
	
}
