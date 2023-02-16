package com.edix.grupo.tienda.full.stack.java.dao;

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

}
