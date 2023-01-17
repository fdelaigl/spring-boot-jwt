package es.fernando.spring.app.service;

import java.util.List;

import es.fernando.spring.app.entity.Cliente;
import es.fernando.spring.app.entity.Factura;
import es.fernando.spring.app.entity.Producto;

/**
 * The Interface ClienteService.
 */
public interface ClienteService {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Cliente> findAll();
	
	/**
	 * Save.
	 *
	 * @param cliente the cliente
	 */
	public void save(Cliente cliente);
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	public Cliente findOne(Long id);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);
	
	
	public List<Producto> findByNombre(String term);
	
	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
	
	public Factura findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id);
	
	public Cliente fetchByIdWithFacturas(Long id);
}
