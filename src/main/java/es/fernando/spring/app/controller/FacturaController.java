package es.fernando.spring.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.fernando.spring.app.entity.Cliente;
import es.fernando.spring.app.entity.Factura;
import es.fernando.spring.app.entity.ItemFactura;
import es.fernando.spring.app.entity.Producto;
import es.fernando.spring.app.service.ClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
			RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteId);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		Factura factura = new Factura();
		factura.setCliente(cliente);

		model.put("factura", factura);
		model.put("titulo", "Crear Factura");

		return "factura/form";
	}
	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return clienteService.findByNombre(term);
	}

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, 
			Model model) {
		Factura factura = clienteService.fetchFacturaByIdWithClienteWithItemFacturaWithProducto(id); //clienteService.findFacturaById(id);
		
		if(factura == null) {
			return "redirect:/listar";
		}
		
		model.addAttribute("factura", factura);
		
		return "factura/ver";
	}
	@PostMapping("/form")
	public String guardar(Factura factura, @RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			SessionStatus status) {
		
		for(int i = 0;  i < itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);
			
			ItemFactura linea = new ItemFactura();
			
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemsFactura(linea);
			}
		clienteService.saveFactura(factura);
		status.setComplete();
		return "redirect:/ver/" + factura.getCliente().getId();
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {

		Factura factura = clienteService.findFacturaById(id);
		if(factura != null) {
			clienteService.deleteFactura(id);
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		
		return "redirect:/listar";
	}

}
