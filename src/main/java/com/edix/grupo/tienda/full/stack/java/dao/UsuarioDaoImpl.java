package com.edix.grupo.tienda.full.stack.java.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Usuario;
import com.edix.grupo.tienda.full.stack.java.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@Autowired
	private UsuarioRepository urepo;
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

}
