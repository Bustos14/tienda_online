package com.edix.grupo.tienda.full.stack.java.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
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
	
	@GetMapping("/modCarrito/{id}")
	public String procCarrito(Model model, @PathVariable("id") int idProd, Authentication aut) {
		Usuario u = udao.findById(aut.getName());
		Producto p = pdao.detallesProdutos(idProd);
		Pedido pe = pedao.obtenerCarrito(u.getUsername());
		AticulosPedido ap = null;
		List<AticulosPedido>apList = new ArrayList<>();
		if(pe==null) {
			pe = new Pedido(0, "En el carrito", new Date(), new BigDecimal(0), u);
			pedao.guardarPedido(pe);
		}else {
			 apList = ardao.findByPedido(pe.getIdPedido());
			 for (AticulosPedido atp : apList) {
				 if(atp.getProducto().getIdProducto() == p.getIdProducto()) {
					 atp.setCantidad(atp.getCantidad()+1);
					 ardao.guardarArPe(atp);
					 return "redirect:/";
				 }
			}
		}
		ap = new AticulosPedido(0, 1, pe, p);
		ardao.guardarArPe(ap);
		return "redirect:/";	
	}
	
	@GetMapping("/efectuarCompra")
	public String procCompra() {
		
		
		return "carrito";	
	}
	@GetMapping("/carrito")
	public String getCarrito(Model model, Authentication aut) {
		Usuario usu = udao.findById(aut.getName());
		
		Pedido pe = pedao.obtenerCarrito(aut.getName());
		List<AticulosPedido>apList =  ardao.findByPedido(pe.getIdPedido());
		double cantidadTotal = 0;
		for (AticulosPedido aticulosPedido : apList) {
			cantidadTotal = (aticulosPedido.getCantidad() * aticulosPedido.getProducto().getPrice()) + cantidadTotal;
		}
		List<Direccione> lDir = usu.getDirecciones();
		List<TarjetasBancaria> lTar = usu.getTarjetasBancarias();
		System.out.println(lDir.get(0).getCalle());
		model.addAttribute("total", cantidadTotal);
		model.addAttribute("direcciones", lDir);
		model.addAttribute("tarjetas", lTar);
		model.addAttribute("carrito", apList);
		return "carrito";
	}
	
}
