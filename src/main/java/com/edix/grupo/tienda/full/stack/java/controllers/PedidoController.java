package com.edix.grupo.tienda.full.stack.java.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.ArticuloPedidoDao;
import com.edix.grupo.tienda.full.stack.java.dao.DireccionDao;
import com.edix.grupo.tienda.full.stack.java.dao.PedidoDao;
import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.dao.RolDao;
import com.edix.grupo.tienda.full.stack.java.dao.TarjetaDao;
import com.edix.grupo.tienda.full.stack.java.dao.UsuarioDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.AticulosPedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Pedido;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;

/**
 * @author Raul-Alvaro
 * 
 * Esta clase controlador nos ayuda a hacer todo lo necesario con la entidad Pedidos
 *
 */
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
	@Autowired
	DireccionDao ddao;
	@Autowired
	TarjetaDao tdao;
	
	/**
	 * M??todo para a??adir al carrito un producto
	 * 
	 * @param model
	 * @param idProd
	 * @param aut
	 * @param session
	 * @return
	 */
	@GetMapping("/modCarrito/{id}")
	public String procCarrito(Model model, @PathVariable("id") int idProd, Authentication aut, HttpSession session) {
		Usuario usu = new Usuario();
		//Comprobamos el tipo de usuario que esta realizando la acci??n
        if (aut != null) {
        	//Recogemos si es un usuario autentificado y lo fusionamos con el que existia en sesi??n
            usu = udao.findById(aut.getName());
            if(session.getAttribute("invitado")!= null) {
            	fusionPedidos(session,usu);
            }
        } else {
        	if(session.getAttribute("invitado")==null) {
        		usuarioRandom(usu, session);
	    	}else {
	    		// Si el nombre de usuario del invitado no est?? en la sesi??n, crea uno aleatorio.
	    		String nombreInvitado = (String) session.getAttribute("invitado");
	    		usu = udao.findById(nombreInvitado);		    
	    	}    
        	}
		Producto p = pdao.detallesProdutos(idProd);
		Pedido pe = pedao.obtenerCarrito(usu.getUsername());
		AticulosPedido ap = null;
		List<AticulosPedido>apList = new ArrayList<>();
		if(pe==null) {
			Direccione dir = null;
			TarjetasBancaria tb = null;
			pe = new Pedido(0, "En el carrito", new Date(), new BigDecimal(0), usu, dir, tb);
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
			 AticulosPedido aux = ardao.findByPedidoProdcuto(idProd, idProd);
			 if(aux==null) {
				 if(p.getStock() > 0) {
					 ap = new AticulosPedido(0, 1, pe, p);
					 p.setStock(p.getStock() -1);
					 pdao.modificarProducto(p);
					 ardao.guardarArPe(ap);
				 }
				 return "redirect:/";
			 }
		}
		ap = new AticulosPedido(0, 1, pe, p);
		ardao.guardarArPe(ap);
		return "redirect:/";	
	}

	
	/**
	 * M??todo para efectuar la compra de los productos que tenemos en el carrito
	 * 
	 * @param aut
	 * @param misession
	 * @param model
	 * @param idDireccion
	 * @param tarjeta
	 * @return
	 */
	@GetMapping("/efectuarCompra")
	public String procCompra(Authentication aut, HttpSession misession, Model model, @RequestParam("dir") String idDireccion, @RequestParam("tarjetas") String tarjeta) {
		Usuario u = udao.findById(aut.getName());
		Pedido pe = pedao.obtenerCarrito(u.getUsername());
		List<AticulosPedido>apList =  ardao.findByPedido(pe.getIdPedido());
		double cantidadTotal = 0;
		for (AticulosPedido aticulosPedido : apList) {
			
			cantidadTotal = (aticulosPedido.getCantidad() * aticulosPedido.getProducto().getPrice()) + cantidadTotal;
		}
		pe.setEstado("Comprado");
		pe.setPrecioTotal(new BigDecimal(cantidadTotal));
		int idDireccione = Integer.parseInt(idDireccion);
		int idTarjeta = Integer.parseInt(tarjeta);
		Direccione d = ddao.buscarUna(idDireccione);
		TarjetasBancaria t = tdao.buscarUna(idTarjeta);
		pe.setTarjetasBancaria(t);
		pe.setDireccione(d);

		if(pedao.guardarPedido(pe)) {
			misession.removeAttribute("contador");
			return "redirect:/usuario/realizados/"+aut.getName();	
		}else {
			return "redirect:/pedidos/carrito";	
		}
	}
	/**
	 * M??todo para ir al carrito
	 * 
	 * @param model
	 * @param aut
	 * @param session
	 * @return
	 */
	@GetMapping("/carrito")
	public String getCarrito(Model model, Authentication aut, HttpSession session) {
		Usuario usu = new Usuario();
			  if (aut != null) {
			        usu = udao.findById(aut.getName());
			        if(session.getAttribute("invitado") !=null) {
				        String username = (String) session.getAttribute("invitado");
						Usuario usesion = udao.findById(username);
						session.removeAttribute("invitado");
							Pedido peSession = pedao.obtenerCarrito(usesion.getUsername());
							Pedido pe = pedao.obtenerCarrito(usu.getUsername());
							if(pe==null) {
								peSession.setUsuario(usu);
								pedao.guardarPedido(peSession);						
								pedao.elminarPedido(peSession.getIdPedido());
							}else {
								List <AticulosPedido> lSession = ardao.findByPedido(peSession.getIdPedido());
								List <AticulosPedido> lLog = ardao.findByPedido(pe.getIdPedido());
								for (AticulosPedido aticulosPedido : lSession) {
									boolean found = false;
									for (AticulosPedido aticulosPedido2 : lLog) {
										if(aticulosPedido.getProducto() == aticulosPedido2.getProducto()) {
											aticulosPedido2.setCantidad(aticulosPedido.getCantidad() + aticulosPedido2.getCantidad());
											ardao.modArPe(aticulosPedido2);
											ardao.delArPe(aticulosPedido);
											found = true;
										}
										if(found==false) {
											aticulosPedido.setPedido(pe);
											ardao.modArPe(aticulosPedido);
										}
									}						
								}
								int contador = 0;
								for (AticulosPedido aticulosPedido : lLog) {
									contador = aticulosPedido.getCantidad() + contador;
								}
								session.setAttribute("contador", contador);
								Pedido aux = pedao.obtenerCarrito(usesion.getUsername());
								pedao.elminarPedido(aux.getIdPedido());
								udao.eliminarUsuario(username);
								
							}
					}
			    } else {
			    	if(session.getAttribute("invitado")==null) {
			    		usuarioRandom(usu, session);
			    	}else {
			    		// Si el nombre de usuario del invitado no est?? en la sesi??n, crea uno aleatorio.
			    		String nombreInvitado = (String) session.getAttribute("invitado");
			    		usu = udao.findById(nombreInvitado);		    
			    	}
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
			if(usu.getTarjetasBancarias() !=null) {
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
			if(usu.getTarjetasBancarias() !=null) {
			List<TarjetasBancaria> lTar = usu.getTarjetasBancarias();
			if(lTar.size()!=0) {
				model.addAttribute("tarjetas", lTar);
			}
			}
			model.addAttribute("userName", usu.getUsername());
		}
		return "carrito";
	}
	/**
	 * M??todo para borrar el pedido que tenemos en curso
	 * 
	 * @param model
	 * @param aut
	 * @param idPed
	 * @param idProd
	 * @param misession
	 * @return
	 */
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
		}else {
			model.addAttribute("mensaje", "No se pudo eliminar el/los articulo");
		
		}
		List<AticulosPedido> lAp = ardao.findByPedido(idPed);
		System.out.println(lAp.size());
		if(lAp.size()==0) {
			pedao.elminarPedido(idPed);
		}
		return "redirect:/pedidos/carrito";
	}
	
	/**
	 * M??todo para ver los detalles de algun pedido
	 * 
	 * @param idPedido
	 * @param model
	 * @param aut
	 * @return
	 */
	@GetMapping("/verPedido/{idPedido}")
	public String verDetallePedido(@PathVariable("idPedido") int idPedido, Model model, Authentication aut) {

		Pedido pedidoBuscado = pedao.buscarUno(idPedido);
		
		List<AticulosPedido> mostrar = new ArrayList<>();
		
		for(AticulosPedido art : pedidoBuscado.getAticulosPedidos()) {			
			
			Producto anadirP = new Producto();
			anadirP.setNombre(art.getProducto().getNombre());
			anadirP.setPrice(art.getProducto().getPrice());
			
			AticulosPedido anadir = new AticulosPedido();
			anadir.setProducto(anadirP);
			anadir.setCantidad(art.getCantidad());		
			
			mostrar.add(anadir);
			
			
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
		    model.addAttribute("error", "No se encontraron art??culos para el pedido.");
		}

		return "detallePedido";
	}
	
	/**
	 * M??todo par ir a ver los pedidos que se han realizado hoy
	 * 
	 * @param fecha
	 * @param model
	 * @return
	 */
	@GetMapping("/pedidosPorDia")
	public String pedidosPorDia(@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, Model model) {
	  
		LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = hoy.format(formatter);
        
        model.addAttribute("fecha", fechaFormateada);
        
        List<Pedido> pedidosHoy = pedao.porFechaRealizacion(fecha);

        model.addAttribute("pedidos", pedidosHoy);

		List<Producto> listaHoy = new ArrayList<>();

      
	    return "pedidosPorDia";
	}


	/**
	 * M??todo que nos muestra en una vista los pedidos realizados por un usuario
	 * 
	 * @param username
	 * @param model
	 * @param auth
	 * @param attr
	 * @return
	 */
	@GetMapping("/verTotalPedido/{username}")
	public String verElTotalPorUsuario(@PathVariable("username") String username, Model model, Authentication auth, RedirectAttributes attr) {
		
		int cantidadPedidos = 0;
		double valorDouble = 0;
		BigDecimal precioTotalPedidos = new BigDecimal(valorDouble);
		precioTotalPedidos = precioTotalPedidos.setScale(2, RoundingMode.HALF_UP);

		if(udao.findById(username) != null) {
			if(pedao.obtenerPorUsername(username).size() == 0) {
				attr.addFlashAttribute("mensaje", "El usuario " + username + " no ha realizado ningun pedido");
				return "redirect:/usuario/usuarios";
			}
			for(Pedido ped : pedao.obtenerPorUsername(username)) {
				precioTotalPedidos = precioTotalPedidos.add(ped.getPrecioTotal());
				cantidadPedidos++;
				System.out.println("la cantidad va siendo:" + ped.getPrecioTotal());
				System.out.println("El precio total va sumando = " + precioTotalPedidos);
				System.out.println(" la cantidad de pedidos es :" + cantidadPedidos );
			}
			
			model.addAttribute("cantidadPedidos", cantidadPedidos);
			model.addAttribute("precioTotalPedidos", precioTotalPedidos);
		} 
		
		return "totalPedidos";
	}
	
	/**
	 * Este m??todo lo usamos para crear un usuario Random, si entras como invitado
	 * 
	 * @param usu
	 * @param session
	 */
	public void usuarioRandom(Usuario usu, HttpSession session) {
		// Si el nombre de usuario del invitado no est?? en la sesi??n, crea uno aleatorio.
        String nombreInvitado = "invitado_" + UUID.randomUUID().toString();
        usu.setUsername(nombreInvitado);
        usu.setApellidos(null);
        usu.setContrasena(nombreInvitado);
        usu.setNombre(nombreInvitado);
        usu.setTarjetasBancarias(null);
        usu.setDirecciones(null);
        session.setAttribute("invitado", nombreInvitado);
        if(udao.registro(usu))
            System.out.println("Se crea el usuario: " + usu);
        else
            System.out.println("No se ha creado usuario: "+usu);
	}
	public void fusionPedidos(HttpSession session, Usuario usu) {
		
	}
}
