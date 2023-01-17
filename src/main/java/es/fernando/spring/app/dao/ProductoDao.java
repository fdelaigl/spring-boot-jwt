package es.fernando.spring.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.fernando.spring.app.entity.Producto;
	
public interface ProductoDao extends CrudRepository<Producto, Long> {
	
	public List<Producto> findByNombreLikeIgnoreCase(String term);
}
