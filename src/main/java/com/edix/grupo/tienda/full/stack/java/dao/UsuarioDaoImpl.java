package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Direccione;
import com.edix.grupo.tienda.full.stack.java.entitybeans.TarjetasBancaria;
import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;
import com.edix.grupo.tienda.full.stack.java.repository.DireccionRepository;
import com.edix.grupo.tienda.full.stack.java.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@Autowired
	private UsuarioRepository urepo;
	
	@Autowired
	private DireccionDaoImpl ddao;
	
	@Override
	public Usuario findById(String username) {
		// TODO Auto-generated method stub
		return urepo.findById(username).orElse(null);
	}
	@Override
	public boolean registro(Usuario usuario) {
		if (findById(usuario.getUsername()) == null) {
			System.out.println("Usuario registrado");
				urepo.save(usuario);
				return true;
		}
		return false;
	}
	
	@Override
    public boolean modUsuario(Usuario usuario) {
        if (findById(usuario.getUsername()) != null) {
                urepo.save(usuario);
                return true;
        }
        return false;
    }
	@Override
	public List<Usuario> todos() {
		return urepo.findAll();
	}
	@Override
	public int eliminarUsuario(String username) {
		
		Usuario u = urepo.findById(username).orElse(null);
		
		if(u != null) {
			urepo.delete(u);
			return 1;
		}
		return 0;
	}
	@Override
	public int modificarUsuario(Usuario usuario) {
		int filas = 0;
		Usuario mod = null;
		try {
			mod = urepo.getOne(usuario.getUsername());
			mod = usuario;
			urepo.save(mod);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	@Override
	public List<Usuario> buscarPorLocalidad(String localidad) {
		return urepo.findByLocalidad(localidad);
	}
}




















