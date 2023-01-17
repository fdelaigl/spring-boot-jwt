package es.fernando.spring.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.fernando.spring.app.dao.ClienteDao;
import es.fernando.spring.app.dao.FacturaDao;
import es.fernando.spring.app.dao.ProductoDao;
import es.fernando.spring.app.entity.Cliente;
import es.fernando.spring.app.entity.Factura;
import es.fernando.spring.app.entity.Producto;
import es.fernando.spring.app.service.ClienteService;

/**
 * The Class ClienteServiceImpl.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	/** The cliente dao. */
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private FacturaDao facturaDao;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	/**
	 * Save.
	 *
	 * @param cliente the cliente
	 */
	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id) {
		
		return facturaDao.fetchByIdWithClienteWithItemFacturaWithProducto(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente fetchByIdWithFacturas(Long id) {
	
		return clienteDao.fetchByIdWithFacturas(id);
	}
}
