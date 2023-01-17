package es.fernando.spring.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.fernando.spring.app.entity.Cliente;

/**
 * The Interface ClienteDao.
 */
public interface ClienteDao extends CrudRepository<Cliente, Long>{
	//Repository CRUD
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWithFacturas(Long id);
}
