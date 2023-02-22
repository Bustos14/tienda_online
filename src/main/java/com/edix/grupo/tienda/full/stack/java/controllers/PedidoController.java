package com.edix.grupo.tienda.full.stack.java.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.ArticuloPedidoDao;
import com.edix.grupo.tienda.full.stack.java.dao.PedidoDao;
import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.dao.RolDao;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

	
	@Autowired
	UsuarioDao udao;
	@Autowired
	ProductoDao pdao;
	@Autowired
	PedidoDao pedao;
	@Autowired 
	ArticuloPedidoDao ardao;
	@Autowired
	RolDao rdao;
	
	@GetMapping("/modCarrito/{id}")
	public String procCarrito(Model model, @PathVariable("id") int idProd, Authentication aut, HttpSession sesion) {
		Usuario u = null;
		if(aut == null) {
			 u = udao.findById("anonymus");
			 if(u==null) {
				 Role rol = rdao.buscarRol(3);
				 u = new Usuario("anonymus", "anonymus", "anonymus",true,new Date(), new Date(), "anonymus"); 
				 u.addRol(rol);
				 udao.registro(u);
			 }
			 sesion.setAttribute("invitado", u);
		}else {
			 u = udao.findById(aut.getName());
		}
		Producto p = pdao.detallesProdutos(idProd);
		Pedido pe = pedao.obtenerCarrito(u.getUsername());
		AticulosPedido ap = null;
		List<AticulosPedido>apList = new ArrayList<>();
		if(pe==null) {
			Direccione dir = null;
			TarjetasBancaria tb = null;
			pe = new Pedido(0, "En el carrito", new Date(), new BigDecimal(0), u, dir, tb);
			pedao.guardarPedido(pe);
			p.setStock(p.getStock() -1);
			pdao.modificarProducto(p);
		}else {
			 apList = ardao.findByPedido(pe.getIdPedido());
			 for (AticulosPedido atp : apList) {
				 if(atp.getProducto().getIdProducto() == p.getIdProducto()) {
					 if(p.getStock() > 0) {
						 atp.setCantidad(atp.getCantidad()+1);
						 p.setStock(p.getStock() -1);
						 pdao.modificarProducto(p);
						 ardao.guardarArPe(atp);
					 }
					 return "redirect:/";
				 }
			}
		}
		ap = new AticulosPedido(0, 1, pe, p);
		ardao.guardarArPe(ap);
		return "redirect:/";	
	}
	
	@GetMapping("/efectuarCompra")
	public String procCompra(Authentication aut, HttpSession misession, Model model) {
		Usuario u = udao.findById(aut.getName());
		Pedido pe = pedao.obtenerCarrito(u.getUsername());
		List<AticulosPedido>apList =  ardao.findByPedido(pe.getIdPedido());
		double cantidadTotal = 0;
		for (AticulosPedido aticulosPedido : apList) {
			
			cantidadTotal = (aticulosPedido.getCantidad() * aticulosPedido.getProducto().getPrice()) + cantidadTotal;
		}
		pe.setEstado("Comprado");
		pe.setPrecioTotal(new BigDecimal(cantidadTotal));
		if(pedao.guardarPedido(pe)) {
			misession.removeAttribute("contador");
			return "redirect:/pedidos/carrito";	
		}else {
			return "redirect:/pedidos/carrito";	
		}
		
	}
	@GetMapping("/carrito")
	public String getCarrito(Model model, Authentication aut, HttpSession session) {
		Usuario usu = new Usuario();
		
		String userName = aut != null ? aut.getName() : (String) session.getAttribute("invitado");
	    if (userName != null) {
	        usu = udao.findById(userName);
	    } else {
	        // Si el nombre de usuario del invitado no está en la sesión, crea uno aleatorio.
	        String nombreInvitado = "invitado_" + UUID.randomUUID().toString();
	        usu.setNombre(nombreInvitado);
	        session.setAttribute("invitado", nombreInvitado);
	    }
		
		
		Pedido pe = pedao.obtenerCarrito(usu.getUsername());
		if(pe!=null) {
			List<AticulosPedido>apList =  ardao.findByPedido(pe.getIdPedido());
			double cantidadTotal = 0;
			for (AticulosPedido aticulosPedido : apList) {
				cantidadTotal = (aticulosPedido.getCantidad() * aticulosPedido.getProducto().getPrice()) + cantidadTotal;
			}
			if(usu.getDirecciones() != null) {
				List<Direccione> lDir = usu.getDirecciones();
				if(lDir.size()!=0) {
					model.addAttribute("direcciones", lDir);
				}
			}
			if(usu.getDirecciones() !=null) {
				List<TarjetasBancaria> lTar = usu.getTarjetasBancarias();
				if(lTar.size()!=0) {
					model.addAttribute("tarjetas", lTar);
				}
			}
			model.addAttribute("userName", usu.getUsername());
			model.addAttribute("total", cantidadTotal);
			model.addAttribute("carrito", apList);
		}else {
			if(usu.getDirecciones() != null) {
				List<Direccione> lDir = usu.getDirecciones();
				if(lDir.size()!=0) {
					model.addAttribute("direcciones", lDir);
				}
			}
			if(usu.getDirecciones() !=null) {
			List<TarjetasBancaria> lTar = usu.getTarjetasBancarias();
			if(lTar.size()!=0) {
				model.addAttribute("tarjetas", lTar);
			}
			}
			model.addAttribute("userName", usu.getUsername());
		}
		return "carrito";
	}
	@GetMapping("/delete/{id}/{id2}")
	public String procDeletePedido(Model model, Authentication aut, @PathVariable("id") int idPed, @PathVariable("id2") int idProd, HttpSession misession) {
		AticulosPedido aP = ardao.findByPedidoProdcuto(idPed, idProd);
		if(ardao.delArPe(aP)==true) { 
			model.addAttribute("mensaje", "Pedido eliminado");
			int contador = (Integer) misession.getAttribute("contador");
			Producto p = aP.getProducto();
			p.setStock(aP.getProducto().getStock() + aP.getCantidad());
			pdao.modificarProducto(p);
			misession.removeAttribute("contador");
			misession.setAttribute("contador", contador-aP.getCantidad());
			if(ardao.findByPedido(idPed)==null) {
				pedao.elminarPedido(idPed);
			}
			return "redirect:/pedidos/carrito";
		}else {
			model.addAttribute("mensaje", "No se pudo eliminar el/los articulo");
			return "redirect:/pedidos/carrito";
		}
	}
	
	@GetMapping("/verPedido/{idPedido}")
	public String verDetallePedido(@PathVariable("idPedido") int idPedido, Model model, Authentication aut) {

		Pedido pedidoBuscado = pedao.buscarUno(idPedido);
		
		List<AticulosPedido> mostrar = new ArrayList<>();
		
		for(AticulosPedido art : pedidoBuscado.getAticulosPedidos()) {			
			
			Producto añadirP = new Producto();
			añadirP.setNombre(art.getProducto().getNombre());
			añadirP.setPrice(art.getProducto().getPrice());
			
			AticulosPedido añadir = new AticulosPedido();
			añadir.setProducto(añadirP);
			añadir.setCantidad(art.getCantidad());		
			
			mostrar.add(añadir);
			
			
		}
		
		for(AticulosPedido ele: mostrar) {
			System.out.println("______________________");
			System.out.println("| en ForEach | " + ele.getProducto().getNombre());
			System.out.println("| en ForEach | " + ele.getCantidad());
			System.out.println("| en ForEach | " + ele.getProducto().getPrice());
			System.out.println("______________________");
		}
		
		model.addAttribute("articulosPedido", mostrar);
		model.addAttribute("pedido", pedidoBuscado);
		
		if (mostrar == null || mostrar.isEmpty()) {
		    model.addAttribute("error", "No se encontraron artículos para el pedido.");
		}

		return "detallePedido";
	}
	
	
	
}
