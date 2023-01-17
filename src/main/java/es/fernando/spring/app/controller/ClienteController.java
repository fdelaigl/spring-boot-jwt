package es.fernando.spring.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.fernando.spring.app.entity.Cliente;
import es.fernando.spring.app.service.ClienteService;

/**
 * The Class AppController.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	/** The dao. */
	@Autowired
	private ClienteService service;

	@GetMapping(value = "/listar")
	public List<Cliente> listarrest(Model model) {
		return service.findAll();
	}

}
