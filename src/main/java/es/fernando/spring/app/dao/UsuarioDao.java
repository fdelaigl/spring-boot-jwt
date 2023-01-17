package es.fernando.spring.app.dao;

import org.springframework.data.repository.CrudRepository;

import es.fernando.spring.app.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
