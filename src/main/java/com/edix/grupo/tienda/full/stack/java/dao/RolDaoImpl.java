package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Role;
import com.edix.grupo.tienda.full.stack.java.repository.RolRepository;

@Repository
public class RolDaoImpl implements RolDao{

	@Autowired
	RolRepository rrepo;
	@Override
	public Role buscarRol(int idRol) {
		return rrepo.findById(idRol).orElse(null);
	}
	@Override
	public List<Role> todos() {
		return rrepo.findAll();
	}
	@Override
	public int nuevoRol(Role rol) {
		int filas = 0;
		try{
			rrepo.save(rol);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	@Override
	public int eliminarRol(int idRol) {

		Role r = rrepo.findById(idRol).orElse(null);
		
		if(r != null) {
			rrepo.delete(r);
			return 1;
		}
		return 0;
	}
	
	

}
