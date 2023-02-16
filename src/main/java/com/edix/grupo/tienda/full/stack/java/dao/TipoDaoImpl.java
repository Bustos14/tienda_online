package com.edix.grupo.tienda.full.stack.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edix.grupo.tienda.full.stack.java.entitybeans.Tipo;
import com.edix.grupo.tienda.full.stack.java.repository.TipoRepository;

@Repository
public class TipoDaoImpl implements TipoDao{

	@Autowired
	private TipoRepository trepo;
	
	@Override
	public List<Tipo> todoTipo() {
		return trepo.findAll();
	}

}
