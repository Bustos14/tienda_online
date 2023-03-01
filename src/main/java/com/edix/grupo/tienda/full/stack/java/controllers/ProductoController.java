package com.edix.grupo.tienda.full.stack.java.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.grupo.tienda.full.stack.java.dao.ProductoDao;
import com.edix.grupo.tienda.full.stack.java.dao.TipoDao;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Producto;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Tipo;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoDao pdao;
	@Autowired
	private TipoDao tdao;
	@GetMapping("/destacados")
	public String destacados(Model model) {
		List<Producto> listproductos= pdao.lProductoPorEstado("Destacado");
		model.addAttribute("productos", listproductos);
		return "productos";
	}
	@GetMapping("/oferta")
	public String ofertas(Model model, HttpSession misesion) {
		System.out.println(misesion.getAttribute("usuario"));
		List<Producto> listproductos= pdao.lProductoPorEstado("Oferta");
		model.addAttribute("productos", listproductos);
		return "productos";
	}
	@GetMapping("/detallesProducto/{id}")
	public String detalles(@PathVariable("id") int idProd, Model model) {
		Producto p = pdao.detallesProdutos(idProd);
		model.addAttribute("producto", p);
		System.out.println(p.getNombre());
		return "detallesProducto";
	}
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable("id") int idProd, Model model) {
		if(pdao.eliminarProducto(idProd)==1) {
			model.addAttribute("mensaje", "Eliminado, bien hecho");
			return "redirect:/";
		}else {
			model.addAttribute("mensaje", "No eliminado, problemitas.");
			return "redirect:/";
		}
	}
	@GetMapping("/modificarProducto/{id}")
	public String modProducto(@PathVariable ("id") int idProd, Model model) {
		Producto p = pdao.detallesProdutos(idProd);
		model.addAttribute("productoEditable", p);
		List<Tipo> tList = tdao.todoTipo();
		model.addAttribute("tipos", tList);
		return "editarProducto";
	}
	@GetMapping("/tipo/{tipo}")
	public String buscarPorTipo(@PathVariable ("tipo") String tipo, Model model) {
		List<Producto> listproductos= pdao.findByTipo(tipo);
		model.addAttribute("productos", listproductos);
		return "productos";
	}
	@PostMapping("/modificarProducto")
	public String guardarModificado( @ModelAttribute Producto productoEditable, RedirectAttributes attr, @RequestParam("file") MultipartFile image) {
		//Obtenemos la tarjeta existente
				Producto p = pdao.detallesProdutos(productoEditable.getIdProducto());
				
				System.out.println("FOTO"+productoEditable.getImg());
				//Actualizamos los campos necesarios
				p.setNombre(productoEditable.getNombre());
				p.setDescripcion(productoEditable.getDescripcion());
				p.setPrice(productoEditable.getPrice());
				p.setStock(productoEditable.getStock());
				p.setEstado(productoEditable.getEstado());
				String rutaAbsoluta = "C:/Producto/recursos";
				try {
					byte[] bytesImg = image.getBytes();
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytesImg);
					p.setImg(image.getOriginalFilename());
				}catch(Exception e) {
					e.printStackTrace();
				}			
				if(pdao.modificarProducto(p)==1) {
					attr.addFlashAttribute("mensaje", "Producto modificado con exito");
					return "redirect:/";
				}
				attr.addFlashAttribute("mensaje", "Producto modificado sin exito");
				/*
				 * Pendiente ver donde redirigir, de momento va a alta
				 */
				return "redirect:/";
				
	}
	@GetMapping("/altaProducto")
	public String altaProducto(Model model) {
		List<Tipo> tList = tdao.todoTipo();
		model.addAttribute("tipos", tList);
		return "altaProducto";
	}
	@PostMapping("/altaProducto")
	public String altaProducto(@ModelAttribute Producto p, RedirectAttributes attr, @RequestParam("file") MultipartFile image) {
		if(!image.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/images"); 
			String rutaAbsoluta = "C:/Producto/recursos";
			try {
				byte[] bytesImg = image.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				p.setImg(image.getOriginalFilename());
				if(pdao.nuevoProducto(p)==1) {
					attr.addFlashAttribute("mensaje", "Producto añadido correctamente");
					return "redirect:/";
				}
			}catch(Exception e) {
				e.printStackTrace();
			}

			
		}
		
		return "redirect:/altaProducto";
		
	}
	@GetMapping("/search")
	public String busqueda(@RequestParam(name="nombre") String nombre, Model model) {
		List<Producto> listproductos=pdao.lBusquedaProduc(nombre);
		System.out.println(listproductos);
		model.addAttribute("productos", listproductos);
		return "productos";
	}
	
	public boolean imagenes(MultipartFile image) {
	
		return true;
	}
}
