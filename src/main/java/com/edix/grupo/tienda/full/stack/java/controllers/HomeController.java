package com.edix.grupo.tienda.full.stack.java.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.ArticuloPedidoDao;
import com.edix.grupo.tienda.full.stack.java.dao.PedidoDao;
import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.dao.RolDao;
import com.edix.grupo.tienda.full.stack.java.dao.TipoDao;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Tipo;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
public class HomeController {

	
	@Autowired
	private ProductoDao pdao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private PedidoDao pedao;
	@Autowired
	private RolDao rdao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ArticuloPedidoDao apdao;
	@GetMapping("/")
	public String inicio(Model model, HttpSession misesion, Authentication aut, HttpSession misession) {
		List<Producto> listproductos= pdao.listadoProducto();
		int contador = 0;
		if(aut != null) {
			Pedido p = pedao.obtenerCarrito(aut.getName());
			if(p!=null) {
			
			List<AticulosPedido> lArp = apdao.findByPedido(p.getIdPedido());
			for (AticulosPedido aticulosPedido : lArp) {
				contador = aticulosPedido.getCantidad() + contador;
			}
			misession.setAttribute("contador", contador);
			model.addAttribute("productos", listproductos);
			return "index";
			}else {
				List<AticulosPedido> lArp = apdao.findByPedido(p.getIdPedido());
				for (AticulosPedido aticulosPedido : lArp) {
					contador = aticulosPedido.getCantidad() + contador;
				}
				misession.setAttribute("contador", contador);
				model.addAttribute("productos", listproductos);
				return "index";
		}
		}else {
			if( misession.getAttribute("invitado")!=null) {
				String invitado = (String) misession.getAttribute("invitado");
				Pedido p = pedao.obtenerCarrito(invitado);
				if(p!=null) {
				
				List<AticulosPedido> lArp = apdao.findByPedido(p.getIdPedido());
				for (AticulosPedido aticulosPedido : lArp) {
					contador = aticulosPedido.getCantidad() + contador;
				}
				misession.setAttribute("contador", contador);
				model.addAttribute("productos", listproductos);
				return "index";
			}
			}
		}
		model.addAttribute("productos", listproductos);
		return "index";
		
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		List<Role> lRoles = rdao.todos();
		model.addAttribute("roles", lRoles );
		return "/registroUsuario";
	}
	@PostMapping("/registro")
	public String proregistrar(@RequestParam("rol") String rol, Model model, Usuario usuario, RedirectAttributes ratt) {
		System.out.println(rol);
		int idRol = Integer.parseInt(rol);
		Role r = rdao.buscarRol(idRol);
		if(r == null) {
			usuario.addRol(rdao.buscarRol(1));
		}else {
			usuario.addRol(r);
		}

		usuario.setEnabled(true);
		usuario.setFechaRegistro(new Date());
		usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
		if(!mayorEdad(usuario.getFechaNacimiento())) {
			model.addAttribute("mensaje", "Debes ser mayor de edad, para registrarte");
			return "/registroUsuario";
		}
		if(mayorEdad(usuario.getFechaNacimiento())) {
			if (udao.registro(usuario)) {
		 		return "redirect:/login";
		 	}
		 	else {
		 		model.addAttribute("mensaje", "ya existe como usuario");
		 		return "/registroUsuario";
		 		
		 	}
		}else {
			model.addAttribute("mensaje", "Debe ser mayor de edad");
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
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	@PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes, HttpSession misession) {
        if (username.equals("usuario") && password.equals("clave")) {

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
            Usuario uSession = (Usuario) misession.getAttribute("invitado");
            if(uSession!=null) {
            	Pedido pe = (Pedido) pedao.obtenerPorUsername(uSession.getUsername()); 
            	Usuario u = udao.findById(username);
            	pe.setUsuario(u);
            	pedao.guardarPedido(pe);
            }
            return "redirect:/";
        } else {

            redirectAttributes.addFlashAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            return "redirect:/login";
        }
    }
	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
		public boolean mayorEdad(Date feNac) {
			 Date fechaActual = new Date();
		        long edadEnMilisegundos = fechaActual.getTime() - feNac.getTime();
		        long edadEnAnios = edadEnMilisegundos / (365 * 24 * 60 * 60 * 1000L);
		        return edadEnAnios >= 18;
		}
		
		
}
